package com.oms.service;

import com.oms.dto.request.CreateInvoiceRequest;
import com.oms.dto.request.RecordPaymentRequest;
import com.oms.dto.response.InvoiceResponse;
import com.oms.dto.response.PaymentResponse;
import com.oms.exception.BadRequestException;
import com.oms.exception.DuplicateResourceException;
import com.oms.exception.ResourceNotFoundException;
import com.oms.model.*;
import com.oms.repository.InvoiceRepository;
import com.oms.repository.PaymentRepository;
import com.oms.repository.SalesOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final PaymentRepository paymentRepository;

    private final SalesOrderRepository salesOrderRepository;


    //generate invoice for a sales order
    @Transactional
    public InvoiceResponse generateInvoice(CreateInvoiceRequest request) {

        //validate sales order exists
        SalesOrder salesOrder = salesOrderRepository.findById(request.getSalesOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sales order not found with id: " + request.getSalesOrderId()));

        //only confirmed/processing orders can be invoiced
        if (salesOrder.getStatus() == OrderStatus.PENDING || salesOrder.getStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException("Cannot generate invoice for order with status: " + salesOrder.getStatus());
        }

        //prevent duplicate invoice
        if (invoiceRepository.existsBySalesOrder_SalesOrderId(request.getSalesOrderId())) {
            throw new DuplicateResourceException("Invoice already exists for order id: " + request.getSalesOrderId());
        }

        //create invoice
        Invoice invoice = new Invoice();
        invoice.setSalesOrder(salesOrder);
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setTotalAmount(salesOrder.getTotalAmount());
        invoice.setPaidAmount(0.0);
        invoice.setStatus(InvoiceStatus.PENDING);

        return mapToInvoiceResponse(invoiceRepository.save(invoice));
    }

    public List<InvoiceResponse> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
    }

    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        return mapToInvoiceResponse(invoice);
    }

    public InvoiceResponse updateInvoiceStatus(Long id, String status) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
        invoice.setStatus(InvoiceStatus.valueOf(status.toUpperCase()));
        return mapToInvoiceResponse(invoiceRepository.save(invoice));
    }

    // record a payment against an invoice
    @Transactional
    public PaymentResponse recordPayment(RecordPaymentRequest request) {

        //validate invoice
        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + request.getInvoiceId()));

        //cannot pay a cancelled invoice
        if (invoice.getStatus() == InvoiceStatus.CANCELLED) {
            throw new BadRequestException("Cannot record payment for a cancelled invoice");
        }

        //cannot overpay
        double outstanding = invoice.getTotalAmount() - invoice.getPaidAmount();
        if (request.getAmount() > outstanding) {
            throw new BadRequestException("Payment amount exceeds outstanding amount of ₹" + outstanding);
        }

        //record payment
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod().toUpperCase()));
        payment.setNotes(request.getNotes());
        paymentRepository.save(payment);

        //update paid amount on invoice
        double newPaidAmount = invoice.getPaidAmount() + request.getAmount();
        invoice.setPaidAmount(newPaidAmount);

        //auto-update invoice status based on payment
        if (newPaidAmount >= invoice.getTotalAmount()) {
            invoice.setStatus(InvoiceStatus.PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        }

        invoiceRepository.save(invoice);

        return mapToPaymentResponse(payment);
    }

    public List<PaymentResponse> getPaymentsByInvoice(Long invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new ResourceNotFoundException("Invoice not found with id: " + invoiceId);
        }
        return paymentRepository.findByInvoice_InvoiceId(invoiceId)
                .stream()
                .map(this::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    //mapping methods to convert Invoice to InvoiceResponse and
    private InvoiceResponse mapToInvoiceResponse(Invoice invoice) {
        return new InvoiceResponse(
                invoice.getInvoiceId(),
                invoice.getSalesOrder().getSalesOrderId(),
                invoice.getSalesOrder().getCustomer().getName(),
                invoice.getInvoiceDate(),
                invoice.getTotalAmount(),
                invoice.getPaidAmount(),
                invoice.getTotalAmount() - invoice.getPaidAmount(), // outstanding
                invoice.getStatus().name()
        );
    }

    //mapping method to convert Payment to PaymentResponse
    private PaymentResponse mapToPaymentResponse(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getInvoice().getInvoiceId(),
                payment.getPaymentDate(),
                payment.getAmount(),
                payment.getPaymentMethod().name(),
                payment.getNotes()
        );
    }
}
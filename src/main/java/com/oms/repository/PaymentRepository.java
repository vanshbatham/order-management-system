package com.oms.repository;

import com.oms.model.Payment;
import com.oms.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByInvoice_InvoiceId(Long invoiceId);

    // get all distinct payment methods
    @Query("SELECT DISTINCT p.paymentMethod FROM Payment p")
    List<PaymentMethod> findDistinctPaymentMethods();

    // count transactions per payment method
    long countByPaymentMethod(PaymentMethod paymentMethod);

    // sum amount per payment method
    @Query("SELECT COALESCE(SUM(p.amount), 0) " + "FROM Payment p WHERE p.paymentMethod = :method")
    double sumAmountByPaymentMethod(PaymentMethod method);
}
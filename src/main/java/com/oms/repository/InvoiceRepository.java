package com.oms.repository;

import com.oms.model.Invoice;
import com.oms.model.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // check if invoice already exists for this sales order
    boolean existsBySalesOrder_SalesOrderId(Long salesOrderId);

    // get invoice by sales order id
    Optional<Invoice> findBySalesOrder_SalesOrderId(Long salesOrderId);

    long countByStatus(InvoiceStatus status);

    // total invoiced amount
    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i")
    double sumTotalInvoiced();

    // total collected amount
    @Query("SELECT COALESCE(SUM(i.paidAmount), 0) FROM Invoice i")
    double sumTotalCollected();
}
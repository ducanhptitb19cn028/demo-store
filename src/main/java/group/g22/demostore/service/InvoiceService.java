package group.g22.demostore.service;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface InvoiceService {
    void save(Invoice invoice);

    List<Invoice> findAll();

    Invoice getInvoiceById(long id);

    List<Invoice> findByDate(LocalDate date);

    List<Invoice> findByDateRange(LocalDate start, LocalDate end);

}

package group.g22.demostore.service;

import group.g22.demostore.model.Invoice;

import java.util.List;

public interface InvoiceService {
    void save(Invoice invoice);

    List<Invoice> findAll();

    Invoice getInvoiceById(long id);
}

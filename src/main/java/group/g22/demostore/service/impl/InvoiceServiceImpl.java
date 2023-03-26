package group.g22.demostore.service.impl;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.repository.InvoiceRepository;
import group.g22.demostore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}

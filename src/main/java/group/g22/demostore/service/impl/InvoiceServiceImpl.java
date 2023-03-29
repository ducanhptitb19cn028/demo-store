package group.g22.demostore.service.impl;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.InvoiceRepository;
import group.g22.demostore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceById(long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        Invoice invoice;
        if (optionalInvoice.isPresent()) {
            invoice = optionalInvoice.get();
        } else {
            throw new RuntimeException("Employee not found for id:" + id);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findByDate(LocalDate date) {
        return invoiceRepository.findAllByCreateDateEquals(date);
    }

    @Override
    public List<Invoice> findByDateRange(LocalDate start, LocalDate end) {
        return invoiceRepository.findAllByCreateDateBetween(start, end);
    }

}

package group.g22.demostore.service.impl;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.repository.InvoiceRepository;
import group.g22.demostore.repository.ProductRepository;
import group.g22.demostore.service.InvoiceService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public Page<Invoice> findByDate(int pageNo, int pageSize, String sortField, String sortDir, LocalDate date) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.invoiceRepository.findAllByCreateDateEquals(date, pageable);
    }

    @Override
    public Page<Invoice> findByDateRange(int pageNo, int pageSize, String sortField, String sortDir, LocalDate start, LocalDate end) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.invoiceRepository.findAllByCreateDateBetween(start, end, pageable);
    }

    @Override
    public Page<Invoice> findPaginated(int pageNo, int pageSize, String sortField, @NotNull String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.invoiceRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findPaginatedPt(int pageNo, int pageSize, String sortField, @NotNull String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }
}

package group.g22.demostore.service;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    void save(Invoice invoice);

    List<Invoice> findAll();

    Invoice getInvoiceById(long id);

    Page<Invoice> findByDate(int pageNo, int pageSize, String sortField, String sortDir, LocalDate date);

    Page<Invoice> findByDateRange(int pageNo, int pageSize, String sortField, String sortDir, LocalDate start, LocalDate end);

    Page<Invoice> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

    Page<Product> findPaginatedPt(int pageNo, int pageSize, String sortField, String sortDir);
}

package group.g22.demostore.repository;

import group.g22.demostore.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findAllByCreateDateEquals(LocalDate date, Pageable pageable);

    Page<Invoice> findAllByCreateDateBetween(LocalDate start, LocalDate end, Pageable pageable);

}

package group.g22.demostore.repository;

import group.g22.demostore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByCreateDateEquals(LocalDate date);

    List<Invoice> findAllByCreateDateBetween(LocalDate start, LocalDate end);
}

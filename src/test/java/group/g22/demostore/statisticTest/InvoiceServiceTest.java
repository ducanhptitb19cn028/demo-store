package group.g22.demostore.statisticTest;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.repository.InvoiceRepository;
import group.g22.demostore.repository.ProductRepository;
import group.g22.demostore.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InvoiceServiceTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    void testSaveInvoice() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setCreateDate(LocalDate.now());
        invoice.setTotalAmount(1000);

//        doNothing().when(invoiceRepository).save(invoice);
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        invoiceService.save(invoice);

        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void testFindAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice1 = new Invoice();
        invoice1.setId(1L);
        invoice1.setCreateDate(LocalDate.now());
        invoice1.setTotalAmount(1000);

        Invoice invoice2 = new Invoice();
        invoice2.setId(2L);
        invoice2.setCreateDate(LocalDate.now());
        invoice2.setTotalAmount(2000);

        invoices.add(invoice1);
        invoices.add(invoice2);

        when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoice> result = invoiceService.findAll();

        assertEquals(2, result.size());
        verify(invoiceRepository, times(1)).findAll();
    }


    @Test
    void testGetInvoiceById() {
        long id = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setCreateDate(LocalDate.now());
        invoice.setTotalAmount(1000);

        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.getInvoiceById(id);

        assertEquals(invoice, result);
        verify(invoiceRepository, times(1)).findById(id);
    }

    @Test
    void testFindByDate() {
        Page<Invoice> page = new PageImpl<>(Collections.emptyList());
        LocalDate date = LocalDate.now();

        when(invoiceRepository.findAllByCreateDateEquals(eq(date), any(Pageable.class))).thenReturn(page);

        Page<Invoice> result = invoiceService.findByDate(1, 10, "createDate", "asc", date);

        assertEquals(page, result);
        verify(invoiceRepository, times(1)).findAllByCreateDateEquals(eq(date), any(Pageable.class));
    }

    @Test
    void testFindByDateRange() {
        Page<Invoice> page = new PageImpl<>(Collections.emptyList());
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();

        when(invoiceRepository.findAllByCreateDateBetween(eq(start), eq(end), any(Pageable.class))).thenReturn(page);

        Page<Invoice> result = invoiceService.findByDateRange(1, 10, "createDate", "asc", start, end);

        assertEquals(page, result);
        verify(invoiceRepository, times(1)).findAllByCreateDateBetween(eq(start), eq(end), any(Pageable.class));
    }
}

package group.g22.demostore.statisticTest;

import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class HandlerInvoiceTest {
    @Mock
    ProductService productService;

    @InjectMocks
    HandlerInvoice handlerInvoice;

    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateInvoice() {
        Map<String, String> params = new HashMap<>();
        params.put("pId.1", "1");
        params.put("pId.2", "4");

        Product p1 = new Product();
        p1.setProductId(1L);
        p1.setSellingPrice(2000.0);

        Product p2 = new Product();
        p2.setProductId(2L);
        p2.setSellingPrice(300.0);

        when(productService.getProductById(1L)).thenReturn(p1);
        when(productService.getProductById(2L)).thenReturn(p2);

        Invoice invoice = handlerInvoice.createInvoice(params);

        assertEquals(LocalDate.now(), invoice.getCreateDate());
        assertEquals(1, invoice.getProductQuantities().get(p1));
        assertEquals(4, invoice.getProductQuantities().get(p2));
        assertEquals(1 * 2000.0 + 4 * 300.0, invoice.getTotalAmount());
    }

}

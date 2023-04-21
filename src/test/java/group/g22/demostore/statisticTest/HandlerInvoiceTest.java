package group.g22.demostore.statisticTest;

import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class HandlerInvoiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    HandlerInvoice handlerInvoice;

    @Test
    void contextLoads() {
    }
    @Test
    @Transactional
    @Rollback
    public void testCreateInvoice() {
        Map<String, String> params = new HashMap<>();
        params.put("pId.1", "1");
        params.put("pId.2", "4");
        Product p1Test = productService.getProductById(1);
        Product p2Test = productService.getProductById(2);

        Invoice invoice = handlerInvoice.createInvoice(params);

        // kiem tra ngay tao
        assertEquals(LocalDate.now(), invoice.getCreateDate());
        // kiem tra so luong
        assertEquals(1, invoice.getProductQuantities().get(p1Test));
        assertEquals(4, invoice.getProductQuantities().get(p2Test));
        //kiem tra tong tien
        assertEquals(1 * p1Test.getSellingPrice() + 4 * p2Test.getSellingPrice(), invoice.getTotalAmount());
    }

}

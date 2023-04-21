package group.g22.demostore.statisticTest;

import group.g22.demostore.controller.InvoiceController;
import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class InvoiceControllerTest {
    @Autowired
    InvoiceController invoiceController;

    @Autowired
    HandlerInvoice handlerInvoice;

    private Model model = new Model() {
        @Override
        public Model addAttribute(String attributeName, Object attributeValue) {
            return null;
        }

        @Override
        public Model addAttribute(Object attributeValue) {
            return null;
        }

        @Override
        public Model addAllAttributes(Collection<?> attributeValues) {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public Model mergeAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public boolean containsAttribute(String attributeName) {
            return false;
        }

        @Override
        public Object getAttribute(String attributeName) {
            return null;
        }

        @Override
        public Map<String, Object> asMap() {
            return null;
        }
    };

    Map<String, String> params() {
        Map<String, String> params = new HashMap<>();
        params.put("pId.1", "1");
        params.put("pId.2", "4");
        return params;
    }

    @Test
    @Transactional
    @Rollback
    void getInvoiceTest() {
        Invoice invoice = handlerInvoice.createInvoice(params());
        Assert.assertEquals("invoice_view/detail_invoice", invoiceController.getInvoice(invoice.getId(), model));
    }
}

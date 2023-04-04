package group.g22.demostore.handler;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.InvoiceService;
import group.g22.demostore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandlerInvoice {
    @Autowired
    ProductService productService;

    @Autowired
    InvoiceService invoiceService;

    public Invoice createInvoice(Map<String, String> params) {
        Invoice invoice = new Invoice();
        List<Product> products = new ArrayList<>();
        Map<Product, Integer> productQuantities = new HashMap<>();
        double totalAmount = 0;
        try {
            for (String pId : params.keySet()) {
                if (pId.contains("pId.")) {
                    Long productId = Long.parseLong(pId.substring(4));
                    Integer quantity = Integer.parseInt(params.get(pId));
                    if (quantity > 0) {
                        Product product = productService.getProductById(productId);
                        double amount = product.getSellingPrice() * quantity;
                        totalAmount += amount;
                        product.setTotalRevenue(product.getTotalRevenue() + amount);
                        product.setSellNumber(product.getSellNumber() + quantity);
                        productService.update(product);
                        productQuantities.put(product, quantity);
                    }
                }
            }
        } catch (Exception e) {

        }

        invoice.setCreateDate(LocalDate.now());
        invoice.setProductQuantities(productQuantities);
        invoice.setTotalAmount(totalAmount);

        return invoice;
    }
}

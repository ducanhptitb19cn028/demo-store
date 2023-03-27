package group.g22.demostore.handler;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandlerInvoice {
    @Autowired
    TypeProductService typeProductService;

    // TODO thêm validate, page, sort
    public Invoice createInvoice(Map<String, String> params) {
        Invoice invoice = new Invoice();
        List<TypeProduct> products = new ArrayList<>();
        Map<TypeProduct, Integer> productQuantities = new HashMap<>();
        try {
            for (String productId : params.keySet()) {
                Integer quantity = Integer.parseInt(params.get(productId));
                if (quantity > 0) {
                    TypeProduct typeProduct = typeProductService.getTypeProductById(Long.parseLong(productId));
                    products.add(typeProduct);
                    productQuantities.put(typeProduct, quantity);
                }
            }
        } catch (Exception e) {

        }

        double totalAmount = 0;
        for (TypeProduct product : productQuantities.keySet()) {
            totalAmount += product.getSellingPrice() * productQuantities.get(product);
        }
        invoice.setCreateDate(LocalDate.now());
        invoice.setProducts(products);
        invoice.setProductQuantities(productQuantities);
        invoice.setTotalAmount(totalAmount);

        return invoice;
    }
}

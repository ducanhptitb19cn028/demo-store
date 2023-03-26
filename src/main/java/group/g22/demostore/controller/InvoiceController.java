package group.g22.demostore.controller;

import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.InvoiceService;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    TypeProductService typeProductService;

    @GetMapping("/add-invoice")
    public String createInvoice(Model model) {
        List<TypeProduct> typeProductList = typeProductService.findAll();
        model.addAttribute("listTypeProduct", typeProductList);
        return "invoice_view/new_invoice";
    }

    @PostMapping("/save")
    public String saveInvoice(Model model, @RequestParam Map<String, String> params) {
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
            invoice.setCreateDate(LocalDateTime.now());
        invoice.setProducts(products);
        invoice.setProductQuantities(productQuantities);
        invoice.setTotalAmount(totalAmount);
        invoiceService.save(invoice);
        model.addAttribute("invoice", invoice);
        return "invoice_view/detail_invoice";
    }
}

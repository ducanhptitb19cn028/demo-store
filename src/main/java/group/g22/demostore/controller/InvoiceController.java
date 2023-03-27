package group.g22.demostore.controller;

import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.InvoiceService;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    TypeProductService typeProductService;

    @Autowired
    HandlerInvoice handlerInvoice;

    @GetMapping("/add-invoice")
    public String createInvoice(Model model) {
        List<TypeProduct> typeProductList = typeProductService.findAll();
        model.addAttribute("listTypeProduct", typeProductList);
        return "invoice_view/new_invoice";
    }

    @PostMapping("/save")
    public String saveInvoice(Model model, @RequestParam Map<String, String> params) {
        Invoice invoice = handlerInvoice.createInvoice(params);
        invoiceService.save(invoice);
        model.addAttribute("invoice", invoice);
        return "invoice_view/create_success";
    }

    @GetMapping("/get-invoices")
    public String getInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoice_view/invoices";
    }

    @GetMapping("detail-invoice/{id}")
    public String getInvoice(@PathVariable(value = "id") long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "invoice_view/detail_invoice";
    }
}

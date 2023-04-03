package group.g22.demostore.controller;

import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.InvoiceService;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    private String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;
        Page<Invoice> page = invoiceService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Invoice> invoices = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("invoices", invoices);
        return "invoice_view/invoices";
    }

    @GetMapping("detail-invoice/{id}")
    public String getInvoice(@PathVariable(value = "id") long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "invoice_view/detail_invoice";
    }
}

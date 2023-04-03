package group.g22.demostore.handler;

import group.g22.demostore.constant.Constant;
import group.g22.demostore.helper.ConversionHelper;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.InvoiceService;
import group.g22.demostore.service.ProductService;
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
    ProductService productService;

    @Autowired
    InvoiceService invoiceService;

    // TODO thêm validate, page, sort
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

//        for (TypeProduct product : productQuantities.keySet()) {
//            totalAmount += product.getSellingPrice() * productQuantities.get(product);
//        }
        invoice.setCreateDate(LocalDate.now());
        invoice.setProductQuantities(productQuantities);
        invoice.setTotalAmount(totalAmount);

        return invoice;
    }

    public Map<String, Object> getInvoicesByDate(Map<String, String> params) {
        String option = params.get(Constant.OPTION);
        List<Invoice> invoices = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        if (option.equals(Constant.DATE)) {
            String date = params.get(Constant.SPECIFIC_DATE);
            LocalDate localDate = ConversionHelper.stringToLocalDate(date);
            invoices = invoiceService.findByDate(localDate);
            result.put(Constant.DATE, date);
        } else if (option.equals(Constant.DATE_RANGE)) {
            String startStr = params.get(Constant.FROM_DATE);
            String endStr = params.get(Constant.TO_DATE);
            LocalDate start = ConversionHelper.stringToLocalDate(startStr);
            LocalDate end = ConversionHelper.stringToLocalDate(endStr);
            invoices = invoiceService.findByDateRange(start, end);
            result.put(Constant.START, startStr);
            result.put(Constant.END, endStr);
        }
        double totalAmountAll = 0;
        for (Invoice invoice : invoices) {
            totalAmountAll += invoice.getTotalAmount();
        }
        result.put("totalAmountAll", totalAmountAll);
        result.put("invoices", invoices);
        return result;
    }
}

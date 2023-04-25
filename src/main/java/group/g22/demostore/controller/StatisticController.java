package group.g22.demostore.controller;

import group.g22.demostore.constant.Constant;
import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.helper.ConversionHelper;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.InvoiceService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

@RequestMapping("statistic")
@Controller
@PreAuthorize("hasRole('ADMIN')")
public class StatisticController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("")
    public String StatisticMain() {
        return "statistic";
    }

    @GetMapping("/search-money-statistic")
    public String moneyStatisticForm() {
        return ("statistic_view/search_money_statistic");
    }

    @GetMapping("/get-money-statistic")
    public String getMoneyStatistic(@RequestParam Map<String, String> params, Model model) {
        return findPaginated(1, "id", "asc", "", "", "", "", model, params);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @RequestParam(value = "option") String option,
                                @RequestParam(value = "specificDate") String specificDate,
                                @RequestParam(value = "fromDate") String fromDate,
                                @RequestParam(value = "toDate") String toDate,
                                Model model,
                                Map<String, String> params) {
        // trả lại dữ liệu
        if (params.size() > 0) {
            option = params.get(Constant.OPTION);
            specificDate = params.get(Constant.SPECIFIC_DATE);
            fromDate = params.get(Constant.FROM_DATE);
            toDate = params.get(Constant.TO_DATE);
            returnData(model, params.get(Constant.OPTION), params.get(Constant.FROM_DATE), params.get(Constant.TO_DATE), params.get(Constant.SPECIFIC_DATE));
        } else {
            returnData(model, option, fromDate, toDate, specificDate);
        }

        Page<Invoice> page = Page.empty();
        int pageSize = 5;
        Map<String, Object> result = new HashMap<>();
        if (option.equals(Constant.DATE)) {
            LocalDate localDate = ConversionHelper.stringToLocalDate(specificDate);
            page = invoiceService.findByDate(pageNo, pageSize, sortField, sortDir, localDate);
            result.put(Constant.DATE, specificDate);
        } else if (option.equals(Constant.DATE_RANGE)) {
            LocalDate start = ConversionHelper.stringToLocalDate(fromDate);
            LocalDate end = ConversionHelper.stringToLocalDate(toDate);
            page = invoiceService.findByDateRange(pageNo, pageSize, sortField, sortDir, start, end);
            result.put(Constant.START, fromDate);
            result.put(Constant.END, toDate);
        }

        List<Invoice> invoices = page.getContent();
        double totalAmountAll = 0;
        for (Invoice invoice : invoices) {
            totalAmountAll += invoice.getTotalAmount();
        }
        model.addAttribute("result", result);
        model.addAttribute("totalAmountAll", totalAmountAll);
        model.addAttribute("invoices", invoices);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "statistic_view/money_statistic";
    }

    public void returnData(Model model, String option, String fromDate, String toDate, String specificDate) {
        if (option.equals(Constant.DATE)) {
            model.addAttribute(Constant.OPTION, option);
            model.addAttribute(Constant.SPECIFIC_DATE, specificDate);
        } else if (option.equals(Constant.DATE_RANGE)) {
            model.addAttribute(Constant.OPTION, option);
            model.addAttribute(Constant.FROM_DATE, fromDate);
            model.addAttribute(Constant.TO_DATE, toDate);
        }
    }

    @GetMapping("/get-product-statistic")
    public String getProductStatistic(Model model) {
        return findPaginatedPt(1, "sellNumber", "desc", model);
    }

    @GetMapping("/pt/page/{pageNo}")
    private String findPaginatedPt(@PathVariable(value = "pageNo") int pageNo,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir,
                                   Model model) {
        int pageSize = 5;
        Page<Product> page = invoiceService.findPaginatedPt(pageNo, pageSize, sortField, sortDir);
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);
        return "statistic_view/product_statistic";
    }
}

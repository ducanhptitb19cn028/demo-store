package group.g22.demostore.controller;

import group.g22.demostore.constant.Constant;
import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.Invoice;
import group.g22.demostore.model.Product;
import group.g22.demostore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("statistic")
@Controller
public class StatisticController {
    @Autowired
    private HandlerInvoice handlerInvoice;

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
    private String findPaginated(@PathVariable(value = "pageNo") int pageNo,
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
            returnData(model, params.get(Constant.OPTION), params.get(Constant.FROM_DATE), params.get(Constant.TO_DATE), params.get(Constant.SPECIFIC_DATE));
        } else {
            params.put(Constant.OPTION, option);
            params.put(Constant.FROM_DATE, fromDate);
            params.put(Constant.TO_DATE, toDate);
            params.put(Constant.SPECIFIC_DATE, specificDate);
            returnData(model, option, fromDate, toDate, specificDate);
        }

        Map<String, Object> results = handlerInvoice.getInvoicesByDate(params);
        model.addAttribute("result", results);

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
//        Map<TypeProduct, Integer> result = handlerInvoice.getQuantityProduct();
//        model.addAttribute("result", result);
//        return "statistic_view/product_statistic";
        return findPaginatedPt(1, "productId", "asc", model);
    }

    @GetMapping("/pt/page/{pageNo}")
    private String findPaginatedPt(@PathVariable(value = "pageNo") int pageNo,
                                 @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir,
                                 Model model) {

//        Map<TypeProduct, Integer> result = handlerInvoice.getQuantityProduct();
//        model.addAttribute("result", result);
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

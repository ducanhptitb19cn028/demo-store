package group.g22.demostore.controller;

import group.g22.demostore.handler.HandlerInvoice;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        Map<String, Object> results = handlerInvoice.getInvoicesByDate(params);
        model.addAttribute("result", results);
        return "statistic_view/money_statistic";
    }

    @GetMapping("/get-product-statistic")
    public String getProductStatistic(Model model) {
        Map<TypeProduct, Integer> result = handlerInvoice.getQuantityProduct();
        model.addAttribute("result", result);
        return "statistic_view/product_statistic";
    }

}

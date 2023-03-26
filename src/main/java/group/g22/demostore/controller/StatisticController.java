package group.g22.demostore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {
    @GetMapping("/statistic")
    public String StatisticMain() {
        return "statistic";
    }
}

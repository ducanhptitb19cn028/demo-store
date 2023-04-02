package group.g22.demostore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String viewLoginPage() {
        return "login";
    }
    @GetMapping("/home")
    public String Home(){
        return "home";
    }
}
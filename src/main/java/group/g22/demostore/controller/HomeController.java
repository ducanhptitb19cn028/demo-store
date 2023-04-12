package group.g22.demostore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }
    @GetMapping("/")
    public String defaultLoginPage(){
        return  "redirect:/login";
    }
    @GetMapping("/home")
    public String Home(){
        return "home";
    }
}
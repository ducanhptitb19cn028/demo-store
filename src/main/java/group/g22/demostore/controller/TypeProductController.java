package group.g22.demostore.controller;

import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeProductController {
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping("/type-product")
    public String search(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000000") Integer size,
            Model model) {

        Page<TypeProduct> typeProductPage = typeProductService.search(PageRequest.of(page, size));
        List<TypeProduct> typeProductList = typeProductPage.getContent();
        model.addAttribute("listTypeProduct", typeProductList);
        return "type-product";
    }
}

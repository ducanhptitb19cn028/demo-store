package group.g22.demostore.controller;

import group.g22.demostore.enums.TypeProductStatusOption;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("type-product")
@PreAuthorize("hasRole('ADMIN')")
public class TypeProductController {
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping("")
    public String search(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "1000000") Integer size,
            Model model) {

        Page<TypeProduct> typeProductPage = typeProductService.search(PageRequest.of(page, size));
        List<TypeProduct> typeProductList = typeProductPage.getContent();
        model.addAttribute("listTypeProduct", typeProductList);
        return "type-product";
    }

    @GetMapping("/new")
    public String getNewTypeProductForm(Model model) {
        TypeProduct typeProduct = new TypeProduct();
        model.addAttribute("typeProduct", typeProduct);
        return "type_product_view/new";
    }

    @PostMapping("/save")
    public String saveTypeProduct(@ModelAttribute("typeProduct") TypeProduct typeProduct) {
        Boolean success = typeProduct.getTypeProductId() == null
                ? typeProductService.save(typeProduct)
                : typeProductService.update(typeProduct);
        return success ? "redirect:/type-product" : "redirect:/error";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        typeProductService.delete(id);
        return "redirect:/type-product";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        TypeProduct typeProduct = typeProductService.detail(id);
        model.addAttribute("typeProduct", typeProduct);
        model.addAttribute("typeProductStatus", TypeProductStatusOption.valueOf(typeProduct.getTypeProductStatus()).getName());
        return "/type_product_view/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        TypeProduct typeProduct = typeProductService.detail(id);
        model.addAttribute("typeProduct", typeProduct);
        return "/type_product_view/update";
    }
}

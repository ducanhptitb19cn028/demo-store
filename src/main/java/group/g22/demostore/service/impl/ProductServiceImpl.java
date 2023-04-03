package group.g22.demostore.service.impl;

import group.g22.demostore.enums.TypeProductStatusOption;
import group.g22.demostore.model.Product;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.ProductRepository;
import group.g22.demostore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//import java.util.Optional;
//
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new RuntimeException("Employee not found for id:" + id);
        }
        return product;
    }

    @Override
    public String update(Product product) {
        Product productUpdate = productRepository.findById(product.getProductId()).orElseThrow(() -> new RuntimeException());
//        productUpdate.setProductCode(product.getProductCode());
//        productUpdate.setProductName(product.getProductName());
        productRepository.save(productUpdate);
        return "Update success";
    }
}

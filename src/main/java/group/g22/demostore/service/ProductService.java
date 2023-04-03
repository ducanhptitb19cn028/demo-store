package group.g22.demostore.service;

import group.g22.demostore.model.Product;
import group.g22.demostore.model.TypeProduct;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product getProductById(long id);

    String update(Product typeProduct);
}

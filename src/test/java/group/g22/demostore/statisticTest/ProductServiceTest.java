package group.g22.demostore.statisticTest;

import group.g22.demostore.model.Product;
import group.g22.demostore.repository.ProductRepository;
import group.g22.demostore.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> result = productService.findAll();
        Assertions.assertEquals(productList.size(), result.size());
    }

    @Test
    public void testGetProductById() {
        long id = 1L;
        Product product = new Product();
        product.setProductId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(id);
        Assertions.assertEquals(id, result.getProductId());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("new name");
        when(productRepository.save(product)).thenReturn(product);
        String result = productService.update(product);
        Assertions.assertEquals("new name", product.getProductName());
        Assertions.assertEquals("Update success", result);
    }
}

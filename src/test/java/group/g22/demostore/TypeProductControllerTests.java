package group.g22.demostore;

import group.g22.demostore.controller.TypeProductController;
import group.g22.demostore.model.TypeProduct;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@SpringBootTest
@Log4j2
class TypeProductControllerTests {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private TypeProductController typeProductController;

    private Model model = new Model() {
        @Override
        public Model addAttribute(String attributeName, Object attributeValue) {
            return null;
        }

        @Override
        public Model addAttribute(Object attributeValue) {
            return null;
        }

        @Override
        public Model addAllAttributes(Collection<?> attributeValues) {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public Model mergeAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public boolean containsAttribute(String attributeName) {
            return false;
        }

        @Override
        public Object getAttribute(String attributeName) {
            return null;
        }

        @Override
        public Map<String, Object> asMap() {
            return null;
        }
    };

    @Test
    void contextLoads() {
    }

    @Test
    void testTypeProductController() {
        Assertions.assertNotNull(context.getBean(TypeProductController.class));
    }

    @BeforeClass
    void setUp() {
       log.error("Chạy qua set up nhé");
    }

    @Test
    void testCountSearch() {
        Assert.assertEquals("type-product", typeProductController.search(0, 10, model));
    }

    @Test
    void testNew() {
        Assert.assertEquals("type_product_view/new", typeProductController.getNewTypeProductForm(model));
    }

    @Test
    @Transactional
    @Rollback
    void testSave() {
        TypeProduct typeProduct = new TypeProduct();
        typeProduct.setTypeProductCode("LHH1");
        typeProduct.setTypeProductName("Loại hàng hóa 1");
        typeProduct.setTypeProductStatus(1);
        Assert.assertEquals("redirect:/type-product", typeProductController.saveTypeProduct(typeProduct));
    }

}

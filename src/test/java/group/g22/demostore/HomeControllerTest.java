package group.g22.demostore;

import group.g22.demostore.controller.HomeController;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class HomeControllerTest {
    @Autowired
    HomeController homeController;
    @Test
    public void testLoginPage() {
        Assertions.assertEquals("login", homeController.viewLoginPage());
    }
    @Test
    public void testLoginDefault() {

        Assertions.assertEquals("redirect:/login", homeController.defaultLoginPage());

    }
    @Test
    public void testHome() {
        Assertions.assertEquals("home", homeController.Home());
    }
}

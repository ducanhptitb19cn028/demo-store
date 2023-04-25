package group.g22.demostore.selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) {
        // Khởi tạo WebDriver
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe\"");
        WebDriver driver = new ChromeDriver();

        // Mở trang web của bạn và kiểm tra tiêu đề trang
        driver.get("http://localhost:8080");
        String pageTitle = driver.getTitle();
        if (pageTitle.equals("Your Page Title")) {
            System.out.println("Page title is correct");
        } else {
            System.out.println("Page title is incorrect");
        }

        // Đóng trình duyệt
        driver.quit();
    }
}
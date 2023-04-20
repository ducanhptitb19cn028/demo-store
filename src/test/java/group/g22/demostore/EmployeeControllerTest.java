package group.g22.demostore;

import group.g22.demostore.controller.EmployeeController;
import group.g22.demostore.controller.TypeProductController;
import group.g22.demostore.model.Employee;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.EmployeeRepository;
import group.g22.demostore.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@Log4j2
public class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeService employeeService;
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

//    @Test
//    void testCountSearch() {
//        Assert.assertEquals("type-product", EmployeeController.search(0, 10, model));
//    }

    @Test
    void testNew() {
        Assert.assertEquals("employee_view/new_employees", employeeController.newEmployeesForm(model));
    }

    @Test
    @Transactional
    @Rollback
    void testSave() {
        Employee employee = new Employee();
        employee.setAddress("Hanoi");
        employee.setId(100L);
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setImages("cccd.jpg");
        employee.setEmail("pa@gmail.com");
        employee.setFirstName("Anh");
        employee.setGenderOption("Male");
        employee.setPeopleOption("Kinh");
        employee.setIdentityno("001203424532");

        Assert.assertEquals("redirect:/employee", employeeController.saveEmployee(employee));
    }
    @Test
    @Transactional
    @Rollback
    void testUpdate() {
        Employee employee = new Employee();
        employee.setAddress("Hanoi");
        employee.setId(100L);
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setImages("cccd.jpg");
        employee.setEmail("pa@gmail.com");
        employee.setFirstName("Anh");
        employee.setGenderOption("Male");
        employee.setPeopleOption("Kinh");
        employee.setIdentityno("001203424532");
        employeeController.saveEmployee(employee);
        Assert.assertEquals("employee_view/update_employees", employeeController.showFormUpdate(employee.getId(),model));
    }
}

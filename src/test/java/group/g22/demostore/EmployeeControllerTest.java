package group.g22.demostore;

import group.g22.demostore.controller.EmployeeController;
import group.g22.demostore.controller.TypeProductController;
import group.g22.demostore.model.Employee;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.EmployeeRepository;
import group.g22.demostore.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@Log4j2
public class EmployeeControllerTest {
    @Autowired
    @InjectMocks
    private EmployeeController employeeController;
    @Autowired
    private ApplicationContext context;
    @Mock
    private Page<Employee> employeePage;
    @Autowired
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeService employeeService;
    private MockMvc mockMvc;
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
        employee.setId(null);
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setImages("cccd.jpg");
        employee.setEmail("pa@gmail.com");
        employee.setFirstName("Anh");
        employee.setLastName("Nguyen Ngoc Duc");
        employee.setGenderOption("Male");
        employee.setPeopleOption("Kinh");
        employee.setIdentityno("001203424532");
        employeeRepository.save(employee);
        Assert.assertEquals("redirect:/employee", employeeController.saveEmployee(employee));
    }
    @Test
    @Transactional
    @Rollback
    void testUpdate() {
        Employee employee = employeeRepository.getById(Long.valueOf(2));
        employee.setAddress("Hanoi");
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setImages("cccd.jpg");
        employee.setEmail("pa@gmail.com");
        employee.setFirstName("Anh");
        employee.setGenderOption("Male");
        employee.setPeopleOption("Kinh");
        employee.setIdentityno("001201021885");
        employeeRepository.save(employee);
        Assert.assertEquals("redirect:/employee", employeeController.saveEmployee(employee));

    }
    @Test
    @Transactional
    @Rollback
    void testDelete() {
        Employee employee = employeeRepository.getById(Long.valueOf(2));
        employee.setAddress("Hanoi");
        employee.setDob(Date.valueOf(LocalDate.now()));
        employee.setImages("cccd.jpg");
        employee.setEmail("pa@gmail.com");
        employee.setFirstName("Anh");
        employee.setGenderOption("Male");
        employee.setPeopleOption("Kinh");
        employee.setIdentityno("001201021885");
        employeeRepository.delete(employee);
        Assert.assertEquals("redirect:/employee", employeeController.deleteEmployee(employee.getId()));

    }
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
}

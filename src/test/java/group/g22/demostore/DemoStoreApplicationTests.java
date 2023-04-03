package group.g22.demostore;

import group.g22.demostore.controller.EmployeeController;
import group.g22.demostore.controller.HomeController;
import group.g22.demostore.controller.InvoiceController;
import group.g22.demostore.controller.StatisticController;
import group.g22.demostore.model.Employee;
import group.g22.demostore.repository.EmployeeRepository;
import group.g22.demostore.security.SecurityConfig;
import group.g22.demostore.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoStoreApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private ApplicationContext context;

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeServiceImpl employeeService;
    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Employee> mockEmployees = new ArrayList<>();

        // 2. define behavior of Repository
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // 3. call service method
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        // 4. assert the result
        assertThat(actualEmployees.size()).isEqualTo(mockEmployees.size());

        // 4.1 ensure repository is called
        verify(employeeRepository).findAll();
    }
    @Test
    public void testHomeController() {
        Assertions.assertTrue(context.getBean(HomeController.class) != null);
    }
    @Test
    public void testInvoicesController() {
        Assertions.assertTrue(context.getBean(InvoiceController.class) != null);
    }
    @Test
    public void testStatisticsController() {
        Assertions.assertTrue(context.getBean(StatisticController.class) != null);
    }
    @Test
    public void SecurityTest() {
        Assertions.assertTrue(context.getBean(SecurityConfig.class) != null);
    }
}

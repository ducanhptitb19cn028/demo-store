package group.g22.demostore;

import group.g22.demostore.controller.EmployeeController;
import group.g22.demostore.model.Employee;
import group.g22.demostore.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeePaginationTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testFindPaginated() {
        // Arrange
        int pageNo = 0;
        String sortField = "firstName";
        String sortDir = "asc";
        int pageSize = 5;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", Date.valueOf(LocalDate.now()), "123 Main St", "Male", "Single", "123456789", "image1.png"));
        employees.add(new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Date.valueOf(LocalDate.now()), "456 Second St", "Female", "Married", "987654321", "image2.png"));
        Page<Employee> page = new PageImpl<>(employees);
        when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);

        // Act
        String viewName = employeeController.findPaginated(pageNo, sortField, sortDir, model);

        // Assert
        verify(employeeService).findPaginated(pageNo, pageSize, sortField, sortDir);
        verify(model).addAttribute(eq("currentPage"), eq(pageNo));
        verify(model).addAttribute(eq("totalPages"), eq(page.getTotalPages()));
        verify(model).addAttribute(eq("totalItems"), eq(page.getTotalElements()));
        verify(model).addAttribute(eq("sortField"), eq(sortField));
        verify(model).addAttribute(eq("sortDir"), eq(sortDir));
        verify(model).addAttribute(eq("reverseSortDir"), eq("desc"));
        verify(model).addAttribute(eq("listEmployees"), eq(employees));
        assertEquals("employee", viewName);
    }
    @Test
    public void testFindPaginatedlastname() {
        // Arrange
        int pageNo = 0;
        String sortField = "lastName";
        String sortDir = "asc";
        int pageSize = 5;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", Date.valueOf(LocalDate.now()), "123 Main St", "Male", "Single", "123456789", "image1.png"));
        employees.add(new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Date.valueOf(LocalDate.now()), "456 Second St", "Female", "Married", "987654321", "image2.png"));
        Page<Employee> page = new PageImpl<>(employees);
        when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);

        // Act
        String viewName = employeeController.findPaginated(pageNo, sortField, sortDir, model);

        // Assert
        verify(employeeService).findPaginated(pageNo, pageSize, sortField, sortDir);
        verify(model).addAttribute(eq("currentPage"), eq(pageNo));
        verify(model).addAttribute(eq("totalPages"), eq(page.getTotalPages()));
        verify(model).addAttribute(eq("totalItems"), eq(page.getTotalElements()));
        verify(model).addAttribute(eq("sortField"), eq(sortField));
        verify(model).addAttribute(eq("sortDir"), eq(sortDir));
        verify(model).addAttribute(eq("reverseSortDir"), eq("desc"));
        verify(model).addAttribute(eq("listEmployees"), eq(employees));
        assertEquals("employee", viewName);
    }
    @Test
    public void testFindPaginatedemail() {
        // Arrange
        int pageNo = 0;
        String sortField = "email";
        String sortDir = "asc";
        int pageSize = 5;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", "john.doe@example.com", Date.valueOf(LocalDate.now()), "123 Main St", "Male", "Single", "123456789", "image1.png"));
        employees.add(new Employee(2L, "Jane", "Doe", "jane.doe@example.com", Date.valueOf(LocalDate.now()), "456 Second St", "Female", "Married", "987654321", "image2.png"));
        Page<Employee> page = new PageImpl<>(employees);
        when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);

        // Act
        String viewName = employeeController.findPaginated(pageNo, sortField, sortDir, model);

        // Assert
        verify(employeeService).findPaginated(pageNo, pageSize, sortField, sortDir);
        verify(model).addAttribute(eq("currentPage"), eq(pageNo));
        verify(model).addAttribute(eq("totalPages"), eq(page.getTotalPages()));
        verify(model).addAttribute(eq("totalItems"), eq(page.getTotalElements()));
        verify(model).addAttribute(eq("sortField"), eq(sortField));
        verify(model).addAttribute(eq("sortDir"), eq(sortDir));
        verify(model).addAttribute(eq("reverseSortDir"), eq("desc"));
        verify(model).addAttribute(eq("listEmployees"), eq(employees));
        assertEquals("employee", viewName);
    }
}

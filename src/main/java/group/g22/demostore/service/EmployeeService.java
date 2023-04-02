package group.g22.demostore.service;

import group.g22.demostore.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void insertEmployee(Employee employee);

    Employee getEmployeebyId(long Id);

    void deleteEmployee(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

package group.g22.demostore.service;

import group.g22.demostore.model.Employee;
import group.g22.demostore.repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public void insertEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeebyId(long Id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(Id);
        Employee employee;
        if (optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        else {
            throw new RuntimeException("Employee not found for id:"+ Id);
        }
        return employee;
    }



    @Override
    public void deleteEmployee(long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, @NotNull String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }
}

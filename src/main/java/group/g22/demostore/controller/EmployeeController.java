package group.g22.demostore.controller;

import group.g22.demostore.model.Employee;
import group.g22.demostore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/employee")
    public String viewEmployeepage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/newEmployeesForm")
    public String newEmployeesForm(Model model) {
        // create a new employee
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee_view/new_employees";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save the employee to database
        employeeService.insertEmployee(employee);
        return "redirect:/employee";
    }

    @GetMapping("/showFormupdate/{id}")
    public String showFormUpdate(@PathVariable(value = "id") long id, Model model) {
        // get employee from service by id to update
        Employee employee = employeeService.getEmployeebyId(id);
        model.addAttribute("employee", employee);
        return "employee_view/update_employees";
    }

    @GetMapping("/deleteEmplyee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/employee";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "employee";
    }
}

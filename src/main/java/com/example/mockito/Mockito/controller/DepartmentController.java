package com.example.mockito.Mockito.controller;

import com.example.mockito.Mockito.employee.Employee;
import com.example.mockito.Mockito.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department/")
public class DepartmentController {
    private final DepartmentService employeeService;

    public DepartmentController(DepartmentService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}/salary/min")
    public Employee minSalaryDepartment(@PathVariable int id) {
        return employeeService.minSalary(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee maxSalaryDepartment(@PathVariable int id) {
        return employeeService.maxSalary(id);
    }

    @GetMapping("/{id}/salary/sum")
    public Double calculateAllSalaryByDepartment(@PathVariable int id) {
        return employeeService.calculateAllSalaryByDepartment(id);
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> employeesDepartment(@PathVariable int id) {
        return employeeService.employeesDepartment(id);
    }


    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allEmployeesDepartments() {
        return employeeService.allEmployeesDepartments();
    }
}

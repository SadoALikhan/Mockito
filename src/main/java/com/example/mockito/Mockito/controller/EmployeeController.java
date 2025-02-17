package com.example.mockito.Mockito.controller;

import com.example.mockito.Mockito.employee.Employee;
import com.example.mockito.Mockito.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("lastName") String lastName,
                        @RequestParam("firstName") String firstName,
                        @RequestParam("department") int department,
                        @RequestParam("salary") double salary) {
        return employeeService.addEmployee(lastName, firstName, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam("lastName") String lastName,
                           @RequestParam("firstName") String firstName) {
        return employeeService.removeEmployee(lastName, firstName);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam("lastName") String lastName,
                         @RequestParam("firstName") String firstName) {
        return employeeService.findEmployee(lastName, firstName);
    }

    @GetMapping("/all")
    public Collection<Employee> allEmployees() {
        return employeeService.getEmployees();
    }
}

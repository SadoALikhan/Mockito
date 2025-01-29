package com.example.mockito.Mockito.service;

import com.example.mockito.Mockito.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EmployeeService {
    Employee addEmployee(String lastName, String firstName, int department, double salary);

    Employee removeEmployee(String lastName, String firstName);

    Employee findEmployee(String lastName, String firstName);

    Collection<Employee> getEmployees();
}

package com.example.mockito.Mockito.service;

import com.example.mockito.Mockito.employee.Employee;
import com.example.mockito.Mockito.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {


    Employee employee1 = new Employee("тест", "тест", 1, 20000);
    Employee employee2 = new Employee("тестдва", "тестдва", 1, 60000);
    Employee employee3 = new Employee("Гога", "Бага", 2, 40000);
    Employee employee4 = new Employee("Гава", "тестинг", 2, 70000);
    List<Employee> employeeList = new ArrayList<>(List.of(employee1, employee2, employee3, employee4));


    @Mock
    private EmployeeServiceImpl employeeService;


    @InjectMocks
    private DepartmentServiceImpl departmentService;


    @Test
    void minSalary() {
        when(employeeService.getEmployees()).thenReturn(employeeList);
        Assertions.assertEquals(departmentService.minSalary(1), employee1);
    }

    @Test
    void maxSalary() {
        when(employeeService.getEmployees()).thenReturn(employeeList);
        Assertions.assertEquals(departmentService.maxSalary(1), employee2);
    }

    @Test
    void employeesDepartment() {
        List<Employee> employeesByDepartment1 = new ArrayList<>(List.of(employee1, employee2));
        when(employeeService.getEmployees()).thenReturn(employeeList);
        Assertions.assertEquals(departmentService.employeesDepartment(1), employeesByDepartment1);
    }

    @Test
    void calculateAllSalaryByDepartment() {
        when(employeeService.getEmployees()).thenReturn(employeeList);
        Assertions.assertEquals(departmentService.calculateAllSalaryByDepartment(1), 80000);
    }

    @Test
    void allEmployeesDepartments() {
        List<Employee> employeesByDepartment1 = new ArrayList<>(List.of(employee1, employee2));
        List<Employee> employeesByDepartment2 = new ArrayList<>(List.of(employee3, employee4));

        Map<Integer, List<Employee>> result = new HashMap<>();

        result.put(1, employeesByDepartment1);
        result.put(2, employeesByDepartment2);

        when(employeeService.getEmployees()).thenReturn(employeeList);
        Assertions.assertEquals(departmentService.allEmployeesDepartments(), result);
    }

    @Test
    void wrongEmployeeDepartmentIsExist() {
        when(employeeService.getEmployees()).thenThrow(EmployeeNotFoundException.class);


        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.employeesDepartment(0));
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalary(0));
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalary(0));
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.calculateAllSalaryByDepartment(0));
    }

    @Test
    void wrongThenEmployeesIsNull() {
        when(employeeService.getEmployees()).thenThrow(EmployeeNotFoundException.class);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.allEmployeesDepartments());

    }
}

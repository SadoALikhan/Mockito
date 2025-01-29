package com.example.mockito.Mockito.service;

import com.example.mockito.Mockito.employee.Employee;
import com.example.mockito.Mockito.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

    @Override
    public Employee minSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee maxSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> employeesDepartment(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Double calculateAllSalaryByDepartment(int department) {

        return employeeService.getEmployees().stream()
                .filter(emp -> emp.getDepartment() == department)
                .mapToDouble(Employee::getSalary).sum();
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeesDepartments() {
        if (employeeService.getEmployees()!= null){

            return employeeService.getEmployees().stream()
                    .collect(groupingBy(Employee::getDepartment));
        }
        throw new EmployeeNotFoundException();
    }
}

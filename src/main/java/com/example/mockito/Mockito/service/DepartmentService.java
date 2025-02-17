package com.example.mockito.Mockito.service;

import com.example.mockito.Mockito.employee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee minSalary(int department);

    Employee maxSalary(int department);

    Collection<Employee> employeesDepartment(int department);

    Double calculateAllSalaryByDepartment(int department);

    Map<Integer, List<Employee>> allEmployeesDepartments();

}

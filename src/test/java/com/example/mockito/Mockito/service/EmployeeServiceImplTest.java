package com.example.mockito.Mockito.service;

import com.example.mockito.Mockito.employee.Employee;
import com.example.mockito.Mockito.exceptions.EmployeeAlreadyAddedException;
import com.example.mockito.Mockito.exceptions.EmployeeNotFoundException;
import com.example.mockito.Mockito.exceptions.EmployeeStorageIsFullException;
import com.example.mockito.Mockito.exceptions.WrongFormatException;
import com.example.mockito.Mockito.valueForTesting.EmployeeValueGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTest {

    EmployeeValueGenerator employeeValueGenerator = new EmployeeValueGenerator();

    private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(employeeValueGenerator);

    @Test
    void wrongEmptyLastNameField() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("test", "", 1, 20000.0));
    }

    @Test
    void wrongEmptyFirstNameField() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("", "test", 1, 20000.0));
    }

    @Test
    void wrongInvalidCharacterInLastNameField() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("test", "тест", 1, 20000.0));

    }

    @Test
    void wrongInvalidCharacterInFirstNameField() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("тест", "test", 1, 20000.0));

    }

    @Test
    void wrongSalaryIsNegative() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("тест", "тест", 1, -20000.0));

    }

    @Test
    void wrongSalaryIsZero() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("тест", "тест", 1, 0));

    }

    @Test
    void wrongNotExistingDepartment() {
        Assertions.assertThrows(WrongFormatException.class, () -> employeeServiceImpl.addEmployee("тест", "тест", 6, 20000.0));

    }

    @Test
    void wrongEmployeeStorageIsFullException() {
        employeeServiceImpl.addEmployee("Иванов", "Иван", 2, 50_000);
        employeeServiceImpl.addEmployee("Леонов", "Никита", 3, 45_000);
        employeeServiceImpl.addEmployee("Степанов", "Алексей", 1, 60_000);
        employeeServiceImpl.addEmployee("Семёнов", "Ярослав", 3, 55_000);
        employeeServiceImpl.addEmployee("Максимов", "Николай", 5, 49_000);
        employeeServiceImpl.addEmployee("Степанов", "Ермак", 1, 62_000);
        employeeServiceImpl.addEmployee("Морозов", "Василий", 4, 59_000);
        employeeServiceImpl.addEmployee("Михайлов", "Артур", 2, 44_000);
        employeeServiceImpl.addEmployee("Кешелев", "Константин", 4, 63_000);
        employeeServiceImpl.addEmployee("Тихонов", "Вадим", 5, 53_000);

        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> employeeServiceImpl.addEmployee("тест", "тест", 4, 20000.0));

    }


    @Test
    void addNewEmployee() {

        Employee test = new Employee("тест", "тест", 1, 20000);
        Assertions.assertEquals(test, employeeServiceImpl.addEmployee("тест", "тест", 1, 20000.0));
    }

    @Test
    void addExistingEmployee() {

        employeeServiceImpl.addEmployee("тест", "тест", 1, 20000.0);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> employeeServiceImpl.addEmployee("тест", "тест", 1, 20000.0));
    }

    @Test
    void removeEmployee() {
        Employee test = new Employee("тест", "тест", 1, 20000);

        employeeServiceImpl.addEmployee("тест", "тест", 1, 20000);

        Assertions.assertEquals(test, employeeServiceImpl.removeEmployee("тест", "тест"));

    }

    @Test
    void removeNotExistingEmployee() {

        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.removeEmployee("тест", "тест"));
    }

    @Test
    void findEmployee() {
        Employee test = new Employee("тест", "тест", 2, 20000);
        employeeServiceImpl.addEmployee("тест", "тест", 2, 20000);
        Assertions.assertEquals(test, employeeServiceImpl.findEmployee("тест", "тест"));
    }

    @Test
    void findNotExistingEmployee() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.findEmployee("тест", "тест"));
    }

}

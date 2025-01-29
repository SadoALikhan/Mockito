package com.example.mockito.Mockito.valueForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmployeeValueGenerator {


    private static double DEFAULT_MIN_SALARY = 20000;
    private static double DEFAULT_MAX_SALARY = 125000;

    private static int DEFAULT_MIN_DEPARTMENT = 1;
    private static int DEFAULT_MAX_DEPARTMENT = 5;

    private final int indexLastName = 0;
    private final int indexFirstName = 0;

    private static List<String> firstNames = new ArrayList<>(Arrays.asList("Пабло", "Мигель", "Джон", "Карлос", "Захар",
            "Мари", "Елена", "Анастасия", "Джордж", "Кейт"));

    private static List<String> lastNames = new ArrayList<>(Arrays.asList("Абрамсон", "Адамс", "Бенедикт", "Сихаджок", "Сихарулидзе", "Король",
            "Сантамария", "Киригаев", "Царь", "Ужас"));

    public static List<String> getFirstNames() {
        return firstNames;
    }

    public static void setFirstNames(List<String> firstNames) {
        EmployeeValueGenerator.firstNames = firstNames;
    }

    public static List<String> getLastNames() {
        return lastNames;
    }

    public static void setLastNames(List<String> lastNames) {
        EmployeeValueGenerator.lastNames = lastNames;
    }


    public String getRandomString(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            throw new IllegalArgumentException("Список не должен быть пустым");
        }

        Random random = new Random();
        int index = random.nextInt(strings.size());
        return strings.get(index);
    }

    public String getRandomLastName() {
        String randomLastName = getRandomString(lastNames);
        return randomLastName;
    }

    public String getRandomFirstName() {
        String randomFirstName = getRandomString(firstNames);
        return randomFirstName;
    }

    public Double generateSalaryInRange(double min, double max) {
        return (double) ((Math.random() * (max - min)) + min);
    }

    public Double generateSalary() {
        return generateSalaryInRange(DEFAULT_MIN_SALARY, DEFAULT_MAX_SALARY);
    }

    public int generateDepartmentInRange(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int generateDepartment() {
        return generateDepartmentInRange(DEFAULT_MIN_DEPARTMENT, DEFAULT_MAX_DEPARTMENT);
    }
}

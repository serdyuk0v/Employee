package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employee.exception.EmployeeNotFoundException;
import pro.sky.java.course2.employee.exception.EmployeeStorageIsFullException;
import pro.sky.java.course2.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        int sizeMassive = 10;
        if (employees.size() >= sizeMassive) {
            throw new EmployeeStorageIsFullException("Количество сотрудников переполнено!");
        }
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Такого сотрудника не существует!");
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Такого сотрудника не существует!");
    }

    public List<Employee> showAll() {
        return employees;
    }

}

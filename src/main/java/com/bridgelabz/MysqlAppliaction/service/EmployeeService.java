package com.bridgelabz.MysqlAppliaction.service;

import com.bridgelabz.MysqlAppliaction.model.Employee;
import com.bridgelabz.MysqlAppliaction.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        if (repository.existsById(id)) {
            updatedEmployee.setId(id);
            return repository.save(updatedEmployee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
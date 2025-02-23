package com.bridgelabz.MysqlAppliaction.controller;

import com.bridgelabz.MysqlAppliaction.model.Employee;
import com.bridgelabz.MysqlAppliaction.repository.EmployeeRepository;
import com.bridgelabz.MysqlAppliaction.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/api/employees")
    public class EmployeeController {
        private final EmployeeService service;

        public EmployeeController(EmployeeService service) {
            this.service = service;
        }
        @Autowired
        private EmployeeRepository employeeRepository;


        @GetMapping
        public List<Employee> getAllEmployees() {
            return service.getAllEmployees();
        }

        @GetMapping("/{id}")
        public Employee getEmployeeById(@PathVariable Long id) {
            return service.getEmployeeById(id);
        }

        @PostMapping
        public Employee addEmployee(@RequestBody Employee employee) {
            return service.addEmployee(employee);
        }

        @PutMapping("/{id}")
        public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
            return service.updateEmployee(id, employee);
        }

        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable Long id) {
            service.deleteEmployee(id);
        }
    }


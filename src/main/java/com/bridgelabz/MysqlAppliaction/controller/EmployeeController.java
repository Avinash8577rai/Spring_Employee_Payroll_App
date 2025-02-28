package com.bridgelabz.MysqlAppliaction.controller;

import com.bridgelabz.MysqlAppliaction.dto.EmployeePayrollDTO;
import com.bridgelabz.MysqlAppliaction.model.Employee;
import com.bridgelabz.MysqlAppliaction.repository.EmployeeRepository;
import com.bridgelabz.MysqlAppliaction.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }


    @GetMapping("/by-id/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }


    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

   /* @PostMapping("/dto/add")
    public EmployeePayrollDTO createEmployee(@Valid @RequestBody EmployeePayrollDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        employeeRepository.save(employee);
        return employeeDTO;
    }

    */
   @PostMapping("/dto/add")
   public Employee createEmployee(@Valid @RequestBody EmployeePayrollDTO employeeDTO) {
       Employee employee = new Employee();
       employee.setName(employeeDTO.getName());
       employee.setSalary(employeeDTO.getSalary());
       employee.setGender(employeeDTO.getGender());
       employee.setStartDate(employeeDTO.getStartDate());
       employee.setNote(employeeDTO.getNote());
       employee.setProfilePic(employeeDTO.getProfilePic());
       employee.setDepartment(employeeDTO.getDepartment());

       return employeeRepository.save(employee);
   }


    @GetMapping("/dto/all")
    public List<EmployeePayrollDTO> getAllEmployeesDTO() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeePayrollDTO(emp.getName(), emp.getSalary(), emp.getGender(), emp.getStartDate(),
                        emp.getNote(), emp.getProfilePic(), emp.getDepartment()))
                .collect(Collectors.toList());
    }

    /*** ---- GET EMPLOYEE BY ID AS DTO ---- ***/
    @GetMapping("/dto/by-id/{id}")
    public EmployeePayrollDTO getEmployeeByIdDTO(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeePayrollDTO(employee.getName(), employee.getSalary(), employee.getGender(),
                employee.getStartDate(), employee.getNote(), employee.getProfilePic(), employee.getDepartment());
    }

    /*** ---- UPDATE EMPLOYEE USING DTO ---- ***/
    @PutMapping("/dto/update/{id}")
    public EmployeePayrollDTO updateEmployeeDTO(@PathVariable Long id,@Valid @RequestBody EmployeePayrollDTO updatedDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedDTO.getName());
                    employee.setSalary(updatedDTO.getSalary());
                    employee.setGender(updatedDTO.getGender());
                    employee.setStartDate(updatedDTO.getStartDate());
                    employee.setNote(updatedDTO.getNote());
                    employee.setProfilePic(updatedDTO.getProfilePic());
                    employee.setDepartment(updatedDTO.getDepartment());
                    employeeRepository.save(employee);
                    return updatedDTO;
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @GetMapping("/list/all")
    public List<Employee> getAllEmployeesList() {
        return service.getAllEmployeesList();
    }

    @GetMapping("/list/by-id/{id}")
    public Employee getEmployeeByIdList(@PathVariable int id) {
        return service.getEmployeeByIdList(id);
    }

    /*** ---- ADD EMPLOYEE TO LIST ---- ***/
    @PostMapping("/list/add")
    public Employee addEmployeeList(@RequestBody Employee employee) {
        return service.addEmployeeList(employee);
    }

    @PutMapping("/list/update/{id}")
    public Employee updateEmployeeList(@PathVariable int id, @RequestBody Employee employee) {
        return service.updateEmployeeList(id, employee);
    }

    @DeleteMapping("/list/delete/{id}")
    public String deleteEmployeeList(@PathVariable int id) {
        return service.deleteEmployeeList(id) ? "Deleted Successfully" : "Employee Not Found";
    }
    @GetMapping("/log/all")
    public String getAllEmployeeslog() {
        log.info("Fetching all employees...");
        return "List of employees";
    }

    @GetMapping("/log//by-id/{id}")
    public String getEmployeeByIdlog(@PathVariable Long id) {
        log.debug("Fetching employee with ID: {}", id);
        return "Employee details";
    }

    @PostMapping("/log/add")
    public String addEmployeelog(@RequestBody String employee) {
        log.warn("Adding new employee: {}", employee);
        return "Employee added";
    }
    @GetMapping("/sales-department")
    public List<Employee> getEmployeesBySalesDepartment() {
        return service.getEmployeesBySalesDepartment();
    }

}
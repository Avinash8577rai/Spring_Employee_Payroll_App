package com.bridgelabz.MysqlAppliaction.repository;

import com.bridgelabz.MysqlAppliaction.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

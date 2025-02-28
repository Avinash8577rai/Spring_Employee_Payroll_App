package com.bridgelabz.MysqlAppliaction.repository;

import com.bridgelabz.MysqlAppliaction.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e JOIN e.department d WHERE d = 'Sales'")
    List<Employee> findEmployeesBySalesDepartment();
}

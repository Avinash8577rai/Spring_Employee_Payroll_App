package com.bridgelabz.MysqlAppliaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class EmployeePayrollDTO {


        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[A-Z][a-zA-Z ]{2,}$", message = "Name must start with a capital letter and be at least 3 characters long")
        private String name;

        @NotNull(message = "Salary cannot be null")
        @Min(value = 10000, message = "Salary must be at least 10,000")
        private Double salary;

        @NotEmpty(message = "Gender is required")
        @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
        private String gender;

        @JsonFormat(pattern = "dd-MM-yyyy")
        @PastOrPresent(message = "Start date must be a past or present date")
        private LocalDate startDate;

        @NotEmpty(message = "Department list cannot be empty")
        private List<String> department;

        @NotBlank(message = "Note cannot be blank")
        private String note;

        @NotBlank(message = "Profile picture URL cannot be blank")
        private String profilePic;

    public EmployeePayrollDTO(String name, double salary, String gender, LocalDate startDate, String note, String profilePic, List<String> department) {
        this.name = name ;
        this.salary = salary;
        this.gender = gender ;
        this.startDate = startDate;
        this.profilePic = profilePic;
        this.note = note ;
        this.department = department;
    }


/*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

 */
}
package com.example.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="EMPLOYEE_DB")
public class EmployeeDTO {
    @Id
    private long emp_id;
    private String emp_first_name;
    private String emp_last_name;
    private String role;
    private String joining_date;
    private String work_location;
}
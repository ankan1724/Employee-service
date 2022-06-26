package com.example.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="EMPLOYEE_DB")
public class Model {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private long emp_id;
    private String emp_first_name;
    private String emp_last_name;
    private String department;
    private String joining_date;
    private String work_location;
    
    
    
}
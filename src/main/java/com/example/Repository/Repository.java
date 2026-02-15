package com.example.Repository;

import com.example.Entity.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository <EmployeeDTO,Long> {
}
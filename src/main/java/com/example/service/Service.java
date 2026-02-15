package com.example.service;

import com.example.Entity.EmployeeDTO;
import com.example.Repository.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;
  
    public List<EmployeeDTO> findAll() {
        return repository.findAll();
    }

    public void addEmployee(EmployeeDTO employeeDTO){
        this.repository.save(employeeDTO);
        log.info("Employees added to DB");
    }
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        Optional<EmployeeDTO> optionalEmployee = searchEmployee(id);
        if (optionalEmployee.isEmpty()) {
            return null;
        }
        EmployeeDTO existingEmployee = optionalEmployee.get();
        existingEmployee.setEmp_first_name(updatedEmployee.getEmp_first_name());
        existingEmployee.setEmp_last_name(updatedEmployee.getEmp_last_name());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setJoining_date(updatedEmployee.getJoining_date());
        existingEmployee.setWork_location(updatedEmployee.getWork_location());
        EmployeeDTO updatedDetails=repository.save(existingEmployee);
        return updatedDetails;
    }

    public Optional<EmployeeDTO> searchEmployee(long emp_id){
        return this.repository.findById(emp_id);
    }

    public String deleteEmployee(long emp_id){
        this.repository.deleteById(emp_id);
        return "Employee deleted";
    }
}
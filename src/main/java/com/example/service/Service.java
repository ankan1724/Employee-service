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
    public String updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        Optional<EmployeeDTO> optionalEmployee = searchEmployee(id);
        if (optionalEmployee.isEmpty()) {
            return "Employee not found";
        }
        EmployeeDTO existingEmployee = optionalEmployee.get();
        existingEmployee.setEmp_first_name(updatedEmployee.getEmp_first_name());
        existingEmployee.setEmp_last_name(updatedEmployee.getEmp_last_name());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        repository.save(existingEmployee);
        return "Employee updated successfully";
    }

    public Optional<EmployeeDTO> searchEmployee(long emp_id){
        return this.repository.findById(emp_id);
    }

    public String deleteEmployee(long emp_id){
        this.repository.deleteById(emp_id);
        return "Employee deleted";
    }
}
package com.example.controller;

import com.example.Entity.EmployeeDTO;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/get")
    public List<EmployeeDTO> getAllEmployees() {
        return service.findAll();
    }

    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployee) {
        EmployeeDTO employeeDTO=service.updateEmployee(id, updatedEmployee);
        return employeeDTO;
    }

    @GetMapping("/search")
    public Optional<EmployeeDTO> searchEmployee(@RequestParam(name = "emp_id", required = true) long emp_id) {
        return this.service.searchEmployee(emp_id);
    }

    @DeleteMapping("/delete")
    public String deleteEmployee(@RequestParam(name = "emp_id", required = true) long emp_id) {
        this.service.deleteEmployee(emp_id);
        return "Employee deleted";
    }
}
package com.example.controller;

import com.example.Entity.Model;
import com.example.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")
public class Controller{
    @Autowired
    private Service service;

    @GetMapping("/")
    public List<Model> getAllEmployees() {
        return service.findAll();
    }

@PostMapping("/add")
    public String addEmployee(@RequestBody Model model){
        this.service.addEmployee(model);
        return "Employee added";
    }
      @GetMapping("/search") 
      public Optional<Model> searchEmployee(@RequestParam(name="emp_id", required = true)long emp_id){
        return this.service.searchEmployee(emp_id);
    }
    @DeleteMapping("/delete")
    public String deleteEmployee(@RequestParam(name="emp_id", required = true)long emp_id){
        this.service.deleteEmployee(emp_id);
        return "Employee deleted";
    }
}
package com.example.controller;

import com.example.Entity.Model;
import com.example.service.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("to get list of all employees")
    public List<Model> getAllEmployees() {
        return service.findAll();
    }

@PostMapping("/add")
@ApiOperation("to add employee to database")
    public String addEmployee(@RequestBody Model model){
        this.service.addEmployee(model);
        return "Employee added";
    }
      @GetMapping("/search") 
      @ApiOperation("to search for an employee")
      public Optional<Model> searchEmployee(@RequestParam(name="emp_id", required = true)long emp_id){
        return this.service.searchEmployee(emp_id);
    }
    @DeleteMapping("/delete")
    @ApiOperation("to remove employee")
    public String deleteEmployee(@RequestParam(name="emp_id", required = true)long emp_id){
        this.service.deleteEmployee(emp_id);
        return "Employee deleted";
    }
}
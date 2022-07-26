package com.example.service;

import com.example.Entity.Model;
import com.example.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;
  
    public List<Model> findAll() {
        return repository.findAll();
    }
    public String addEmployee(Model model){
        this.repository.save(model);
        return "Employee added";
    }
    public Optional<Model> searchEmployee(long emp_id){
        return this.repository.findById(emp_id);
    }
    public String deleteEmployee(long emp_id){
        this.repository.deleteById(emp_id);
        return "Employee deleted";
    }
}
package com.example;

import com.example.Entity.EmployeeDTO;
import com.example.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class EmployeeServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(Service service) {
        return args -> {


            List<EmployeeDTO> employees = List.of(new EmployeeDTO(1993656, "Tom", "Derry", "Manager", "05.06.2009", "Canada"),
                    new EmployeeDTO(1993657, "Ben", "Thompson", "Senior Manager", "07.02.2001", "Canada"),
                    new EmployeeDTO(1993658, "Maria", "Lopez", "Tech Lead", "12.10.2014", "Pune"),
                    new EmployeeDTO(1993659, "Aaron", "Wilson", "Developer", "01.11.2021", "Belfast"),
                    new EmployeeDTO(1993680, "Ankan", "Ghosh", "Developer", "01.09.2021", "Kolkata"));
            employees.forEach(emp -> service.addEmployee(emp));
        };
    }

}
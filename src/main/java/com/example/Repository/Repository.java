package com.example.Repository;

import com.example.Entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository <Model,Long> {
}
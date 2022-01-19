package com.your_help.example.repository;

import com.your_help.example.model.Service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}

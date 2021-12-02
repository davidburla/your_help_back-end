package com.your_help.example.repository;

import com.your_help.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
}

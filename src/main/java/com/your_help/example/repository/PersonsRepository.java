package com.your_help.example.repository;

import com.your_help.example.model.Person.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonsRepository extends JpaRepository<Person, Integer> {
    List<Person> findAllByIsDeletedFalse();
}

package com.your_help.example.controller;

import com.your_help.example.model.Person;
import com.your_help.example.repository.PersonsRepository;
import com.your_help.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PersonController {
    @Autowired
    private PersonsRepository personsRepository;

    // get all persons
    @GetMapping("/persons")
    public List<Person> getAllPersons()
    {
        return personsRepository.findAll();
    }

    //create person rest api
    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person)
    {
        return personsRepository.save(person);
    }

    // get person by id rest api
    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id)
    {
        Person person = personsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id: " + id));
        return ResponseEntity.ok(person);
    }

    // update person rest api
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person personDetails)
    {
        Person person = personsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id: " + id));
        person.setNume(personDetails.getNume());
        person.setPrenume(personDetails.getPrenume());
        person.setEmail(personDetails.getEmail());
        person.setPassword(personDetails.getPassword());
        person.setTelefon(personDetails.getTelefon());

        Person updatePerson = personsRepository.save(person);
        return ResponseEntity.ok(updatePerson);
    }
}

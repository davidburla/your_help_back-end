package com.your_help.example.controller;

import com.your_help.example.model.Person.Person;
import com.your_help.example.model.Person.PersonDto;
import com.your_help.example.repository.PersonsRepository;
import com.your_help.example.exception.ResourceNotFoundException;
import com.your_help.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }
    // get all persons
    @GetMapping("/persons")
    public ResponseEntity<List<PersonDto>> getAllPersons()
    {
        return ResponseEntity.ok(personService.getAll());
    }

    //create person rest api
    @PostMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto person)
    {
        return ResponseEntity.ok(personService.create(person));
    }

    // get person by id rest api
    @GetMapping(path = "/persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(personService.getById(id));
    }

    // update person rest api
    @PutMapping(path = "/persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer id, @RequestBody PersonDto dto)
    {
        System.out.println(dto);
        return ResponseEntity.ok(personService.update(dto));
    }
}

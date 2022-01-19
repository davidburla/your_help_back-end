package com.your_help.example.service;
import com.sun.istack.NotNull;
import com.your_help.example.model.Person.Person;
import com.your_help.example.model.Person.PersonDto;
import com.your_help.example.repository.PersonsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonService {
    private PersonsRepository personRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PersonService(PersonsRepository personRepository,
                         ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public List<PersonDto> getAll()
    {
        return personRepository.
                findAll()
                .stream()
                .map(item -> modelMapper.map(item, PersonDto.class))
                .collect(Collectors.toList());
    }

    public PersonDto getById(Integer id)
    {
        return personRepository
                .findById(id)
                .map(result->modelMapper.map(result, PersonDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    public PersonDto create(@NotNull PersonDto dto)
    {
        Person person = modelMapper.map(dto, Person.class);
        return modelMapper.map(personRepository.save(person), PersonDto.class);
    }

    public PersonDto update(@NotNull PersonDto dto)
    {
        return personRepository
                .findById(dto.getId())
                .map(result -> {
                    Person toBeUpdated  = modelMapper.map(dto, Person.class);
                    return modelMapper.map(personRepository.save(toBeUpdated), PersonDto.class);
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}

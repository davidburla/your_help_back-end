package com.your_help.example.service;
import com.sun.istack.NotNull;
import com.your_help.example.model.Person.Person;
import com.your_help.example.model.Person.PersonDto;
import com.your_help.example.repository.PersonsRepository;

import org.hibernate.annotations.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Cacheable("all_persons")
    public List<PersonDto> getAll()
    {
        return personRepository.
                findAllByIsDeletedFalse()
                .stream()
                .map(item -> modelMapper.map(item, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "person_by_id", key="#id")
    public PersonDto getById(Integer id)
    {
        return personRepository
                .findById(id)
                .map(result->modelMapper.map(result, PersonDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @CacheEvict(value = "all_persons", allEntries = true)
    public PersonDto create(@NotNull PersonDto dto)
    {
        Person person = modelMapper.map(dto, Person.class);
        return modelMapper.map(personRepository.save(person), PersonDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "all_persons", allEntries = true),
            @CacheEvict(value = "person_by_id", key ="#dto.personId")
    })
    public PersonDto update(@NotNull PersonDto dto)
    {
        return personRepository
                .findById(dto.getPersonId())
                .map(result -> {
                    Person toBeUpdated  = modelMapper.map(dto, Person.class);
                    return modelMapper.map(personRepository.save(toBeUpdated), PersonDto.class);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Caching(evict = {
            @CacheEvict(value = "all_persons", allEntries = true),
            @CacheEvict(value = "person_by_id", key ="#id")
    })
    public Boolean delete(@NotNull Integer id)
    {
        return personRepository.
                findById(id)
                .map(result -> {
                    result.setIsDeleted(true);
                    return personRepository.save(result).getIsDeleted();
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}

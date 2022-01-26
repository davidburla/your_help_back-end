package com.your_help.example.service;

import com.sun.istack.NotNull;
import com.your_help.example.model.Offer.Offer;
import com.your_help.example.model.Offer.OfferDto;
import com.your_help.example.model.Offer.Utils;
import com.your_help.example.model.Person.Person;
import com.your_help.example.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OfferService {
    private OfferRepository offerRepository;
    private ModelMapper modelMapper;
    private PersonService personService;

    @Autowired
    public OfferService(OfferRepository offerRepository,
                        ModelMapper modelMapper, PersonService personService)
    {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.personService = personService;
        modelMapper.addMappings(Utils.offerMapping);
        modelMapper.addMappings(Utils.offerFieldMapping);
    }

    @Cacheable(value = "all_offer_valid")
    public List<OfferDto> getAllIsValid()
    {
        return offerRepository
                .findAllByIsValidTrueAndPersonIsDeletedFalse()
                .stream()
                .map(item -> modelMapper.map(item, OfferDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "offer_by_id", key = "#id")
    public OfferDto getOfferById(@NotNull Integer id)
    {
        return offerRepository.findById(id)
                .map(result -> modelMapper.map(result, OfferDto.class))
                .orElseThrow(EmptyStackException::new);
    }

    @CacheEvict(value = "all_offer_valid", allEntries = true)
    public OfferDto addOffer(@NotNull OfferDto offerDto)
    {
        Person person = modelMapper.map(offerDto, Person.class);
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offer.setPerson(person);
        return modelMapper.map(offerRepository.save(offer), OfferDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "all_offer_valid", allEntries = true),
            @CacheEvict(value = "offer_by_id", key="#offerId")
    })
    public  Boolean delete(@NotNull Integer offerId)
    {
        return offerRepository.findById(offerId)
                .map(result -> {
                    result.setIsValid(false);
                    return offerRepository.save(result).getIsValid();
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Caching(evict = {
            @CacheEvict(value = "all_offer_valid", allEntries = true),
            @CacheEvict(value = "offer_by_id", key="#offerDto.offerId")
    })
    public OfferDto update(@NotNull OfferDto offerDto)
    {
        return offerRepository
                .findById(offerDto.getOfferId())
                .map(result -> {
                    Offer toBeUpdated = modelMapper.map(offerDto, Offer.class);
                    return modelMapper.map(offerRepository.save(toBeUpdated), OfferDto.class);
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}

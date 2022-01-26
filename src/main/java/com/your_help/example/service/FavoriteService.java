package com.your_help.example.service;

import com.sun.istack.NotNull;
import com.your_help.example.model.Favorite.Favorite;
import com.your_help.example.model.Favorite.FavoriteDto;
import com.your_help.example.model.Favorite.Utils;
import com.your_help.example.model.Offer.Offer;
import com.your_help.example.model.Person.Person;
import com.your_help.example.repository.FavoriteRepository;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private ModelMapper modelMapper;
    private OfferService offerService;
    private PersonService personService;
    private Integer personId = 0;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository,
                           ModelMapper modelMapper, OfferService offerService,
                           PersonService personService)
    {
        this.favoriteRepository = favoriteRepository;
        this.modelMapper = modelMapper;
        this.offerService = offerService;
        this.personService = personService;

        this.modelMapper.addMappings(Utils.favoriteMapping);
        this.modelMapper.addMappings(Utils.favoriteFieldMapping);
    }

    @Cacheable("all_favorites")
    public List<FavoriteDto> getAll()
    {
        return favoriteRepository.
                findAll()
                .stream()
                .map(item -> modelMapper.map(item, FavoriteDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("all_favorites_valid")
    public List<FavoriteDto> getAllFavoriteByPersonAndOfferValid()
    {
        return favoriteRepository.
                findAllByPersonIsDeletedFalseAndOfferIsValidTrue()
                .stream()
                .map(item -> modelMapper.map(item, FavoriteDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "favorite_by_personId", key = "#personId")
    public List<FavoriteDto> getByPersonId(@NotNull Integer personId)
    {
        return favoriteRepository.
                findAllByPersonPersonId(modelMapper.map(personService.getById(personId), Person.class).getPersonId())
                .stream()
                .map(item->modelMapper.map(item, FavoriteDto.class))
                .collect(Collectors.toList());
    }

    @Caching(evict = {
            @CacheEvict(value = "all_favorites", allEntries = true),
            @CacheEvict(value = "all_favorites_valid", allEntries = true)
    })
    public  FavoriteDto create(FavoriteDto favoriteDto)
    {
        Person person = modelMapper.map(favoriteDto, Person.class);
        Offer offer = modelMapper.map(favoriteDto, Offer.class);
        Favorite favorite = modelMapper.map(favoriteDto, Favorite.class);
        favorite.setOffer(offer);
        favorite.setPerson(person);
        return modelMapper.map(favoriteRepository.save(favorite), FavoriteDto.class);
    }

    @Caching(evict = {
            @CacheEvict(value = "all_favorites", allEntries = true),
            @CacheEvict(value = "all_favorites_valid", allEntries = true),
            @CacheEvict(value = "favorite_by_personId")
    })
    public Boolean delete(@NotNull Integer id)
    {
        this.personId = favoriteRepository.getById(id).getPerson().getPersonId();
        favoriteRepository.deleteById(id);
        return true;
    }
}

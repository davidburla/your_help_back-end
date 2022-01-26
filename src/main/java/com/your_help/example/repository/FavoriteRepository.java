package com.your_help.example.repository;

import com.your_help.example.model.Favorite.Favorite;
import com.your_help.example.model.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findAllByPersonPersonId(Integer personId);
    List<Favorite> findAllByPersonIsDeletedFalseAndOfferIsValidTrue();

}

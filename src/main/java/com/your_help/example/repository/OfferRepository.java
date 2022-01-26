package com.your_help.example.repository;

import com.your_help.example.model.Offer.Offer;
import com.your_help.example.model.Person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByIsValidTrueAndPersonIsDeletedFalse();
}

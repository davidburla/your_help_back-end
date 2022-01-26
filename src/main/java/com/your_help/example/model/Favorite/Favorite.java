package com.your_help.example.model.Favorite;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.your_help.example.model.Offer.Offer;
import com.your_help.example.model.Person.Person;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteId", nullable = false)
    private Integer favoriteId;

    @ManyToOne
    @JoinColumn(name="personId", referencedColumnName = "personId", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name="offerId", referencedColumnName = "offerId", nullable = false)
    private Offer offer;
}

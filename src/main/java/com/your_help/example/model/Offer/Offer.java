package com.your_help.example.model.Offer;

import com.your_help.example.model.Favorite.Favorite;
import com.your_help.example.model.Person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="offerId")
    private Integer offerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "category_name", nullable = false)
    private String category_name;

    @Column(name = "description")
    private String description;

    @Column(name = "zona")
    private String zona;

    @Column(name = "isValid")
    private Boolean isValid = true;

    @ManyToOne
    @JoinColumn(name="personId", referencedColumnName = "personId", nullable = false)
    private Person person;

    @OneToMany(mappedBy = "offer")
    Collection<Favorite> favorites;
}

package com.your_help.example.model.Person;
import com.your_help.example.model.Favorite.Favorite;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Integer personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "prename", nullable = false)
    private String prename;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "location")
    private String location;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "person")
    private Collection<Favorite> favorites;

}


package com.your_help.example.model.Service;

import com.your_help.example.model.Category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table (name = "services")
public class Service implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name_service", nullable = false)
    private String name_service;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category categoryId;
}

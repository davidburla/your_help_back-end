package com.your_help.example.model.Category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_category", nullable = false)
    private String name_category;

    public Category()
    {
    }

    public Category(String name_category)
    {
        super();
        this.name_category = name_category;
    }

}

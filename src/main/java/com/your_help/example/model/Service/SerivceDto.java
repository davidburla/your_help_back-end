package com.your_help.example.model.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SerivceDto {
    private Integer id;
    private String name_service;
    private String description;
    private Integer categoryId;
}

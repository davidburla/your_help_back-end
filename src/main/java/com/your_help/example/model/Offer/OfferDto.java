package com.your_help.example.model.Offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferDto {
    private Integer offerId;
    private Integer personId;
    private String name;
    private Double price;
    private String category_name;
    private String description;
    private String zona;
    private Boolean isValid;
    private String namePerson;
    private String prenamePerson;
    private String emailPerson;
    private String phonePerson;
}

package com.your_help.example.model.Favorite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteDto {
    private Integer favoriteId;
    private Integer personId;
    private Integer offerId;
    private String nameOffer;
    private Double priceOffer;
    private String category_nameOffer;
    private String descriptionOffer;
    private String zonaOffer;
    private String namePersonOffer;
    private String prenamePersonOffer;
    private String namePerson;
    private String prenamePerson;
    private String emailPerson;
    private String phonePerson;
}

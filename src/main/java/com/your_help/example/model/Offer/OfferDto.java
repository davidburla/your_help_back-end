package com.your_help.example.model.Offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferDto {
    private Integer id;
    private Integer personId;
    private Integer serviceId;
    private Boolean isValid;
}

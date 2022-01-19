package com.your_help.example.model.Offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OfferId implements Serializable
{
    @Column(name = "personId")
    private Integer personId;

    @Column(name = "serviceId")
    private Integer serviceId;
}

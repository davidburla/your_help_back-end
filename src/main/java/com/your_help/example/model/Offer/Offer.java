package com.your_help.example.model.Offer;

import com.your_help.example.model.Person.Person;
import com.your_help.example.model.Service.Service;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer implements Serializable
{

    @EmbeddedId
    private OfferId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name="personId", nullable = false)
    Person personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    @JoinColumn(name="serviceId", nullable = false)
    Service serviceId;

    @Column(name="isValid")
    Boolean isValid;

}

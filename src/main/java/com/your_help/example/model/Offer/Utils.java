package com.your_help.example.model.Offer;

import org.modelmapper.PropertyMap;

public class Utils {
    public static PropertyMap<OfferDto, Offer>  offerMapping = new PropertyMap<OfferDto, Offer>() {
        @Override
        protected void configure() {
            map().getPerson().setPersonId(source.getPersonId());
        }
    };

    public static PropertyMap<Offer, OfferDto> offerFieldMapping = new PropertyMap<Offer, OfferDto>() {
        @Override
        protected void configure() {
            map().setPersonId(source.getPerson().getPersonId());
            map().setNamePerson(source.getPerson().getName());
            map().setPrenamePerson(source.getPerson().getPrename());
            map().setEmailPerson(source.getPerson().getEmail());
            map().setPhonePerson(source.getPerson().getPhone());
        }
    };
}

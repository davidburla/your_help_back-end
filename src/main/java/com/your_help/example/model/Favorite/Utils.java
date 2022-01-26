package com.your_help.example.model.Favorite;

import org.modelmapper.PropertyMap;

public class Utils {
    public static PropertyMap<FavoriteDto, Favorite> favoriteMapping = new PropertyMap<FavoriteDto, Favorite>() {
        @Override
        protected void configure() {
            map().getOffer().setOfferId(source.getOfferId());
            map().getPerson().setPersonId(source.getPersonId());
        }
    };

    public static PropertyMap<Favorite, FavoriteDto> favoriteFieldMapping = new PropertyMap<Favorite, FavoriteDto>() {
        @Override
        protected void configure() {
            map().setPersonId(source.getPerson().getPersonId());
            map().setNamePerson(source.getPerson().getName());
            map().setPrenamePerson(source.getPerson().getPrename());
            map().setEmailPerson(source.getPerson().getEmail());
            map().setPhonePerson(source.getPerson().getPhone());

            map().setOfferId(source.getOffer().getOfferId());
            map().setNameOffer(source.getOffer().getName());
            map().setDescriptionOffer(source.getOffer().getDescription());
            map().setCategory_nameOffer(source.getOffer().getCategory_name());
            map().setZonaOffer(source.getOffer().getZona());
            map().setPriceOffer(source.getOffer().getPrice());
            map().setNamePersonOffer(source.getOffer().getPerson().getName());
            map().setPrenamePersonOffer(source.getOffer().getPerson().getPrename());
        }
    };
}

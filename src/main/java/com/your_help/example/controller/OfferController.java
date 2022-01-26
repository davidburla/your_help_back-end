package com.your_help.example.controller;

import com.your_help.example.model.Offer.Offer;
import com.your_help.example.model.Offer.OfferDto;
import com.your_help.example.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired OfferController(OfferService offerService)
    {
        this.offerService = offerService;
    }

    @GetMapping(path="")
    public ResponseEntity<List<OfferDto>> getAllOffer()
    {
        return ResponseEntity.ok(offerService.getAllIsValid());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<OfferDto> getById(@PathVariable("id") Integer id)
    {
        return  ResponseEntity.ok(offerService.getOfferById(id));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferDto offerDto)
    {
        return ResponseEntity.ok(offerService.addOffer(offerDto));
    }

    @DeleteMapping(value = "/delete/{idOffer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOffer(@PathVariable("idOffer") Integer idOffer)
    {
        return ResponseEntity.ok(offerService.delete(idOffer));
    }
}

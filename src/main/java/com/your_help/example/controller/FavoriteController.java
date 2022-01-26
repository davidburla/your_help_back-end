package com.your_help.example.controller;

import com.your_help.example.model.Favorite.FavoriteDto;
import com.your_help.example.service.FavoriteService;
import com.your_help.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired FavoriteController(FavoriteService favoriteService)
    {
        this.favoriteService = favoriteService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteDto>> getAllFavorite()
    {
        return ResponseEntity.ok(favoriteService.getAll());
    }

    @GetMapping(path = "/valid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteDto>> getAllFavoriteValid()
    {
        return ResponseEntity.ok(favoriteService.getAllFavoriteByPersonAndOfferValid());
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteDto> createFavorite(@RequestBody FavoriteDto favorite)
    {
        return ResponseEntity.ok(favoriteService.create(favorite));
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFavorite(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(favoriteService.delete(id));
    }

    @GetMapping(path = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteDto>> getAllFavoriteByPersonId(@PathVariable("personId") Integer personId)
    {
        return ResponseEntity.ok(favoriteService.getByPersonId(personId));
    }


}

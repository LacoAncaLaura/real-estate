package org.fasttrackit.realestate.web;

import org.fasttrackit.realestate.service.FavoriteService;
import org.fasttrackit.realestate.transfer.favorite.AddEstateToFavoriteRequest;
import org.fasttrackit.realestate.transfer.favorite.FavoriteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/carts")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @PutMapping("/(userId)")
    public ResponseEntity<Void>addEstatesToFavorite(@PathVariable long userId, @Valid @RequestBody AddEstateToFavoriteRequest request){
        favoriteService.addEstateToFavorite(userId,request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/(userId)")
    public ResponseEntity<FavoriteResponse> getFavorite(@PathVariable long userId){
        FavoriteResponse favorite = favoriteService.getFavorite(userId);
        return new ResponseEntity<>(favorite,HttpStatus.OK);
    }
}

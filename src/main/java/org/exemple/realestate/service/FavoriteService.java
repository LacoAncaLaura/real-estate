package org.exemple.realestate.service;

import org.exemple.realestate.domain.Estate;
import org.exemple.realestate.domain.Favorite;
import org.exemple.realestate.domain.User;
import org.exemple.realestate.exception.ResourceNotFoundException;
import org.exemple.realestate.persistance.FavoriteRepository;
import org.exemple.realestate.transfer.favorite.AddEstateToFavoriteRequest;
import org.exemple.realestate.transfer.favorite.EstateInFavoriteResponse;
import org.exemple.realestate.transfer.favorite.FavoriteResponse;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavoriteService.class);

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final EstateService estateService;

    public FavoriteService(FavoriteRepository favoriteRepository, UserService userService, EstateService estateService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.estateService = estateService;
    }

    @Transactional
    public void addEstateToFavorite(long favoriteId, AddEstateToFavoriteRequest request) {
        LOGGER.info("Adding estate to favorite {},{}", favoriteId, request);

        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElse(new Favorite());
        if (favorite.getUser() == null) {
            User user = userService.getUser(favoriteId);
            favorite.setUser(user);
        }
        for (Long estateId : request.getEstateIds()) {
            Estate estate = estateService.getEstate(estateId);
            favorite.addToFavorites(estate);
        }
        favoriteRepository.save(favorite);
    }
    @Transactional
    public FavoriteResponse getFavorite(long id){
        LOGGER.info("Retrieving favorite {}" , id);
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite" + id + "dose not exist"));

        FavoriteResponse favoriteResponse = new FavoriteResponse();
        favoriteResponse.setId(favorite.getId());

        List<EstateInFavoriteResponse> estateDTO = new ArrayList<>();

        for (Estate estate: favorite.getEstates()){
            EstateInFavoriteResponse estateResponse = new EstateInFavoriteResponse();
            estateResponse.setId(estate.getId());
            estateResponse.setType(estate.getType());
            estateResponse.setCity(estate.getCity());
            estateResponse.setStreet(estate.getStreet());
            estateResponse.setValue(estate.getValue());
            estateResponse.setTax(estate.getTax());

            estateDTO.add(estateResponse);
        }
        favoriteResponse.setEstates(estateDTO);
        return favoriteResponse;
    }
}

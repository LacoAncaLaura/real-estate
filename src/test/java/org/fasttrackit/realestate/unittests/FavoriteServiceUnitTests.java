package org.fasttrackit.realestate.unittests;

import org.fasttrackit.realestate.domain.Estate;
import org.fasttrackit.realestate.domain.Favorite;
import org.fasttrackit.realestate.domain.User;
import org.fasttrackit.realestate.persistance.FavoriteRepository;
import org.fasttrackit.realestate.service.EstateService;
import org.fasttrackit.realestate.service.FavoriteService;
import org.fasttrackit.realestate.service.UserService;
import org.fasttrackit.realestate.transfer.favorite.AddEstateToFavoriteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceUnitTests {

    private FavoriteService favoriteService;
    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private UserService userService;
    @Mock
    private EstateService estateService;

    @BeforeEach
    public void setup(){
        favoriteService = new FavoriteService(favoriteRepository,userService,estateService);
    }
    @Test
    public void addEstateToFavorite_whenNewUser_thenNoErrorIsThrown(){
        when(favoriteRepository.findById(anyLong())).thenReturn(Optional.empty());
        User user = new User();
        user.setId(1);
        user.setRole(User.UserRole.COSTUMER);
        user.setFirstName("Test First Name");
        user.setLastName("Test Last Name");
        when(userService.getUser(ArgumentMatchers.anyLong())).thenReturn(user);

        Estate estate = new Estate();
        estate.setId(5);
        when(estateService.getEstate(ArgumentMatchers.anyLong())).thenReturn(estate);

        when(favoriteRepository.save(any(Favorite.class))).thenReturn(null);

        AddEstateToFavoriteRequest request = new AddEstateToFavoriteRequest();
        request.setEstateIds(Collections.singletonList(estate.getId()));
        favoriteService.addEstateToFavorite(user.getId(),request);
        verify(favoriteRepository).findById(ArgumentMatchers.anyLong());
        verify(userService).getUser(ArgumentMatchers.anyLong());
        verify(estateService).getEstate(ArgumentMatchers.anyLong());
        verify(favoriteRepository).save(any(Favorite.class));
    }
}

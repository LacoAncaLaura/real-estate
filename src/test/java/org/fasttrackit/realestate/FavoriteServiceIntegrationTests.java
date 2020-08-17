package org.fasttrackit.realestate;

import org.fasttrackit.realestate.domain.User;
import org.fasttrackit.realestate.service.FavoriteService;
import org.fasttrackit.realestate.teststeps.EstateTestSteps;
import org.fasttrackit.realestate.teststeps.UserTestSteps;
import org.fasttrackit.realestate.transfer.estate.EstateResponse;
import org.fasttrackit.realestate.transfer.favorite.AddEstateToFavoriteRequest;
import org.fasttrackit.realestate.transfer.favorite.FavoriteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class FavoriteServiceIntegrationTests {


    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Autowired
    private EstateTestSteps estateTestSteps;

    @Test
    public void addEstateToFavorite_whenNewUser_thenCreateFavoriteForUser() {
        User user = userTestSteps.createUser();

        EstateResponse estate = estateTestSteps.createEstate();

        AddEstateToFavoriteRequest request = new AddEstateToFavoriteRequest();
        request.setEstateIds(Collections.singletonList(estate.getId()));

        favoriteService.addEstateToFavorite(user.getId(), request);

        FavoriteResponse favoriteResponse = favoriteService.getFavorite(user.getId());

        assertThat(favoriteResponse, notNullValue());
        assertThat(favoriteResponse.getId(), is(user.getId()));
        assertThat(favoriteResponse.getEstates(), notNullValue());
        assertThat(favoriteResponse.getEstates(), hasSize(1));
        assertThat(favoriteResponse.getEstates().get(0), notNullValue());
        assertThat(favoriteResponse.getEstates().get(0).getId(), is(estate.getId()));
        assertThat(favoriteResponse.getEstates().get(0).getCity(), is(estate.getCity()));
        assertThat(favoriteResponse.getEstates().get(0).getValue(), is(estate.getValue()));
        assertThat(favoriteResponse.getEstates().get(0).getQuantity(), is(estate.getQuantity()));
        assertThat(favoriteResponse.getEstates().get(0).getImageURL(), is(estate.getImageUrl()));
    }
}


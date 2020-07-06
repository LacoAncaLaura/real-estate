package org.exemple.realestate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.exemple.realestate.exception.ResourceNotFoundException;
import org.exemple.realestate.service.EstateService;
import org.exemple.realestate.web.dto.SaveEstateRequestDto;
import org.exemple.realestate.persistance.entity.Estate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
class RealEstateApplicationTests {



@Autowired
EstateService estateService;
    @Test
    void createEstate_whenValidRequest_thenReturnCreatedEstate() {
        createEstate();
    }

    private Estate createEstate() {
        SaveEstateRequestDto request = new SaveEstateRequestDto();
        request.setType("House");
        request.setOwner("John Preston");
        request.setCity("Cluj");
        request.setStreet("MainStreet");
        request.setNumber(121);
        request.setSize(70.5);
        request.setValue(90000);
        request.setQuantity(1);
        request.setTax(3.3);


        Estate estate = estateService.createEstate(request);

        assertThat(estate, notNullValue());
        assertThat(estate.getId(), greaterThan(0L));
        assertThat(estate.getType(), is(request.getType()));
        assertThat(estate.getOwner(), is(request.getOwner()));
        assertThat(estate.getCity(), is(request.getCity()));
        assertThat(estate.getStreet(), is(request.getStreet()));
        assertThat(estate.getNumber(), is(request.getNumber()));
        assertThat(estate.getSize(), is(request.getSize()));
        assertThat(estate.getValue(), is(request.getValue()));
        assertThat(estate.getQuantity(), is(request.getQuantity()));
        assertThat(estate.getTax(),is(request.getTax()));
     return estate;
    }

    @Test
    void getEstate_whenExistingEstate_thenReturnEstate() {
        Estate estate = createEstate();
        Estate response = estateService.getEstate(estate.getId());

        assertThat(estate, notNullValue());
        assertThat(response.getId(), greaterThan(0l));
        assertThat(response.getType(), is(estate.getType()));
        assertThat(response.getOwner(), is(estate.getOwner()));
        assertThat(response.getCity(), is(estate.getCity()));
        assertThat(response.getStreet(), is(estate.getStreet()));
        assertThat(response.getNumber(), is(estate.getNumber()));
        assertThat(response.getSize(), is(estate.getSize()));
        assertThat(response.getValue(), is(estate.getValue()));
        assertThat(response.getQuantity(), is(estate.getQuantity()));
        assertThat(response.getTax(),is(estate.getTax()));
    }

    @Test
    void createEstate_whenMissingMandatoryDetails_thenThrowException() {
        SaveEstateRequestDto request = new SaveEstateRequestDto();
        try {
            Estate estate = estateService.createEstate(request);
        } catch (Exception e) {
            assertThat("Missing mandatory details", e instanceof ConstraintViolationException);
        }
    }
    @Test
    void getEstate_whenNoExistingEstate_thenThrowNotFoundException(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> estateService.getEstate(0));
    }
    @Test
    void updateEstate_whenValidDetailsChange_thenUpdatingEstate(){
        SaveEstateRequestDto request = new SaveEstateRequestDto();
        request.setType("House");
        request.setOwner("John Preston");
        request.setCity("Cluj");
        request.setStreet("MainStreet");
        request.setNumber(121);
        request.setSize(70.5);
        request.setValue(90000);
        request.setQuantity(1);
        request.setTax(3.3);
        Estate updateEstate = estateService.createEstate(request);
        assertThat(updateEstate, notNullValue());
        assertThat(updateEstate.getId(), greaterThan(0l));
        assertThat(updateEstate.getType(), is(request.getType()));
        assertThat(updateEstate.getOwner(), is(request.getOwner()));
        assertThat(updateEstate.getCity(), is(request.getCity()));
        assertThat(updateEstate.getStreet(), is(request.getStreet()));
        assertThat(updateEstate.getNumber(), is(request.getNumber()));
        assertThat(updateEstate.getSize(), is(request.getSize()));
        assertThat(updateEstate.getValue(), is(request.getValue()));
        assertThat(updateEstate.getQuantity(), is(request.getQuantity()));
        assertThat(updateEstate.getTax(),is(request.getTax()));
    }
    @Test
    void deleteEstate_whenExistingEstate_theEstateDoseNotExistAnymore(){
        Estate estate = createEstate();
        estateService.deleteEstate(estate.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> estateService.getEstate(0));
    }
}

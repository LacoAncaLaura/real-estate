package org.fasttrackit.realestate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.fasttrackit.realestate.domain.Estate;
import org.fasttrackit.realestate.exception.ResourceNotFoundException;
import org.fasttrackit.realestate.service.EstateService;
import org.fasttrackit.realestate.transfer.SaveEstateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import static org.hamcrest.CoreMatchers.notNullValue;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RealEstateApplicationTests {
//    private static final String MOCK_OUTPUT = "Mocked show ";
    @Mock
    @InjectMocks
    private EstateService estateService;

    @Test
    void createEstate_whenValidRequest_thenReturnCreatedEstate() {
        createEstate();
    }

    private Estate createEstate() {
        SaveEstateRequest request = new SaveEstateRequest();
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
        assertThat(estate.getId(), greaterThan(0l));
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
    }

    @Test
    void createEstate_whenMissingMandatoryDetails_thenThrowException() {
        SaveEstateRequest request = new SaveEstateRequest();
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
        SaveEstateRequest request = new SaveEstateRequest();
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

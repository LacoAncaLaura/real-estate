package org.fasttrackit.realestate;

import org.fasttrackit.realestate.exception.ResourceNotFoundException;
import org.fasttrackit.realestate.service.EstateService;
import org.fasttrackit.realestate.teststeps.EstateTestSteps;
import org.fasttrackit.realestate.transfer.estate.EstateResponse;
import org.fasttrackit.realestate.web.dto.GetEstateRequestDto;
import org.fasttrackit.realestate.web.dto.SaveEstateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class EstateServiceIntegrationTests {



        // field injection
        @Autowired
        private EstateService estateService;

        @Autowired
        private EstateTestSteps estateTestSteps;

        @Test
        void createEstate_whenValidRequest_thenReturnCreatedEstate() {
            estateTestSteps.createEstate();
        }

        @Test
        void createEstate_whenMissingMandatoryProperties_thenThrowException() {
            SaveEstateRequestDto request = new SaveEstateRequestDto();

            try {
                estateService.createEstate(request);
            } catch (Exception e) {
                assertThat("Unexpected exception thrown", e instanceof ConstraintViolationException);
            }
        }

        @Test
        void getEstate_whenExistingEstate_thenReturnEstate() {
            EstateResponse estate = estateTestSteps.createEstate();

            EstateResponse response = estateService.getEstateResponse(estate.getId());

            assertThat(response, notNullValue());
            assertThat(response.getId(), is(estate.getId()));
            assertThat(response.getCity(), is(estate.getCity()));
            assertThat(response.getValue(), is(estate.getValue()));
            assertThat(response.getQuantity(), is(estate.getQuantity()));
            assertThat(response.getStreet(), is(estate.getStreet()));
            assertThat(response.getType(), is(estate.getType()));
            assertThat(response.getBedroom(), is(estate.getBedroom()));
            assertThat(response.getBathroom(), is(estate.getBathroom()));
            assertThat(response.getImageUrl(), is(estate.getImageUrl()));
        }

        @Test
        void getEstate_whenNonExistingEstate_thenThrowResourceNotFoundException() {
            Assertions.assertThrows(ResourceNotFoundException.class,
                    () -> estateService.getEstateResponse(0));
        }

        @Test
        void getEstate_whenOneExistingEstate_thenReturnPageOfOneEstate() {
            EstateResponse estate = estateTestSteps.createEstate();

            Page<EstateResponse> estatePage = estateService.getEstates(new GetEstateRequestDto(), PageRequest.of(0, 1000));

            assertThat(estatePage, notNullValue());
            assertThat(estatePage.getTotalElements(), is(1L));
            assertThat(estatePage.getContent().get(0).getId(), is(estate.getId()));
        }

        @Test
        void updateEstate_whenValidRequest_thenReturnUpdatedEstate() {
            EstateResponse estate = estateTestSteps.createEstate();

            SaveEstateRequestDto request = new SaveEstateRequestDto();
            request.setCity(estate.getCity() + " Updated");
            request.setValue(estate.getValue() + 1000);
            request.setQuantity(estate.getQuantity() + 1);

            EstateResponse updateEstate = estateService.updateEstate(estate.getId(), request);

            assertThat(updateEstate, notNullValue());
            assertThat(updateEstate.getId(), is(estate.getId()));
            assertThat(updateEstate.getCity(), is(request.getCity()));
            assertThat(updateEstate.getValue(), is(request.getValue()));
            assertThat(updateEstate.getQuantity(), is(request.getQuantity()));
        }

        @Test
        void deleteEstate_whenExistingEstate_thenEstateDoesNotExistAnymore() {
            EstateResponse estate = estateTestSteps.createEstate();

            estateService.deleteEstate(estate.getId());

            Assertions.assertThrows(ResourceNotFoundException.class,
                    () -> estateService.getEstateResponse(estate.getId()));
        }

    }


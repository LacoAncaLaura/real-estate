package org.fasttrackit.realestate.teststeps;

import org.fasttrackit.realestate.service.EstateService;
import org.fasttrackit.realestate.transfer.estate.EstateResponse;
import org.fasttrackit.realestate.web.dto.SaveEstateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class EstateTestSteps {

    @Autowired
    public EstateService estateService;

    public EstateResponse createEstate() {
        SaveEstateRequestDto request = new SaveEstateRequestDto();
        request.setCity("Cluj");
        request.setValue(50000);
        request.setQuantity(1);
        EstateResponse estate = estateService.createEstate(request);
        assertThat(estate,notNullValue());
       assertThat(estate.getId(), greaterThan(0L));
       assertThat(estate.getCity(),is(request.getCity()));
       assertThat(estate.getValue(),is(request.getValue()));
       assertThat(estate.getQuantity(),is(request.getQuantity()));
       return estate;
    }
}

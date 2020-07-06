package org.exemple.realestate;


import org.exemple.realestate.persistance.EstateRepository;
import org.exemple.realestate.service.EstateService;
import org.exemple.realestate.domain.Estate;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class EstateServiceUnitTestes {
    private EstateService estateService;
    @Mock
    EstateRepository estateRepository;
    @Before
    public void setup(){
        estateService = new EstateService(estateRepository);
    }
    @Test
    public void createEstate_whenValidRequest_thenReturnCreatedEstate() {
       when(estateRepository.findById(anyLong())).thenReturn(Optional.empty());
      Estate estate = new Estate();
        estate.setType("PentHouse");
        estate.setOwner("Michael Jackson");
        estate.setCity("Bucharest");
        estate.setStreet("MainStreet");
        estate.setNumber(57);
        estate.setSize(150.5);
        estate.setValue(100000);
        estate.setQuantity(1);
        estate.setTax(10.5);
        when(estateService.getEstate(anyLong())).thenReturn(estate);
        when((estateRepository.save(any(Estate.class)))).thenReturn(null);

//        SaveEstateRequest request = new SaveEstateRequest();
//        request.setType(Collections.singletonList(estate.getType()));

        verify(estateRepository).findById(anyLong());
        verify(estateService).getEstate(anyLong());
    }
}

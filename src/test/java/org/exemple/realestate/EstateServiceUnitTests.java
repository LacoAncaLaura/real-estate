package org.exemple.realestate;

import org.exemple.realestate.domain.Estate;
import org.exemple.realestate.persistance.EstateRepository;
import org.exemple.realestate.service.EstateService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstateServiceUnitTests {
    @Mock
    EstateRepository estateRepository;

    @InjectMocks
    EstateService estateService;

    private Estate resultEstate1;

    private static final long ID1 = 1;

    @Before
    public void setUp(){
        resultEstate1 = Estate.builder().id(ID1).build();
        when(estateRepository.findById(ID1)).thenReturn(Optional.of(resultEstate1));
    }
    @Test
    public void test_getEstateById(){
        Estate result = estateService.getEstate(ID1);
        assertEquals(result.getId(), resultEstate1.getId());
    }

}

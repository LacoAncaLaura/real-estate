package org.fasttrackit.realestate.web;

import lombok.Builder;
import lombok.Data;
import org.fasttrackit.realestate.domain.Estate;
import org.fasttrackit.realestate.service.EstateService;
import org.fasttrackit.realestate.transfer.SaveEstateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Builder
@Data
@CrossOrigin
@RestController
@RequestMapping("/estates")
public class EstateController {

    private final EstateService estateService;

    @Autowired
    public EstateController(EstateService estateService) {
        this.estateService = estateService;
    }

    @PostMapping
    public ResponseEntity<Estate> createEstate(@Valid @RequestBody SaveEstateRequest request) {
        Estate estate = estateService.createEstate(request);
        return new ResponseEntity<>(estate, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Estate> updateEstate(@PathVariable long id, @Valid @RequestBody SaveEstateRequest request) {
        Estate estate = estateService.updateEstate(id, request);
        return new ResponseEntity<>(estate, HttpStatus.OK);
    }

}

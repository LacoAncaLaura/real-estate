package org.fasttrackit.realestate.web;

import lombok.Builder;
import lombok.Data;
import org.fasttrackit.realestate.domain.Estate;
import org.fasttrackit.realestate.transfer.estate.EstateResponse;
import org.fasttrackit.realestate.web.dto.GetEstateRequestDto;
import org.fasttrackit.realestate.web.dto.SaveEstateRequestDto;
import org.fasttrackit.realestate.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<EstateResponse> createEstate(@Valid @RequestBody SaveEstateRequestDto request) {
        EstateResponse estate = estateService.createEstate(request);
        return new ResponseEntity<>(estate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstateResponse> updateEstate(@PathVariable long id, @Valid @RequestBody SaveEstateRequestDto request) {
        EstateResponse estate = estateService.updateEstate(id, request);
        return new ResponseEntity<>(estate, HttpStatus.OK);
    }

    @GetMapping("/{id")
    public ResponseEntity<EstateResponse> getProduct(@PathVariable long id) {
        EstateResponse estate = estateService.getEstateResponse(id);

        return new ResponseEntity<>(estate, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<EstateResponse>>getProducts(@Valid GetEstateRequestDto request, Pageable pageable){

        Page<EstateResponse> products = estateService.getEstates(request,pageable);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstate(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
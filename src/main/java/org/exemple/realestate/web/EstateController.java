package org.exemple.realestate.web;

import lombok.Builder;
import lombok.Data;
import org.exemple.realestate.web.dto.GetEstateRequestDto;
import org.exemple.realestate.web.dto.SaveEstateRequestDto;
import org.exemple.realestate.persistance.entity.Estate;
import org.exemple.realestate.service.EstateService;
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
    public ResponseEntity<Estate> createEstate(@Valid @RequestBody SaveEstateRequestDto request) {
        Estate estate = estateService.createEstate(request);
        return new ResponseEntity<>(estate, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Estate> updateEstate(@PathVariable long id, @Valid @RequestBody SaveEstateRequestDto request) {
        Estate estate = estateService.updateEstate(id, request);
        return new ResponseEntity<>(estate, HttpStatus.OK);
    }

    @GetMapping("/{id")
    public ResponseEntity<Estate> getProduct(@PathVariable long id) {
        Estate estate = estateService.getEstate(id);

        return new ResponseEntity<>(estate, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<Estate>>getProducts(@Valid GetEstateRequestDto request, Pageable pageable){

        Page<Estate> products = estateService.getEstates(request,pageable);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstate(@PathVariable long id) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
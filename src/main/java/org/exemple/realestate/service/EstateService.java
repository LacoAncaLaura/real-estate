package org.exemple.realestate.service;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.exemple.realestate.exception.ResourceNotFoundException;
import org.exemple.realestate.web.dto.GetEstateRequestDto;
import org.exemple.realestate.web.dto.SaveEstateRequestDto;
import org.exemple.realestate.domain.Estate;
import org.exemple.realestate.persistance.EstateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Builder
@Data
@Slf4j
@Service

public class EstateService {
    private final static Logger LOGGER= LoggerFactory.getLogger(EstateService.class);
    private final EstateRepository estateRepository;

    @Autowired
    public EstateService(EstateRepository estateRepository ) {
        this.estateRepository = estateRepository;
    }

    public Estate createEstate(SaveEstateRequestDto request) {
        LOGGER.info("Creating new estate{}", request);
        Estate estate = Estate.builder()
                .type(request.getType())
                .city(request.getCity())
                .owner(request.getOwner())
                .street(request.getStreet())
                .number(request.getNumber())
                .size(request.getSize())
                .value(request.getValue())
                .quantity(request.getQuantity())
                .tax(request.getTax())
                .build();
        return estateRepository.save(estate);
    }

    public Estate getEstate(long id) {
        LOGGER.info("Find estate{}", id);
        return estateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estate" + id + "not found"));
    }

    public Page<Estate> getEstates(GetEstateRequestDto request, Pageable pageable) {
        if (request.getPartialCity()!= null && request.getMinimumQuantity() != null) {
            estateRepository.findByCityContainingAndQuantityGreaterThanEqual(request.getPartialCity(), request.getMinimumQuantity(), pageable);
        } else if (request.getPartialCity() != null) {
            return estateRepository.findByCityContaining(request.getPartialCity(), pageable);
        } else {
            return estateRepository.findAll(pageable);
        }return estateRepository.findAll(pageable);
    }

    public Estate updateEstate(long id, SaveEstateRequestDto request) {
        LOGGER.info("Updating estate{}: {}", id, request);
        Estate estate = getEstate(id);
        BeanUtils.copyProperties(request,estate);
        return estateRepository.save(estate);
    }

    public void deleteEstate(long id) {
        LOGGER.info("Deleting estate{}", id);
        estateRepository.deleteById(id);
    }
}

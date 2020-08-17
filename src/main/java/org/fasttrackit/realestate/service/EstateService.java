package org.fasttrackit.realestate.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fasttrackit.realestate.exception.ResourceNotFoundException;
import org.fasttrackit.realestate.transfer.estate.EstateResponse;
import org.fasttrackit.realestate.web.dto.GetEstateRequestDto;
import org.fasttrackit.realestate.web.dto.SaveEstateRequestDto;
import org.fasttrackit.realestate.domain.Estate;
import org.fasttrackit.realestate.persistance.EstateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Slf4j
@Service

public class EstateService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EstateService.class);
    private final EstateRepository estateRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EstateService(EstateRepository estateRepository, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.estateRepository = estateRepository;
    }

    public EstateResponse createEstate(SaveEstateRequestDto request) {
        LOGGER.info("Creating new estate{}", request);
        Estate estate = Estate.builder()
                .type(request.getType())
                .city(request.getCity())
                .street(request.getStreet())
                .bedroom(request.getBedroom())
                .value(request.getValue())
                .quantity(request.getQuantity())
                .bathroom(request.getBathroom())
                .imageUrl(request.getImageUrl())
                .build();
        Estate savedEstate = estateRepository.save(estate);
        return objectMapper.convertValue(savedEstate, EstateResponse.class);
    }

    public EstateResponse getEstateResponse(long id) {
        LOGGER.info("Find estate{}", id);

        Estate estate = getEstate(id);
        return objectMapper.convertValue(estate, EstateResponse.class);
    }

    public Estate getEstate(long id) {
        return estateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estate" + id + "not found"));
    }

    @Transactional
    public Page<EstateResponse> getEstates(GetEstateRequestDto request, Pageable pageable) {
     Page<Estate> page = estateRepository.findByOptionalCriteria(request.getPartialCity(),request.getMinimumQuantity(),pageable);
        List<EstateResponse>estateDto = new ArrayList<>();
        for (Estate estate: page.getContent()){
            EstateResponse estateResponse = new EstateResponse();
            estateResponse.setId(estate.getId());
            estateResponse.setCity(estate.getCity());
            estateResponse.setStreet(estate.getStreet());
            estateResponse.setType(estate.getType());
            estateResponse.setValue(estate.getValue());
            estateResponse.setQuantity(estate.getQuantity());
            estateResponse.setBedroom(estate.getBedroom());
            estateResponse.setBathroom(estate.getBathroom());
            estateResponse.setImageUrl(estate.getImageUrl());
            estateDto.add(estateResponse);
        }
        return new PageImpl<>(estateDto,pageable,page.getTotalElements());
    }

    public EstateResponse updateEstate(long id, SaveEstateRequestDto request) {

        LOGGER.info("Updating estate{}: {}", id, request);
        Estate estate = getEstate(id);
        BeanUtils.copyProperties(request, estate);
        Estate savedEstate = estateRepository.save(estate);

        return mapEstateResponse(savedEstate);
    }

    public void deleteEstate(long id) {
        LOGGER.info("Deleting estate{}", id);
        estateRepository.deleteById(id);
    }
    private EstateResponse mapEstateResponse(Estate estate){

        EstateResponse estateResponse = new EstateResponse();
        estateResponse.setId(estate.getId());
        estateResponse.setCity(estate.getCity());
        estateResponse.setStreet(estate.getStreet());
        estateResponse.setType(estate.getType());
        estateResponse.setValue(estate.getValue());
        estateResponse.setQuantity(estate.getQuantity());
        estateResponse.setBedroom(estate.getBedroom());
        estateResponse.setBathroom(estate.getBathroom());
        estateResponse.setImageUrl(estate.getImageUrl());
        return estateResponse;
    }
}

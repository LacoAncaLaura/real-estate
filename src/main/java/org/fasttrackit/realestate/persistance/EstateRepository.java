package org.fasttrackit.realestate.persistance;

import org.fasttrackit.realestate.domain.Estate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    Page<Estate> findByTypeContaining(String partialType, Pageable pageable);

    Page<Estate> findByTypeContainingAndQuantityGreaterThanEqual(String partialType, int minimumQuantity, Pageable pageable);


    @Query(value = "SELECT estate FROM Estate estate WHERE" +
            "(:partialType IS null OR estate.type = :partialCity) AND" +
            "(:minimumQuantity IS null OR estate.quantity >= :minimimQuantity)")
    Page<Estate> findByOptionalCriteria(String partialType, Integer minimumQuantity, Pageable pageable);
}
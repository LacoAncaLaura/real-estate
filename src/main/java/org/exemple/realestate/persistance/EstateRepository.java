package org.exemple.realestate.persistance;

import org.exemple.realestate.domain.Estate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    Page<Estate> findByCityContaining(String partialCity, Pageable pageable);

    Page<Estate> findByCityContainingAndQuantityGreaterThanEqual(String partialCity, int minimumQuantity, Pageable pageable);


    @Query(value = "SELECT estate FROM Estate estate WHERE" +
            "(:partialCity IS null OR estate.city = :partialCity) AND" +
            "(:minimumQuantity IS null OR estate.quantity >= :minimimQuantity)")
    Page<Estate> findByOptionalCriteria(String partialCity, Integer minimumQuantity, Pageable pageable);
}
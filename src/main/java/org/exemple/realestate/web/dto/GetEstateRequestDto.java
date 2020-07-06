package org.exemple.realestate.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetEstateRequestDto {
    private String partialCity;
    private Integer minimumQuantity;
}

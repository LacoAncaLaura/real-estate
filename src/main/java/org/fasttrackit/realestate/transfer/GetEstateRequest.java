package org.fasttrackit.realestate.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetEstateRequest {
    private String partialCity;
    private Integer minimumQuantity;
}

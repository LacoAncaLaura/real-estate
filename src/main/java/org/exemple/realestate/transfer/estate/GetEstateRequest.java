package org.exemple.realestate.transfer.estate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetEstateRequest {
    private String city;
    private Integer minimumQuantity;
}

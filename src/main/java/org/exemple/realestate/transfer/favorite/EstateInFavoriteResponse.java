package org.exemple.realestate.transfer.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstateInFavoriteResponse {
    private long id;
    private String type;
    private String city;
    private String street;
    private int value;
    private double tax;
}

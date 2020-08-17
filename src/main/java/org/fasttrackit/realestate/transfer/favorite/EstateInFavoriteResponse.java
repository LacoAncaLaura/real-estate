package org.fasttrackit.realestate.transfer.favorite;

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
    private String city;
    private int value;
    private int quantity;
    private String imageURL;
}

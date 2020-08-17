package org.fasttrackit.realestate.transfer.estate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstateResponse {
    private long id;
    private String city;
    private String street;
    private String type;
    private int value;
    private int quantity;
    private int bedroom;
    private int bathroom;
    private String imageUrl;

}

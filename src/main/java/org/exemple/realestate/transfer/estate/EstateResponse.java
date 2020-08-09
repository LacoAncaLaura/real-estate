package org.exemple.realestate.transfer.estate;

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
    private String type;
    private String city;
    private String street;
    private int number;
    private double size;
    private int value;
    private int quantity;
    private double tax;

}

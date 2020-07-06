package org.exemple.realestate.transfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class SaveEstateRequest {
    @NotNull
    private String type;
    @NotNull
    private String owner;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private int number;
    @NotNull
    private double size;
    @NotNull
    private int value;
    @Range(min=0)
    @NotNull
    private int quantity;
    @NotNull
    private double tax;

}

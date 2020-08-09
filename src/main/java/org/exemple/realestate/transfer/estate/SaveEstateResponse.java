package org.exemple.realestate.transfer.estate;

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
public class SaveEstateResponse {
    @NotNull
    private String type;
    private String City;

    @NotNull
    private int value;
    private double size;

    @Range(min=0)
    @NotNull
    private int quantity;
}

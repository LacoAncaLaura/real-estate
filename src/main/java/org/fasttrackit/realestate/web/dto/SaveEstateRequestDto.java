package org.fasttrackit.realestate.web.dto;
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

public class SaveEstateRequestDto {
    @NotNull
    private String city;
    private String street;
    private String type;

    @NotNull
    private int value;

    @Range(min=0)
    @NotNull
    private int quantity;
    private int bedroom;
    private int bathroom;
    private String imageUrl;



}

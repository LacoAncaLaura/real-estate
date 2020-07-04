package org.fasttrackit.realestate.transfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @NotNull
    private int quantity;
    @NotNull
    private double tax;

}
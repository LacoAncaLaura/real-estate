package org.exemple.realestate.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


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
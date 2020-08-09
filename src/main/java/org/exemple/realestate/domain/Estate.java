package org.exemple.realestate.domain;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


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
    //    This should be a reference to a Owner Obj/Table
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

    @ManyToMany(mappedBy = "estates")
    private Set<Favorite>favorites = new HashSet<>();
}
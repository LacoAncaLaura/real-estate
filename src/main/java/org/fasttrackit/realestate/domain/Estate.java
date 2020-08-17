package org.fasttrackit.realestate.domain;
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
    private String city;
    private String street;
    private String type;
    @NotNull
    private int value;

    @NotNull
    private int quantity;
    private int bedroom;
    private int bathroom;
    private String imageUrl;


    @ManyToMany(mappedBy = "estates")
    private Set<Favorite>favorites = new HashSet<>();
}
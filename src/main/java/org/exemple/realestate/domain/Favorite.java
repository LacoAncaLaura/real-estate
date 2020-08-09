package org.exemple.realestate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Favorite {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)

    private Set<Estate> estates = new HashSet<>();

    public void addToFavorites(Estate estate){
        estates.add(estate);
        estate.getFavorites().add(this);
    }
    public void removeFromFavorites(Estate estate){
        estates.remove(estate);
        estate.getFavorites().remove(this);
    }
}

package org.fasttrackit.realestate.persistance;

import org.fasttrackit.realestate.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository  extends JpaRepository<Favorite, Long> {
}

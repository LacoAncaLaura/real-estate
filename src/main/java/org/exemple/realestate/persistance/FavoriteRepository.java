package org.exemple.realestate.persistance;

import org.exemple.realestate.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository  extends JpaRepository<Favorite, Long> {
}

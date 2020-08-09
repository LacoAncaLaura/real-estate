package org.exemple.realestate.persistance;

import org.exemple.realestate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

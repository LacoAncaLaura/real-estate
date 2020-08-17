package org.fasttrackit.realestate.persistance;

import org.fasttrackit.realestate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

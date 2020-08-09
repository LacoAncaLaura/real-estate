package org.exemple.realestate.transfer.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.exemple.realestate.domain.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private User.UserRole role;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}

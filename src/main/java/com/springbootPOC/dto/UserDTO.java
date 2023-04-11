package com.springbootPOC.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.springbootPOC.dbo.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.springbootPOC.constants.RegExConstants.ALPHANUMERIC_WITH_DOT_AND_SPACE_REGEX;
import static com.springbootPOC.constants.RegExConstants.EMAIL_REGEX;
import static com.springbootPOC.constants.RegExConstants.PASSWORD_REGEX;

/**
 * @author mamta.t
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;
    @NotBlank
    @Pattern(regexp = ALPHANUMERIC_WITH_DOT_AND_SPACE_REGEX, message = "Invalid User Name format.")
    private String username;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Email
    @Pattern(regexp = EMAIL_REGEX, message = "Invalid Email format.")
    private String email;
    @NonNull
    @Pattern(regexp = PASSWORD_REGEX, message = "Invalid Password format.")
    private String password;
    private Set<RoleDTO> roles;
}

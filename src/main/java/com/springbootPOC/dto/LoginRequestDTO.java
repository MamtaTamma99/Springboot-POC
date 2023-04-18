package com.springbootPOC.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mamta.t
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull(message = "username shouldn't be null")
    private String email;

//    @Pattern(regexp = "\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\"",
//            message = "Password must be containing Minimum eight characters, at least one uppercase " +
//                    "letter, one lowercase letter, one number and one special character.")
    private String password;
}

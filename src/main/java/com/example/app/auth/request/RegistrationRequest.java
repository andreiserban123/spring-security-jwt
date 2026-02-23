package com.example.app.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(message = "VALIDATION.REGISTRATION.FIRST_NAME.NOT_BLANK")
    @Size(min = 5, max = 50, message = "VALIDATION.REGISTRATION.FIRST_NAME.SIZE")
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.FIRST_NAME.FORMAT"
    )
    private String firstName;

    @NotBlank(message = "VALIDATION.REGISTRATION.LAST_NAME.NOT_BLANK")
    @Size(min = 5, max = 50, message = "VALIDATION.REGISTRATION.LAST_NAME.SIZE")
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.LAST_NAME.FORMAT"
    )
    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private String confirmPassword;
}

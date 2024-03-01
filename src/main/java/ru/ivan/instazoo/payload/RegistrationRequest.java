package ru.ivan.instazoo.payload;

import lombok.Data;
import ru.ivan.instazoo.validators.annotations.CheckEmail;
import ru.ivan.instazoo.validators.annotations.CheckUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Field email is required")
    @Email(message = "Value don't look like email")
    @CheckEmail
    private String email;

    @NotBlank(message = "Field name is required")
    @Size(max = 20, message = "Field name must been less than 20 letters")
    @CheckUsername
    private String name;

    @NotBlank(message = "Field surname is required")
    @Size(max = 30, message = "Field surname must been less than 20 letters")
    @CheckUsername
    private String surname;

    @NotBlank(message = "Field username is required")
    @Size(max = 20, message = "Field username must been less than 20 letters")
    @CheckUsername
    private String username;

    @NotBlank(message = "Field password is required")
    @Size(min = 8, max = 30, message = "Field password must been less than 30 letters and greater 8 letters")
    private String password;

}
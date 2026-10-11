package tech.hemantsharma.urlshortner.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDto(
        @NotBlank(message = USERNAME_MESSAGE)
        @Size(min = 1, max = 255, message = USERNAME_MESSAGE)
        String username,

        @NotBlank(message = PASSWORD_MESSAGE)
        @Size(min = 1, max = 255, message = PASSWORD_MESSAGE)
        String password
) {
    private static final String USERNAME_MESSAGE = "Username must be more than 1 characters long";
    private static final String PASSWORD_MESSAGE = "Password must be more than 1 characters long";
}

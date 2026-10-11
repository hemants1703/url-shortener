package tech.hemantsharma.urlshortner.urlshortener.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ShortenUrlRequestDto(
        @NotBlank(message = ORIGINAL_URL_MESSAGE)
        @Size(min = 10, max = 255, message = ORIGINAL_URL_MESSAGE)
        @Pattern(regexp = "^(https?|ftp)://[^\\s]+$")
        String originalUrl
) {
    private static final String ORIGINAL_URL_MESSAGE = "URL must be valid and more than 10 characters";
}

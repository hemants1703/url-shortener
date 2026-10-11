package tech.hemantsharma.urlshortner.user.dtos;

import java.time.Instant;

public record UserResponseDto(
        int id,
        String username,
        long createdAt,
        long updatedAt
) {}

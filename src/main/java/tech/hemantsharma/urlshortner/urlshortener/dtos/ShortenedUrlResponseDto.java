package tech.hemantsharma.urlshortner.urlshortener.dtos;

public record ShortenedUrlResponseDto(
        String originalUrl,
        String shortCode
) {}

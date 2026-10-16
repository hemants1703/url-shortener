package tech.hemantsharma.urlshortener.urlshortener.dtos;

public record ShortenedUrlResponseDto(
        String originalUrl,
        String shortCode
) {}

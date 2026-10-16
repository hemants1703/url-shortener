package tech.hemantsharma.urlshortener.urlshortener;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.hemantsharma.urlshortener.urlshortener.dtos.ShortenUrlRequestDto;
import tech.hemantsharma.urlshortener.urlshortener.dtos.ShortenedUrlResponseDto;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectUserToOriginalUrl(@PathVariable String shortCode) {
        ShortenedUrlResponseDto url = urlService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.originalUrl()))
                .build();
    }

    @PostMapping("/api/v1/shorten-url")
    public ResponseEntity<String> shortenUrl(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ShortenUrlRequestDto shortenUrlRequestDto) {
        ShortenedUrlResponseDto shortenedUrlResponseDto = urlService.shortenUrl(userDetails.getUsername(), shortenUrlRequestDto.originalUrl());
        String shortenedUrl = "http://localhost:8080/" + shortenedUrlResponseDto.shortCode();
        return new ResponseEntity<>(shortenedUrl, HttpStatus.CREATED);
    }
}

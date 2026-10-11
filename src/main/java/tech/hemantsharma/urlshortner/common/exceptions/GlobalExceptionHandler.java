package tech.hemantsharma.urlshortner.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.hemantsharma.urlshortner.urlshortener.exceptions.ShortenedUrlNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ShortenedUrlNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleShortenedUrlNotFoundException(ShortenedUrlNotFoundException e) {
        Map<String, Object> map = new HashMap<>();

        map.put("status", HttpStatus.NOT_FOUND);
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}

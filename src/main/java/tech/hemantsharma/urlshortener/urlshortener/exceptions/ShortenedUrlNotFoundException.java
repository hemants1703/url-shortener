package tech.hemantsharma.urlshortener.urlshortener.exceptions;

public class ShortenedUrlNotFoundException extends RuntimeException {
    public ShortenedUrlNotFoundException(String message) {
        super(message);
    }
}

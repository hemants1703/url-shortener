package tech.hemantsharma.urlshortener.urlshortener;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.hemantsharma.urlshortener.urlshortener.dtos.ShortenedUrlResponseDto;
import tech.hemantsharma.urlshortener.urlshortener.exceptions.ShortCodeGenerationException;
import tech.hemantsharma.urlshortener.urlshortener.exceptions.ShortenedUrlNotFoundException;
import tech.hemantsharma.urlshortener.user.UserEntity;
import tech.hemantsharma.urlshortener.user.UserRepository;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UserRepository userRepository;
    private final UrlRepository urlRepository;

    private String generateCode() {
        final int SHORT_URL_LENGTH = 6;
        final SecureRandom RANDOM = new SecureRandom();
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortCode = new StringBuilder(SHORT_URL_LENGTH);

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            shortCode.append(CHARACTERS.charAt(index));
        }

        return shortCode.toString();
    }

    public ShortenedUrlResponseDto shortenUrl(String username, String originalUrl) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username for " + username + " not found."));

        int MAX_GENERATION_ATTEMPTS = 10;
        for (int attempt = 0; attempt < MAX_GENERATION_ATTEMPTS; attempt++) {
            String shortCode = generateCode();

            try {
                UrlEntity url = new UrlEntity(user, originalUrl, shortCode);
                urlRepository.saveAndFlush(url);

                return new ShortenedUrlResponseDto(originalUrl, shortCode);
            } catch (DataIntegrityViolationException _) {}
        }

        throw new ShortCodeGenerationException("Could not generate a unique short code.");
    }

    public ShortenedUrlResponseDto getOriginalUrl(String shortCode) throws ShortenedUrlNotFoundException {
        UrlEntity url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortenedUrlNotFoundException("Invalid Short URL"));

        return new ShortenedUrlResponseDto(url.getOriginalUrl(), url.getShortCode());
    }
}

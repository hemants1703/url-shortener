package tech.hemantsharma.urlshortner.urlshortener;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.hemantsharma.urlshortner.urlshortener.dtos.ShortenedUrlResponseDto;
import tech.hemantsharma.urlshortner.urlshortener.exceptions.ShortenedUrlNotFoundException;
import tech.hemantsharma.urlshortner.user.UserEntity;
import tech.hemantsharma.urlshortner.user.UserRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UserRepository userRepository;
    private final UrlRepository urlRepository;

    public ShortenedUrlResponseDto shortenUrl(String username, String originalUrl) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username for " + username + " not found."));

        final String BASE_URL = "http://localhost:8080/url";
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int SHORT_URL_LENGTH = 6;

        StringBuilder shortCode = new StringBuilder(SHORT_URL_LENGTH);
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            shortCode.append(CHARACTERS.charAt(index));
        }

        UrlEntity url = new UrlEntity(user, originalUrl, shortCode.toString());
        urlRepository.save(url);

        return new ShortenedUrlResponseDto(originalUrl, shortCode.toString());
    }

    public ShortenedUrlResponseDto getOriginalUrl(String shortCode) throws ShortenedUrlNotFoundException {
        UrlEntity url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortenedUrlNotFoundException("Invalid Short URL"));

        return new ShortenedUrlResponseDto(url.getOriginalUrl(), url.getShortCode());
    }
}

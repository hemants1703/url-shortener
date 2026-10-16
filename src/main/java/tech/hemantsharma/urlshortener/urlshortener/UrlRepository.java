package tech.hemantsharma.urlshortener.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Integer> {
    Optional<UrlEntity> findByShortCode(String shortCode);
}

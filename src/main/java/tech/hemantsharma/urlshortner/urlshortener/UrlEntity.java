package tech.hemantsharma.urlshortner.urlshortener;

import jakarta.persistence.*;
import lombok.*;
import tech.hemantsharma.urlshortner.user.UserEntity;

import java.time.Instant;

@Entity
@Table(name = "urls")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "original_url", updatable = false, nullable = false)
    private String originalUrl;

    @EqualsAndHashCode.Include
    @Column(name = "short_code", updatable = false, nullable = false, unique = true)
    private String shortCode;

    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "INTEGER")
    private long createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "INTEGER")
    private long updatedAt;

    UrlEntity(UserEntity user, String originalUrl, String shortCode) {
        this.user = user;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    @PreUpdate
    void beforeUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }

    @PrePersist
    void beforePersist() {
        this.createdAt = Instant.now().toEpochMilli();
        this.updatedAt = this.createdAt;
    }
}

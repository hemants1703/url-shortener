package tech.hemantsharma.urlshortener.user;

import jakarta.persistence.*;
import lombok.*;
import tech.hemantsharma.urlshortener.urlshortener.UrlEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<UrlEntity> urls = new ArrayList<>();

    @EqualsAndHashCode.Include
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @ToString.Exclude
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "INTEGER")
    private long createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "INTEGER")
    private long updatedAt;

    UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @PrePersist
    void beforePersist() {
        this.createdAt = Instant.now().toEpochMilli();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    void beforeUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }
}

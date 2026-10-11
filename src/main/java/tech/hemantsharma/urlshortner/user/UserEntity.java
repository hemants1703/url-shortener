package tech.hemantsharma.urlshortner.user;

import jakarta.persistence.*;
import lombok.*;
import tech.hemantsharma.urlshortner.urlshortener.UrlEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany(mappedBy = "user")
    private List<UrlEntity> urls = new ArrayList<>();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
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

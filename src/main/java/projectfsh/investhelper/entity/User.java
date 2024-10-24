package projectfsh.investhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updatedTimestamp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
    }

    public User(UUID userId,String username, String email, String password, Instant creationTimestamp, Instant updatedTimestamp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestamp = creationTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }

}

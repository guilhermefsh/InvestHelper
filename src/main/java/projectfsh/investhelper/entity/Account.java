package projectfsh.investhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "description")
    private String accountDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountStock> accountStocks;

    public Account() {
    }

    public Account(UUID accountId, String accountDescription) {
        this.accountId = accountId;
        this.accountDescription = accountDescription;
    }
}

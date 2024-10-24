package projectfsh.investhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_account_stocks")
@Getter
@Setter
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

    public AccountStock() {}

    public AccountStock(Account account, AccountStockId id, Integer quantity, Stock stock) {
        this.account = account;
        this.id = id;
        this.quantity = quantity;
        this.stock = stock;
    }
}

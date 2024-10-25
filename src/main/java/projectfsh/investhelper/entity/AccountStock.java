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
    private AccountStockId accountStockId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @MapsId("stockId")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

    public AccountStock() {}

    public AccountStock(AccountStockId id, Account account, Stock stock, Integer quantity) {
        this.accountStockId = id;
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
    }
}

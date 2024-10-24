package projectfsh.investhelper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountStockId {

    @Column(name="account_id")
    private String accountId;

    @Column(name="stock_id")
    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(String accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }
}

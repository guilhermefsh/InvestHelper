package projectfsh.investhelper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class AccountStockId {

    @Column(name="account_id")
    private UUID accountId;

    @Column(name="stock_id")
    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(UUID accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }
}

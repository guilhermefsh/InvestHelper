package projectfsh.investhelper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_stocks")
@Getter
@Setter
public class Stock {

    @Id
    private String stockId;

    @Column(name = "description")
    private String description;

    public Stock() {
    }

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }
}

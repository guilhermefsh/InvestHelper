package projectfsh.investhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_billing_address")
@Getter
@Setter
public class BillingAddress {

    @Id
    @Column(name="account_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name="account_id")
    private Account account;

    @Column(name="street")
    private String street;

    @Column(name="number")
    private Integer number;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, Integer number) {
        this.id = id;
        this.street = street;
        this.number = number;
    }
}
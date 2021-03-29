package ua.com.alevel.persistence.entity.balance;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "client_balances")
public class ClientBalance extends AbstractEntity {

    @ManyToOne
    private Client client;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    public ClientBalance() {
        super();
        this.balance = new BigDecimal("00.00");
    }
}

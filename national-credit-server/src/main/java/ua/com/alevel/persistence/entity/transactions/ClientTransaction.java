package ua.com.alevel.persistence.entity.transactions;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "client_transaction")
public class ClientTransaction extends AbstractEntity {

    @Digits(integer = 7, fraction = 2)
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "client_from")
    Client clientFrom;

    @ManyToOne
    @JoinColumn(name = "client_to")
    Client clientTo;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance_from_before", nullable = false)
    private BigDecimal balanceFromBefore;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance_from_after", nullable = false)
    private BigDecimal balanceFromAfter;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance_to_before", nullable = false)
    private BigDecimal balanceToBefore;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "balance_to_after", nullable = false)
    private BigDecimal balanceToAfter;

    public ClientTransaction() {
        super();
    }
}

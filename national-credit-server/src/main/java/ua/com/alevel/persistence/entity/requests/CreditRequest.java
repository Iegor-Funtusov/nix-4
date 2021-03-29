package ua.com.alevel.persistence.entity.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "credit_request")
public class CreditRequest extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    private Client client;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    public CreditRequest() {
        super();
    }
}

package ua.com.alevel.persistence.entity.product;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "unique_key", nullable = false, unique = true)
    private String uniqueKey;

    public ProductEntity() {
        super();
    }
}

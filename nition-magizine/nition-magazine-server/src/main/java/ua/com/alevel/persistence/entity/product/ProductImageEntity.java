package ua.com.alevel.persistence.entity.product;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product_images")
public class ProductImageEntity extends AbstractEntity {

    private String link;
    private Boolean home;

    @ManyToOne
    private ProductEntity productEntity;

    public ProductImageEntity() {
        super();
    }
}

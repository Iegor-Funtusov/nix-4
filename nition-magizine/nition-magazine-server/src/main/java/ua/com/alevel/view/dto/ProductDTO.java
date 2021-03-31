package ua.com.alevel.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.ToString;
import ua.com.alevel.persistence.entity.product.ProductEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends AbstractDto {

    private String name;
    private String description;
    private String uniqueKey;
    private BigDecimal price;
    private List<ProductImageDTO> images;

    public ProductDTO(ProductEntity entity) {
        super(entity.getId(), entity.getCreated(), entity.getUpdated());
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.uniqueKey = entity.getUniqueKey();
        this.images = Collections.emptyList();
    }

    public ProductDTO(ProductEntity entity, List<ProductImageDTO> images) {
        this(entity);
        this.images = images;
    }
}

package ua.com.alevel.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO extends AbstractDto {

    private String link;
    private Boolean home;

    public ProductImageDTO(ProductImageEntity entity) {
        super(entity.getId(), entity.getCreated(), entity.getUpdated());
        this.link = entity.getLink();
        this.home = entity.getHome();
    }
}

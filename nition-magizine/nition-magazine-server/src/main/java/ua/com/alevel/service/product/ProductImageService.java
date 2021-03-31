package ua.com.alevel.service.product;

import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;
import ua.com.alevel.service.CRUDService;

public interface ProductImageService extends CRUDService<ProductImageEntity> {

    ProductImageEntity findByProductEntityAndHomeIsTrue(ProductEntity productEntity);

    void deleteByProductEntityAndId(ProductEntity productEntity, Long id);
}

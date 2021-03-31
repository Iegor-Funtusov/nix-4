package ua.com.alevel.persistence.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface ProductImageEntityRepository extends AbstractRepository<ProductImageEntity> {

    Page<ProductImageEntity> findAllByProductEntity(ProductEntity productEntity, Pageable pageable);

    ProductImageEntity findByProductEntityAndHomeIsTrue(ProductEntity productEntity);

    void deleteByProductEntityAndId(ProductEntity productEntity, Long id);
}

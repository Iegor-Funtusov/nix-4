package ua.com.alevel.persistence.repository.product;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface ProductEntityRepository extends AbstractRepository<ProductEntity> {

    boolean existsByUniqueKey(String uniqueKey);
}

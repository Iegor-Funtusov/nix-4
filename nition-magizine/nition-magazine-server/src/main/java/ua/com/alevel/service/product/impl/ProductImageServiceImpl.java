package ua.com.alevel.service.product.impl;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestBadRequestException;
import ua.com.alevel.exception.status.RestConflictException;
import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;
import ua.com.alevel.persistence.repository.product.ProductImageEntityRepository;
import ua.com.alevel.service.product.ProductImageService;

import java.util.Collections;
import java.util.Map;

import static ua.com.alevel.util.ConverterUtil.PRODUCT_ENTITY;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageEntityRepository productImageEntityRepository;

    public ProductImageServiceImpl(ProductImageEntityRepository productImageEntityRepository) {
        this.productImageEntityRepository = productImageEntityRepository;
    }

    @Override
    public void create(ProductImageEntity e) {

    }

    @Override
    public void update(ProductImageEntity e) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductImageEntity find(Long id) {
        return findById(id);
    }

    @Override
    public Page<ProductImageEntity> find(Map<String, Object> params) {
        ProductEntity productEntity = (ProductEntity) params.get(PRODUCT_ENTITY);
        return productImageEntityRepository.findAllByProductEntity(productEntity, PageRequest.of(0, 100));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageEntity findByProductEntityAndHomeIsTrue(ProductEntity productEntity) {
        return productImageEntityRepository.findByProductEntityAndHomeIsTrue(productEntity);
    }

    @Override
    public void deleteByProductEntityAndId(ProductEntity productEntity, Long id) {
        ProductImageEntity productImageEntity = findById(id);
        if (ObjectUtils.notEqual(productEntity.getId(), productImageEntity.getProductEntity().getId())) {
            throw new RestConflictException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.CONFLICT.getReasonPhrase(), "product image not found")));
        }
        productImageEntityRepository.deleteByProductEntityAndId(productEntity, id);
    }

    private ProductImageEntity findById(Long id) {
        ProductImageEntity productImageEntity = productImageEntityRepository.findById(id).orElse(null);
        if (productImageEntity == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.BAD_REQUEST.getReasonPhrase(), "product image not found")));
        }
        return productImageEntity;
    }
}

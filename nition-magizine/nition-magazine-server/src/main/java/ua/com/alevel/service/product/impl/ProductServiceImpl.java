package ua.com.alevel.service.product.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestBadRequestException;
import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.repository.product.ProductEntityRepository;
import ua.com.alevel.service.product.ProductService;
import ua.com.alevel.view.container.PageDataRequestContainer;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static ua.com.alevel.util.ConverterUtil.PAGE_DATA_REQUEST;
import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductEntityRepository productEntityRepository;

    public ProductServiceImpl(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(ProductEntity product) {
        product.setUniqueKey(generateUniqueKey(UUID.randomUUID().toString()));
        productEntityRepository.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(ProductEntity product) {
        productEntityRepository.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void delete(Long id) {
        ProductEntity productEntity = findById(id);
        productEntityRepository.delete(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity find(Long id) {
        return findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductEntity> find(Map<String, Object> params) {
        PageDataRequestContainer data = (PageDataRequestContainer) params.get(PAGE_DATA_REQUEST);
        Sort sort = data.getOrder().equals(DEFAULT_ORDER_PARAM_VALUE) ? Sort.by(Sort.Order.desc(data.getSort())) : Sort.by(Sort.Order.asc(data.getSort()));
        return productEntityRepository.findAll(PageRequest.of(data.getPage() - 1, data.getSize(), sort));
    }

    private ProductEntity findById(Long id) {
        ProductEntity productEntity = productEntityRepository.findById(id).orElse(null);
        if (productEntity == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.BAD_REQUEST.getReasonPhrase(), "product not found")));
        }
        return productEntity;
    }

    private String generateUniqueKey(String key) {
        if (productEntityRepository.existsByUniqueKey(key)) {
            return generateUniqueKey(UUID.randomUUID().toString());
        }
        return key;
    }
}

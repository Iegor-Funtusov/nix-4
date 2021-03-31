package ua.com.alevel.view.facade.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.view.container.DataContainer;
import ua.com.alevel.view.container.PageDataContainer;
import ua.com.alevel.view.container.PageDataRequestContainer;
import ua.com.alevel.view.dto.ProductDTO;
import ua.com.alevel.view.dto.ProductImageDTO;
import ua.com.alevel.view.facade.ProductFacade;
import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;
import ua.com.alevel.service.product.ProductImageService;
import ua.com.alevel.service.product.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.com.alevel.util.ConverterUtil.PAGE_DATA_REQUEST;
import static ua.com.alevel.util.ConverterUtil.PRODUCT_ENTITY;

@Service
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductFacadeImpl(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @Override
    public void create(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productService.create(productEntity);
    }

    @Override
    public void update(ProductDTO dto, Long id) {
        ProductEntity productEntity = productService.find(id);
        productEntity.setName(dto.getName());
        productEntity.setDescription(dto.getDescription());
        productService.update(productEntity);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public DataContainer<ProductDTO> find(Long id) {
        ProductEntity productEntity = productService.find(id);
        Map<String, Object> map = new HashMap<>();
        map.put(PRODUCT_ENTITY, productEntity);
        Page<ProductImageEntity> productImageEntities = productImageService.find(map);
        List<ProductImageDTO> imageLinks = productImageEntities.get().map(ProductImageDTO::new).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(imageLinks)) {
            imageLinks = Collections.emptyList();
        }
        return new DataContainer<>(new ProductDTO(productEntity, imageLinks));
    }

    @Override
    public DataContainer<PageDataContainer<ProductDTO>> find(WebRequest webRequest) {
        Map<String, Object> map = new HashMap<>();
        PageDataRequestContainer pageDataRequestContainer = new PageDataRequestContainer(webRequest);
        map.put(PAGE_DATA_REQUEST, pageDataRequestContainer);
        Page<ProductEntity> productEntityPage = productService.find(map);
        PageDataContainer<ProductDTO> pageData = new PageDataContainer<>();
        pageData.setCurrentPage(pageDataRequestContainer.getPage());
        pageData.setPageSize(pageDataRequestContainer.getSize());
        pageData.setTotalElements(productEntityPage.getTotalElements());
        pageData.setTotalPages(productEntityPage.getTotalPages());
        if (CollectionUtils.isNotEmpty(productEntityPage.getContent())) {
            List<ProductDTO> list = new ArrayList<>();
            for (ProductEntity productEntity : productEntityPage.getContent()) {
                ProductDTO productDTO;
                ProductImageEntity productImageEntity = productImageService.findByProductEntityAndHomeIsTrue(productEntity);
                if (productImageEntity == null) {
                    productDTO = new ProductDTO(productEntity);
                } else {
                    productDTO = new ProductDTO(productEntity, Collections.singletonList(new ProductImageDTO(productImageEntity)));
                }
                list.add(productDTO);
            }
            pageData.setItems(list);
        }
        pageData.initPaginationState(pageDataRequestContainer.getPage());
        return new DataContainer<>(pageData);
    }

    @Override
    public void deleteImage(Long productId, Long productImageId) {
        ProductEntity productEntity = productService.find(productId);
        productImageService.deleteByProductEntityAndId(productEntity, productImageId);
    }
}

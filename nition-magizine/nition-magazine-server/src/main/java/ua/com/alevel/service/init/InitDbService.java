package ua.com.alevel.service.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.entity.product.ProductEntity;
import ua.com.alevel.persistence.entity.product.ProductImageEntity;
import ua.com.alevel.persistence.entity.user.AdminEntity;
import ua.com.alevel.persistence.repository.product.ProductEntityRepository;
import ua.com.alevel.persistence.repository.product.ProductImageEntityRepository;
import ua.com.alevel.persistence.repository.user.AdminEntityRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class InitDbService {

    @Value("${init.path}")
    private String patchesPath;

    @Value("${init.status}")
    private boolean initStatus;

    private final ObjectMapper mapper;
    private final AdminEntityRepository adminEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final ProductImageEntityRepository productImageEntityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public InitDbService(
            ObjectMapper mapper,
            AdminEntityRepository adminEntityRepository,
            ProductEntityRepository productEntityRepository,
            ProductImageEntityRepository productImageEntityRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mapper = mapper;
        this.adminEntityRepository = adminEntityRepository;
        this.productEntityRepository = productEntityRepository;
        this.productImageEntityRepository = productImageEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void init() throws IOException {
        if (initStatus) {
            ClassLoader cl = this.getClass().getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource[] resources = resolver.getResources(patchesPath);
            for (Resource resource: resources) {
                if (StringUtils.isNotBlank(resource.getFilename())) {
                    switch (resource.getFilename()) {
                        case "admins.json" : {
                            List<UserData> dataList = mapper.readValue(resource.getInputStream(), new TypeReference<List<UserData>>(){});
                            initUsers(dataList);
                        } break;
                        case "products.json" : {
                            List<ProductData> dataList = mapper.readValue(resource.getInputStream(), new TypeReference<List<ProductData>>(){});
                            initProducts(dataList);
                        } break;
                    }
                }
            }
        }
    }

    private void initUsers(List<UserData> dataList) {
        for (UserData userData : dataList) {
            AdminEntity admin = adminEntityRepository.findByEmail(userData.getEmail());
            if (admin == null) {
                admin = new AdminEntity();
                admin.setEmail(userData.getEmail());
                admin.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
                adminEntityRepository.save(admin);
            }
        }
    }

    private void initProducts(List<ProductData> dataList) {
        for (ProductData productData : dataList) {
            if (!productEntityRepository.existsByUniqueKey(productData.getProduct().getUniqueKey())) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setName(productData.getProduct().getName());
                productEntity.setDescription(productData.getProduct().getDescription());
                productEntity.setPrice(productData.getProduct().getPrice());
                productEntity.setUniqueKey(productData.getProduct().getUniqueKey());
                productEntity = productEntityRepository.save(productEntity);
                for (ProductImageData productImageData : productData.getLinks()) {
                    ProductImageEntity productImageEntity = new ProductImageEntity();
                    productImageEntity.setProductEntity(productEntity);
                    productImageEntity.setHome(productImageData.isHome());
                    productImageEntity.setLink(productImageData.getLink());
                    productImageEntityRepository.save(productImageEntity);
                }
            }
        }
    }

    private static class AbstractData { }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class UserData extends AbstractData {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class ProductData {
        private ProductItemData product;
        private List<ProductImageData> links;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class ProductItemData {
        private String name;
        private String description;
        private BigDecimal price;
        private String uniqueKey;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class ProductImageData {
        private String link;
        private boolean home;
    }
}

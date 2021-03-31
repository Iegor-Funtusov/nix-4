package ua.com.alevel.view.facade;

import ua.com.alevel.view.dto.ProductDTO;

public interface ProductFacade extends CRUDFacade<ProductDTO> {

    void deleteImage(Long productId, Long productImageId);
}

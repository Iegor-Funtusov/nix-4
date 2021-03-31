package ua.com.alevel.view.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.view.container.DataContainer;
import ua.com.alevel.view.container.PageDataContainer;
import ua.com.alevel.view.dto.ProductDTO;
import ua.com.alevel.view.facade.ProductFacade;

@RestController
@RequestMapping("/api/open/products")
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public ResponseEntity<DataContainer<PageDataContainer<ProductDTO>>> find(WebRequest request) {
        return ResponseEntity.ok(productFacade.find(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<ProductDTO>> find(@PathVariable Long id) {
        return ResponseEntity.ok(productFacade.find(id));
    }
}

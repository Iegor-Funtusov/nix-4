package ua.com.alevel.view.controller.auth;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.view.container.DataContainer;
import ua.com.alevel.view.container.PageDataContainer;
import ua.com.alevel.view.dto.ProductDTO;
import ua.com.alevel.view.facade.ProductFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductFacade productFacade;

    public AdminProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public String findAll(WebRequest request, Model model) {
        DataContainer<PageDataContainer<ProductDTO>> pageDataContainer = productFacade.find(request);
        String[] columnNames = new String[] { "#", "id", "created","updated", "name", "description", "details"};
        List<HeaderData> headerDataList = new ArrayList<>();
        for (String column : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(column);
            if (column.equals("#") || column.equals("details")) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(column);
                if (pageDataContainer.getData().getSort().equals(column)) {
                    data.setActive(true);
                    data.setOrder(pageDataContainer.getData().getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        model.addAttribute("pageData", pageDataContainer.getData());
        model.addAttribute("headerDataList", headerDataList);
        return "page/admin/product_all";
    }

    @PostMapping
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        return new ModelAndView("redirect:/admin/products", model);
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        model.addAttribute("product", productFacade.find(id));
        return "page/admin/product_details";
    }

    @GetMapping("/new")
    public String redirectToNewPage(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "page/admin/product_new";
    }

    @PostMapping("/new")
    public String newProduct(@ModelAttribute("product") ProductDTO dto) {
        productFacade.create(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productFacade.find(id).getData());
        return "page/admin/product_update";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute("product") ProductDTO dto, @RequestParam Long id) {
        System.out.println("dto = " + dto);
//        productFacade.update(dto, id);
        return "redirect:/admin/products/" + id;
    }

    @GetMapping("/{productId}/delete/image/{productImageId}/modal")
    public String showDeleteProductImageModal(@PathVariable Long productId, @PathVariable Long productImageId, Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("productImageId", productImageId);
        return "page/admin/product_update :: deleteImage";
    }

    @GetMapping("/{productId}/delete/image/{productImageId}")
    public String deleteProductImage(@PathVariable Long productId, @PathVariable Long productImageId, Model model) {
        productFacade.deleteImage(productId, productImageId);
        return "redirect:/admin/products/update/" + productId;
    }

    @Getter
    @Setter
    public static class HeaderData {

        private String headerName;
        private boolean active;
        private boolean sortable;
        private String sort;
        private String order;
    }
}

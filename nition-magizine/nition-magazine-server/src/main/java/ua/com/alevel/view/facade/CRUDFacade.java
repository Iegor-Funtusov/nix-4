package ua.com.alevel.view.facade;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.view.container.DataContainer;
import ua.com.alevel.view.container.PageDataContainer;
import ua.com.alevel.view.dto.AbstractDto;

public interface CRUDFacade<DTO extends AbstractDto> {

    void create(DTO dto);
    void update(DTO dto, Long id);
    void delete(Long id);
    DataContainer<DTO> find(Long id);
    DataContainer<PageDataContainer<DTO>> find(WebRequest webRequest);
}

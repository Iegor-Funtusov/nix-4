package ua.com.alevel.service.requests;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.CreditRequestDto;
import ua.com.alevel.dto.response.DataContainer;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.persistence.entity.requests.CreditRequest;

public interface CreditRequestService {

    void create(CreditRequestDto dto);
    void update(CreditRequestDto dto, Integer id);
    void delete(Integer id);
    DataContainer<CreditRequest> find(Integer id);
    PageContainer<CreditRequest> find(WebRequest webRequest);
}

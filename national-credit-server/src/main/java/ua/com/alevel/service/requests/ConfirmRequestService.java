package ua.com.alevel.service.requests;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.persistence.entity.requests.ConfirmRequest;

public interface ConfirmRequestService {

    void create(Integer id);
    PageContainer<ConfirmRequest> find(WebRequest webRequest);
}

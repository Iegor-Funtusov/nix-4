package ua.com.alevel.service.requests;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.request.CreditRequestDto;
import ua.com.alevel.dto.response.DataContainer;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.persistence.entity.requests.ProposalRequest;

public interface ProposalRequestService {

    void create(CreditRequestDto dto, Integer id);
    void update(CreditRequestDto dto, Integer id);
    void delete(Integer id);
    DataContainer<ProposalRequest> find(Integer id);
    PageContainer<ProposalRequest> find(WebRequest webRequest, Integer id);
}

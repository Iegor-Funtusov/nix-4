package ua.com.alevel.service.requests.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.config.security.SecurityAuthContext;
import ua.com.alevel.dto.request.CreditRequestDto;
import ua.com.alevel.dto.request.PageAndSizeDto;
import ua.com.alevel.dto.request.SortDto;
import ua.com.alevel.dto.response.DataContainer;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestBadRequestException;
import ua.com.alevel.exception.status.RestConflictException;
import ua.com.alevel.persistence.entity.requests.CreditRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.requests.CreditRequestRepository;
import ua.com.alevel.service.requests.CreditRequestService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.Collections;

@Service
public class CreditRequestServiceImpl implements CreditRequestService {

    private final CreditRequestRepository creditRequestRepository;
    private final SecurityAuthContext securityAuthContext;

    public CreditRequestServiceImpl(CreditRequestRepository creditRequestRepository, SecurityAuthContext securityAuthContext) {
        this.creditRequestRepository = creditRequestRepository;
        this.securityAuthContext = securityAuthContext;
    }

    @Override
    public void create(CreditRequestDto dto) {
        Client client = (Client) securityAuthContext.getCurrentUser();
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setClient(client);
        creditRequest.setSum(dto.getSum());
        creditRequestRepository.save(creditRequest);
    }

    @Override
    public void update(CreditRequestDto dto, Integer id) {
        CreditRequest creditRequest = findCreditRequestByOwner(id);
        creditRequest.setSum(dto.getSum());
        creditRequestRepository.save(creditRequest);
    }

    @Override
    public void delete(Integer id) {
        CreditRequest creditRequest = findCreditRequestByOwner(id);
        creditRequestRepository.delete(creditRequest);
    }

    @Override
    public DataContainer<CreditRequest> find(Integer id) {
        CreditRequest creditRequest = findCreditRequestByOwner(id);
        return new DataContainer<>(creditRequest);
    }

    @Override
    public PageContainer<CreditRequest> find(WebRequest webRequest) {
        Client client = (Client) securityAuthContext.getCurrentUser();
        boolean owner = false;
        try {
            owner = Boolean.parseBoolean(webRequest.getParameter("owner"));
        } catch (Exception e) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("owner", HttpStatus.BAD_REQUEST.getReasonPhrase(), "owner not found")));
        }
        PageAndSizeDto pageAndSizeModel = WebRequestUtil.generatePageAndSizeModel(webRequest);
        SortDto sortModel = WebRequestUtil.generateSortModel(webRequest);
        Page<CreditRequest> page;
        if (StringUtils.equalsIgnoreCase(sortModel.getOrder(), "asc")) {
            if (owner) {
                page = creditRequestRepository.findAllByClient(client, PageRequest.of(pageAndSizeModel.getPage()- 1, pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            } else {
                page = creditRequestRepository.findAllByClientNot(client, PageRequest.of(pageAndSizeModel.getPage()- 1, pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            }
        } else {
            if (owner) {
                page = creditRequestRepository.findAllByClient(client, PageRequest.of(pageAndSizeModel.getPage() - 1, pageAndSizeModel.getSize(), Sort.by("id").descending()));
            } else {
                page = creditRequestRepository.findAllByClientNot(client, PageRequest.of(pageAndSizeModel.getPage()- 1, pageAndSizeModel.getSize(), Sort.by("id").descending()));
            }
        }
        long totalElements = page.getTotalElements();
        if (totalElements == 0) {
            return new PageContainer<>(pageAndSizeModel.getPage(), pageAndSizeModel.getSize());
        }
        PageContainer<CreditRequest> container = new PageContainer<>();
        container.setCollectionSize(totalElements);
        container.setCurrentPage(pageAndSizeModel.getPage());
        container.setPageSize(pageAndSizeModel.getSize());
        container.setItems(page.getContent());
        return container;
    }

    private CreditRequest findCreditRequestByOwner(Integer id) {
        Client client = (Client) securityAuthContext.getCurrentUser();
        CreditRequest creditRequest = creditRequestRepository.findById(id).orElse(null);
        if (creditRequest == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.BAD_REQUEST.getReasonPhrase(), "request not found")));
        }
        Client owner = creditRequest.getClient();
        if (ObjectUtils.notEqual(owner.getId(), client.getId())) {
            throw new RestConflictException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.CONFLICT.getReasonPhrase(), "wee doesn't an owner")));
        }
        return creditRequest;
    }
}

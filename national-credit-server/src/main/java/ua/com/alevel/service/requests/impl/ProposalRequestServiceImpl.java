package ua.com.alevel.service.requests.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
import ua.com.alevel.persistence.entity.requests.ProposalRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.requests.CreditRequestRepository;
import ua.com.alevel.persistence.repository.requests.ProposalRequestRepository;
import ua.com.alevel.service.requests.ProposalRequestService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.Collections;

@Service
public class ProposalRequestServiceImpl implements ProposalRequestService {

    private final CreditRequestRepository creditRequestRepository;
    private final ProposalRequestRepository proposalRequestRepository;
    private final SecurityAuthContext securityAuthContext;

    public ProposalRequestServiceImpl(CreditRequestRepository creditRequestRepository, ProposalRequestRepository proposalRequestRepository, SecurityAuthContext securityAuthContext) {
        this.creditRequestRepository = creditRequestRepository;
        this.proposalRequestRepository = proposalRequestRepository;
        this.securityAuthContext = securityAuthContext;
    }

    @Override
    public void create(CreditRequestDto dto, Integer id) {
        CreditRequest creditRequest = creditRequestRepository.findById(id).orElse(null);
        if (creditRequest == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("creditRequest", HttpStatus.BAD_REQUEST.getReasonPhrase(), "creditRequest not found")));
        }
        Client client = (Client) securityAuthContext.getCurrentUser();
        ProposalRequest proposalRequest = new ProposalRequest();
        proposalRequest.setRequest(creditRequest);
        proposalRequest.setClient(client);
        proposalRequest.setSum(dto.getSum());
        proposalRequestRepository.save(proposalRequest);
    }

    @Override
    public void update(CreditRequestDto dto, Integer id) {
        ProposalRequest proposalRequest = findProposalRequestByOwner(id);
        proposalRequest.setSum(dto.getSum());
        proposalRequestRepository.save(proposalRequest);
    }

    @Override
    public void delete(Integer id) {
        ProposalRequest proposalRequest = findProposalRequestByOwner(id);
        proposalRequestRepository.delete(proposalRequest);
    }

    @Override
    public DataContainer<ProposalRequest> find(Integer id) {
        return new DataContainer<>(findProposalRequestByOwner(id));
    }

    @Override
    public PageContainer<ProposalRequest> find(WebRequest webRequest, Integer id) {
        Client client = (Client) securityAuthContext.getCurrentUser();
        CreditRequest creditRequest = creditRequestRepository.findById(id).orElse(null);
        if (creditRequest == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("creditRequest", HttpStatus.BAD_REQUEST.getReasonPhrase(), "creditRequest not found")));
        }
        boolean owner = false;
        try {
            owner = Boolean.parseBoolean(webRequest.getParameter("owner"));
        } catch (Exception e) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("owner", HttpStatus.BAD_REQUEST.getReasonPhrase(), "owner not found")));
        }
        PageAndSizeDto pageAndSizeModel = WebRequestUtil.generatePageAndSizeModel(webRequest);
        SortDto sortModel = WebRequestUtil.generateSortModel(webRequest);
        Page<ProposalRequest> page;
        if (StringUtils.equalsIgnoreCase(sortModel.getOrder(), "asc")) {
            if (owner) {
                page = proposalRequestRepository.findAllByClient(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            } else {
                page = proposalRequestRepository.findAllByRequest(creditRequest, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            }
        } else {
            if (owner) {
                page = proposalRequestRepository.findAllByClient(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").descending()));
            } else {
                page = proposalRequestRepository.findAllByRequest(creditRequest, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").descending()));
            }
        }
        long totalElements = page.getTotalElements();
        if (totalElements == 0) {
            return new PageContainer<>(pageAndSizeModel.getPage(), pageAndSizeModel.getSize());
        }
        PageContainer<ProposalRequest> container = new PageContainer<>();
        container.setCollectionSize(totalElements);
        container.setCurrentPage(pageAndSizeModel.getPage());
        container.setPageSize(pageAndSizeModel.getSize());
        container.setItems(page.getContent());
        return container;
    }

    private ProposalRequest findProposalRequestByOwner(Integer id) {
        Client client = (Client) securityAuthContext.getCurrentUser();
        ProposalRequest proposalRequest = proposalRequestRepository.findById(id).orElse(null);
        if (proposalRequest == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.BAD_REQUEST.getReasonPhrase(), "request not found")));
        }
        Client owner = proposalRequest.getClient();
        if (ObjectUtils.notEqual(owner.getId(), client.getId())) {
            throw new RestConflictException(Collections.singletonList(
                    new FieldErrorModel("id", HttpStatus.CONFLICT.getReasonPhrase(), "wee doesn't an owner")));
        }
        return proposalRequest;
    }
}

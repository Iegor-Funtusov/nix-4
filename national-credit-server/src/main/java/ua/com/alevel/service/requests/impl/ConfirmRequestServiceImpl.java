package ua.com.alevel.service.requests.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.config.security.SecurityAuthContext;
import ua.com.alevel.dto.request.PageAndSizeDto;
import ua.com.alevel.dto.request.SortDto;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestBadRequestException;
import ua.com.alevel.exception.status.RestConflictException;
import ua.com.alevel.persistence.entity.balance.ClientBalance;
import ua.com.alevel.persistence.entity.requests.ConfirmRequest;
import ua.com.alevel.persistence.entity.requests.ProposalRequest;
import ua.com.alevel.persistence.entity.transactions.ClientTransaction;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.balance.ClientBalanceRepository;
import ua.com.alevel.persistence.repository.requests.ConfirmRequestRepository;
import ua.com.alevel.persistence.repository.requests.ProposalRequestRepository;
import ua.com.alevel.persistence.repository.transactions.ClientTransactionRepository;
import ua.com.alevel.service.requests.ConfirmRequestService;
import ua.com.alevel.util.WebRequestUtil;

import java.math.BigDecimal;
import java.util.Collections;

@Service
public class ConfirmRequestServiceImpl implements ConfirmRequestService {

    private final SecurityAuthContext securityAuthContext;
    private final ProposalRequestRepository proposalRequestRepository;
    private final ConfirmRequestRepository confirmRequestRepository;
    private final ClientBalanceRepository clientBalanceRepository;
    private final ClientTransactionRepository clientTransactionRepository;

    public ConfirmRequestServiceImpl(
            SecurityAuthContext securityAuthContext,
            ProposalRequestRepository proposalRequestRepository,
            ConfirmRequestRepository confirmRequestRepository,
            ClientBalanceRepository clientBalanceRepository,
            ClientTransactionRepository clientTransactionRepository) {
        this.securityAuthContext = securityAuthContext;
        this.proposalRequestRepository = proposalRequestRepository;
        this.confirmRequestRepository = confirmRequestRepository;
        this.clientBalanceRepository = clientBalanceRepository;
        this.clientTransactionRepository = clientTransactionRepository;
    }

    @Override
    public void create(Integer id) {
        ProposalRequest proposalRequest = proposalRequestRepository.findById(id).orElse(null);
        if (proposalRequest == null) {
            throw new RestBadRequestException(Collections.singletonList(
                    new FieldErrorModel("proposal", HttpStatus.BAD_REQUEST.getReasonPhrase(), "proposal request not found")));
        }
        Client clientTo = (Client) securityAuthContext.getCurrentUser();
        Client clientFrom = proposalRequest.getClient();
        ClientBalance clientFromBalance = clientBalanceRepository.findByClient(clientFrom).get();
        ClientBalance clientToBalance = clientBalanceRepository.findByClient(clientTo).get();

        int res = clientFromBalance.getBalance().compareTo(proposalRequest.getSum());
        if (res < 0) {
            throw new RestConflictException(Collections.singletonList(
                    new FieldErrorModel("balance", HttpStatus.CONFLICT.getReasonPhrase(), "client balance is very small")));
        }
        ClientTransaction clientTransaction = new ClientTransaction();
        clientTransaction.setClientFrom(clientFrom);
        clientTransaction.setClientTo(clientTo);
        clientTransaction.setSum(proposalRequest.getSum());
        clientTransaction.setBalanceFromBefore(clientFromBalance.getBalance());
        clientTransaction.setBalanceToBefore(clientToBalance.getBalance());

        BigDecimal balanceFromAfter = clientFromBalance.getBalance().subtract(proposalRequest.getSum());
        BigDecimal balanceToAfter = clientToBalance.getBalance().add(proposalRequest.getSum());

        clientTransaction.setBalanceFromAfter(balanceFromAfter);
        clientTransaction.setBalanceToAfter(balanceToAfter);

        clientTransactionRepository.save(clientTransaction);

        clientFromBalance.setBalance(balanceFromAfter);
        clientBalanceRepository.save(clientFromBalance);

        clientToBalance.setBalance(balanceToAfter);
        clientBalanceRepository.save(clientToBalance);

        ConfirmRequest confirmRequest = new ConfirmRequest();
        confirmRequest.setRequest(proposalRequest);
        confirmRequest.setClientFrom(clientFrom);
        confirmRequest.setClientTo(clientTo);
        confirmRequestRepository.save(confirmRequest);
    }

    @Override
    public PageContainer<ConfirmRequest> find(WebRequest webRequest) {
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
        Page<ConfirmRequest> page;
        if (StringUtils.equalsIgnoreCase(sortModel.getOrder(), "asc")) {
            if (owner) {
                page = confirmRequestRepository.findByClientTo(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            } else {
                page = confirmRequestRepository.findByClientFrom(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").ascending()));
            }
        } else {
            if (owner) {
                page = confirmRequestRepository.findByClientTo(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").descending()));
            } else {
                page = confirmRequestRepository.findByClientFrom(client, PageRequest.of(pageAndSizeModel.getPage(), pageAndSizeModel.getSize(), Sort.by("id").descending()));
            }
        }
        long totalElements = page.getTotalElements();
        if (totalElements == 0) {
            return new PageContainer<>(pageAndSizeModel.getPage(), pageAndSizeModel.getSize());
        }
        PageContainer<ConfirmRequest> container = new PageContainer<>();
        container.setCollectionSize(totalElements);
        container.setCurrentPage(pageAndSizeModel.getPage());
        container.setPageSize(pageAndSizeModel.getSize());
        container.setItems(page.getContent());
        return container;
    }
}

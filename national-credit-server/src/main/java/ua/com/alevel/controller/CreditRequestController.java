package ua.com.alevel.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.dto.request.CreditRequestDto;
import ua.com.alevel.dto.response.DataContainer;
import ua.com.alevel.dto.response.PageContainer;
import ua.com.alevel.persistence.entity.requests.ConfirmRequest;
import ua.com.alevel.persistence.entity.requests.CreditRequest;
import ua.com.alevel.persistence.entity.requests.ProposalRequest;
import ua.com.alevel.service.requests.ConfirmRequestService;
import ua.com.alevel.service.requests.CreditRequestService;
import ua.com.alevel.service.requests.ProposalRequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/client/credit-request")
@Api(tags = "Credit Request endpoints API")
public class CreditRequestController {

    private final CreditRequestService creditRequestService;
    private final ProposalRequestService proposalRequestService;
    private final ConfirmRequestService confirmRequestService;

    public CreditRequestController(CreditRequestService creditRequestService, ProposalRequestService proposalRequestService, ConfirmRequestService confirmRequestService) {
        this.creditRequestService = creditRequestService;
        this.proposalRequestService = proposalRequestService;
        this.confirmRequestService = confirmRequestService;
    }

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody @Valid CreditRequestDto dto) {
        creditRequestService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> update(@RequestBody @Valid CreditRequestDto dto, @NotNull @PathVariable Integer id) {
        creditRequestService.update(dto, id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> delete(@NotNull @PathVariable Integer id) {
        creditRequestService.delete(id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<CreditRequest>> findOne(@NotNull @PathVariable Integer id) {
        return ResponseEntity.ok(creditRequestService.find(id));
    }

    @GetMapping
    public ResponseEntity<PageContainer<CreditRequest>> findAll(WebRequest request) {
        return ResponseEntity.ok(creditRequestService.find(request));
    }

    @PostMapping("/{id}/proposal")
    public ResponseEntity<DataContainer<Boolean>> createProposal(@RequestBody @Valid CreditRequestDto dto, @NotNull @PathVariable Integer id) {
        proposalRequestService.create(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }

    @PutMapping("/proposal/{id}")
    public ResponseEntity<DataContainer<Boolean>> updateProposal(@RequestBody @Valid CreditRequestDto dto, @NotNull @PathVariable Integer id) {
        proposalRequestService.update(dto, id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @DeleteMapping("/proposal/{id}")
    public ResponseEntity<DataContainer<Boolean>> deleteProposal(@NotNull @PathVariable Integer id) {
        proposalRequestService.delete(id);
        return ResponseEntity.ok(new DataContainer<>(true));
    }

    @GetMapping("/proposal/{id}")
    public ResponseEntity<DataContainer<ProposalRequest>> findAllProposals(@NotNull @PathVariable Integer id) {
        return ResponseEntity.ok(proposalRequestService.find(id));
    }

    @GetMapping("/{id}/proposal")
    public ResponseEntity<PageContainer<ProposalRequest>> findAllProposals(@NotNull @PathVariable Integer id, WebRequest webRequest) {
        return ResponseEntity.ok(proposalRequestService.find(webRequest, id));
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<DataContainer<Boolean>> confirmProposal(@NotNull @PathVariable Integer id) {
        confirmRequestService.create(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(true));
    }

    @GetMapping("/confirm")
    public ResponseEntity<PageContainer<ConfirmRequest>> findAllConfirmRequests(WebRequest webRequest) {
        return ResponseEntity.ok(confirmRequestService.find(webRequest));
    }
}

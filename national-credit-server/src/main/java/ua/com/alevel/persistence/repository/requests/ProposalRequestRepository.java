package ua.com.alevel.persistence.repository.requests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.requests.CreditRequest;
import ua.com.alevel.persistence.entity.requests.ProposalRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface ProposalRequestRepository extends AbstractRepository<ProposalRequest> {

    Page<ProposalRequest> findAllByClient(Client client, Pageable pageable);
    Page<ProposalRequest> findAllByRequest(CreditRequest creditRequest, Pageable pageable);
}

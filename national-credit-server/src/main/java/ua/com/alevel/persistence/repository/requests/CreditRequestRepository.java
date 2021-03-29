package ua.com.alevel.persistence.repository.requests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.requests.CreditRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface CreditRequestRepository extends AbstractRepository<CreditRequest> {

    Page<CreditRequest> findAllByClient(Client client, Pageable pageable);
    Page<CreditRequest> findAllByClientNot(Client client, Pageable pageable);
}

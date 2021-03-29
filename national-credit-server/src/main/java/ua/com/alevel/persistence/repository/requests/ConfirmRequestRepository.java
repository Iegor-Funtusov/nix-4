package ua.com.alevel.persistence.repository.requests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.requests.ConfirmRequest;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface ConfirmRequestRepository extends AbstractRepository<ConfirmRequest> {

    Page<ConfirmRequest> findByClientFrom(Client client, Pageable pageable);
    Page<ConfirmRequest> findByClientTo(Client client, Pageable pageable);
}

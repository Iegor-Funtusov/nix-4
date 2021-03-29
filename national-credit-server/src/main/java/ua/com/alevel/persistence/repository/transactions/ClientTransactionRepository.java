package ua.com.alevel.persistence.repository.transactions;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.transactions.ClientTransaction;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface ClientTransactionRepository extends AbstractRepository<ClientTransaction> { }

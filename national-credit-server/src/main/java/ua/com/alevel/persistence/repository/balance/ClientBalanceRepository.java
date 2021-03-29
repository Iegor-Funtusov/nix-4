package ua.com.alevel.persistence.repository.balance;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.balance.ClientBalance;
import ua.com.alevel.persistence.entity.user.Client;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface ClientBalanceRepository extends AbstractRepository<ClientBalance> {

    Optional<ClientBalance> findByClient(Client client);
}

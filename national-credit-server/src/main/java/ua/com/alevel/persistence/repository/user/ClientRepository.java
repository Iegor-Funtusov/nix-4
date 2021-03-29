package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Client;

@Repository
public interface ClientRepository extends AbstractUserRepository<Client> { }

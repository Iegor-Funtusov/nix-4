package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface AbstractUserRepository<U extends User> extends AbstractRepository<U> {

    Optional<U> findByEmail(String email);
}

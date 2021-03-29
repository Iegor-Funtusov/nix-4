package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.User;

@Repository
public interface UserRepository extends AbstractUserRepository<User> {

    boolean existsByEmail(String email);
}

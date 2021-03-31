package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.user.UserEntity;
import ua.com.alevel.persistence.repository.AbstractRepository;

@Repository
public interface UserEntityRepository<USER extends UserEntity> extends AbstractRepository<USER> {

    USER findByEmail(String email);
    boolean existsByEmail(String email);
}

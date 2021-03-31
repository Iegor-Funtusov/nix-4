package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.user.AdminEntity;

@Repository
public interface AdminEntityRepository extends UserEntityRepository<AdminEntity> { }

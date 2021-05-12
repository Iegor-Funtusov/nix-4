package ua.com.alevel.db_all_circle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.db_all_circle.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { }

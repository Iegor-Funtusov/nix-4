package ua.com.alevel.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends AbsRepo<User> {

    List<User> findAllByNameContains(@Param("name") String name);
}

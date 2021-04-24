package ua.com.alevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.entity.AbstractEntity;

@NoRepositoryBean
public interface AbsRepo<E extends AbstractEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> { }

package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import ua.com.alevel.persistence.entity.AbstractEntity;

import java.util.Map;

public interface CRUDService<ENTITY extends AbstractEntity> {

    void create(ENTITY e);
    void update(ENTITY e);
    void delete(Long id);
    ENTITY find(Long id);
    Page<ENTITY> find(Map<String, Object> params);
}

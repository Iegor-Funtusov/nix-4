package ua.com.alevel.service;

import ua.com.alevel.entity.AbstractEntity;

import java.util.List;

public interface AbsService<E extends AbstractEntity> {

    void create(E e);
    void delete(Long id);
    void update(E e);
    E read(Long id);
    List<E> read();
}

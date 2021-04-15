package ua.com.alevel.dao;

import ua.com.alevel.entity.AbsType;

import java.util.List;

public interface AbsDao<ENTITY extends AbsType, ID extends Number> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(ID id);
    List<ENTITY> find();
    ENTITY find(ID id);
}

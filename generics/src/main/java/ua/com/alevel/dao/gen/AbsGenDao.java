package ua.com.alevel.dao.gen;

import ua.com.alevel.dao.AbsDao;
import ua.com.alevel.entity.AbsType;

import java.util.List;

public interface AbsGenDao<ENTITY extends AbsType, ID extends Number> {

    List<ENTITY> find(Class<? extends AbsDao<ENTITY, ID>> dao);
    <DAO extends AbsDao<ENTITY, ID>> List<ENTITY> find(DAO dao);
}

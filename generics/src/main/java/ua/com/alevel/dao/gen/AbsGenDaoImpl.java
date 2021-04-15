package ua.com.alevel.dao.gen;

import ua.com.alevel.dao.AbsDao;
import ua.com.alevel.entity.AbsType;

import java.util.List;

public class AbsGenDaoImpl<ENTITY extends AbsType, ID extends Number> implements AbsGenDao<ENTITY, ID> {

    @Override
    public List<ENTITY> find(Class<? extends AbsDao<ENTITY, ID>> dao) {
        return null;
    }

    @Override
    public <DAO extends AbsDao<ENTITY, ID>> List<ENTITY> find(DAO dao) {
        return dao.find();
    }
}

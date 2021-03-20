package ua.com.alevel.dao;

import ua.com.alevel.data.AbstractData;

import java.util.List;

public interface AbstractDao<DATA extends AbstractData> {

    void create(DATA data);
    DATA read(int id);
    List<DATA> read();
    void update(DATA data);
    void delete(int id);
}

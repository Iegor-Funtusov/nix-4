package ua.com.alevel.service;

import ua.com.alevel.dto.AbsDto;
import ua.com.alevel.entity.AbsType;

import java.util.List;

public interface AbsService<ENTITY extends AbsType, DTO extends AbsDto, ID extends Number> {

    void create(DTO dto);
    void update(DTO dto);
    void delete(ID id);
    List<DTO> find();
    DTO find(ID id);
}

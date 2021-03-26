package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {

    private String id;
    private Date created;

    public BaseEntity() {
        this.created = new Date();
    }
}

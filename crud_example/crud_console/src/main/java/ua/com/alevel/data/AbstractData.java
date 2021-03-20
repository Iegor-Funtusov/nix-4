package ua.com.alevel.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractData {

    private int id;
    private Date created;

    public AbstractData() {
        this.created = new Date();
    }
}

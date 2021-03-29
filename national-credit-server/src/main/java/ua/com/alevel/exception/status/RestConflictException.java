package ua.com.alevel.exception.status;

import ua.com.alevel.exception.base.RestException;
import ua.com.alevel.exception.model.FieldErrorModel;

import java.util.List;

public class RestConflictException extends RestException {

    public RestConflictException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
}

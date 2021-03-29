package ua.com.alevel.exception.status;

import ua.com.alevel.exception.base.RestException;
import ua.com.alevel.exception.model.FieldErrorModel;

import java.util.List;

public class RestBadRequestException extends RestException {

    public RestBadRequestException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
}

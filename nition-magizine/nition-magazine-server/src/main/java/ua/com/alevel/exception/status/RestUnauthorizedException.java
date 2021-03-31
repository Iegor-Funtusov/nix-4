package ua.com.alevel.exception.status;

import ua.com.alevel.exception.base.RestException;
import ua.com.alevel.exception.model.FieldErrorModel;

import java.util.List;

public class RestUnauthorizedException extends RestException {

    public RestUnauthorizedException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
}

package ua.com.alevel.exception.base;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.exception.model.FieldErrorModel;

import java.util.List;

@Getter
@Setter
public class RestException extends RuntimeException {

    private List<FieldErrorModel> fieldErrors;

    public RestException(List<FieldErrorModel> fieldErrors){
        this.fieldErrors = fieldErrors;
    }
}

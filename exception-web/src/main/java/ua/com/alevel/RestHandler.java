package ua.com.alevel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestHandler {

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<DataContainer<ErrorModel>> error(RestException error) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new DataContainer<>(new ErrorModel(error)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> processBadRequestValidationError(ConstraintViolationException ex) {
        List<ErrorModel> errorModels = new ArrayList<>();
        ex.getConstraintViolations().forEach(constraint -> errorModels.add(new ErrorModel(
                constraint.getMessage())));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorListModel(errorModels));
    }
}

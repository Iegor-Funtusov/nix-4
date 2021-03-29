package ua.com.alevel.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ua.com.alevel.exception.model.ErrorResponseModel;
import ua.com.alevel.exception.model.FieldErrorModel;
import ua.com.alevel.exception.status.RestBadRequestException;
import ua.com.alevel.exception.status.RestConflictException;
import ua.com.alevel.exception.status.RestUnProcessableEntityException;
import ua.com.alevel.exception.status.RestUnauthorizedException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = RestBadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(RestBadRequestException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseModel(error));
    }

    @ExceptionHandler(value = RestUnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorized(RestUnauthorizedException error) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseModel(error));
    }

    @ExceptionHandler(value = RestConflictException.class)
    public ResponseEntity<Object> handleConflict(RestConflictException error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseModel(error));
    }

    @ExceptionHandler(value = RestUnProcessableEntityException.class)
    public ResponseEntity<Object> handleUnProcessableEntity(RestUnProcessableEntityException error){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponseModel(error));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> processBadRequestValidationError(ConstraintViolationException ex) {
        List<FieldErrorModel> errorModels = new ArrayList<>();
        ex.getConstraintViolations().forEach(constraint -> errorModels.add(new FieldErrorModel(
                constraint.getInvalidValue().toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                constraint.getMessage())));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseModel(errorModels));
    }
}

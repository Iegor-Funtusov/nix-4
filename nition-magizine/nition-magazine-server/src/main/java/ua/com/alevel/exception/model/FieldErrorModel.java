package ua.com.alevel.exception.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldErrorModel {

    private String field;
    private String error;
    private String message;
    private Object details;

    public FieldErrorModel(String field, String error, String message) {
        this.field = field;
        this.error = error;
        this.message = message;
    }

    public FieldErrorModel(String field, String error, String message, Object details) {
        this.field = field;
        this.error = error;
        this.message = message;
        this.details = details;
    }
}

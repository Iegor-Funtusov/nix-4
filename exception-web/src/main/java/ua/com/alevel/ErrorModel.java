package ua.com.alevel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorModel {

    private String message;

    public ErrorModel(RestException e) {
        this.message = e.getMessage();
    }
}

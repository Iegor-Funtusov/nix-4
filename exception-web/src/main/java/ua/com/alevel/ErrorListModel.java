package ua.com.alevel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorListModel {

    private List<ErrorModel> messages;
}

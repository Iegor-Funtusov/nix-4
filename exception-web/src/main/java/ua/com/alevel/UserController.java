package ua.com.alevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> create(@RequestBody @Valid UserDto user) {
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(Boolean.TRUE));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> update(@PathVariable Integer id, @RequestBody UserDto user) {
        userService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(new DataContainer<>(Boolean.TRUE));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataContainer<>(Boolean.TRUE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<User>> findById(@PathVariable @Min(message = "id must be more then zero", value = 1) Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(new DataContainer<>(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<DataContainer<List<User>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new DataContainer<>(userService.findAll()));
    }
}

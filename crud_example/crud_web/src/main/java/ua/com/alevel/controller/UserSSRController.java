package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserSSRController {

    private final UserService userService;

    public UserSSRController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/user_details";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/user_new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/user_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam Integer id) {
        user.setId(id);
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }
}

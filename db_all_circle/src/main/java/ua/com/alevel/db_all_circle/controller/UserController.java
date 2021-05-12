package ua.com.alevel.db_all_circle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.db_all_circle.dao.UserDao;
import ua.com.alevel.db_all_circle.entity.User;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
//    @Qualifier("jdbcUserDao")
//    @Qualifier("hibernateUserDao")
//    @Qualifier("entityManagerUserDao")
    @Qualifier("springDataUserDao")
    private UserDao userDao;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userDao.find());
        return "users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userDao.find(id));
        return "user_details";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user_new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userDao.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userDao.find(id));
        return "user_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam Integer id) {
        user.setId(id);
        userDao.update(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        userDao.delete(id);
        return "redirect:/users";
    }
}

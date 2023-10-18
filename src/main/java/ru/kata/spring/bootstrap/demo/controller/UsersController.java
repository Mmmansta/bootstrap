package ru.kata.spring.bootstrap.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.bootstrap.demo.model.Role;
import ru.kata.spring.bootstrap.demo.model.User;
import ru.kata.spring.bootstrap.demo.service.UserService;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")

public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }


    @GetMapping()
    public String getUsers(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("authentication", authentication.getPrincipal());
        model.addAttribute("user", new User(List.of(new Role("ROLE_USER"), new Role("ROLE_ADMIN")), "", "", 0, ""));
        model.addAttribute("roles", List.of("ROLE_ADMIN", "ROLE_USER"));
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("user") @Valid User user,BindingResult bindingResult ) {
        if (bindingResult.hasErrors()){
           return "new";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model,@PathVariable("id") int id){
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute ("user") User user, @PathVariable ("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.domain.User;
import com.olehpodolin.springmvcwithsecurity.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model){

        model.addAttribute("users", userService.listAll());

        return "user/users";
    }

    @GetMapping("/users/{id}/show")
    public String getUser(@PathVariable Long id, Model model){

        model.addAttribute("user", userService.getById(id));

        return "user/showuser";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        model.addAttribute("user", userService.getById(id));

        return "user/userform";
    }

    @GetMapping("/users/new")
    public String newUser(Model model){

        model.addAttribute("user", new User());

        return "user/userform";
    }

    @PostMapping("/user")
    public String saveOrUpdate(User user){

        User savedUser = userService.saveOrUpdate(user);

        return "redirect:/users/" + savedUser.getId() + "/show" ;
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Long id){

        userService.delete(id);

        return "redirect:/users";
    }
}

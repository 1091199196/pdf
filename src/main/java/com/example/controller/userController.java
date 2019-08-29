package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userController")
public class userController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public List<User> getAllUser() {
        List<User> allUser = userService.getAllUser();
        System.out.println(allUser);
        return allUser;
    }
}

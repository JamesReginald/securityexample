package com.jimmiereggievanvliet.springSecurityfull.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Jim van Vliet"),
            new User(2, "Debbie van Vliet"),
            new User(3, "Lisa van Vliet")
    );

    @GetMapping(path = "{userId}")
    public User getUser (@PathVariable("userId") Integer userId){
        return USERS.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + userId + " does not exist"));
    }
}

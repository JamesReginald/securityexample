package com.jimmiereggievanvliet.springSecurityfull.user;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Jim van Vliet"),
            new User(2, "Debbie van Vliet"),
            new User(3, "Lisa van Vliet")
    );
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ADMINTRAINEE')")
    public List<User> getAllUsers(){
        return USERS;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void addUser(@RequestBody User user){
        System.out.println(user);
    }
    @PreAuthorize("hasAuthority('user:write')")
    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        System.out.println(userId);
    }
    @PutMapping(path="{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user){
        System.out.println(String.format("%s -- %s",userId, user));

    }
}

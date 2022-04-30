package com.senutech.security.app.controller;

import com.senutech.security.app.service.UserManagementService;
import com.senutech.security.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserMgtController {

    @Autowired
    UserManagementService userManagementService;

    @RequestMapping("/s/users")
    public User[] getUserList() {
        return userManagementService.getUserList().toArray(new User[0]);
    }

    @RequestMapping(value = "/s/users/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userManagementService.addUserList(user);
    }

    @RequestMapping("/s/users/{Username}")
    public String getUserId(@PathVariable("Username") String username){
        return userManagementService.getUerId(username);
    }
}

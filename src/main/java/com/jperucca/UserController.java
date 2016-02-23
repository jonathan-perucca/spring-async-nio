package com.jperucca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET, value = "/users")
    public DeferredResult getPublicUsers() {
        return userService.getPublicUsers();
    }
}

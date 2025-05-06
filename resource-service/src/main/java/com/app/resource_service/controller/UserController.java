package com.app.resource_service.controller;

import com.app.resource_service.dto.Coordinates;
import com.app.resource_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/nearby")
    public List<?> findNearbyResources(
            @RequestBody Coordinates coordinates,
            @RequestParam String type) {


        return userService.findNearByResource(coordinates, type);
    }
}

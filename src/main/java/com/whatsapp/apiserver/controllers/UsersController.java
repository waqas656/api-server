package com.whatsapp.apiserver.controllers;

import com.whatsapp.apiserver.models.dto.UserDto;
import com.whatsapp.apiserver.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    @ApiOperation(value = "Send an attachment")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto user = userService.saveUser(userDto);
        return ResponseEntity.ok(user);
    }

}

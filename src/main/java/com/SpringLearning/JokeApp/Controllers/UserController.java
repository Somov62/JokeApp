package com.SpringLearning.JokeApp.Controllers;


import com.SpringLearning.JokeApp.Models.UserModel;
import com.SpringLearning.JokeApp.Services.RateJokeService;
import com.SpringLearning.JokeApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> registration(@RequestBody UserModel user) {
        var newUser = userService.registration(user);
        return ResponseEntity.ok(newUser);
    }


}

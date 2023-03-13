package com.SpringLearning.JokeApp.Controllers;

import com.SpringLearning.JokeApp.Exceptions.UserAlreadyExistsException;
import com.SpringLearning.JokeApp.Exceptions.UserNotFoundException;
import com.SpringLearning.JokeApp.Models.UserModel;
import com.SpringLearning.JokeApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{login}")
    public ResponseEntity login(@PathVariable String login) {

        try {
            UserModel newUser = userService.login(login);
            return ResponseEntity.ok(newUser);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity registration(@RequestBody UserModel user) {

        try {
             UserModel newUser = userService.registration(user);
            return ResponseEntity.ok(newUser);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

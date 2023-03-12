package com.SpringLearning.JokeApp.Controllers;

import com.SpringLearning.JokeApp.Exceptions.UserNotFoundException;
import com.SpringLearning.JokeApp.Models.JokeModel;
import com.SpringLearning.JokeApp.Services.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jokes")
public class JokeController {

    @Autowired
    private JokeService jokeService;

    @PostMapping
    public ResponseEntity publishJoke(@RequestBody JokeModel joke) {
        try {
            JokeModel newJoke = jokeService.publishJoke(joke);
            return  ResponseEntity.ok(newJoke);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<JokeModel>> getJokesPage(
            @RequestParam(name = "lastReceivedSortedFieldValue", defaultValue = "" + Long.MAX_VALUE)
                Long lastReceivedSortedFieldValue,
            @RequestParam(name = "sortByField", defaultValue = "createdUnixTime")
                String sortByField)
    {
        return  ResponseEntity.ok(jokeService.getJokes(lastReceivedSortedFieldValue, sortByField));
    }


}

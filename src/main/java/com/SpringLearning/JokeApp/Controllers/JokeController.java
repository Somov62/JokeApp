package com.SpringLearning.JokeApp.Controllers;

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
    public ResponseEntity<JokeModel> publishJoke(@RequestBody JokeModel joke) {
        JokeModel newJoke = jokeService.publishJoke(joke);
        return  ResponseEntity.ok(newJoke);
    }

    @GetMapping
    public ResponseEntity<List<JokeModel>> getJokesPage(@RequestParam Optional<Long> lastReceivedModelId, @RequestParam Optional<String> sortByField) {
        if (lastReceivedModelId.isEmpty())
            return  ResponseEntity.ok(jokeService.getJokes(0));
        return  ResponseEntity.ok(jokeService.getJokes(lastReceivedModelId.get()));
    }


}

package com.SpringLearning.JokeApp.Controllers;

import com.SpringLearning.JokeApp.Services.RateJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private RateJokeService rateService;


    @GetMapping("/{userId}/setLike")
    public ResponseEntity<Long> setLike(@PathVariable long userId, @RequestParam long jokeId ) {
        long countLikes = rateService.setLike(userId, jokeId);
        return  ResponseEntity.ok(countLikes);
    }

    @GetMapping("/{userId}/setDislike")
    public ResponseEntity<Long> setDislike(@PathVariable long userId, @RequestParam long jokeId ) {
        long countLikes = rateService.setDislike(userId, jokeId);
        return  ResponseEntity.ok(countLikes);
    }
}

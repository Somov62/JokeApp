package com.SpringLearning.JokeApp.Services;

import com.SpringLearning.JokeApp.Entities.JokeEntity;
import com.SpringLearning.JokeApp.Entities.UserEntity;
import com.SpringLearning.JokeApp.Repos.JokeRepo;
import com.SpringLearning.JokeApp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateJokeService {

    @Autowired
    private JokeRepo jokeRepo;

    @Autowired
    private UserRepo userRepo;

    public long setLike (long userId, long jokeId) {
        JokeEntity joke = jokeRepo.findById(jokeId).orElseThrow();
        UserEntity user = userRepo.findById(userId).orElseThrow();
        if (joke.getUsersDisliked().contains(user))
            joke.getUsersDisliked().remove(user);
        if (joke.getUsersLiked().contains(user))
            joke.getUsersLiked().remove(user);
        else
            joke.getUsersLiked().add(user);
        jokeRepo.save(joke);
        return joke.getUsersLiked().stream().count();
    }

    public long setDislike (long userId, long jokeId) {
        JokeEntity joke = jokeRepo.findById(jokeId).orElseThrow();
        UserEntity user = userRepo.findById(userId).orElseThrow();
        if (joke.getUsersLiked().contains(user))
            joke.getUsersLiked().remove(user);
        if (joke.getUsersDisliked().contains(user))
            joke.getUsersDisliked().remove(user);
        else
            joke.getUsersDisliked().add(user);
        jokeRepo.save(joke);
        return joke.getUsersDisliked().stream().count();
    }
}

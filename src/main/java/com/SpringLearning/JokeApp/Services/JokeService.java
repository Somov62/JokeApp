package com.SpringLearning.JokeApp.Services;

import com.SpringLearning.JokeApp.Entities.JokeEntity;
import com.SpringLearning.JokeApp.Entities.UserEntity;
import com.SpringLearning.JokeApp.Models.JokeModel;
import com.SpringLearning.JokeApp.Repos.JokeRepo;
import com.SpringLearning.JokeApp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

@Service
public class JokeService {

    @Autowired
    private JokeRepo jokeRepo;
    @Autowired
    private UserRepo userRepo;
    public JokeModel publishJoke(JokeModel joke) {

        JokeEntity entity = new JokeEntity();
        entity.setId(joke.getId());
        entity.setJoke(joke.getJoke());
        entity.setCreatedUnixTime(System.currentTimeMillis() / 1000L);

        UserEntity user = userRepo.findById(joke.getUser().getId()).orElseThrow();
        entity.setUser(user);

        jokeRepo.save(entity);
        return new JokeModel(entity);
    }

    public List<JokeModel> getJokes(long createdUnixTime) {

        List<JokeEntity> entities = jokeRepo.findAll();
        entities = entities.stream().filter(entity -> entity.getCreatedUnixTime() < createdUnixTime).toList();
        entities = entities.stream().sorted(reverseOrder(Comparator.comparing(JokeEntity::getCreatedUnixTime))).toList();
        entities = entities.stream().limit(2).toList();

        return  entities.stream().map(e -> new JokeModel(e)).toList();
    }

}

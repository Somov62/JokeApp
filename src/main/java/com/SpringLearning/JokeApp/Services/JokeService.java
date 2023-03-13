package com.SpringLearning.JokeApp.Services;

import com.SpringLearning.JokeApp.Entities.JokeEntity;
import com.SpringLearning.JokeApp.Exceptions.UserNotFoundException;
import com.SpringLearning.JokeApp.Models.JokeModel;
import com.SpringLearning.JokeApp.Repos.JokeRepo;
import com.SpringLearning.JokeApp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class JokeService {

    @Autowired
    private JokeRepo jokeRepo;
    @Autowired
    private UserRepo userRepo;
    public JokeModel publishJoke(JokeModel joke) throws UserNotFoundException {

        if (joke.getJoke() == null || joke.getJoke().trim().isEmpty())
            throw new IllegalArgumentException("Анекдот не может быть пустым");
        if (joke.getUser() == null)
            throw new IllegalArgumentException("Укажите автора анекдота");

        var user = userRepo.findById(joke.getUser().getId());
        if (user.isEmpty())
            throw new UserNotFoundException("Автор не найден");


        JokeEntity entity = new JokeEntity();
        entity.setJoke(joke.getJoke());
        entity.setCreatedUnixTime(System.currentTimeMillis() / 1000L);
        entity.setUser(user.get());

        jokeRepo.save(entity);
        return new JokeModel(entity);
    }

    public List<JokeModel> getJokes(Long lastReceivedSortedFieldValue, String sortByField) {
        Stream<JokeEntity> entities = jokeRepo.findAll().stream();

        switch (sortByField.toLowerCase()) {
            case "likescount" -> {
                long likesCount = lastReceivedSortedFieldValue;
                entities = entities.filter(entity -> entity.getCountLikes() < likesCount);
                entities = entities.sorted(Collections.reverseOrder(Comparator.comparing(JokeEntity::getCountLikes)));
            }
            case "dislikescount" -> {
                long dislikesCount = lastReceivedSortedFieldValue;
                entities = entities.filter(entity -> entity.getCountDislikes() < dislikesCount);
                entities = entities.sorted(Collections.reverseOrder(Comparator.comparing(JokeEntity::getCountDislikes)));
            }
            default -> {
                long createdUnixTime = lastReceivedSortedFieldValue;
                entities = entities.filter(entity -> entity.getCreatedUnixTime() < createdUnixTime);
                entities = entities.sorted(Collections.reverseOrder(Comparator.comparing(JokeEntity::getCreatedUnixTime)));
            }
        }

        entities = entities.limit(10);
        return  entities.map(JokeModel::new).toList();
    }

}

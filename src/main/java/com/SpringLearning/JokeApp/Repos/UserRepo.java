package com.SpringLearning.JokeApp.Repos;

import com.SpringLearning.JokeApp.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}

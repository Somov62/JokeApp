package com.SpringLearning.JokeApp.Services;

import com.SpringLearning.JokeApp.Entities.UserEntity;
import com.SpringLearning.JokeApp.Models.UserModel;
import com.SpringLearning.JokeApp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserModel registration(UserModel user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLogin(user.getLogin());
        entity.setLastName(user.getLastName());

        userRepo.save(entity);
        return new UserModel(entity);
    }


}

package com.SpringLearning.JokeApp.Services;

import com.SpringLearning.JokeApp.Entities.UserEntity;
import com.SpringLearning.JokeApp.Exceptions.UserAlreadyExistsException;
import com.SpringLearning.JokeApp.Exceptions.UserNotFoundException;
import com.SpringLearning.JokeApp.Models.UserModel;
import com.SpringLearning.JokeApp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserModel login(String login) throws UserNotFoundException {
        if (login == null || login.trim().isEmpty())
            throw new IllegalArgumentException("Логин не может быть пустым");

        UserEntity entity = userRepo.findByLogin(login);
        if (entity == null)
            throw new UserNotFoundException("Пользователь не найден");

        return  new UserModel(entity);
    }


    public UserModel registration(UserModel user) throws UserAlreadyExistsException {

        if (user.getLogin() == null || user.getLogin().trim().isEmpty())
            throw new IllegalArgumentException("Логин не может быть пустым");

        if (userRepo.findByLogin(user.getLogin()) != null)
            throw new UserAlreadyExistsException("Пользователь с именем " + user.getLogin() + " уже существует");

        UserEntity entity = new UserEntity();
        entity.setLogin(user.getLogin());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());

        userRepo.save(entity);
        return new UserModel(entity);
    }


}

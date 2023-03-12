package com.SpringLearning.JokeApp.Models;

import com.SpringLearning.JokeApp.Entities.UserEntity;

public class UserModel {

    private long id;
    private String login;
    private String firstName;
    private String lastName;

    public UserModel() {
    }

    public UserModel(UserEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.login = entity.getLogin();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}

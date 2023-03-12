package com.SpringLearning.JokeApp.Models;

import com.SpringLearning.JokeApp.Entities.JokeEntity;

public class JokeModel {

    private long id;
    private String joke;
    private long createdUnixTime;
    private UserModel user;

    public JokeModel() {

    }

    public JokeModel(JokeEntity entity) {
        this.id = entity.getId();
        this.joke = entity.getJoke();
        this.createdUnixTime = entity.getCreatedUnixTime();
        this.user = new UserModel(entity.getUser());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public long getCreatedUnixTime() {
        return createdUnixTime;
    }

    public void setCreatedUnixTime(long createdUnixTime) {
        this.createdUnixTime = createdUnixTime;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}

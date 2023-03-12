package com.SpringLearning.JokeApp.Models;

import com.SpringLearning.JokeApp.Entities.JokeEntity;

import java.util.*;

public class JokeModel {

    private long id;
    private String joke;
    private long createdUnixTime;
    private UserModel user;
    private List<UserModel> usersLiked;
    private List<UserModel> usersDisliked;

    public JokeModel() {

    }

    public JokeModel(JokeEntity entity) {
        this.id = entity.getId();
        this.joke = entity.getJoke();
        this.createdUnixTime = entity.getCreatedUnixTime();
        this.user = new UserModel(entity.getUser());
        if (entity.getUsersLiked() != null)
            this.usersLiked = entity.getUsersLiked().stream().map(e -> new UserModel(e)).toList();
        else this.usersLiked = new ArrayList<>();

        if (entity.getUsersDisliked() != null)
            this.usersDisliked = entity.getUsersDisliked().stream().map(e -> new UserModel(e)).toList();
        else this.usersDisliked = new ArrayList<>();
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

    public List<UserModel> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(List<UserModel> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public List<UserModel> getUsersDisliked() {
        return usersDisliked;
    }

    public void setUsersDisliked(List<UserModel> usersDisliked) {
        this.usersDisliked = usersDisliked;
    }
}

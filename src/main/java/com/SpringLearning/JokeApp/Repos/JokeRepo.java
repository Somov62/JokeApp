package com.SpringLearning.JokeApp.Repos;

import com.SpringLearning.JokeApp.Entities.JokeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JokeRepo extends ListCrudRepository<JokeEntity, Long> {
    @Query(value = "SELECT * FROM joke where id > :startIndex LIMIT 30", nativeQuery = true)
    List<JokeEntity> getJokes(@Param("startIndex") long startIndex);
}

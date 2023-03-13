create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

CREATE TABLE `user` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login varchar(64) not null unique,
    first_name varchar(64),
    last_name varchar(64),
    PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE joke (
    id BIGINT NOT NULL AUTO_INCREMENT,
    joke TEXT not null,
    created_unix_time BIGINT not null,
    author_id BIGINT,
    PRIMARY KEY (id),

    CONSTRAINT FK_User_Joke
        FOREIGN KEY (author_id)
        REFERENCES `user` (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) engine=MyISAM;

CREATE TABLE user_joke_like (
    joke_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY(joke_id, user_id),

    CONSTRAINT FK_User_JokeLike
        FOREIGN KEY (user_id)
        REFERENCES `user` (id),

    CONSTRAINT FK_Joke_JokeLike
        FOREIGN KEY (joke_id)
        REFERENCES joke (id)
) engine=MyISAM;

CREATE TABLE user_joke_dislike (
    joke_id BIGINT,
    user_id BIGINT,
    PRIMARY KEY(joke_id, user_id),

    CONSTRAINT FK_User_JokeDislike
        FOREIGN KEY (user_id)
        REFERENCES `user` (id),

    CONSTRAINT FK_Joke_JokeDislike
        FOREIGN KEY (joke_id)
        REFERENCES joke (id)
) engine=MyISAM;
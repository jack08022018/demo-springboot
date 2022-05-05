package com.demo.entity.sakila;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FILM_ACTOR")
public class FilmActorEntity implements Serializable {
    @Id
    @Column(name = "film_id")
    private Integer filmId;

    @Id
    @Column(name = "actor_id")
    private Integer actorId;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}

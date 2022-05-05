package com.demo.repository.sakila;

import com.demo.dto.ActorDto;
import com.demo.dto.FilmInfo;
import com.demo.entity.sakila.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    @Query("select new ActorEntity(a.actorId, a.firstName) from ActorEntity a WHERE a.actorId = 1")
    List<ActorEntity> getActor();

//    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections
    @Query(nativeQuery = true, value = ""
            +   "select a.ACTOR_ID, a.FIRST_NAME, c.title, c.release_year "
            +   "from actor a "
            +   "   INNER JOIN film_actor b "
            +   "       ON b.actor_id = a.actor_id "
            +   "   INNER JOIN film c "
            +   "       ON c.film_id = b.film_id "
            +   "WHERE a.actor_id = 1 ")
    List<ActorDto> getListFilmNative();

    @Query(""
            +   "select new com.demo.dto.FilmInfo(a.actorId, a.firstName, c.title) "
            +   "from ActorEntity a "
            +   "   INNER JOIN FilmActorEntity b "
            +   "       ON b.actorId = a.actorId "
            +   "   INNER JOIN FilmEntity c "
            +   "       ON c.filmId = b.filmId "
            +   "WHERE a.actorId = 1 ")
    List<FilmInfo> getListFilm();
}


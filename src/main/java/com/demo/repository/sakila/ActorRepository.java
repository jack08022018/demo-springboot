package com.demo.repository.sakila;

import com.demo.dto.ActorDto;
import com.demo.entity.sakila.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    @Query("select new ActorEntity(a.actorId, a.firstName) from ActorEntity a WHERE a.actorId = 1")
    List<ActorEntity> getActor();

    @Query(nativeQuery = true, value = ""
            +   "select a.ACTOR_ID as actorId, a.FIRST_NAME as firstName, c.title "
            +   "from actor a "
            +   "   INNER JOIN film_actor b "
            +   "       ON b.actor_id = a.actor_id "
            +   "   INNER JOIN film c "
            +   "       ON c.film_id = b.film_id "
            +   "WHERE a.actor_id = 1 ")
    List<ActorDto> getListFilmByActor();
}


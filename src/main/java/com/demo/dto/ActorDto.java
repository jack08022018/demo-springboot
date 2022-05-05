package com.demo.dto;

import org.springframework.beans.factory.annotation.Value;

public interface ActorDto {
    @Value("#{target.ACTOR_ID}")
    Integer getActorId();

    @Value("#{target.FIRST_NAME}")
    String getFirstName();

    @Value("#{target.title + ' - ' + target.release_year}")
    String getTitleYear();

//    FilmInfo getFilmInfo();
//
//    interface FilmInfo {
//        String getTitle();
//        String getReleaseYear();
//    }
}

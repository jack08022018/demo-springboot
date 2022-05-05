package com.demo.dto;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public class FilmInfo implements Serializable {
    private Integer actorId;
    private String firstName;
    private String title;

    public FilmInfo(Integer actorId, String firstName, String title) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.title = title;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FilmInfo dto = (FilmInfo) o;
//        return Objects.equals(actorId, dto.actorId)
//                && Objects.equals(firstName, dto.firstName)
//                && Objects.equals(title, dto.title);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(actorId, firstName, title);
//    }
}

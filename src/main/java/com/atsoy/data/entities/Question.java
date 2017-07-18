package com.atsoy.data.entities;

import java.util.List;

/**
 * Created by alexander on 17.07.17.
 */
public class Question {

    private List<Song> songs;

    private Long answerId;

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "Question{" +
                "songs=" + songs +
                ", answerId=" + answerId +
                '}';
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

}

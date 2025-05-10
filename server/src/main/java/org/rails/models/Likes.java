package org.rails.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Likes {
    int id;
    UserProfile liker;
    UserClip clip;
    LocalDateTime created_at;

    public Likes() {
    }

    public Likes(int id, UserProfile liker, UserClip clip) {
        this.id = id;
        this.liker = liker;
        this.clip = clip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getLiker() {
        return liker;
    }

    public void setLiker(UserProfile liker) {
        this.liker = liker;
    }

    public UserClip getClip() {
        return clip;
    }

    public void setClip(UserClip clip) {
        this.clip = clip;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Likes likes = (Likes) o;
        return id == likes.id && Objects.equals(liker, likes.liker) && Objects.equals(clip, likes.clip) && Objects.equals(created_at, likes.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, liker, clip, created_at);
    }
}


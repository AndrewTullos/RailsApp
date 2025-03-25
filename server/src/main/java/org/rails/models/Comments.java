package org.rails.models;

import javax.sound.sampled.Clip;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comments {
    int id;
    UserProfile commenter;
    UserClip clip;
    String text;
    LocalDateTime created_at;

    public Comments() {
    }

    public Comments(int id, UserProfile commenter, UserClip clip, String text) {
        this.id = id;
        this.commenter = commenter;
        this.clip = clip;
        this.text = text;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getCommenter() {
        return commenter;
    }

    public void setCommenter(UserProfile commenter) {
        this.commenter = commenter;
    }

    public UserClip getClip() {
        return clip;
    }

    public void setClip(UserClip clip) {
        this.clip = clip;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        Comments comments = (Comments) o;
        return id == comments.id && Objects.equals(commenter, comments.commenter) && Objects.equals(clip, comments.clip) && Objects.equals(text, comments.text) && Objects.equals(created_at, comments.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commenter, clip, text, created_at);
    }
}

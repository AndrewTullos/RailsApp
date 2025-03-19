package org.rails.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Follow {
    private int id;
    private UserProfile followerId;
    private UserProfile followingId;
    private LocalDateTime createdAt;

    public Follow() {
    }

    public Follow(int id, UserProfile followerId, UserProfile followingId) {
        this.id = id;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getFollowerId() {
        return followerId;
    }

    public void setFollowerId(UserProfile followerId) {
        this.followerId = followerId;
    }

    public UserProfile getFollowingId() {
        return followingId;
    }

    public void setFollowingId(UserProfile followingId) {
        this.followingId = followingId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return id == follow.id && Objects.equals(followerId, follow.followerId) && Objects.equals(followingId, follow.followingId) && Objects.equals(createdAt, follow.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, followerId, followingId, createdAt);
    }
}

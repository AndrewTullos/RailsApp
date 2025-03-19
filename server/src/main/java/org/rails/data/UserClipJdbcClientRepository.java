package org.rails.data;

import org.rails.models.UserClip;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserClipJdbcClientRepository {

    JdbcClient client;

    public UserClipJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    public UserClip findById(int clipId) {
        return null;
    }

    public List<UserClip> findAllByUserId(int userId) {
        return null;
    }

    public List<UserClip> findAllByFollowees(int userId) {
        return null;
    }

    public List<UserClip> findRecentClips() {
        return null;
    }

    public UserClip create(UserClip userClip) {
        return null;
    }

    public boolean update(UserClip userClip) {
        return false;
    }

    public boolean deleteById(int clipId) {
        return false;
    }

}

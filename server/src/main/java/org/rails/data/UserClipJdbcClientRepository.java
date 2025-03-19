package org.rails.data;

import org.rails.data.mappers.UserClipMapper;
import org.rails.models.UserClip;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserClipJdbcClientRepository implements UserClipRepository {

    JdbcClient client;

    public UserClipJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public UserClip findById(int clipId) {
        final String sql = """
                SELECT
                    user_clip.id AS clip_id,
                    user_id,
                    media_url,
                    caption,
                    user_clip.created_at,
                    user_profile.id AS user_id,
                    user_profile.username,
                    user_profile.email,
                    user_profile.password,
                    user_profile.profile_picture,
                    user_profile.first_name,
                    user_profile.last_name,
                    user_profile.city,
                    user_profile.state,
                    user_profile.postal_code,
                    user_profile.created_at
               FROM `user_clip`
               JOIN `user_profile` ON user_clip.user_id = user_profile.id
               WHERE user_id = ?;
            """;
        return client.sql(sql)
                .param(clipId)
                .query(new UserClipMapper())
                .optional().orElse(null);
    }

    public List<UserClip> findAllClipsByUserId(int userId) {
        final String sql = """
                SELECT
                    user_clip.id AS clip_id,
                    user_id,
                    media_url,
                    caption,
                    user_clip.created_at,
                    user_profile.id AS user_id,
                    user_profile.username,
                    user_profile.email,
                    user_profile.password,
                    user_profile.profile_picture,
                    user_profile.first_name,
                    user_profile.last_name,
                    user_profile.city,
                    user_profile.state,
                    user_profile.postal_code,
                    user_profile.created_at
               FROM `user_clip`
               JOIN `user_profile` ON user_clip.user_id = user_profile.id
               WHERE user_id = ?;
            """;
        return client.sql(sql)
                .param(userId)
                .query(new UserClipMapper())
                .list();
    }

    public List<UserClip> findAllClipsByFollowees(int userId) {
        final String sql = """
            SELECT
                user_clip.id AS clip_id,
                user_clip.user_id,
                media_url,
                caption,
                user_clip.created_at,
                user_profile.id AS user_id,
                user_profile.username,
                user_profile.email,
                user_profile.password,
                user_profile.profile_picture,
                user_profile.first_name,
                user_profile.last_name,
                user_profile.city,
                user_profile.state,
                user_profile.postal_code,
                user_profile.created_at AS profile_created_at
           FROM `user_clip`
           JOIN `user_profile` ON user_clip.user_id = user_profile.id
           JOIN `follow` ON user_clip.user_id = follow.followee_id
           WHERE follow.follower_id = ?;
        """;
        return client.sql(sql)
                .param(userId)
                .query(new UserClipMapper())
                .list();
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

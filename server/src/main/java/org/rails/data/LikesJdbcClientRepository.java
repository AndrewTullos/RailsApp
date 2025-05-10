package org.rails.data;

import org.rails.data.mappers.LikesMapper;
import org.rails.models.Likes;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LikesJdbcClientRepository implements LikesRepository {

    JdbcClient client;

    public LikesJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<Likes> findAllLikesByClipId(int clipId) {
        final String sql = """
                SELECT
                   likes.id AS like_id,
                   likes.created_at AS created_at,
                   user_profile.id AS liker_id,
                   user_profile.username AS liker_username,
                   user_profile.email AS liker_email,
                   user_profile.password AS liker_password,
                   user_profile.profile_picture AS liker_profile_picture,
                   user_profile.first_name AS liker_first_name,
                   user_profile.last_name AS liker_last_name,
                   user_profile.city AS liker_city,
                   user_profile.state AS liker_state,
                   user_profile.postal_code AS liker_postal_code,
                   user_profile.created_at AS liker_created_at,
                   user_clip.id AS clip_id,
                   user_clip.media_url,
                   user_clip.caption,
                   user_clip.created_at
               FROM `like` AS likes
               JOIN user_profile ON likes.user_id = user_profile.id
               JOIN user_clip ON likes.clip_id = user_clip.id
               WHERE user_clip.id = ?;
               """;

        return client.sql(sql)
                .param(clipId)
                .query(new LikesMapper())
                .list();
    }

    @Override
    public Likes create(Likes like) {
        LocalDateTime currentTime = LocalDateTime.now();
        like.setCreated_at(currentTime);
        final String sql = """
                INSERT INTO `like` (user_id, clip_id, created_at)
                VALUES (:user_id, :clip_id, :created_at);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = client.sql(sql)
                .param("user_id", like.getLiker().getUserId())
                .param("clip_id", like.getClip().getClipId())
                .param("created_at", like.getCreated_at())
                .update(keyHolder);

        if (rowsAffected > 0) {
            like.setId(keyHolder.getKey().intValue());
            return like;
        }

        like.setId(keyHolder.getKey().intValue());
        return like;
    }

    @Override
    public boolean deleteById(int likeId) {
        int rowsAffected = client.sql("DELETE FROM `like` WHERE id = ?;")
                .param(likeId)
                .update();

        return rowsAffected > 0;
    }
}

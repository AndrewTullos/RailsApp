package org.rails.data;

import org.rails.data.mappers.CommentsMapper;
import org.rails.models.Comments;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CommentsJdbcClientRepository implements CommentsRepository {

    JdbcClient client;

    public CommentsJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List findAllCommentsByClipId(int clipId) {
        final String sql = """
               SELECT
                    comments.id AS comment_id,
                    comments.text AS comment,
                    comments.created_at AS created_at,
                    user_profile.id AS commenter_id,
                    user_profile.username AS commenter_username,
                    user_profile.email AS commenter_email,
                    user_profile.password AS commenter_password,
                    user_profile.profile_picture AS commenter_profile_picture,
                    user_profile.first_name AS commenter_first_name,
                    user_profile.last_name AS commenter_last_name,
                    user_profile.city AS commenter_city,
                    user_profile.state AS commenter_state,
                    user_profile.postal_code AS commenter_postal_code,
                    user_profile.created_at AS commenter_created_at,
                    user_clip.id AS clip_id,
                    user_clip.media_url,
                    user_clip.caption,
                    user_clip.created_at
               FROM comment AS comments
               JOIN user_profile ON comments.comment_user_id = user_profile.id
               JOIN user_clip ON comments.clip_id = user_clip.id
               WHERE user_clip.id = ?;
               """;

        return client.sql(sql)
                .param(clipId)
                .query(new CommentsMapper())
                .list();
    }

    @Override
    public Comments create(Comments comment) {
        LocalDateTime currentTime = LocalDateTime.now();
        comment.setCreated_at(currentTime);
        final String sql = """
            INSERT INTO comment (comment_user_id, clip_id, text, created_at)
            VALUES (:comment_user_id, :clip_id, :text, created_at);
            """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = client.sql(sql)
                .param("comment_user_id", comment.getCommenter().getUserId())
                .param("clip_id", comment.getClip().getClipId())
                .param("text", comment.getText())
                .param("created_at", comment.getCreated_at())
                .update(keyHolder);

        if (rowsAffected > 0) {
            comment.setId(keyHolder.getKey().intValue());
            return comment;
        }

        comment.setId(keyHolder.getKey().intValue());
        return comment;
    }

    @Override
    public boolean deleteById(int commentId) {
        int rowsAffected = client.sql("DELETE FROM comment WHERE id = ?;")
                .param(commentId)
                .update();

        return rowsAffected > 0;
    }
}

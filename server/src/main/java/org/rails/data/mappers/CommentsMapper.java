package org.rails.data.mappers;

import org.rails.models.Comments;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentsMapper implements RowMapper<Comments> {
    @Override
    public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comments comment = new Comments();
        comment.setId(rs.getInt("comment_id"));
        comment.setText(rs.getString("comment"));
        comment.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());

        UserProfile commenter = new UserProfile();
        commenter.setUserId(rs.getInt("commenter_id"));
        commenter.setUsername(rs.getString("commenter_username"));
        commenter.setEmail(rs.getString("commenter_email"));
        commenter.setPassword(rs.getString("commenter_password"));
        commenter.setPassword(null);
        commenter.setProfilePicture(rs.getString("commenter_profile_picture"));
        commenter.setFirstName(rs.getString("commenter_first_name"));
        commenter.setLastName(rs.getString("commenter_last_name"));
        commenter.setCity(rs.getString("commenter_city"));
        commenter.setState(rs.getString("commenter_state"));
        commenter.setPostalCode(rs.getString("commenter_postal_code"));
        commenter.setCreatedAt(rs.getTimestamp("commenter_created_at").toLocalDateTime());
        comment.setCommenter(commenter);

        UserClip clip = new UserClip();
        clip.setClipId(rs.getInt("clip_id"));
        clip.setMediaUrl(rs.getString("media_url"));
        clip.setCaption(rs.getString("caption"));
        clip.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        comment.setClip(clip);

        return comment;
    }
}

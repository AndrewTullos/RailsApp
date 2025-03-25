package org.rails.data.mappers;

import org.rails.models.Likes;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LikesMapper implements RowMapper<Likes> {
    @Override
    public Likes mapRow(ResultSet rs, int rowNum) throws SQLException {
        Likes like = new Likes();
        like.setId(rs.getInt("like_id"));
        like.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());

        UserProfile liker = new UserProfile();
        liker.setUserId(rs.getInt("liker_id"));
        liker.setUsername(rs.getString("liker_username"));
        liker.setEmail(rs.getString("liker_email"));
        liker.setPassword(rs.getString("liker_password"));
        liker.setPassword(null);
        liker.setProfilePicture(rs.getString("liker_profile_picture"));
        liker.setFirstName(rs.getString("liker_first_name"));
        liker.setLastName(rs.getString("liker_last_name"));
        liker.setCity(rs.getString("liker_city"));
        liker.setState(rs.getString("liker_state"));
        liker.setPostalCode(rs.getString("liker_postal_code"));
        liker.setCreatedAt(rs.getTimestamp("liker_created_at").toLocalDateTime());
        like.setLiker(liker);

        UserClip clip = new UserClip();
        clip.setClipId(rs.getInt("clip_id"));
        clip.setMediaUrl(rs.getString("media_url"));
        clip.setCaption(rs.getString("caption"));
        clip.setCreatedAt(rs.getTimestamp("user_clip.created_at").toLocalDateTime());
        like.setClip(clip);

        return like;
    }
}
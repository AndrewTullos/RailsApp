package org.rails.data;

import org.rails.models.UserClip;

import java.util.List;

public interface UserClipRepository {

    public UserClip findById(int clipId);

    public List<UserClip> findAllByUserId(int userId);

    public List<UserClip> findAllByFollowees(int userId);

    public List<UserClip> findRecentClips();

    public UserClip create(UserClip userClip);

    public boolean update(UserClip userClip);

    public boolean deleteById(int clipId);
}

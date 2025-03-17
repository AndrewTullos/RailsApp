package org.rails.data;

import org.rails.models.UserProfile;

import java.util.List;

public interface UserProfileRepository {

    public UserProfile create(UserProfile userProfile);

    public List<UserProfile> findById(int userId);

    public List<UserProfile> findByUsername(String username);

    public UserProfile findByEmail(String email);

    public boolean update(UserProfile userProfile);

    public boolean deleteById(int userId);
}

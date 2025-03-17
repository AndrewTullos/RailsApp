package org.rails.data;

import org.rails.models.UserProfile;

import java.util.List;

public interface UserProfileRepository {

    public UserProfile findById(int userId);

    public UserProfile findByUsername(String username);

    public UserProfile findByEmail(String email);

    public UserProfile create(UserProfile userProfile);

    public boolean update(UserProfile userProfile);

    public boolean deleteById(int userId);
}

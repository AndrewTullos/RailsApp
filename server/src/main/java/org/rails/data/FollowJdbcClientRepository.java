package org.rails.data;

import org.rails.models.Follow;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowJdbcClientRepository implements FollowRepository {

    JdbcClient client;

    public FollowJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<Follow> findAllFollowersByUserId(int userId) {
        return List.of();
    }

    @Override
    public List<Follow> findAllFolloweesByUserId(int userId) {
        return List.of();
    }

    @Override
    public Follow create(Follow follow) {
        return null;
    }

    @Override
    public boolean deleteById(int followId) {
        return false;
    }
}

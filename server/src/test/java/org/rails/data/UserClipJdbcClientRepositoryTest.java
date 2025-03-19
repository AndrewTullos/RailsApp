package org.rails.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rails.TestHelper;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserClipJdbcClientRepositoryTest {


    @Autowired
    JdbcClient client;

    @Autowired
    UserClipJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }


    @Test
    void findById() {
        UserClip actual = repository.findById(1 );
        UserClip expected = TestHelper.makeClip();

        assertNotNull(actual);
        assertEquals(expected.getClipId(), actual.getClipId());
    }

    @Test
    void findAllByUserId() {
        List<UserClip> actual = repository.findAllClipsByUserId(1);
        UserClip expected = TestHelper.makeClip();

        assertNotNull(actual);
        assertEquals(expected.getClipId(), actual.get(0).getClipId());
    }

//    Two clip inserts in test schema
//    Checks size
    @Test
    void findAllClipsByFollowees() {
        List<UserClip> actual = repository.findAllClipsByFollowees(1);

        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    void findRecentClips() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}
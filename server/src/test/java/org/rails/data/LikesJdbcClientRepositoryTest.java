package org.rails.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rails.TestHelper;
import org.rails.models.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LikesJdbcClientRepositoryTest {

    @Autowired
    JdbcClient client;

    @Autowired
    LikesJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }

    @Test
    void findAllLikesByClipId() {
        List<Likes> actual = repository.findAllLikesByClipId(1);
        List<Likes> expected = List.of(TestHelper.makeLike());

        assertNotNull(actual);
        assertEquals(3, actual.size());
    }

    @Test
    void create() {
        Likes toCreate = TestHelper.makeLike();
        toCreate.setId(0);

        Likes actual = repository.create(toCreate);
        assertNotNull(actual);
        assertNotEquals(0, actual.getId());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }
}
package org.rails.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rails.TestHelper;
import org.rails.models.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CommentsJdbcClientRepositoryTest {

    @Autowired
    JdbcClient client;

    @Autowired
    CommentsJdbcClientRepository repository;

    @BeforeEach
    void setup() {
        client.sql("call set_known_good_state();").update();
    }

    @Test
    void findAllCommentsByClipId() {
        List<Comments> actual = repository.findAllCommentsByClipId(1);
        List<Comments> expected = List.of(TestHelper.makeComment());

        assertNotNull(actual);
        assertEquals(3, actual.size());
    }

    @Test
    void create() {
        Comments comment = TestHelper.makeComment();
        Comments actual = repository.create(comment);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }
}
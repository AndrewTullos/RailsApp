package org.rails.domain;

import org.junit.jupiter.api.Test;
import org.rails.data.CommentsRepository;
import org.rails.models.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.rails.TestHelper.makeComment;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CommentServiceTest {

    @MockBean
    CommentsRepository repository;

    @Autowired
    CommentService service;

    @Test
    void findAllCommentsByClipId() {
        Comments comment = makeComment();
        when(repository.findAllCommentsByClipId(1))
                .thenReturn(List.of(makeComment()));

        List<Comments> actual = service.findAllCommentsByClipId(1).getPayload();

        assertEquals(List.of(comment.getId()).size(), actual.size());
        assertEquals(comment.getCommenter(), actual.get(0).getCommenter());
    }

    @Test
    void create() {
        Comments expected = makeComment();

        when(repository.create(expected))
                .thenReturn(makeComment());

        Comments actual = service.create(expected).getPayload();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCommenter(), actual.getCommenter());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1))
                .thenReturn(true);

        boolean actual = service.deleteById(1).getPayload();

        assertTrue(actual);
    }
}
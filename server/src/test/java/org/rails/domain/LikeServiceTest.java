package org.rails.domain;

import org.junit.jupiter.api.Test;
import org.rails.data.LikesRepository;
import org.rails.models.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.rails.TestHelper.makeLike;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LikeServiceTest {

    @MockBean
    LikesRepository repository;

    @Autowired
    LikeService service;

    @Test
    void findAllLikesByClipId() {
        Likes like = makeLike();
        when(repository.findAllLikesByClipId(1))
                .thenReturn(List.of(makeLike()));

        List<Likes> actual = service.findAllLikesByClipId(1).getPayload();

        assertEquals(List.of(like.getId()).size(), actual.size());
        assertEquals(like.getLiker().getUserId(), actual.get(0).getLiker().getUserId());
    }

    @Test
    void create() {
        Likes expected = makeLike();

        when(repository.create(expected))
                .thenReturn(makeLike());

        Likes actual = service.create(expected).getPayload();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLiker().getUserId(), actual.getLiker().getUserId());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1))
                .thenReturn(true);

        boolean actual = service.deleteById(1).getPayload();

        assertTrue(actual);
    }
}
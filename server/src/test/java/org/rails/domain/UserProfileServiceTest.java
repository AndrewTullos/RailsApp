package org.rails.domain;

import org.junit.jupiter.api.Test;
import org.rails.data.UserProfileRepository;
import org.rails.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.rails.TestHelper.makeUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserProfileServiceTest {

    @MockBean
    UserProfileRepository repository;

    @Autowired
    UserProfileService service;

    @Test
    void findById() {
        UserProfile expected = makeUser();

        when(repository.findById(1))
                .thenReturn(makeUser());

        UserProfile actual = service.findById(1).getPayload();
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    void shouldNotFindMissingId() {
        when(repository.findById(1000))
                .thenReturn(null);

        assertNull(service.findById(1000).getPayload());
    }

    @Test
    void findByUsername() {
        UserProfile expected = makeUser();

        when(repository.findByUsername("JoeSchmoe"))
                .thenReturn(makeUser());

        UserProfile actual = service.findByUsername("JoeSchmoe").getPayload();
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    void shouldNotFindByMissingUsername() {
        when(repository.findByUsername("MissingUsername"))
                .thenReturn(null);

        assertNull(service.findByUsername("MissingUsername").getPayload());
    }

    @Test
    void findByEmail() {
        UserProfile expected = makeUser();

        when(repository.findByEmail("joeschmoe@test.com"))
                .thenReturn(makeUser());

        UserProfile actual = service.findByEmail("joeschmoe@test.com").getPayload();
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    void shouldNotFindByMissingEmail() {
        when(repository.findByEmail("MissingEmail"))
                .thenReturn(null);

        assertNull(service.findByEmail("MissingEmail").getPayload());
    }

    @Test
    void create() {
        UserProfile expected = makeUser();

        when(repository.create(any(UserProfile.class))).thenReturn(makeUser());


        UserProfile actual = service.create(makeUser()).getPayload();
        assertEquals(expected.getUsername(), actual.getUsername());
    }

    @Test
    void update() {
        UserProfile arg = makeUser();
        arg.setEmail("newemail@test.com");

        when(repository.update(any(UserProfile.class))).thenReturn(true);

        assertTrue(service.update(arg).isSuccess());
    }

    @Test
    void deleteById() {
        when(repository.deleteById(1)).thenReturn(true);

        assertTrue(service.deleteById(1).isSuccess());
    }
}
package org.rails;

import org.rails.models.UserProfile;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;

public class TestHelper {
    public static UserProfile makeUser() {
        return new UserProfile(1,"JoeSchmoe", "joeschmoe@test.com", "testPassword1!", "testprofile1URL.com", "Joe", "Schmoe", "San Francisco", "CA", "94105");
    }
}

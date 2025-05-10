package org.rails;

import org.rails.models.*;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;

public class TestHelper {
    public static UserProfile makeUser() {
        return new UserProfile(1,"JoeSchmoe", "JoeSchmoe@Test.com", "testPassword1!", "testprofile1URL.com", "Joe", "Schmoe", "San Francisco", "CA", "94105");
    }

    public static UserProfile makeUser2() {
        return new UserProfile(2,"JoeSchmoe", "JoeSchmoe@Test.com", "testPassword1!", "testprofile1URL.com", "Joe", "Schmoe", "San Francisco", "CA", "94105");
    }

    public static UserClip makeClip() {
        return new UserClip(1, makeUser(), "testClip1URL.com", "Skateboard");
    }

    public static Follow makeFollow() {
        return new Follow(1, makeUser(), makeUser2());
    }

    public static Likes makeLike() {
        return new Likes(1, makeUser(), makeClip());
    }

    public static Comments makeComment() {
        return new Comments(1, makeUser(), makeClip(), "This is a test comment");
    }
}

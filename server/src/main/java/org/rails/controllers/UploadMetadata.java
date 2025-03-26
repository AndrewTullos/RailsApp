package org.rails.controllers;

public class UploadMetadata {
    private int userId;
    private String caption;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String password;
    private String city;
    private String state;


    public UploadMetadata() {
    }

    public UploadMetadata(int userId, String caption, String username, String email, String firstName, String lastName, String profilePicture, String password, String city, String state) {
        this.userId = userId;
        this.caption = caption;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.password = password;
        this.city = city;
        this.state = state;
    }

    public UploadMetadata(int userId, String caption) {
        this.userId = userId;
        this.caption = caption;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UploadMetadata{" +
                "userId=" + userId +
                ", caption='" + caption + '\'' +
                '}';
    }
}

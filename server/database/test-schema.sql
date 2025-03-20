drop database if exists rails_app_test;
create database rails_app_test;
use rails_app_test;

-- !!REMINDER!!
-- Followers: These are the people who follow you. They’ve subscribed to see your content.
-- Followees: These are the people you follow. You’ve subscribed to see their content.

DROP TABLE IF EXISTS `follow`;
DROP TABLE IF EXISTS `like`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `user_clip`;
DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE IF NOT EXISTS `user_profile` (
    `id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `username` varchar(100) NOT NULL,
    `email` varchar(255) NOT NULL UNIQUE,
    `password` varchar(255) NOT NULL,
    `profile_picture` varchar(255) NULL,
    `first_name` varchar(100) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    `city` varchar(255) NOT NULL,
    `state` varchar(50) NOT NULL,
    `postal_code` varchar(10) NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_clip` (
    `id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `user_id` int NOT NULL,
    `media_url` text NOT NULL,
    `caption` varchar(255) NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `user_clip_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_profile`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `comment` (
    `id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `comment_user_id` int NOT NULL,
    `clip_id` int NOT NULL,
    `text` varchar(255) NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `comment_fk1` FOREIGN KEY (`comment_user_id`) REFERENCES `user_profile`(`id`) ON DELETE CASCADE,
    CONSTRAINT `comment_fk2` FOREIGN KEY (`clip_id`) REFERENCES `user_clip`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `like` (
    `id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `user_id` int NOT NULL,
    `clip_id` int NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `like_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_profile`(`id`) ON DELETE CASCADE,
    CONSTRAINT `like_fk2` FOREIGN KEY (`clip_id`) REFERENCES `user_clip`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `follow` (
    `id` int AUTO_INCREMENT NOT NULL UNIQUE,
    `follower_id` int NOT NULL,
    `followee_id` int NOT NULL,
    `created_at` datetime NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `follow_fk1` FOREIGN KEY (`follower_id`) REFERENCES `user_profile`(`id`) ON DELETE CASCADE,
    CONSTRAINT `follow_fk2` FOREIGN KEY (`followee_id`) REFERENCES `user_profile`(`id`) ON DELETE CASCADE
);

delimiter //
create procedure set_known_good_state()
begin


    delete from `comment`;
    alter table `comment` auto_increment = 1;
    delete from `like`;
    alter table `like` auto_increment = 1;
    delete from `follow`;
    alter table `follow` auto_increment = 1;
    delete from `user_clip`;
    alter table `user_clip` auto_increment = 1;
    delete from `user_profile`;
    alter table `user_profile` auto_increment = 1;

    insert into `user_profile` (username, email, `password`, profile_picture, first_name, last_name, city, state, postal_code, created_at)
        values
        ('JoeSchmoe', 'JoeSchmoe@Test.com', 'testPassword1!', 'testProfilePicture.com', 'Joe', 'Schmoe', 'San Francisco', 'CA', '94105', '2020-01-01 00:00:00'),
        ('JaneDoe', 'JaneDoe@Test.com', 'testPassword1!', 'testProfilePicture2.com', 'Jane', 'Doe', 'San Francisco', 'CA', '94105', '2020-01-01 00:00:00'),
        ('SuzyQ', 'SuzyQ@Test.com', 'testPassword1!', 'testProfilePicture2.com', 'Suzy', 'Q', 'San Francisco', 'CA', '94105', '2020-01-01 00:00:00');

    insert into `user_clip` (user_id, media_url, caption, created_at)
        values
        (1, 'testMediaUrl.com', 'testCaption', '2020-01-01 00:00:00'),
        (2, 'testMediaUrl2.com', 'testCaption2', '2020-01-01 00:00:00'),
        (2, 'testMediaUrl3.com', 'testCaption3', '2020-01-01 00:00:00');

    insert into `comment` (comment_user_id, clip_id, `text`, created_at)
        values
        (1, 1, 'testComment', '2020-01-01 00:00:00'),
        (1, 1, 'testComment2', '2020-01-01 00:00:00'),
        (2, 1, 'testComment3', '2020-01-01 00:00:00');

    insert into `like` (user_id, clip_id, created_at)
        values
        (1, 1, '2020-01-01 00:00:00'),
        (1, 1, '2020-01-01 00:00:00'),
        (2, 1, '2020-01-01 00:00:00');

    insert into `follow` (follower_id, followee_id, created_at)
        values
        (1, 2, '2020-01-01 00:00:00'),
        (3, 2, '2020-01-01 00:00:00'),
        (2, 1, '2020-01-01 00:00:00');

end//
delimiter ;
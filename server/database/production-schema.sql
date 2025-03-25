drop database if exists rails_app_db;
create database rails_app_db;
use rails_app_db;

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


SELECT * FROM user_profile;
SELECT * FROM user_clip;
SELECT * FROM follow;

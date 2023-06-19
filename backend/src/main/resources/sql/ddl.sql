DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `user_id` bigint NOT NULL AUTO_INCREMENT,
                         `country` varchar(255) DEFAULT NULL,
                         `login_id` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `nickname` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `UK_i3xs7wmfu2i3jt079uuetycit` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `posts` (
                         `post_id` bigint NOT NULL AUTO_INCREMENT,
                         `content` longtext,
                         `created_at` datetime(6) DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `user_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`post_id`),
                         KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`),
                         CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comments` (
                            `comment_id` bigint NOT NULL AUTO_INCREMENT,
                            `content` longtext,
                            `created_at` datetime(6) DEFAULT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `post_id` bigint DEFAULT NULL,
                            `user_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`comment_id`),
                            KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
                            KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
                            CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                            CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
DROP DATABASE IF EXISTS sbb_dev2;
CREATE DATABASE sbb_dev2;
USE sbb_dev2;

CREATE TABLE `site_user` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `email` varchar(255) DEFAULT NULL,
                             `password` varchar(255) DEFAULT NULL,
                             `username` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_email` (`email`),
                             UNIQUE KEY `uk_username` (`username`)
);

CREATE TABLE `question` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `subject` varchar(200) DEFAULT NULL,
                            `content` text,
                            `author_id` int DEFAULT NULL,
                            `create_date` datetime(6) DEFAULT NULL,
                            `modify_date` datetime(6) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `fk_author_id` (`author_id`),
                            CONSTRAINT `fk_site_user` FOREIGN KEY (`author_id`) REFERENCES `site_user` (`id`)
);


CREATE TABLE `answer` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `question_id` int DEFAULT NULL,
                          `author_id` int DEFAULT NULL,
                          `content` text,
                          `create_date` datetime(6) DEFAULT NULL,
                          `modify_date` datetime(6) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_question_id2` (`question_id`),
                          KEY `fk_quthor_id2` (`author_id`),
                          CONSTRAINT `fk_site_user2` FOREIGN KEY (`author_id`) REFERENCES `site_user` (`id`),
                          CONSTRAINT `fk_question2` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);

TRUNCATE TABLE answer;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE question;
SET FOREIGN_KEY_CHECKS = 1;



CREATE TABLE `answer_voter` (
                                `answer_id` int NOT NULL,
                                `voter_id` int NOT NULL,
                                PRIMARY KEY (`answer_id`,`voter_id`),
                                KEY `fk_voter_id2` (`voter_id`),
                                CONSTRAINT `fk_answer3` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
                                CONSTRAINT `fk_site_user3` FOREIGN KEY (`voter_id`) REFERENCES `site_user` (`id`)
);

CREATE TABLE `question_voter` (
                                  `question_id` int NOT NULL,
                                  `voter_id` int NOT NULL,
                                  PRIMARY KEY (`question_id`,`voter_id`),
                                  KEY `fk_voter_id4` (`voter_id`),
                                  CONSTRAINT `fk_question4` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
                                  CONSTRAINT `fk_site_user4` FOREIGN KEY (`voter_id`) REFERENCES `site_user` (`id`)
);
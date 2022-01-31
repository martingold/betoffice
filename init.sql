DROP TABLE IF EXISTS `bet`;
CREATE TABLE `bet` (
  `id` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bet_match`;
CREATE TABLE `bet_match` (
  `id` bigint(20) NOT NULL,
  `match` bigint(20) NOT NULL,
  `bet` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bet_match_bet_id_fk` (`bet`),
  KEY `bet_match_match_id_fk` (`match`),
  CONSTRAINT `bet_match_bet_id_fk` FOREIGN KEY (`bet`) REFERENCES `bet` (`id`),
  CONSTRAINT `bet_match_match_id_fk` FOREIGN KEY (`match`) REFERENCES `match` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `match`;
CREATE TABLE `match` (
  `id` bigint(20) NOT NULL,
  `team1` bigint(20) NOT NULL,
  `team2` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(300) NOT NULL,
  `result` varchar(45) DEFAULT NULL,
  `stream` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TeamMatch2___fk` (`team2`),
  KEY `TeamMatch___fk` (`team1`),
  KEY `stream___fk` (`stream`),
  CONSTRAINT `TeamMatch2___fk` FOREIGN KEY (`team2`) REFERENCES `team` (`id`),
  CONSTRAINT `TeamMatch___fk` FOREIGN KEY (`team1`) REFERENCES `team` (`id`),
  CONSTRAINT `stream___fk` FOREIGN KEY (`stream`) REFERENCES `stream` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stream`;
CREATE TABLE `stream` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `name` varchar(30) NOT NULL,
  `wins` int(11) DEFAULT NULL,
  `loses` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

INSERT INTO `match` (`id`, `team1`, `team2`, `date`, `description`, `result`, `stream`) VALUES
(1, 1, 2, '2022-01-16 19:51:00', 'Finále ligy', NULL, 1);

INSERT INTO `stream` (`id`, `name`, `url`) VALUES
(1, 'Youtube přenos', 'https://www.youtube.com/watch?v=aKUfjkiB_5Q');

INSERT INTO `team` (`name`, `wins`, `loses`, `id`) VALUES
('FC Sparta Praha', 1, 2, 1),
('SK Sigma Olomouc', 1, 3, 2);

INSERT INTO `user` (`id`, `amount`, `birth_date`, `created_at`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES
(3, 200, '1996-01-01 00:00:00', '2022-01-31 13:36:48', 'admin@example.com', 'Admin', 'Admin', '$2a$10$z8nK0j2l7Sq.RBa8RfeAn.uHC0/NhxXi1bTtgZiuq5gNShY6Pz2ta', 'admin', 'admin'),
(4, 200, '2022-01-01 00:00:00', '2022-01-31 13:41:08', 'user@example.com', 'User', 'User', '$2a$10$.kRRvy/40dwOx2anZ7nS.e4eTLlK7aonHnliwni6FNRHzufQQE7.a', 'user', 'user');

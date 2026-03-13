USE opi_backend;

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name1` varchar(45) NOT NULL,
  `last_name2` varchar(45) DEFAULT NULL,
  `phone` varchar(9) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `height` int NOT NULL,
  `weight` int NOT NULL,
  `birth_date` date DEFAULT NULL,
  `desired_water` int NOT NULL,
  `last_login` date DEFAULT NULL,

  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `bottle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `first_connection` date DEFAULT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bottle_ibfk_1` (`account_id`),
  CONSTRAINT `bottle_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `data` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `bottle_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_ibfk_1` (`bottle_id`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`bottle_id`) REFERENCES `bottle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `daily_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `amount_drunk` int NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `daily_record_ibfk_1` (`account_id`),
  CONSTRAINT `daily_record_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

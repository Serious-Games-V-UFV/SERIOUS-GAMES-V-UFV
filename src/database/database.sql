CREATE DATABASE opi_backend;
USE opi_backend;

CREATE TABLE `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name1` VARCHAR(45) NOT NULL,
  `last_name2` VARCHAR(45) DEFAULT NULL,
  `phone` VARCHAR(9) DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `height` INT NOT NULL,
  `weight` INT NOT NULL,
  `birth_date` DATE DEFAULT NULL,
  `desired_water` INT NOT NULL,
  `last_login` DATE DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `bag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `first_connection` DATE DEFAULT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bag_ibfk_1` (`account_id`),
  CONSTRAINT `bag_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `data` VARCHAR(45) NOT NULL,
  `date` DATE DEFAULT NULL,
  `bag_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_ibfk_1` (`bag_id`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`bag_id`) REFERENCES `bag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `daily_reminder` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE DEFAULT NULL,
  `amount_drunk` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `daily_reminder_ibfk_1` (`account_id`),
  CONSTRAINT `daily_reminder_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

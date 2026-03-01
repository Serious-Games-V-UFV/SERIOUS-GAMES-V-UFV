CREATE TABLE `cuenta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `altura` int NOT NULL,
  `peso` int NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `agua_deseada` int NOT NULL,
  `last_login` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `bolso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `primera_conexion` date DEFAULT NULL,
  `id_cuenta` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bolso_ibfk_1` (`id_cuenta`),
  CONSTRAINT `bolso_ibfk_1` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `evento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `dato` varchar(45) NOT NULL,
  `fecha` date DEFAULT NULL,
  `id_bolso` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `evento_ibfk_1` (`id_bolso`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_bolso`) REFERENCES `bolso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recuerdo_diario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `cantidad_bebida` int NOT NULL,
  `id_cuenta` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recuerdo_ibfk_1` (`id_cuenta`),
  CONSTRAINT `recuerdo_ibfk_1` FOREIGN KEY (`id_cuenta`) REFERENCES `cuenta` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
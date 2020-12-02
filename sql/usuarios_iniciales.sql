DROP TABLE IF EXISTS `USUARIO`;
CREATE TABLE `USUARIO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCESO` datetime DEFAULT NULL,
  `ACTIVO` tinyint(1) DEFAULT '0',
  `CREACION` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `USUARIO` VALUES 
    (1,'2019-11-25 13:19:20',1,'2019-11-25 13:19:20','admin@facturaaas.net','admin','Administrador','qfIP9LNksOTN3EeDyqLC7Jow+kKeCpx8','ADMINISTRADOR'),
    (2,'2019-11-25 13:19:21',1,'2019-11-25 13:19:21','ana@facturaaas.net','ana','Ana Anido Anido','ulAxyNxAODr9XXG3ZOVvotwg6dCySRea','USUARIO');


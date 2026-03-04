/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.27-MariaDB : Database - seminarski_fudbal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski_fudbal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `seminarski_fudbal`;

/*Table structure for table `kategorija` */

DROP TABLE IF EXISTS `kategorija`;

CREATE TABLE `kategorija` (
  `idKategorija` int(11) NOT NULL AUTO_INCREMENT,
  `nazivKategorije` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kategorija` */

insert  into `kategorija`(`idKategorija`,`nazivKategorije`) values 
(1,'student'),
(2,'deca'),
(3,'ostali');

/*Table structure for table `osoba` */

DROP TABLE IF EXISTS `osoba`;

CREATE TABLE `osoba` (
  `idOsoba` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `broj` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `idKategorija` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOsoba`),
  KEY `idKategorija` (`idKategorija`),
  CONSTRAINT `osoba_ibfk_1` FOREIGN KEY (`idKategorija`) REFERENCES `kategorija` (`idKategorija`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `osoba` */

insert  into `osoba`(`idOsoba`,`ime`,`prezime`,`broj`,`email`,`idKategorija`) values 
(1,'Petar ','Janjetovic','063063063','petar@gmail.com',1),
(2,'Dimitrije','Ilic','062062062','dimitrije@gmail.com',3),
(3,'Pavle','Simonovic','061061061','pavle@gmail.com',2),
(4,'Miroslav ','Simonovic','064064064','miroslav@gmail.com',2);

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `idRezervacija` int(11) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `satOd` int(11) DEFAULT NULL,
  `satDo` int(11) DEFAULT NULL,
  `popust` double DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `idVlasnik` int(11) DEFAULT NULL,
  `idOsoba` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRezervacija`),
  KEY `idVlasnik` (`idVlasnik`),
  KEY `idOsoba` (`idOsoba`),
  CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`idVlasnik`) REFERENCES `vlasnik` (`idVlasnik`),
  CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`idOsoba`) REFERENCES `osoba` (`idOsoba`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `rezervacija` */

insert  into `rezervacija`(`idRezervacija`,`datum`,`satOd`,`satDo`,`popust`,`ukupanIznos`,`idVlasnik`,`idOsoba`) values 
(1,'2026-03-18',10,11,0.7,6000,1,4);

/*Table structure for table `smena` */

DROP TABLE IF EXISTS `smena`;

CREATE TABLE `smena` (
  `idSmena` int(11) NOT NULL AUTO_INCREMENT,
  `nazivSmena` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idSmena`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `smena` */

insert  into `smena`(`idSmena`,`nazivSmena`) values 
(1,'prva'),
(2,'druga'),
(3,'medju');

/*Table structure for table `stavkarezervacije` */

DROP TABLE IF EXISTS `stavkarezervacije`;

CREATE TABLE `stavkarezervacije` (
  `rb` int(11) NOT NULL AUTO_INCREMENT,
  `cenaUsluge` double DEFAULT NULL,
  `brojUsluga` int(11) DEFAULT NULL,
  `iznos` double DEFAULT NULL,
  `idRezervacija` int(11) NOT NULL,
  `idUsluga` int(11) DEFAULT NULL,
  PRIMARY KEY (`rb`,`idRezervacija`),
  KEY `idRezervacija` (`idRezervacija`),
  KEY `idUsluga` (`idUsluga`),
  CONSTRAINT `stavkarezervacije_ibfk_1` FOREIGN KEY (`idRezervacija`) REFERENCES `rezervacija` (`idRezervacija`),
  CONSTRAINT `stavkarezervacije_ibfk_2` FOREIGN KEY (`idUsluga`) REFERENCES `usluga` (`idUsluga`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkarezervacije` */

insert  into `stavkarezervacije`(`rb`,`cenaUsluge`,`brojUsluga`,`iznos`,`idRezervacija`,`idUsluga`) values 
(1,15,3,45,1,2);

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `idUsluga` int(11) NOT NULL AUTO_INCREMENT,
  `cena` double DEFAULT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUsluga`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `usluga` */

insert  into `usluga`(`idUsluga`,`cena`,`naziv`) values 
(1,50,'voda'),
(2,15,'markeri'),
(3,100,'lopta');

/*Table structure for table `vlasnik` */

DROP TABLE IF EXISTS `vlasnik`;

CREATE TABLE `vlasnik` (
  `idVlasnik` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `korisničkoIme` varchar(50) DEFAULT NULL,
  `šifra` varchar(509) DEFAULT NULL,
  PRIMARY KEY (`idVlasnik`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vlasnik` */

insert  into `vlasnik`(`idVlasnik`,`ime`,`prezime`,`korisničkoIme`,`šifra`) values 
(1,'Aleksa','Kablar','Kabi','0046'),
(2,'Andjelija','Jovanovic','Andjelija','0244'),
(3,'Andrija','Jelic','Jela','0038');

/*Table structure for table `vlsm` */

DROP TABLE IF EXISTS `vlsm`;

CREATE TABLE `vlsm` (
  `idVlasnik` int(11) NOT NULL,
  `idSmena` int(11) NOT NULL,
  `datumSmene` date NOT NULL,
  PRIMARY KEY (`idVlasnik`,`idSmena`,`datumSmene`),
  KEY `idSmena` (`idSmena`),
  CONSTRAINT `vlsm_ibfk_1` FOREIGN KEY (`idVlasnik`) REFERENCES `vlasnik` (`idVlasnik`),
  CONSTRAINT `vlsm_ibfk_2` FOREIGN KEY (`idSmena`) REFERENCES `smena` (`idSmena`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vlsm` */

insert  into `vlsm`(`idVlasnik`,`idSmena`,`datumSmene`) values 
(1,1,'2026-03-25'),
(1,2,'2026-03-16'),
(2,2,'2026-03-25'),
(2,3,'2026-03-16'),
(3,1,'2026-03-16'),
(3,3,'2026-03-25');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

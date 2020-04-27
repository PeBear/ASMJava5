CREATE DATABASE  IF NOT EXISTS `Personel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `Personel`;
-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Personel
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Depart`
--

DROP TABLE IF EXISTS `Depart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Depart` (
  `Id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Depart`
--

LOCK TABLES `Depart` WRITE;
/*!40000 ALTER TABLE `Depart` DISABLE KEYS */;
INSERT INTO `Depart` VALUES ('PB01','TPHCM'),('PB02','Hà Nội');
/*!40000 ALTER TABLE `Depart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Record`
--

DROP TABLE IF EXISTS `Record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Record` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` int(11) NOT NULL,
  `Reason` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Date` date NOT NULL,
  `StaffId` varchar(10) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `StaffId` (`StaffId`),
  CONSTRAINT `Record_ibfk_1` FOREIGN KEY (`StaffId`) REFERENCES `Staff` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Record`
--

LOCK TABLES `Record` WRITE;
/*!40000 ALTER TABLE `Record` DISABLE KEYS */;
INSERT INTO `Record` VALUES (1,1,'Vượt tiến độ','2020-02-20','PS09070'),(2,0,'Đi muộn','2020-02-15','PS12345'),(3,0,'Đi muộn','2020-02-14','PS23456'),(4,1,'SAD','2020-02-25','PS09070'),(5,1,'Very Sada','2020-02-25','PS12345'),(7,1,'SADDD','2020-02-04','PS09070'),(9,0,'DDDa','2020-02-03','PS12345'),(10,0,'sad','2020-01-01','PS09070');
/*!40000 ALTER TABLE `Record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Staff` (
  `Id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Gender` bit(1) NOT NULL,
  `Birthday` date NOT NULL,
  `Photo` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Phone` varchar(25) CHARACTER SET utf8 NOT NULL,
  `Salary` float NOT NULL,
  `Notes` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `DepartId` varchar(10) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `DepartId` (`DepartId`),
  CONSTRAINT `Staff_ibfk_1` FOREIGN KEY (`DepartId`) REFERENCES `Depart` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES ('PS09070','Vương Thế Minh ',_binary '','2000-05-05','945789.png','minhthang@gmail.com','0902284213',10500000,'','PB01'),('PS12345','Vương Hạ Vũ',_binary '\0','2000-08-20','714828.png','havu@gmail.com','0919559331',8500000,'','PB01'),('PS23456','Võ Thành Long',_binary '','2000-04-21','unknow.jpeg','vothanhlong@gmail.com','0123456789',9200000,'','PB02'),('PS42548','Hoàng Hồng Sơn',_binary '','2000-11-12','unknow.jpeg','hoanghongson@gmail.com','0235854698',8700000,'','PB02'),('PS52354','Phạm Lê Huy',_binary '','2000-06-18','unknow.jpeg','phamlehuy@gmail.com','0369851235',7700000,'','PB01'),('temp','temp',_binary '\0','2020-02-18','unknow.jpeg','temp@gmail.com','0902284213',555,'','PB02');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('admin','admin','admin'),('HuyPL','123456','Phạm Lê Huy'),('LongVT','123456','Võ Thành Long'),('SonHH','123456','Hoàng Hồng Sơn'),('ThangVTM','123456','Vương Thế Minh Thăng');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'Personel'
--

--
-- Dumping routines for database 'Personel'
--
/*!50003 DROP PROCEDURE IF EXISTS `spGetTop10Staff` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetTop10Staff`()
BEGIN
		SELECT Staff.Id, 
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END) Achivement,
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Discipline,
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END)
			-
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Result
		FROM Record, Staff
		WHERE Record.StaffId = Staff.Id
		GROUP BY StaffId
        ORDER BY Result desc
        LIMIT 10;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spThongKeThanhTich` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spThongKeThanhTich`(IN _StaffId varchar(10))
BEGIN
	IF _StaffId IS NULL THEN
		SELECT Staff.Name, 
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END) Achivement,
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Discipline,
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END)
			-
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Result
		FROM Record, Staff
		WHERE Record.StaffId = Staff.Id
		GROUP BY StaffId
        ORDER BY Result desc;
    ELSE
		SELECT Staff.Name, 
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END) Achivement,
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Discipline,
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END)
			-
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Result
		FROM Record, Staff
		WHERE Record.StaffId = Staff.Id
			AND StaffId = _StaffId collate utf8_unicode_ci
		GROUP BY StaffId
        ORDER BY Result desc;
    END IF; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spThongKeThanhTichPhongBan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spThongKeThanhTichPhongBan`()
BEGIN
		SELECT Depart.Name, 
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END) Achivement,
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Discipline,
			SUM(CASE
				WHEN Record.Type = true then 1 ELSE 0 END)
			-
			SUM(CASE
				WHEN Record.Type = false then 1 ELSE 0 END) Result
		FROM Record, Staff, Depart
		WHERE Record.StaffId = Staff.Id
			AND Staff.DepartId = Depart.Id
		GROUP BY Depart.Id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-27 20:16:55

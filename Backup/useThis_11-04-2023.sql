-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: ats
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ats`
--

/*!40000 DROP DATABASE IF EXISTS `ats`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ats`;

--
-- Table structure for table `blanks`
--

DROP TABLE IF EXISTS `blanks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blanks` (
  `blankID` int NOT NULL,
  `blankType` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `dateRecieved` date NOT NULL,
  `dateAssigned` date DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  PRIMARY KEY (`blankID`,`blankType`),
  KEY `blankEmployeesID_idx` (`employeeID`) /*!80000 INVISIBLE */,
  CONSTRAINT `blankEmployeesID` FOREIGN KEY (`employeeID`) REFERENCES `employeeaccount` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (1,444,'Sold','2023-04-10','2023-04-10',0),(2,444,'Sold','2023-04-10','2023-04-10',0),(3,444,'Assigned','2023-04-10','2023-04-10',1),(4,440,'Sold','2023-04-10','2023-04-10',0),(5,440,'Sold','2023-04-10','2023-04-10',0),(6,440,'Assigned','2023-04-10','2023-04-10',1),(7,420,'Refunded','2023-04-10','2023-04-10',0),(8,420,'Refunded','2023-04-10','2023-04-10',0),(9,420,'Assigned','2023-04-10','2023-04-10',1),(10,201,'Sold','2023-04-10','2023-04-10',0),(11,201,'Sold','2023-04-10','2023-04-10',0),(12,201,'Assigned','2023-04-10','2023-04-10',1),(13,101,'Refunded','2023-04-10','2023-04-10',0),(14,101,'Refunded','2023-04-10','2023-04-10',0),(15,101,'Assigned','2023-04-10','2023-04-10',1),(16,101,'Refunded','2023-04-10','2023-04-11',0),(17,101,'Assigned','2023-04-10','2023-04-11',0),(18,444,'Assigned','2023-04-11','2023-04-11',0),(19,444,'Assigned','2023-04-11','2023-04-11',0),(20,444,'Assigned','2023-04-11','2023-04-11',0),(21,420,'Assigned','2023-04-11','2023-04-11',0),(22,420,'Assigned','2023-04-11','2023-04-11',0),(23,420,'Assigned','2023-04-11','2023-04-11',0),(24,440,'Assigned','2023-04-11','2023-04-11',0),(25,440,'Assigned','2023-04-11','2023-04-11',0),(26,440,'Assigned','2023-04-11','2023-04-11',0),(27,201,'Assigned','2023-04-11','2023-04-11',0),(28,201,'Assigned','2023-04-11','2023-04-11',0),(29,201,'Assigned','2023-04-11','2023-04-11',0),(30,101,'Assigned','2023-04-11','2023-04-11',0),(31,101,'Assigned','2023-04-11','2023-04-11',0),(32,101,'Assigned','2023-04-11','2023-04-11',0);
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission` (
  `CommissionID` int NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `commissionDate` date NOT NULL,
  `commissionEndDate` date DEFAULT NULL,
  `BlankType` int NOT NULL,
  PRIMARY KEY (`CommissionID`),
  UNIQUE KEY `commissionID_UNIQUE` (`CommissionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
INSERT INTO `commission` VALUES (1,10,'2023-04-10','2023-05-10',444),(2,12,'2023-04-10','2023-05-07',440),(3,15,'2023-04-10','2023-05-15',420),(4,20,'2023-04-10','2023-05-18',201),(5,5,'2023-04-10','2023-05-22',101);
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupondestination`
--

DROP TABLE IF EXISTS `coupondestination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupondestination` (
  `destinationID` int NOT NULL AUTO_INCREMENT,
  `destinationTo` varchar(45) NOT NULL,
  `destinationFrom` varchar(45) NOT NULL,
  `couponIDDestination` int NOT NULL,
  PRIMARY KEY (`destinationID`),
  KEY `couponDestinationCouponID_idx` (`couponIDDestination`),
  CONSTRAINT `couponDestinationCouponID` FOREIGN KEY (`couponIDDestination`) REFERENCES `coupons` (`couponID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupondestination`
--

LOCK TABLES `coupondestination` WRITE;
/*!40000 ALTER TABLE `coupondestination` DISABLE KEYS */;
INSERT INTO `coupondestination` VALUES (3,'2','1',5);
/*!40000 ALTER TABLE `coupondestination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `couponID` int NOT NULL AUTO_INCREMENT,
  `couponType` varchar(45) NOT NULL,
  `blankIDCoupons` int NOT NULL,
  `blankType` int NOT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponID_UNIQUE` (`couponID`),
  KEY `blankIDCoupons_idx` (`blankIDCoupons`,`blankType`),
  CONSTRAINT `blankIDCoupons` FOREIGN KEY (`blankIDCoupons`, `blankType`) REFERENCES `blanks` (`blankID`, `blankType`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (4,'Auditor',1,444),(5,'Flight',1,444);
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customeraccount`
--

DROP TABLE IF EXISTS `customeraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customeraccount` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `CustomerEmail` varchar(45) NOT NULL,
  `AccountType` varchar(45) NOT NULL,
  `DiscountID` int DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `discountID_idx` (`DiscountID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customeraccount`
--

LOCK TABLES `customeraccount` WRITE;
/*!40000 ALTER TABLE `customeraccount` DISABLE KEYS */;
INSERT INTO `customeraccount` VALUES (33,'test','test','test@gmail.com','Customer',NULL),(34,'Nash','Gru','nash.gru@gmail.com','Regular',NULL),(35,'Hell','Hell','hell@gmail.com','Valued',NULL);
/*!40000 ALTER TABLE `customeraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `DiscountID` int NOT NULL AUTO_INCREMENT,
  `DiscountType` varchar(45) NOT NULL,
  PRIMARY KEY (`DiscountID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (7,'Fixed'),(8,'Flexible'),(9,'Fixed'),(10,'Flexible');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeaccount`
--

DROP TABLE IF EXISTS `employeeaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeeaccount` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `AccountType` varchar(45) NOT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeaccount`
--

LOCK TABLES `employeeaccount` WRITE;
/*!40000 ALTER TABLE `employeeaccount` DISABLE KEYS */;
INSERT INTO `employeeaccount` VALUES (0,'Bob','Nick','bob','circle','Manager'),(1,'Qasim','Rafiq','qrafiq','gorilla3','Travel Advisor');
/*!40000 ALTER TABLE `employeeaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangerate`
--

DROP TABLE IF EXISTS `exchangerate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exchangerate` (
  `exchangeID` int NOT NULL AUTO_INCREMENT,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `exchangeAmount` float NOT NULL,
  PRIMARY KEY (`exchangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerate`
--

LOCK TABLES `exchangerate` WRITE;
/*!40000 ALTER TABLE `exchangerate` DISABLE KEYS */;
INSERT INTO `exchangerate` VALUES (9,'2019-12-31','2020-02-01',0.54),(10,'2020-02-01',NULL,0.43),(17,'2020-04-10',NULL,0.43),(18,'2020-04-10',NULL,0.54);
/*!40000 ALTER TABLE `exchangerate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `saleID` int NOT NULL AUTO_INCREMENT,
  `blankTypeSale` int NOT NULL,
  `PaymentType` varchar(45) NOT NULL,
  `flightType` varchar(45) NOT NULL,
  `cardDetails` int DEFAULT NULL,
  `PaymentAmount` float NOT NULL,
  `saleDate` date NOT NULL,
  `exchangeRate` float DEFAULT NULL,
  `taxes` float NOT NULL,
  `latePaymentDate` date DEFAULT NULL,
  `latePaymentStatus` varchar(45) DEFAULT NULL,
  `blankIDSale` int NOT NULL,
  `employeeIDSale` int NOT NULL,
  `customerIDSale` int NOT NULL,
  `commissionIDSale` int NOT NULL,
  PRIMARY KEY (`saleID`),
  UNIQUE KEY `salesID_UNIQUE` (`saleID`),
  KEY `SalesCommissionID_idx` (`commissionIDSale`),
  KEY `SalesCustomerID_idx` (`customerIDSale`),
  KEY `SalesEmployeeID_idx` (`employeeIDSale`),
  KEY `SalesBlankID_idx` (`blankIDSale`),
  CONSTRAINT `SalesBlankID` FOREIGN KEY (`blankIDSale`) REFERENCES `blanks` (`blankID`),
  CONSTRAINT `SalesCommissionID` FOREIGN KEY (`commissionIDSale`) REFERENCES `commission` (`CommissionID`),
  CONSTRAINT `SalesCustomerID` FOREIGN KEY (`customerIDSale`) REFERENCES `customeraccount` (`CustomerID`),
  CONSTRAINT `SalesEmployeeID` FOREIGN KEY (`employeeIDSale`) REFERENCES `employeeaccount` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,444,'CASH','Domestic',NULL,99,'2023-04-10',1.8,26,NULL,NULL,1,0,33,1),(2,101,'CASH','Domestic',456789123,57.56,'2023-04-10',2.1,29,NULL,NULL,13,0,34,5),(3,420,'CASH','Domestic',NULL,33,'2023-04-11',NULL,22,NULL,NULL,7,0,33,3),(4,201,'CASH','Domestic',NULL,33,'2023-04-11',NULL,22,'2023-04-11',NULL,11,0,34,4),(5,101,'CASH','Domestic',NULL,33,'2023-04-11',NULL,22,'2023-05-12',NULL,14,0,34,5),(6,420,'CASH','Domestic',NULL,33,'2023-04-11',NULL,22,'2023-06-06',NULL,8,0,34,3),(7,101,'CARD','Global',456789123,101,'2023-04-11',3.2,13.9,NULL,NULL,16,0,33,5);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travelagent`
--

DROP TABLE IF EXISTS `travelagent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travelagent` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelagent`
--

LOCK TABLES `travelagent` WRITE;
/*!40000 ALTER TABLE `travelagent` DISABLE KEYS */;
/*!40000 ALTER TABLE `travelagent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 12:39:57

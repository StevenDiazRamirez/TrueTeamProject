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
  `blankType` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `destFrom` varchar(255) DEFAULT NULL,
  `destTo` varchar(255) DEFAULT NULL,
  `dateRecieved` date DEFAULT NULL,
  `dateAssigned` date DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  PRIMARY KEY (`blankID`),
  KEY `blanks_ibfk_1_idx` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (1,444,'Sold',NULL,NULL,'2023-04-05','2023-04-05',0),(2,444,'Assigned',NULL,NULL,'2023-04-05','2023-04-05',0),(3,444,'Sold',NULL,NULL,'2023-04-05','2023-04-05',0),(4,444,'Assigned',NULL,NULL,'2023-04-05','2023-04-05',1),(5,444,'Assigned',NULL,NULL,'2023-04-05','2023-04-05',0),(6,444,'Assigned',NULL,NULL,'2023-04-05','2023-04-05',0),(7,444,'Sold',NULL,NULL,'2023-04-05','2023-04-05',1),(8,444,'Received',NULL,NULL,'2023-04-05',NULL,NULL),(9,440,'Assigned',NULL,NULL,'2023-04-07','2023-04-07',0),(10,440,'Assigned',NULL,NULL,'2023-04-07','2023-04-07',1),(11,440,'Received',NULL,NULL,'2023-04-07',NULL,NULL),(12,420,'Assigned',NULL,NULL,'2023-04-10','2023-04-10',0),(13,420,'Assigned',NULL,NULL,'2023-04-10','2023-04-10',0),(14,420,'Received',NULL,NULL,'2023-04-10',NULL,NULL),(15,420,'Received',NULL,NULL,'2023-04-10',NULL,NULL);
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission` (
  `CommissionID` int NOT NULL,
  `amount` float NOT NULL,
  `commissionDate` date NOT NULL,
  `commissionEndDate` date DEFAULT NULL,
  `blankType` int NOT NULL,
  PRIMARY KEY (`CommissionID`),
  UNIQUE KEY `CommissionID_UNIQUE` (`CommissionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
INSERT INTO `commission` VALUES (2,12,'2023-04-08','2023-04-15',440),(3,22,'2023-04-10','2023-06-07',201),(4,10,'2023-04-10','2023-05-10',444);
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customeraccount`
--

DROP TABLE IF EXISTS `customeraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customeraccount` (
  `CustomerID` int NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `CustomerEmail` varchar(255) NOT NULL,
  `AccountType` varchar(255) NOT NULL,
  `DiscountID` int DEFAULT NULL,
  `EmployeeID` int DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `CustomerEmail_UNIQUE` (`CustomerEmail`),
  UNIQUE KEY `CustomerID_UNIQUE` (`CustomerID`),
  UNIQUE KEY `DiscountID_UNIQUE` (`DiscountID`),
  CONSTRAINT `customeraccount_ibfk_1` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customeraccount`
--

LOCK TABLES `customeraccount` WRITE;
/*!40000 ALTER TABLE `customeraccount` DISABLE KEYS */;
INSERT INTO `customeraccount` VALUES (1,'George','Mike','dom.mike@gmail.com','Valued',NULL,NULL),(2,'test','fert','test.fert@gmail.com','Valued',NULL,NULL),(3,'Dave','Fence','dave.fence@gmail.com','Customer',NULL,NULL);
/*!40000 ALTER TABLE `customeraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `DiscountID` int NOT NULL,
  `DiscountType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeaccount`
--

DROP TABLE IF EXISTS `employeeaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeeaccount` (
  `EmployeeID` int NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `AccountType` varchar(255) NOT NULL,
  `SaleID` int DEFAULT NULL,
  `CommissionID` int DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `SaleID` (`SaleID`),
  KEY `CommissionID` (`CommissionID`),
  CONSTRAINT `employeeaccount_ibfk_1` FOREIGN KEY (`SaleID`) REFERENCES `sale` (`SaleID`),
  CONSTRAINT `employeeaccount_ibfk_2` FOREIGN KEY (`CommissionID`) REFERENCES `commission` (`CommissionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeaccount`
--

LOCK TABLES `employeeaccount` WRITE;
/*!40000 ALTER TABLE `employeeaccount` DISABLE KEYS */;
INSERT INTO `employeeaccount` VALUES (0,NULL,NULL,'bob','circle','Manager',NULL,NULL);
/*!40000 ALTER TABLE `employeeaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangerates`
--

DROP TABLE IF EXISTS `exchangerates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exchangerates` (
  `exchangeDate` date NOT NULL,
  `currencycode` varchar(3) NOT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `IATANumber` int DEFAULT NULL,
  PRIMARY KEY (`exchangeDate`,`currencycode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerates`
--

LOCK TABLES `exchangerates` WRITE;
/*!40000 ALTER TABLE `exchangerates` DISABLE KEYS */;
/*!40000 ALTER TABLE `exchangerates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixedid`
--

DROP TABLE IF EXISTS `fixedid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixedid` (
  `FixedID` int NOT NULL,
  `DiscountAmount` float DEFAULT NULL,
  `DiscountID` int DEFAULT NULL,
  PRIMARY KEY (`FixedID`),
  KEY `DiscountID` (`DiscountID`),
  CONSTRAINT `fixedid_ibfk_1` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixedid`
--

LOCK TABLES `fixedid` WRITE;
/*!40000 ALTER TABLE `fixedid` DISABLE KEYS */;
/*!40000 ALTER TABLE `fixedid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexibleid`
--

DROP TABLE IF EXISTS `flexibleid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexibleid` (
  `FlexID` int NOT NULL,
  `discountdate` date DEFAULT NULL,
  `LowerDiscount` float DEFAULT NULL,
  `HigherDiscount` float DEFAULT NULL,
  `DiscountAmount` float DEFAULT NULL,
  `DiscountID` int DEFAULT NULL,
  PRIMARY KEY (`FlexID`),
  KEY `DiscountID` (`DiscountID`),
  CONSTRAINT `flexibleid_ibfk_1` FOREIGN KEY (`DiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexibleid`
--

LOCK TABLES `flexibleid` WRITE;
/*!40000 ALTER TABLE `flexibleid` DISABLE KEYS */;
/*!40000 ALTER TABLE `flexibleid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `SaleID` int NOT NULL,
  `blankType` int NOT NULL,
  `PaymentType` varchar(255) NOT NULL,
  `flightType` varchar(45) NOT NULL,
  `exchangeRate` float DEFAULT NULL,
  `taxes` float DEFAULT NULL,
  `PaymentAmount` float NOT NULL,
  `CardNumber` bigint DEFAULT NULL,
  `CardName` varchar(255) DEFAULT NULL,
  `saleDate` date NOT NULL,
  `latePaymentDate` date DEFAULT NULL,
  `latePaymentStatus` varchar(45) DEFAULT NULL,
  `blanksID` int NOT NULL,
  `employeeID` int NOT NULL,
  `customerID` int NOT NULL,
  `commissionID` int NOT NULL,
  PRIMARY KEY (`SaleID`),
  KEY `employeeID_idx` (`employeeID`),
  KEY `customerID_idx` (`customerID`),
  KEY `blanksID_idx` (`blanksID`),
  KEY `commissionID_idx` (`commissionID`),
  CONSTRAINT `blanksID` FOREIGN KEY (`blanksID`) REFERENCES `blanks` (`blankID`),
  CONSTRAINT `CommissionID` FOREIGN KEY (`commissionID`) REFERENCES `commission` (`CommissionID`),
  CONSTRAINT `customerID` FOREIGN KEY (`customerID`) REFERENCES `customeraccount` (`CustomerID`),
  CONSTRAINT `employeeID` FOREIGN KEY (`employeeID`) REFERENCES `employeeaccount` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-10 18:08:56

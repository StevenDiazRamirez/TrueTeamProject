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
  `employeeIDBlank` int DEFAULT NULL,
  PRIMARY KEY (`blankID`,`blankType`),
  KEY `blankEmployeesID_idx` (`employeeIDBlank`) /*!80000 INVISIBLE */,
  CONSTRAINT `blankEmployeesID` FOREIGN KEY (`employeeIDBlank`) REFERENCES `employeeaccount` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (1,101,'Assigned','2019-07-09','2019-07-11',211),(1,201,'Sold','2019-06-03','2019-06-03',250),(1,420,'Assigned','2019-05-08','2019-05-08',250),(1,444,'Sold','2019-04-01','2019-04-01',250),(2,101,'Assigned','2019-07-09','2019-07-11',211),(2,201,'Sold','2019-06-03','2019-06-03',250),(2,420,'Assigned','2019-05-08','2019-05-08',250),(2,444,'Refunded','2019-04-01','2019-04-01',250),(3,101,'Assigned','2019-07-09','2019-07-11',211),(3,201,'Assigned','2019-06-03','2019-06-03',250),(3,420,'Assigned','2019-05-08','2019-05-08',250),(3,444,'Sold','2019-04-01','2019-04-01',250),(4,101,'Assigned','2019-07-09','2019-07-11',211),(4,201,'Assigned','2019-06-03','2019-06-03',250),(4,420,'Assigned','2019-05-08','2019-05-08',250),(4,444,'Sold','2019-04-01','2019-04-01',250),(5,101,'Assigned','2019-07-09','2019-07-11',211),(5,201,'Assigned','2019-06-03','2019-06-03',250),(5,420,'Assigned','2019-05-08','2019-05-08',250),(5,444,'Assigned','2019-04-01','2019-04-01',250),(6,101,'Assigned','2019-07-09','2019-07-11',211),(6,201,'Assigned','2019-06-03','2019-06-03',250),(6,420,'Assigned','2019-05-08','2019-05-08',250),(6,444,'Assigned','2019-04-01','2019-04-01',250),(7,101,'Assigned','2019-07-09','2019-07-11',211),(7,201,'Assigned','2019-06-03','2019-06-03',250),(7,420,'Assigned','2019-05-08','2019-05-08',250),(7,444,'Assigned','2019-04-01','2019-04-01',250),(8,101,'Assigned','2019-07-09','2019-07-11',211),(8,201,'Assigned','2019-06-03','2019-06-03',250),(8,420,'Assigned','2019-05-08','2019-05-08',250),(8,444,'Assigned','2019-04-01','2019-04-01',250),(9,101,'Assigned','2019-07-09','2019-07-11',211),(9,201,'Assigned','2019-06-03','2019-06-03',250),(9,420,'Assigned','2019-05-08','2019-05-08',250),(9,444,'Assigned','2019-04-01','2019-04-01',250),(10,101,'Assigned','2019-07-09','2019-07-11',211),(10,201,'Assigned','2019-06-03','2019-06-03',250),(10,420,'Assigned','2019-05-08','2019-05-08',250),(10,444,'Assigned','2019-04-01','2019-04-01',250),(11,101,'Assigned','2019-07-09','2019-07-11',211),(11,201,'Refunded','2019-06-03','2019-06-15',211),(11,420,'Assigned','2019-05-08','2019-05-08',250),(11,444,'Assigned','2019-04-01','2019-04-01',250),(12,101,'Assigned','2019-07-09','2019-07-11',211),(12,201,'Assigned','2019-06-03','2019-06-15',211),(12,420,'Assigned','2019-05-08','2019-05-08',250),(12,444,'Assigned','2019-04-01','2019-04-01',250),(13,101,'Assigned','2019-07-09','2019-07-11',211),(13,201,'Assigned','2019-06-03','2019-06-15',211),(13,420,'Assigned','2019-05-08','2019-05-08',250),(13,444,'Assigned','2019-04-01','2019-04-01',250),(14,101,'Assigned','2019-07-09','2019-07-11',211),(14,201,'Assigned','2019-06-03','2019-06-15',211),(14,420,'Assigned','2019-05-08','2019-05-08',250),(14,444,'Assigned','2019-04-01','2019-04-01',250),(15,101,'Assigned','2019-07-09','2019-07-11',211),(15,201,'Assigned','2019-06-03','2019-06-15',211),(15,420,'Assigned','2019-05-08','2019-05-08',250),(15,444,'Assigned','2019-04-01','2019-04-01',250),(16,101,'Assigned','2019-07-09','2019-07-11',211),(16,201,'Assigned','2019-06-03','2019-06-15',211),(16,420,'Assigned','2019-05-08','2019-05-08',250),(16,444,'Assigned','2019-04-01','2019-04-01',250),(17,101,'Assigned','2019-07-09','2019-07-11',211),(17,201,'Assigned','2019-06-03','2019-06-15',211),(17,420,'Assigned','2019-05-08','2019-05-08',250),(17,444,'Assigned','2019-04-01','2019-04-01',250),(18,101,'Assigned','2019-07-09','2019-07-11',211),(18,201,'Assigned','2019-06-03','2019-06-15',211),(18,420,'Assigned','2019-05-08','2019-05-08',250),(18,444,'Assigned','2019-04-01','2019-04-01',250),(19,101,'Assigned','2019-07-09','2019-07-11',211),(19,201,'Assigned','2019-06-03','2019-06-15',211),(19,420,'Assigned','2019-05-08','2019-05-08',250),(19,444,'Assigned','2019-04-01','2019-04-01',250),(20,101,'Assigned','2019-07-09','2019-07-11',211),(20,201,'Assigned','2019-06-03','2019-06-15',211),(20,420,'Assigned','2019-05-08','2019-05-08',250),(20,444,'Assigned','2019-04-01','2019-04-01',250),(21,101,'Assigned','2019-07-09','2019-07-11',211),(21,201,'Assigned','2019-06-03','2019-06-15',211),(21,420,'Assigned','2019-05-08','2019-05-08',250),(21,444,'Sold','2019-04-01','2019-04-05',211),(22,101,'Assigned','2019-07-09','2019-07-11',211),(22,201,'Assigned','2019-06-03','2019-06-15',211),(22,420,'Assigned','2019-05-08','2019-05-08',250),(22,444,'Sold','2019-04-01','2019-04-05',211),(23,101,'Assigned','2019-07-09','2019-07-11',211),(23,201,'Assigned','2019-06-03','2019-06-15',211),(23,420,'Assigned','2019-05-08','2019-05-08',250),(23,444,'Assigned','2019-04-01','2019-04-05',211),(24,101,'Assigned','2019-07-09','2019-07-11',211),(24,201,'Assigned','2019-06-03','2019-06-15',211),(24,420,'Assigned','2019-05-08','2019-05-08',250),(24,444,'Assigned','2019-04-01','2019-04-05',211),(25,101,'Assigned','2019-07-09','2019-07-11',211),(25,201,'Assigned','2019-06-03','2019-06-15',211),(25,420,'Assigned','2019-05-08','2019-05-08',250),(25,444,'Assigned','2019-04-01','2019-04-05',211),(26,101,'Assigned','2019-07-09','2019-07-11',211),(26,201,'Received','2019-06-03',NULL,NULL),(26,420,'Assigned','2019-05-08','2019-05-08',250),(26,444,'Assigned','2019-04-01','2019-04-05',211),(27,101,'Assigned','2019-07-09','2019-07-11',211),(27,201,'Received','2019-06-03',NULL,NULL),(27,420,'Assigned','2019-05-08','2019-05-08',250),(27,444,'Assigned','2019-04-01','2019-04-05',211),(28,101,'Assigned','2019-07-09','2019-07-11',211),(28,201,'Received','2019-06-03',NULL,NULL),(28,420,'Assigned','2019-05-08','2019-05-08',250),(28,444,'Assigned','2019-04-01','2019-04-05',211),(29,101,'Assigned','2019-07-09','2019-07-11',211),(29,201,'Received','2019-06-03',NULL,NULL),(29,420,'Assigned','2019-05-08','2019-05-08',250),(29,444,'Assigned','2019-04-01','2019-04-05',211),(30,101,'Assigned','2019-07-09','2019-07-11',211),(30,201,'Received','2019-06-03',NULL,NULL),(30,420,'Assigned','2019-05-08','2019-05-08',250),(30,444,'Assigned','2019-04-01','2019-04-05',211),(31,101,'Assigned','2019-07-09','2019-07-11',211),(31,201,'Received','2019-06-03',NULL,NULL),(31,420,'Assigned','2019-05-08','2019-05-10',211),(31,444,'Assigned','2019-04-01','2019-04-05',211),(32,101,'Assigned','2019-07-09','2019-07-11',211),(32,201,'Received','2019-06-03',NULL,NULL),(32,420,'Assigned','2019-05-08','2019-05-10',211),(32,444,'Assigned','2019-04-01','2019-04-05',211),(33,101,'Assigned','2019-07-09','2019-07-11',211),(33,201,'Received','2019-06-03',NULL,NULL),(33,420,'Assigned','2019-05-08','2019-05-10',211),(33,444,'Assigned','2019-04-01','2019-04-05',211),(34,101,'Assigned','2019-07-09','2019-07-11',211),(34,201,'Received','2019-06-03',NULL,NULL),(34,420,'Assigned','2019-05-08','2019-05-10',211),(34,444,'Assigned','2019-04-01','2019-04-05',211),(35,101,'Assigned','2019-07-09','2019-07-11',211),(35,201,'Received','2019-06-03',NULL,NULL),(35,420,'Assigned','2019-05-08','2019-05-10',211),(35,444,'Assigned','2019-04-01','2019-04-05',211),(36,101,'Assigned','2019-07-09','2019-07-11',211),(36,201,'Received','2019-06-03',NULL,NULL),(36,420,'Assigned','2019-05-08','2019-05-10',211),(36,444,'Assigned','2019-04-01','2019-04-05',211),(37,101,'Assigned','2019-07-09','2019-07-11',211),(37,201,'Received','2019-06-03',NULL,NULL),(37,420,'Assigned','2019-05-08','2019-05-10',211),(37,444,'Assigned','2019-04-01','2019-04-05',211),(38,101,'Assigned','2019-07-09','2019-07-11',211),(38,201,'Received','2019-06-03',NULL,NULL),(38,420,'Assigned','2019-05-08','2019-05-10',211),(38,444,'Assigned','2019-04-01','2019-04-05',211),(39,101,'Assigned','2019-07-09','2019-07-11',211),(39,201,'Received','2019-06-03',NULL,NULL),(39,420,'Assigned','2019-05-08','2019-05-10',211),(39,444,'Assigned','2019-04-01','2019-04-05',211),(40,101,'Assigned','2019-07-09','2019-07-11',211),(40,201,'Received','2019-06-03',NULL,NULL),(40,420,'Assigned','2019-05-08','2019-05-10',211),(40,444,'Assigned','2019-04-01','2019-04-05',211),(41,101,'Assigned','2019-07-09','2019-07-11',211),(41,201,'Received','2019-06-03',NULL,NULL),(41,420,'Assigned','2019-05-08','2019-05-10',211),(41,444,'Received','2019-04-01',NULL,NULL),(42,101,'Assigned','2019-07-09','2019-07-11',211),(42,201,'Received','2019-06-03',NULL,NULL),(42,420,'Assigned','2019-05-08','2019-05-10',211),(42,444,'Received','2019-04-01',NULL,NULL),(43,101,'Assigned','2019-07-09','2019-07-11',211),(43,201,'Received','2019-06-03',NULL,NULL),(43,420,'Assigned','2019-05-08','2019-05-10',211),(43,444,'Received','2019-04-01',NULL,NULL),(44,101,'Assigned','2019-07-09','2019-07-11',211),(44,201,'Received','2019-06-03',NULL,NULL),(44,420,'Assigned','2019-05-08','2019-05-10',211),(44,444,'Received','2019-04-01',NULL,NULL),(45,101,'Assigned','2019-07-09','2019-07-11',211),(45,201,'Received','2019-06-03',NULL,NULL),(45,420,'Assigned','2019-05-08','2019-05-10',211),(45,444,'Received','2019-04-01',NULL,NULL),(46,101,'Assigned','2019-07-09','2019-07-11',211),(46,201,'Received','2019-06-03',NULL,NULL),(46,420,'Assigned','2019-05-08','2019-05-10',211),(46,444,'Received','2019-04-01',NULL,NULL),(47,101,'Assigned','2019-07-09','2019-07-11',211),(47,201,'Received','2019-06-03',NULL,NULL),(47,420,'Assigned','2019-05-08','2019-05-10',211),(47,444,'Received','2019-04-01',NULL,NULL),(48,101,'Assigned','2019-07-09','2019-07-11',211),(48,201,'Received','2019-06-03',NULL,NULL),(48,420,'Assigned','2019-05-08','2019-05-10',211),(48,444,'Received','2019-04-01',NULL,NULL),(49,101,'Assigned','2019-07-09','2019-07-11',211),(49,201,'Received','2019-06-03',NULL,NULL),(49,420,'Assigned','2019-05-08','2019-05-10',211),(49,444,'Received','2019-04-01',NULL,NULL),(50,101,'Assigned','2019-07-09','2019-07-11',211),(50,201,'Received','2019-06-03',NULL,NULL),(50,420,'Assigned','2019-05-08','2019-05-10',211),(50,444,'Received','2019-04-01',NULL,NULL),(51,201,'Received','2019-06-03',NULL,NULL),(51,420,'Received','2019-05-08',NULL,NULL),(51,444,'Received','2019-04-01',NULL,NULL),(52,201,'Received','2019-06-03',NULL,NULL),(52,420,'Received','2019-05-08',NULL,NULL),(52,444,'Received','2019-04-01',NULL,NULL),(53,201,'Received','2019-06-03',NULL,NULL),(53,420,'Received','2019-05-08',NULL,NULL),(53,444,'Received','2019-04-01',NULL,NULL),(54,201,'Received','2019-06-03',NULL,NULL),(54,420,'Received','2019-05-08',NULL,NULL),(54,444,'Received','2019-04-01',NULL,NULL),(55,201,'Received','2019-06-03',NULL,NULL),(55,420,'Received','2019-05-08',NULL,NULL),(55,444,'Received','2019-04-01',NULL,NULL),(56,201,'Received','2019-06-03',NULL,NULL),(56,420,'Received','2019-05-08',NULL,NULL),(56,444,'Received','2019-04-01',NULL,NULL),(57,201,'Received','2019-06-03',NULL,NULL),(57,420,'Received','2019-05-08',NULL,NULL),(57,444,'Received','2019-04-01',NULL,NULL),(58,201,'Received','2019-06-03',NULL,NULL),(58,420,'Received','2019-05-08',NULL,NULL),(58,444,'Received','2019-04-01',NULL,NULL),(59,201,'Received','2019-06-03',NULL,NULL),(59,420,'Received','2019-05-08',NULL,NULL),(59,444,'Received','2019-04-01',NULL,NULL),(60,201,'Received','2019-06-03',NULL,NULL),(60,420,'Received','2019-05-08',NULL,NULL),(60,444,'Received','2019-04-01',NULL,NULL),(61,201,'Received','2019-06-03',NULL,NULL),(61,420,'Received','2019-05-08',NULL,NULL),(61,444,'Received','2019-04-01',NULL,NULL),(62,201,'Received','2019-06-03',NULL,NULL),(62,420,'Received','2019-05-08',NULL,NULL),(62,444,'Received','2019-04-01',NULL,NULL),(63,201,'Received','2019-06-03',NULL,NULL),(63,420,'Received','2019-05-08',NULL,NULL),(63,444,'Received','2019-04-01',NULL,NULL),(64,201,'Received','2019-06-03',NULL,NULL),(64,420,'Received','2019-05-08',NULL,NULL),(64,444,'Received','2019-04-01',NULL,NULL),(65,201,'Received','2019-06-03',NULL,NULL),(65,420,'Received','2019-05-08',NULL,NULL),(65,444,'Received','2019-04-01',NULL,NULL),(66,201,'Received','2019-06-03',NULL,NULL),(66,420,'Received','2019-05-08',NULL,NULL),(66,444,'Received','2019-04-01',NULL,NULL),(67,201,'Received','2019-06-03',NULL,NULL),(67,420,'Received','2019-05-08',NULL,NULL),(67,444,'Received','2019-04-01',NULL,NULL),(68,201,'Received','2019-06-03',NULL,NULL),(68,420,'Received','2019-05-08',NULL,NULL),(68,444,'Received','2019-04-01',NULL,NULL),(69,201,'Received','2019-06-03',NULL,NULL),(69,420,'Received','2019-05-08',NULL,NULL),(69,444,'Received','2019-04-01',NULL,NULL),(70,201,'Received','2019-06-03',NULL,NULL),(70,420,'Received','2019-05-08',NULL,NULL),(70,444,'Received','2019-04-01',NULL,NULL),(71,201,'Received','2019-06-03',NULL,NULL),(71,420,'Received','2019-05-08',NULL,NULL),(71,444,'Received','2019-04-01',NULL,NULL),(72,201,'Received','2019-06-03',NULL,NULL),(72,420,'Received','2019-05-08',NULL,NULL),(72,444,'Received','2019-04-01',NULL,NULL),(73,201,'Received','2019-06-03',NULL,NULL),(73,420,'Received','2019-05-08',NULL,NULL),(73,444,'Received','2019-04-01',NULL,NULL),(74,201,'Received','2019-06-03',NULL,NULL),(74,420,'Received','2019-05-08',NULL,NULL),(74,444,'Received','2019-04-01',NULL,NULL),(75,201,'Received','2019-06-03',NULL,NULL),(75,420,'Received','2019-05-08',NULL,NULL),(75,444,'Received','2019-04-01',NULL,NULL),(76,201,'Received','2019-06-03',NULL,NULL),(76,420,'Received','2019-05-08',NULL,NULL),(76,444,'Received','2019-04-01',NULL,NULL),(77,201,'Received','2019-06-03',NULL,NULL),(77,420,'Received','2019-05-08',NULL,NULL),(77,444,'Received','2019-04-01',NULL,NULL),(78,201,'Received','2019-06-03',NULL,NULL),(78,420,'Received','2019-05-08',NULL,NULL),(78,444,'Received','2019-04-01',NULL,NULL),(79,201,'Received','2019-06-03',NULL,NULL),(79,420,'Received','2019-05-08',NULL,NULL),(79,444,'Received','2019-04-01',NULL,NULL),(80,201,'Received','2019-06-03',NULL,NULL),(80,420,'Received','2019-05-08',NULL,NULL),(80,444,'Received','2019-04-01',NULL,NULL),(81,201,'Received','2019-06-03',NULL,NULL),(81,420,'Received','2019-05-08',NULL,NULL),(81,444,'Received','2019-04-01',NULL,NULL),(82,201,'Received','2019-06-03',NULL,NULL),(82,420,'Received','2019-05-08',NULL,NULL),(82,444,'Received','2019-04-01',NULL,NULL),(83,201,'Received','2019-06-03',NULL,NULL),(83,420,'Received','2019-05-08',NULL,NULL),(83,444,'Received','2019-04-01',NULL,NULL),(84,201,'Received','2019-06-03',NULL,NULL),(84,420,'Received','2019-05-08',NULL,NULL),(84,444,'Received','2019-04-01',NULL,NULL),(85,201,'Received','2019-06-03',NULL,NULL),(85,420,'Received','2019-05-08',NULL,NULL),(85,444,'Received','2019-04-01',NULL,NULL),(86,201,'Received','2019-06-03',NULL,NULL),(86,420,'Received','2019-05-08',NULL,NULL),(86,444,'Received','2019-04-01',NULL,NULL),(87,201,'Received','2019-06-03',NULL,NULL),(87,420,'Received','2019-05-08',NULL,NULL),(87,444,'Received','2019-04-01',NULL,NULL),(88,201,'Received','2019-06-03',NULL,NULL),(88,420,'Received','2019-05-08',NULL,NULL),(88,444,'Received','2019-04-01',NULL,NULL),(89,201,'Received','2019-06-03',NULL,NULL),(89,420,'Received','2019-05-08',NULL,NULL),(89,444,'Received','2019-04-01',NULL,NULL),(90,201,'Received','2019-06-03',NULL,NULL),(90,420,'Received','2019-05-08',NULL,NULL),(90,444,'Received','2019-04-01',NULL,NULL),(91,201,'Received','2019-06-03',NULL,NULL),(91,420,'Received','2019-05-08',NULL,NULL),(91,444,'Received','2019-04-01',NULL,NULL),(92,201,'Received','2019-06-03',NULL,NULL),(92,420,'Received','2019-05-08',NULL,NULL),(92,444,'Received','2019-04-01',NULL,NULL),(93,201,'Received','2019-06-03',NULL,NULL),(93,420,'Received','2019-05-08',NULL,NULL),(93,444,'Received','2019-04-01',NULL,NULL),(94,201,'Received','2019-06-03',NULL,NULL),(94,420,'Received','2019-05-08',NULL,NULL),(94,444,'Received','2019-04-01',NULL,NULL),(95,201,'Received','2019-06-03',NULL,NULL),(95,420,'Received','2019-05-08',NULL,NULL),(95,444,'Received','2019-04-01',NULL,NULL),(96,201,'Received','2019-06-03',NULL,NULL),(96,420,'Received','2019-05-08',NULL,NULL),(96,444,'Received','2019-04-01',NULL,NULL),(97,201,'Received','2019-06-03',NULL,NULL),(97,420,'Received','2019-05-08',NULL,NULL),(97,444,'Received','2019-04-01',NULL,NULL),(98,201,'Received','2019-06-03',NULL,NULL),(98,420,'Received','2019-05-08',NULL,NULL),(98,444,'Received','2019-04-01',NULL,NULL),(99,201,'Received','2019-06-03',NULL,NULL),(99,420,'Received','2019-05-08',NULL,NULL),(99,444,'Received','2019-04-01',NULL,NULL),(100,201,'Received','2019-06-03',NULL,NULL),(100,420,'Received','2019-05-08',NULL,NULL),(100,444,'Received','2019-04-01',NULL,NULL);
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
INSERT INTO `commission` VALUES (1,9,'2023-01-01','2023-04-01',444),(2,5,'2023-01-01','2023-04-01',201);
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
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
  `blankType` int NOT NULL,
  `destFrom1` varchar(45) DEFAULT NULL,
  `destTo1` varchar(45) DEFAULT NULL,
  `destFrom2` varchar(45) DEFAULT NULL,
  `destTo2` varchar(45) DEFAULT NULL,
  `blankIDCoupons` int NOT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponID_UNIQUE` (`couponID`),
  KEY `blankIDCoupons_idx` (`blankIDCoupons`,`blankType`),
  CONSTRAINT `blankIDCoupons` FOREIGN KEY (`blankIDCoupons`, `blankType`) REFERENCES `blanks` (`blankID`, `blankType`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (1,'Flight',444,'USA','Canada','Canada','Mexico',1),(2,'Flight',444,'uk','france','france','spain',2),(3,'Flight',444,'india','pakistan','pakistan','china',3),(4,'Flight',444,'Germany','Dubai','Dubai','Italy',4),(5,'Flight',201,'USA','Mexico','null','null',2),(6,'Flight',444,'Italy','France','France','UK',21),(7,'Flight',444,'UAE','Jordan','Jordan','Spain',22),(8,'Flight',201,'Belgium','Germany','null','null',11);
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
  `DiscountIDCustomer` int DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `CustomerEmail_UNIQUE` (`CustomerEmail`),
  KEY `discountID_idx` (`DiscountIDCustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customeraccount`
--

LOCK TABLES `customeraccount` WRITE;
/*!40000 ALTER TABLE `customeraccount` DISABLE KEYS */;
INSERT INTO `customeraccount` VALUES (1,'Chris','Smart','chris.smart@gmail.com','Valued',1),(2,'David','Dodson','david.dodson@gmail.com','Valued',2),(3,'Sarah','Broklehurst','sarah.broklehurst@gmail.com','Valued',3),(4,'Dominic','Beatty','dominic.beatty@gmail.com','Regular',NULL),(5,'Mike','Ross','mike.ross@gmail.com','Customer',NULL),(6,'harvey','specter','harvey.specter@gmail.com','Customer',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'Fixed'),(2,'Flexible'),(3,'Fixed');
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
INSERT INTO `employeeaccount` VALUES (211,'Dennis','Menace','dennis','Gnasher','Travel Advisor'),(220,'Minnie','Minx','minnie','NotiGirl','Manager'),(250,'Penelope','Pitstop','penelope','PinkMobile','Travel Advisor'),(320,'Arthur','Daley','arthur','LiesaLot','Admin');
/*!40000 ALTER TABLE `employeeaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixeddiscount`
--

DROP TABLE IF EXISTS `fixeddiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixeddiscount` (
  `fixedID` int NOT NULL AUTO_INCREMENT,
  `fixedDiscountID` int DEFAULT NULL,
  `amount` decimal(4,2) NOT NULL,
  PRIMARY KEY (`fixedID`),
  UNIQUE KEY `fixedDiscountID_UNIQUE` (`fixedDiscountID`),
  KEY `fixedDiscountID_idx` (`fixedDiscountID`),
  CONSTRAINT `fixedDiscountID` FOREIGN KEY (`fixedDiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixeddiscount`
--

LOCK TABLES `fixeddiscount` WRITE;
/*!40000 ALTER TABLE `fixeddiscount` DISABLE KEYS */;
INSERT INTO `fixeddiscount` VALUES (1,1,1.00),(2,3,2.00);
/*!40000 ALTER TABLE `fixeddiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexiblediscount`
--

DROP TABLE IF EXISTS `flexiblediscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexiblediscount` (
  `flexibleID` int NOT NULL AUTO_INCREMENT,
  `flexibleDiscountID` int NOT NULL,
  `discountAmount` float NOT NULL,
  PRIMARY KEY (`flexibleID`),
  KEY `flexibleDiscountID_idx` (`flexibleDiscountID`),
  CONSTRAINT `flexibleDiscountID` FOREIGN KEY (`flexibleDiscountID`) REFERENCES `discount` (`DiscountID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblediscount`
--

LOCK TABLES `flexiblediscount` WRITE;
/*!40000 ALTER TABLE `flexiblediscount` DISABLE KEYS */;
INSERT INTO `flexiblediscount` VALUES (1,2,2);
/*!40000 ALTER TABLE `flexiblediscount` ENABLE KEYS */;
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
  `cardDetails` varchar(45) DEFAULT NULL,
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
INSERT INTO `sale` VALUES (1,444,'CASH','Global',NULL,215.6,'2023-01-01',0.54,58,NULL,NULL,1,250,3,1),(2,444,'CARD','Global','4901000223453456',230,'2023-01-01',0.54,98,NULL,NULL,2,250,5,1),(3,201,'CASH','Domestic',NULL,86,'2023-01-01',NULL,15.6,NULL,NULL,1,250,6,2),(4,444,'CASH','Global',NULL,215.6,'2023-02-02',0.43,138,'2023-03-02','Failed to pay',3,250,2,1),(5,444,'CASH','Domestic',NULL,227.7,'2023-02-02',NULL,58,'2023-03-02','Failed to pay',4,250,1,1),(6,201,'CARD','Domestic','6454986387338876 ',75,'2023-02-02',NULL,13.8,NULL,NULL,2,250,5,2),(7,444,'CARD','Global','5301023456981234',245,'2023-02-02',0.43,60,'2023-02-02','Paid',21,211,3,1),(8,444,'CARD','Global','7449155545893456',300,'2023-02-02',0.43,65,NULL,NULL,22,211,6,1),(9,201,'CASH','Domestic',NULL,75,'2023-02-02',NULL,13.8,NULL,NULL,11,211,5,2);
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

-- Dump completed on 2023-04-13  6:02:40

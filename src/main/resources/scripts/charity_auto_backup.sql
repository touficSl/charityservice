-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: charity
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `charities`
--

DROP TABLE IF EXISTS `charities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `projectid` bigint NOT NULL,
  `username` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `paymentreference` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `paymentstatus` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_reference` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `payment_status` varchar(300) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8eahuejhet8vsavdjosfy3331` (`paymentreference`),
  UNIQUE KEY `UK_okqwr08xrdoidk7p8obor7h5` (`payment_reference`),
  KEY `FK6sn7xan83ybq4h58acdovtsrd` (`projectid`),
  CONSTRAINT `FK6sn7xan83ybq4h58acdovtsrd` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='User charities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charities`
--

LOCK TABLES `charities` WRITE;
/*!40000 ALTER TABLE `charities` DISABLE KEYS */;
/*!40000 ALTER TABLE `charities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_images`
--

DROP TABLE IF EXISTS `project_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `projectid` bigint NOT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `path` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order` int DEFAULT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKldmuvu8ss0o8lp2vpy34p796e` (`projectid`),
  CONSTRAINT `FKldmuvu8ss0o8lp2vpy34p796e` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='images';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_images`
--

LOCK TABLES `project_images` WRITE;
/*!40000 ALTER TABLE `project_images` DISABLE KEYS */;
INSERT INTO `project_images` VALUES (1,3,'PRJT__20250417210921419logo.png',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250417210921419logo.png',NULL,'2025-04-17 21:09:21'),(3,2,'PRJT__20250417212639049logo2.png',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250417212639049logo2.png',NULL,'2025-04-17 21:26:39'),(4,2,'PRJT__20250417212639118favicon.ico',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250417212639118favicon.ico',NULL,'2025-04-17 21:26:39'),(5,3,'PRJT__20250417212710626loading.gif',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250417212710626loading.gif',NULL,'2025-04-17 21:27:10'),(6,8,'PRJT__20250417215000532loading.gif',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250417215000532loading.gif',NULL,'2025-04-17 21:50:00'),(7,14,'PRJT__20250419182953136logo2.png',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250419182953136logo2.png',NULL,'2025-04-19 18:29:53'),(8,14,'PRJT__20250419183027622logo2.png',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250419183027622logo2.png',NULL,'2025-04-19 18:30:27'),(9,14,'PRJT__20250419183027655loading.gif',NULL,'http://localhost:8080/charity/api/public/project/download/file/PRJT__20250419183027655loading.gif',NULL,'2025-04-19 18:30:27');
/*!40000 ALTER TABLE `project_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reference` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `estimation_time` date DEFAULT NULL,
  `cost` decimal(10,2) NOT NULL,
  `total_charity_amount` decimal(10,2) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `status` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8eahuejhet8vsavdjosfy3331` (`reference`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='projects for charity';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (2,'PRCT_734af55c-c6c0-4132-97e2-dd5df7ad4bcc','ssdf','sdfdsf',NULL,1.00,0.00,'2025-04-17 20:16:31','2','1',_binary ''),(3,'PRCT_3c4822cc-f1e5-4621-867f-3c9d29a6596c','','',NULL,1.00,0.00,'2025-04-17 20:17:43','','',_binary ''),(4,'PRCT_1e562a4f-ef21-4507-b66c-81509bfe423d','dd','33',NULL,234.00,0.00,'2025-04-17 21:31:07','23','3',_binary ''),(5,'PRCT_2d5d5c11-09f0-4ec0-8a54-490f130bb584','jjj','hj',NULL,22.00,0.00,'2025-04-17 21:32:10','','',_binary ''),(6,'PRCT_da7fe51b-5a34-4eaf-923a-1a4cf64ca05c','uuuuuuuuuuuuu','',NULL,1.00,0.00,'2025-04-17 21:34:10','','',_binary ''),(7,'PRCT_e5fa0c2d-f175-400f-95f2-f4e6ff048d25','sss','',NULL,1.00,0.00,'2025-04-17 21:38:22','','',_binary ''),(8,'PRCT_cae8e2da-ea13-4617-99e4-06c81cb47648','ff','',NULL,1.00,0.00,'2025-04-17 21:46:32','','',_binary ''),(9,'PRCT_aa9ef89c-1975-4060-9268-6d7b6b70c31a','ssssssssssssssssssssssssssssssss','',NULL,1.00,0.00,'2025-04-17 21:50:21','','',_binary ''),(10,'PRCT_c798b966-a5bd-4ccf-8748-fb2b8fb04d05','aa11','aa11',NULL,1.00,0.00,'2025-04-19 18:14:45','','',_binary ''),(11,'PRCT_7774b37e-1a8c-4b69-9634-f4d1db3e68e2','a22','a22',NULL,1.00,0.00,'2025-04-19 18:17:27','','',_binary ''),(12,'PRCT_39e91689-4b22-4846-840c-6b9fa5c50964','1','',NULL,1.00,0.00,'2025-04-19 18:19:30','','',_binary ''),(13,'PRCT_1a21737c-fdd8-47c6-ba0a-c0138e95d5d5','a','',NULL,1.00,0.00,'2025-04-19 18:23:31','','',_binary ''),(14,'PRCT_31fc1589-2fb7-4d59-a1d4-32784846cc83','b1','1','2025-04-19',12.00,0.00,'2025-04-19 18:29:34','3','4',_binary '');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adminkey` varchar(255) DEFAULT NULL,
  `isdefault` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-19 18:45:16

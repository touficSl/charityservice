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
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_reference` varchar(700) COLLATE utf8mb4_general_ci NOT NULL,
  `payment_status` varchar(300) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_okqwr08xrdoidk7p8obor7h5` (`payment_reference`),
  KEY `FK6sn7xan83ybq4h58acdovtsrd` (`projectid`),
  CONSTRAINT `FK6sn7xan83ybq4h58acdovtsrd` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='User charities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charities`
--

LOCK TABLES `charities` WRITE;
/*!40000 ALTER TABLE `charities` DISABLE KEYS */;
INSERT INTO `charities` VALUES (1,9,'toufic@gmail,com',25.00,'2025-05-28 20:05:37','f2849744-1829-4da2-bb5b-be4a1328ae31','COMPLETED'),(2,9,'toufic@gmail.com',50.00,'2025-05-28 20:42:16','372dfc8e-8c33-4e6d-8cbd-db1944704497','COMPLETED'),(3,9,'toufic@gmail,com',25.00,'2025-05-28 20:47:11','f19c52e0-c182-4dc6-ac54-afdf8f26519e','COMPLETED'),(4,9,'toufic@gmail,com',100.00,'2025-05-28 20:48:01','8926700d-eaea-4bdb-b7ed-e0e0e3fd6c42','COMPLETED'),(5,9,'toufic@gmail,com',100.00,'2025-05-28 20:55:44','602adff5-3a0b-46c4-a928-70c7369ae848','COMPLETED'),(6,9,'toufic@gmail,com',10.00,'2025-05-28 21:01:56','a8d0b979-64b9-48e3-bec3-b29ab3f3d933','COMPLETED'),(7,9,'toufic@gmail,com',25.00,'2025-05-28 21:03:51','9a6b0e9a-3c30-423e-a284-0b742cf4f78e','COMPLETED'),(8,9,'touficsleiman.lb@gmail.com',50.00,'2025-06-02 16:26:09','e9f7fe61-c16b-4911-8b47-3e6711884918','COMPLETED'),(9,9,'touficsleiman.lb@gmail.com',50.00,'2025-06-02 16:40:04','93bd5038-f55b-4432-a709-d164de850c2c','COMPLETED'),(11,9,'touficsleiman.lb@gmail.com',50.00,'2025-06-02 17:24:01','3b347d63-d066-4e14-bc0c-91f91343b5ac','COMPLETED'),(13,9,'touficsleiman.lb@gmail.com',25.00,'2025-06-02 18:44:00','4d5a0061-b785-42f7-a0d5-4827418f5fce','COMPLETED'),(14,9,'touficsleiman.lb@gmail.com',50.00,'2025-06-02 19:22:46','bcb7f0cc-d8df-4e5b-9e4f-7b933e527d84','COMPLETED'),(15,9,'touficsleiman.lb@gmail.com',100.00,'2025-06-02 19:43:41','93b4583e-8021-4f93-8d14-a6585fb0a7df','COMPLETED'),(16,9,'touficsleiman.lb@gmail.com',25.00,'2025-06-02 19:52:03','489a5c4a-d70f-4280-a71c-3e6964306656','COMPLETED'),(17,9,'touficsleiman.lb@gmail.com',100.00,'2025-06-02 20:08:01','d8054aba-fb06-4302-8564-de18f2851c19','COMPLETED'),(18,9,'touficsleiman.lb@gmail.com',25.00,'2025-06-02 20:17:10','3027d977-346d-4241-9c6c-05fefd654378','COMPLETED'),(19,9,'touficsleiman.lb@gmail.com',25.00,'2025-06-02 20:51:15','f77740f9-1509-4394-9de1-2e0a5da4cce4','COMPLETED'),(20,9,'tsleiman.freelance@gmail.com',10.00,'2025-06-02 20:54:09','159e1922-83ae-45d0-98c9-05d3b93416f2','COMPLETED'),(21,9,'tsleiman.freelance@gmail.com',25.00,'2025-06-02 21:07:22','095e054a-4a67-44dd-b4b5-7260f936a9ea','COMPLETED'),(22,9,'touficsleiman.lb@gmail.com',10.00,'2025-06-03 18:27:50','262934fb-5bc9-457d-beef-fe3622a9144c','COMPLETED'),(23,9,'touficsleiman.lb@gmail.com',25.00,'2025-06-03 20:17:47','ee3555b2-6e6b-4917-ad2e-8cb52ce269fd','COMPLETED'),(24,9,'tsleiman.freelance@gmail.com',25.00,'2025-06-03 21:49:49','20c47785-fadc-47d1-aee7-373c35b3d857','COMPLETED'),(25,9,'touficsleiman.lb@gmail.com',60.20,'2025-06-05 12:34:27','3ce2341f-a4a8-4419-b332-e21f5560f69d','COMPLETED'),(26,19,'ts.spsa@gmail.com',15.50,'2025-06-05 15:17:36','648a4cd4-66b1-4016-98f3-cd86e043242e','COMPLETED'),(27,9,'ts.spsa@gmail.com',109.80,'2025-06-05 15:40:20','14c36d6f-9216-4aa3-9886-980675c6013c','COMPLETED'),(28,20,'tsleiman.freelance@gmail.com',15.00,'2025-06-05 15:52:34','02c8e456-db89-46de-a658-8955c5a1c2fd','COMPLETED');
/*!40000 ALTER TABLE `charities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentsessions`
--

DROP TABLE IF EXISTS `paymentsessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentsessions` (
  `id` varchar(700) NOT NULL,
  `username` varchar(1000) DEFAULT NULL,
  `name` varchar(450) DEFAULT NULL,
  `message` text,
  `status` varchar(450) DEFAULT NULL,
  `datetime` datetime(6) DEFAULT NULL,
  `thirdpartystatus` varchar(450) DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `projectid` bigint NOT NULL,
  `thirdpartyerror` text,
  `registeruser` bit(1) DEFAULT b'0',
  `email` varchar(750) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentsessions`
--

LOCK TABLES `paymentsessions` WRITE;
/*!40000 ALTER TABLE `paymentsessions` DISABLE KEYS */;
INSERT INTO `paymentsessions` VALUES ('02c8e456-db89-46de-a658-8955c5a1c2fd','tsleiman.freelance@gmail.com','toufic','','COMPLETED','2025-06-05 15:52:16.988000','checkout.session.completed',15.00,20,NULL,_binary '\0','touficsleiman.lb@gmail.com'),('095e054a-4a67-44dd-b4b5-7260f936a9ea','tsleiman.freelance@gmail.com','toufic','','COMPLETED','2025-06-02 21:06:56.742000','checkout.session.completed',25.00,9,NULL,_binary '','tsleiman.freelance@gmail.com'),('14c36d6f-9216-4aa3-9886-980675c6013c','ts.spsa@gmail.com','TS SPSA','','COMPLETED','2025-06-05 15:40:08.166000','checkout.session.completed',109.80,9,NULL,_binary '','ts.spsa@gmail.com'),('159e1922-83ae-45d0-98c9-05d3b93416f2','tsleiman.freelance@gmail.com','te','','COMPLETED','2025-06-02 20:53:56.409000','checkout.session.completed',10.00,9,NULL,_binary '','tsleiman.freelance@gmail.com'),('20c47785-fadc-47d1-aee7-373c35b3d857','tsleiman.freelance@gmail.com','toufic','tsleiman.freelance@gmail.com','COMPLETED','2025-06-03 21:49:34.041000','checkout.session.completed',25.00,9,NULL,_binary '','tsleiman.freelance@gmail.com'),('262934fb-5bc9-457d-beef-fe3622a9144c','touficsleiman.lb@gmail.com','k','','COMPLETED','2025-06-03 18:27:33.907000','checkout.session.completed',10.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('3027d977-346d-4241-9c6c-05fefd654378','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 20:16:56.737000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('372dfc8e-8c33-4e6d-8cbd-db1944704497','toufic@gmail.com','toufic','','COMPLETED','2025-05-28 19:41:42.502000','checkout.session.completed',50.00,9,NULL,_binary '','touficsleyman@gmail.com'),('3b347d63-d066-4e14-bc0c-91f91343b5ac','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 17:23:29.144000','checkout.session.completed',50.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('3ce2341f-a4a8-4419-b332-e21f5560f69d','touficsleiman.lb@gmail.com','k','dd','COMPLETED','2025-06-05 12:34:10.490000','checkout.session.completed',60.20,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('434828b0-0890-46e4-8993-a895f4287375','touficsleiman.lb@gmail.com','toufic','','PENDING','2025-06-02 16:53:02.409000',NULL,50.00,9,NULL,_binary '',NULL),('489a5c4a-d70f-4280-a71c-3e6964306656','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 19:51:45.503000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('4d5a0061-b785-42f7-a0d5-4827418f5fce','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 18:43:03.530000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('602adff5-3a0b-46c4-a928-70c7369ae848','toufic@gmail,com','toufic','','COMPLETED','2025-05-28 19:54:36.981000','checkout.session.completed',100.00,9,NULL,_binary '','touficsleyman@gmail.com'),('648a4cd4-66b1-4016-98f3-cd86e043242e','ts.spsa@gmail.com','TS SPSA','We want to help people','COMPLETED','2025-06-05 15:17:21.519000','checkout.session.completed',15.50,19,NULL,_binary '','ts.spsa@gmail.com'),('660bcfc3-0723-49b7-99be-f35900b469bb','touficsleiman.lb@gmail.com','te','','PENDING','2025-06-02 17:04:28.884000',NULL,10.00,9,NULL,_binary '',NULL),('8926700d-eaea-4bdb-b7ed-e0e0e3fd6c42','toufic@gmail,com','toufic','','COMPLETED','2025-05-28 20:47:45.344000','checkout.session.completed',100.00,9,NULL,_binary '','touficsleyman@gmail.com'),('93b4583e-8021-4f93-8d14-a6585fb0a7df','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 19:43:23.603000','checkout.session.completed',100.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('93bd5038-f55b-4432-a709-d164de850c2c','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 16:39:25.152000','checkout.session.completed',50.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('9a6b0e9a-3c30-423e-a284-0b742cf4f78e','toufic@gmail,com','toufic','','COMPLETED','2025-05-28 20:02:57.865000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleyman@gmail.com'),('a8d0b979-64b9-48e3-bec3-b29ab3f3d933','toufic@gmail,com','toufic','testing dcharity','COMPLETED','2025-05-28 20:00:40.168000','checkout.session.completed',10.00,9,NULL,_binary '','touficsleyman@gmail.com'),('bcb7f0cc-d8df-4e5b-9e4f-7b933e527d84','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 19:22:21.402000','checkout.session.completed',50.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('d8054aba-fb06-4302-8564-de18f2851c19','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 20:07:44.619000','checkout.session.completed',100.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('e148ce12-a9b6-4a5d-9e00-c5636a162705','touficsleiman.lb@gmail.com','toufic','','PENDING','2025-06-02 17:02:21.032000',NULL,50.00,9,NULL,_binary '',NULL),('e9f7fe61-c16b-4911-8b47-3e6711884918','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 16:25:20.968000','checkout.session.completed',50.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('ee3555b2-6e6b-4917-ad2e-8cb52ce269fd','touficsleiman.lb@gmail.com','sss','','COMPLETED','2025-06-03 20:17:22.138000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleyman@gmail.com'),('f19c52e0-c182-4dc6-ac54-afdf8f26519e','toufic@gmail,com','toufic','','COMPLETED','2025-05-28 20:46:49.017000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleyman@gmail.com'),('f2849744-1829-4da2-bb5b-be4a1328ae31','toufic@gmail,com','toufic','','COMPLETED','2025-05-28 20:05:12.539000','checkout.session.completed',25.00,9,NULL,_binary '',NULL),('f77740f9-1509-4394-9de1-2e0a5da4cce4','touficsleiman.lb@gmail.com','toufic','','COMPLETED','2025-06-02 20:50:58.105000','checkout.session.completed',25.00,9,NULL,_binary '','touficsleiman.lb@gmail.com'),('fbf095fa-e832-4aac-95e0-c36ba4b00da6','ts.spsa@gmail.com','SAMI','sami wants to donate to brothersss','PENDING','2025-06-05 15:06:13.871000',NULL,15.50,17,NULL,_binary '',NULL);
/*!40000 ALTER TABLE `paymentsessions` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='images';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_images`
--

LOCK TABLES `project_images` WRITE;
/*!40000 ALTER TABLE `project_images` DISABLE KEYS */;
INSERT INTO `project_images` VALUES (19,9,'PRJT__20250605144232236african_woman_pouring_water_recipient_outdoors.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605144232236african_woman_pouring_water_recipient_outdoors.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605144232236african_woman_pouring_water_recipient_outdoors.jpg',0,'2025-06-05 14:42:32'),(20,9,'PRJT__20250605144410401group_people_volunteering_foodbank_poor_people.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605144410401group_people_volunteering_foodbank_poor_people.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605144410401group_people_volunteering_foodbank_poor_people.jpg',0,'2025-06-05 14:44:10'),(29,19,'PRJT__20250605151226596group_african_kids_paying_attention_class.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226596group_african_kids_paying_attention_class.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226596group_african_kids_paying_attention_class.jpg',0,'2025-06-05 15:12:26'),(30,19,'PRJT__20250605151226673close_up_volunteer_oganizing_stuff_donation.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226673close_up_volunteer_oganizing_stuff_donation.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226673close_up_volunteer_oganizing_stuff_donation.jpg',0,'2025-06-05 15:12:26'),(31,19,'PRJT__20250605151226706medium_shot_people_collecting_foodstuff.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226706medium_shot_people_collecting_foodstuff.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226706medium_shot_people_collecting_foodstuff.jpg',0,'2025-06-05 15:12:26'),(32,19,'PRJT__20250605151226725medium_shot_volunteers_with_clothing_donations.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226725medium_shot_volunteers_with_clothing_donations.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605151226725medium_shot_volunteers_with_clothing_donations.jpg',0,'2025-06-05 15:12:26'),(33,20,'PRJT__20250605154942188africa_humanitarian_aid_doctor.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605154942188africa_humanitarian_aid_doctor.jpg','http://mission.westeurope.cloudapp.azure.com:8080/charity/api/public/project/download/file/PRJT__20250605154942188africa_humanitarian_aid_doctor.jpg',0,'2025-06-05 15:49:42');
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
  `cost` decimal(10,2) NOT NULL,
  `total_charity_amount` decimal(10,2) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `status` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` bit(1) DEFAULT b'1',
  `currency` varchar(45) COLLATE utf8mb4_general_ci DEFAULT 'aed',
  `startdate` datetime(6) DEFAULT NULL,
  `enddate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8eahuejhet8vsavdjosfy3331` (`reference`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='projects for charity';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (9,'PRCT_aa9ef89c-1975-4060-9268-6d7b6b70c31a','Donate water for South Africa Sahara','People in south Africa are dying due to the hot weather, we are planning to provide water supply to them to reduce this problem.',1100.00,1100.00,'2025-04-17 21:50:21','','Fully Funded',_binary '','aed','2025-04-22 00:00:00.000000','2025-08-26 00:00:00.000000'),(19,'PRCT_db8d70f4-fd75-4a0a-ab27-ec51862f229f','Donate Food, Clothes and Education Materials for South Africa','People in South Africa needs a lot of attention that we are in 2025 and these people still don&apos;t have clothes to wear. We will help as much as we can, we appreciate your donations to offer all the help needed to these people',3000.00,15.50,'2025-06-05 15:12:26','','Lunching',_binary '','usd','2025-06-05 00:00:00.000000','2026-04-02 00:00:00.000000'),(20,'PRCT_ae4242ab-bc75-4217-86c7-16fd60e11594','Make Vaccination for People','Make sure that all people in the company have the vaccination',1000.00,15.00,'2025-06-05 15:49:42','','Lunching',_binary '','usd','2025-06-05 00:00:00.000000','2025-07-31 00:00:00.000000');
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

-- Dump completed on 2025-06-05 16:05:19

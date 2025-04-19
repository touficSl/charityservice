-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: auth
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
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `user_role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `api` varchar(350) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `menuauthid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enable` bit(1) DEFAULT b'1',
  PRIMARY KEY (`user_role`,`api`,`menuauthid`),
  UNIQUE KEY `uniquefield` (`user_role`,`menuauthid`,`api`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='User authorization';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization`
--

LOCK TABLES `authorization` WRITE;
/*!40000 ALTER TABLE `authorization` DISABLE KEYS */;
INSERT INTO `authorization` VALUES ('Admin','/api/admin/auth/type/list','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/menu/list','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/role/list','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/role/list','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/role/menu/list','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/role/menu/remove','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/role/menu/save','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/settings/details','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/settings/update','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/team/list','manageroles','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/team/list','manageteams','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/team/remove','manageteams','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/team/save','manageteams','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/token/force/revoke','managetokens','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/token/list','managetokens','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/able','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/create','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/details','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/list','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/list/count','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/admin/user/update','manageusers','2025-04-08 18:00:35',_binary ''),('Admin','/api/request/list','managerequests','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/authorization','global','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/change/email','profile','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/change/mobile','profile','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/change/password','profile','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/details','global','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/menu/list','global','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/token/force/revoke','tokens','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/token/list','tokens','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/update','profile','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/verify/change/email','profile','2025-04-08 18:00:35',_binary ''),('Admin','/api/user/verify/change/mobile','profile','2025-04-08 18:00:35',_binary ''),('GRCAdmin','/api/request/list','','2025-02-02 13:44:37',_binary ''),('SuperAdmin','/api/admin/auth/type/list','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/charity/list','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/menu/list','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/project/files/list','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/project/files/upload','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/project/list','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/project/remove','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/project/save','manageprojects','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/role/list','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/role/list','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/role/menu/list','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/role/menu/remove','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/role/menu/save','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/settings/details','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/settings/update','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/team/list','manageroles','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/team/list','manageteams','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/team/remove','manageteams','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/team/save','manageteams','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/token/force/revoke','managetokens','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/token/list','managetokens','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/able','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/create','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/details','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/list','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/list/count','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/admin/user/update','manageusers','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/request/list','managerequests','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/authorization','global','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/change/email','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/change/mobile','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/change/password','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/details','global','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/menu/list','global','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/token/force/revoke','tokens','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/token/list','tokens','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/update','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/verify/change/email','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','/api/user/verify/change/mobile','profile','2025-04-17 19:53:52',_binary ''),('SuperAdmin','api/admin/project/file/remove/','manageprojects','2025-04-17 19:53:52',_binary ''),('User','/api/admin/auth/type/list','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/menu/list','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/role/list','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/role/list','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/role/menu/list','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/role/menu/remove','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/role/menu/save','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/settings/details','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/settings/update','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/team/list','manageroles','2025-04-06 18:36:48',_binary ''),('User','/api/admin/team/list','manageteams','2025-04-06 18:36:48',_binary ''),('User','/api/admin/team/remove','manageteams','2025-04-06 18:36:48',_binary ''),('User','/api/admin/team/save','manageteams','2025-04-06 18:36:48',_binary ''),('User','/api/admin/token/force/revoke','managetokens','2025-04-06 18:36:48',_binary ''),('User','/api/admin/token/list','managetokens','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/able','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/create','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/details','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/list','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/list/count','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/admin/user/update','manageusers','2025-04-06 18:36:48',_binary ''),('User','/api/user/authorization','global','2025-04-06 18:36:48',_binary ''),('User','/api/user/change/email','profile','2025-04-06 18:36:48',_binary ''),('User','/api/user/change/mobile','profile','2025-04-06 18:36:48',_binary ''),('User','/api/user/change/password','profile','2025-04-06 18:36:48',_binary ''),('User','/api/user/details','global','2025-04-06 18:36:48',_binary ''),('User','/api/user/menu/list','global','2025-04-06 18:36:48',_binary ''),('User','/api/user/token/force/revoke','tokens','2025-04-06 18:36:48',_binary ''),('User','/api/user/token/list','tokens','2025-04-06 18:36:48',_binary ''),('User','/api/user/update','profile','2025-04-06 18:36:48',_binary ''),('User','/api/user/verify/change/email','profile','2025-04-06 18:36:48',_binary ''),('User','/api/user/verify/change/mobile','profile','2025-04-06 18:36:48',_binary '');
/*!40000 ALTER TABLE `authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lang` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'en',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `href` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `onclick` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `order` int DEFAULT '0',
  `isget` bit(1) DEFAULT b'0',
  `ispost` bit(1) DEFAULT b'0',
  `isupdate` bit(1) DEFAULT b'0',
  `isdelete` bit(1) DEFAULT b'0',
  `isconfiguration` bit(1) DEFAULT b'0',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `auth_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `show` bit(1) DEFAULT b'1',
  `additionalconfig` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `opendropdownlist` bit(1) NOT NULL DEFAULT b'0',
  `showdropdownlist` bit(1) NOT NULL DEFAULT b'0',
  `accessibleactions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='I added get, post, update, delete, and configure in case a page has these actions (if true), admin can choose to allow users menu action for the selected menu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('arabic','en','Arabic',NULL,'changeLanguageSwitch(\'ar\')','fa fa-language',80,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','translateen','2025-01-12 09:04:02','enar',_binary '',NULL,_binary '\0',_binary '\0',NULL),('charityar','ar','صدقة',NULL,NULL,'fa fa-briefcase',800,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','charity',_binary '',NULL,_binary '\0',_binary '',NULL),('charityen','en','Charity',NULL,NULL,'fa fa-briefcase',800,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','charity',_binary '',NULL,_binary '\0',_binary '',NULL),('darkar','ar','عتمة',NULL,'changeStyleSwitch(\'dark\')','fa fa-square',130,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','stylear','2025-01-12 09:04:02','dark',_binary '',NULL,_binary '\0',_binary '\0',NULL),('darken','en','Dark',NULL,'changeStyleSwitch(\'dark\')','fa fa-square',110,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','styleen','2025-01-12 09:04:02','dark',_binary '',NULL,_binary '\0',_binary '\0',NULL),('english','ar','إنجليزي',NULL,'changeLanguageSwitch(\'en\')','fa fa-language',70,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','translatear','2025-01-12 09:04:02','enar',_binary '',NULL,_binary '\0',_binary '\0',NULL),('indexar','ar','الصفحة الرئيسية','index',NULL,'icon-speedometer',20,_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL,'2025-01-12 09:04:02','index',_binary '',NULL,_binary '\0',_binary '\0',NULL),('indexen','en','Dashboard','index',NULL,'icon-speedometer',10,_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL,'2025-01-12 09:04:02','index',_binary '',NULL,_binary '\0',_binary '\0',NULL),('logoutar','ar','تسجيل خروج','logout',NULL,'fa fa-sign-out',1110,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','logout',_binary '',NULL,_binary '\0',_binary '\0',NULL),('logouten','en','Logout','logout',NULL,'fa fa-sign-out',1100,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','logout',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managear','ar','إدارة',NULL,NULL,'fa fa-briefcase',200,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','manage',_binary '',NULL,_binary '\0',_binary '',NULL),('manageen','en','Manage',NULL,NULL,'fa fa-briefcase',190,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','manage',_binary '',NULL,_binary '\0',_binary '',NULL),('manageprojectsar','ar','إدارة المشاريع','manageprojects',NULL,'fa fa-folder-o',810,_binary '',_binary '',_binary '',_binary '',_binary '\0','charityar','2025-02-02 13:47:02','manageprojects',_binary '',NULL,_binary '\0',_binary '\0',NULL),('manageprojectsen','en','Manage Projects','manageprojects',NULL,'fa fa-folder-o',810,_binary '',_binary '',_binary '',_binary '',_binary '\0','charityen','2025-02-02 13:47:02','manageprojects',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managerequestsar','ar','الطلبات','managerequests',NULL,'fa fa-folder-o',400,_binary '',_binary '',_binary '',_binary '',_binary '\0','mounaqasaar','2025-02-02 13:47:02','managerequests',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managerequestsen','en','Requests','managerequests',NULL,'fa fa-folder-o',400,_binary '',_binary '',_binary '',_binary '',_binary '\0','mounaqasaen','2025-02-02 13:47:02','managerequests',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managerolesar','ar','الأدوار','manageroles',NULL,'fa fa-cogs',240,_binary '',_binary '',_binary '',_binary '',_binary '\0','managear','2025-01-12 09:04:02','manageroles',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managerolesen','en','Roles','manageroles',NULL,'fa fa-cogs',230,_binary '',_binary '',_binary '',_binary '',_binary '\0','manageen','2025-01-12 09:04:02','manageroles',_binary '',NULL,_binary '\0',_binary '\0',NULL),('manageteamsar','ar','الفرق','manageteams',NULL,'fa fa-code-fork',235,_binary '',_binary '',_binary '',_binary '',_binary '\0','managear','2025-04-06 10:52:56','manageteams',_binary '',NULL,_binary '\0',_binary '\0',NULL),('manageteamsen','en','Teams','manageteams',NULL,'fa fa-code-fork',225,_binary '',_binary '',_binary '',_binary '',_binary '\0','manageen','2025-04-06 10:52:55','manageteams',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managetokensar','ar','جلسات المستخدمين','managetokens',NULL,'fa fa-wrench',260,_binary '',_binary '',_binary '',_binary '',_binary '\0','managear','2025-01-12 09:04:02','managetokens',_binary '',NULL,_binary '\0',_binary '\0',NULL),('managetokensen','en','User Sessions','managetokens',NULL,'fa fa-wrench',250,_binary '',_binary '',_binary '',_binary '',_binary '\0','manageen','2025-01-12 09:04:02','managetokens',_binary '',NULL,_binary '\0',_binary '\0',NULL),('manageusersar','ar','المستخدمين','manageusers',NULL,'fa fa-users',220,_binary '',_binary '',_binary '',_binary '',_binary '\0','managear','2025-01-12 09:04:02','manageusers',_binary '',NULL,_binary '\0',_binary '\0',NULL),('manageusersen','en','Users','manageusers',NULL,'fa fa-users',210,_binary '',_binary '',_binary '',_binary '',_binary '\0','manageen','2025-01-12 09:04:02','manageusers',_binary '',NULL,_binary '\0',_binary '\0',NULL),('mounaqasaar','ar','المناقصة',NULL,NULL,'fa fa-briefcase',600,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','mounaqasa',_binary '',NULL,_binary '\0',_binary '',NULL),('mounaqasaen','en','Mounaqasa',NULL,NULL,'fa fa-briefcase',600,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','mounaqasa',_binary '',NULL,_binary '\0',_binary '',NULL),('profilear','ar','الملف الشخصي','profile',NULL,'fa fa-user',160,_binary '',_binary '',_binary '',_binary '\0',_binary '','settingsar','2025-01-12 09:04:02','profile',_binary '',NULL,_binary '\0',_binary '\0',NULL),('profileen','en','Profile','profile',NULL,'fa fa-user',150,_binary '',_binary '',_binary '',_binary '\0',_binary '','settingsen','2025-01-12 09:04:02','profile',_binary '',NULL,_binary '\0',_binary '\0',NULL),('settingsar','ar','إعدادات',NULL,NULL,'icon-settings',40,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','settings',_binary '',NULL,_binary '\0',_binary '',NULL),('settingsen','en','Settings',NULL,NULL,'icon-settings',30,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,'2025-01-12 09:04:02','settings',_binary '',NULL,_binary '\0',_binary '',NULL),('stylear','ar','شكل',NULL,NULL,'fa fa-eyedropper',100,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsar','2025-01-12 09:04:02','style',_binary '',NULL,_binary '\0',_binary '',NULL),('styleen','en','Style',NULL,NULL,'fa fa-eyedropper',90,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsen','2025-01-12 09:04:02','style',_binary '',NULL,_binary '\0',_binary '',NULL),('tokensar','ar','الجلسات','tokens',NULL,'fa fa-wrench',260,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsar','2025-01-12 09:04:02','tokens',_binary '',NULL,_binary '\0',_binary '\0',NULL),('tokensen','en','Sessions','tokens',NULL,'fa fa-wrench',250,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsen','2025-01-12 09:04:02','tokens',_binary '',NULL,_binary '\0',_binary '\0',NULL),('translatear','ar','ترجم',NULL,NULL,'fa fa-language',60,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsar','2025-01-12 09:04:02','translate',_binary '',NULL,_binary '\0',_binary '',NULL),('translateen','en','Translate',NULL,NULL,'fa fa-language',50,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','settingsen','2025-01-12 09:04:02','translate',_binary '',NULL,_binary '\0',_binary '',NULL),('whitear','ar','أبيض',NULL,'changeStyleSwitch(\'white\')','fa fa-square-o',140,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','stylear','2025-01-12 09:04:02','white',_binary '',NULL,_binary '\0',_binary '\0',NULL),('whiteen','en','White',NULL,'changeStyleSwitch(\'white\')','fa fa-square-o',120,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0','styleen','2025-01-12 09:04:02','white',_binary '',NULL,_binary '\0',_binary '\0',NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_authorization`
--

DROP TABLE IF EXISTS `menu_authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_authorization` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_auth_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `api` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isget` bit(1) DEFAULT b'0',
  `ispost` bit(1) DEFAULT b'0',
  `isupdate` bit(1) DEFAULT b'0',
  `isdelete` bit(1) DEFAULT b'0',
  `isconfiguration` bit(1) DEFAULT b'0',
  `accessibleaction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Menu authorization';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_authorization`
--

LOCK TABLES `menu_authorization` WRITE;
/*!40000 ALTER TABLE `menu_authorization` DISABLE KEYS */;
INSERT INTO `menu_authorization` VALUES (1,'global','/api/user/menu/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(2,'global','/api/user/authorization','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(3,'global','/api/user/details','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(4,'profile','/api/user/update','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '',_binary '\0',_binary '\0',NULL),(5,'profile','/api/user/verify/change/email','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(6,'profile','/api/user/change/email','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(7,'profile','/api/user/verify/change/mobile','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(8,'profile','/api/user/change/mobile','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '',_binary '\0',_binary '\0',NULL),(9,'profile','/api/user/change/password','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL),(10,'manageusers','/api/admin/user/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(11,'manageusers','/api/admin/user/list/count','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(12,'manageusers','/api/admin/user/update','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '',_binary '\0',_binary '\0',NULL),(13,'manageusers','/api/admin/role/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(14,'manageusers','/api/admin/user/details','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(15,'manageusers','/api/admin/user/able','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(16,'manageusers','/api/admin/user/create','2025-01-12 09:04:01',_binary '\0',_binary '',_binary '\0',_binary '\0',_binary '\0',NULL),(17,'manageroles','/api/admin/role/menu/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(18,'manageroles','/api/admin/role/menu/save','2025-01-12 09:04:01',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',NULL),(19,'manageroles','/api/admin/auth/type/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(20,'manageroles','/api/admin/menu/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(21,'manageroles','/api/admin/role/menu/remove','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(22,'manageroles','/api/admin/settings/details','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(23,'manageroles','/api/admin/settings/update','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '\0',NULL),(24,'managetokens','/api/admin/token/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(25,'managetokens','/api/admin/token/force/revoke','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(26,'tokens','/api/user/token/list','2025-01-12 09:04:01',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(27,'tokens','/api/user/token/force/revoke','2025-01-12 09:04:01',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(28,'managerequests','/api/request/list','2025-02-02 13:44:37',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(29,'managerequests','/api/request/list','2025-02-02 13:44:37',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',NULL),(30,'manageteams','/api/admin/team/list','2025-04-06 10:53:28',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(31,'manageroles','/api/admin/team/list','2025-04-06 10:53:28',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(32,'manageteams','/api/admin/team/save','2025-04-06 10:53:43',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',NULL),(37,'manageteams','/api/admin/team/remove','2025-04-06 10:55:01',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(38,'manageroles','/api/admin/role/list','2025-04-06 11:40:42',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(39,'manageprojects','/api/admin/project/list','2025-04-08 18:58:17',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(40,'manageprojects','/api/admin/project/save','2025-04-08 18:58:52',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',NULL),(41,'manageprojects','/api/admin/charity/list','2025-04-08 19:51:39',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(42,'manageprojects','/api/admin/project/files/list','2025-04-17 17:44:28',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),(43,'manageprojects','/api/admin/project/files/upload','2025-04-17 17:44:57',_binary '\0',_binary '',_binary '',_binary '\0',_binary '\0',NULL),(45,'manageprojects','api/admin/project/file/remove/','2025-04-17 17:45:39',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL),(46,'manageprojects','/api/admin/project/remove','2025-04-17 18:37:10',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',NULL);
/*!40000 ALTER TABLE `menu_authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_role`
--

DROP TABLE IF EXISTS `menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_role` (
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `isget` bit(1) DEFAULT b'0',
  `ispost` bit(1) DEFAULT b'0',
  `isupdate` bit(1) DEFAULT b'0',
  `isdelete` bit(1) DEFAULT b'0',
  `isconfiguration` bit(1) DEFAULT b'0',
  `accessibleactions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`menu_id`,`user_role`),
  CONSTRAINT `FK3gapbw9hw4pgxo6x90qb1b69` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='User Roles Menu, I added get, post, update delete configuration to allow specific role to specific action';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role`
--

LOCK TABLES `menu_role` WRITE;
/*!40000 ALTER TABLE `menu_role` DISABLE KEYS */;
INSERT INTO `menu_role` VALUES ('arabic','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('arabic','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('arabic','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('charityar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('charityar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('charityen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('charityen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darkar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darkar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darkar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darken','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darken','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('darken','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('english','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('english','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('english','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('indexar','Admin',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL),('indexar','SuperAdmin',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL),('indexar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('indexen','Admin',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL),('indexen','SuperAdmin',_binary '',_binary '\0',_binary '\0',_binary '\0',_binary '',NULL),('indexen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logoutar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logoutar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logoutar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logouten','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logouten','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('logouten','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('managear','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('managear','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('managear','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('manageen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('manageen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('manageen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('manageprojectsar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '',NULL),('manageprojectsar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageprojectsen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '',NULL),('manageprojectsen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerequestsar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '',NULL),('managerequestsar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerequestsen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '',NULL),('managerequestsen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesar','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managerolesen','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsar','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageteamsen','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensar','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('managetokensen','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersar','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersar','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersar','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersen','Admin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersen','SuperAdmin',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('manageusersen','User',_binary '',_binary '',_binary '',_binary '',_binary '\0',NULL),('mounaqasaar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('mounaqasaar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('mounaqasaar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('mounaqasaen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('mounaqasaen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('mounaqasaen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('profilear','Admin',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('profilear','SuperAdmin',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('profilear','User',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('profileen','Admin',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('profileen','SuperAdmin',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('profileen','User',_binary '',_binary '',_binary '',_binary '\0',_binary '',NULL),('settingsar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('settingsar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('settingsar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('settingsen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('settingsen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('settingsen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('stylear','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('stylear','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('stylear','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('styleen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('styleen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('styleen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensar','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensar','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensar','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('tokensen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translatear','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translatear','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translatear','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translateen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translateen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('translateen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whitear','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whitear','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whitear','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whiteen','Admin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whiteen','SuperAdmin',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL),('whiteen','User',_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL);
/*!40000 ALTER TABLE `menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `user_role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auth_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `require_2fa` bit(1) DEFAULT b'1',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `parentrole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `team` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `superadmin` bit(1) DEFAULT b'0',
  PRIMARY KEY (`user_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Registered Users with LDAP connection and GRC users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('Admin','2025-04-08 18:00:35','PASS',_binary '\0','Admin',NULL,'Admin',_binary '\0'),('SuperAdmin','2025-04-17 19:53:52','PASS',_binary '\0','Super Admin',NULL,'Admin',_binary '\0'),('User','2025-04-06 18:36:48','PASS',_binary '\0','user',NULL,'Admin',_binary '\0');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ldapregisterrole` bit(1) DEFAULT b'0',
  `ldapdefaultrole` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `ldapdomain` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `ldapurl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `ldapbasedn` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `apikey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `apisecret` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `adminkey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `jwtsecret` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `jwtexpirationms` bigint DEFAULT '86400000',
  `jwtexpirationmsshort` bigint DEFAULT '7200000',
  `jwtexpirationmscode` bigint DEFAULT '900000',
  `uaepassregisterrole` bit(1) DEFAULT b'0',
  `uaepassdefaultrole` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassusername` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepasspassword` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassendpoint` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepasscallbackurl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassuserinfourl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassstate` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassredirecturl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassauthurl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `uaepassuseeid` bit(1) DEFAULT b'0' COMMENT 'use eid as username\nelse use uuid as username',
  `passregisterrole` bit(1) DEFAULT b'0',
  `passdefaultrole` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `maximuminvalidattempts` int DEFAULT '5',
  `recaptchavalidation` bit(1) DEFAULT b'0',
  `recaptchasitekey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `recaptchaapi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smsauthapi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smsauthusername` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smsauthpassword` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smsapi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smsapplicationid` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `smspassword` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `mailhost` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `mailport` int DEFAULT '25',
  `mailusername` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `mailpassword` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `mailstarttlsenable` bit(1) DEFAULT b'1',
  `mailfrom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `mailauth` bit(1) DEFAULT b'1',
  `mailsupport` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `isdefault` bit(1) DEFAULT b'0',
  `uaepassspsaredirecturl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='This table contains all project settings';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,_binary '\0','User','@.shj.ae','','','hde36b429-f747-44l7-a841-e6djf4fce2dh','UAyFQThz0RdPzHCpShthfVB3zcoskeeo920mEbrwieyRiO1jqPAOhO4oM','wsTk5JDXd74XLGUrJ1','======================AUTH=Spring===========================',86400000,7200000,900000,_binary '\0','UAEInspector','sandbox_stage','sandbox_stage','https://stg-id.uaepass.ae/','idshub/token?grant_type=authorization_code&redirect_uri=#CALLBACKURL#&code=#CODE#','idshub/userinfo','testing','http://localhost/login#USERNAME#','idshub/authorize?response_type=code&client_id=#CLIENTID#&scope=urn:uae:digitalid:profile:general&state=#STATE#&redirect_uri=#REDIRECTURL#&acr_values=urn:safelayer:tws:policies:authentication:level:low',_binary '\0',_binary '\0','GRCInspector',5,_binary '\0','6LeHQ00qAAAAAFcHhSxKSNd3RU3xDnYlPFygLsNw','https://recaptchaenterprise.googleapis.com/v1/projects/inspection-412005/assessments?key=AIzaSyD7Da8bHTmTFScXJDYdvxklV9aD_ySlqJ4','','','','','','','',25,'','P@ssw0rd',_binary '','',_binary '','touficsleiman.uae@gmail.com',_binary '',NULL);
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_logs`
--

DROP TABLE IF EXISTS `system_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_logs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `event` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ref_link_id` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `type` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='System logs without logins';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_logs`
--

LOCK TABLES `system_logs` WRITE;
/*!40000 ALTER TABLE `system_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `code` varchar(200) NOT NULL,
  `description` tinytext,
  `namear` varchar(200) NOT NULL,
  `nameen` varchar(200) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES ('Admin','Admin','Admin','Admin'),('user','User','User','User');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `accesstoken` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `refreshtoken` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `refreshkey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `device` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `access_expiry_date` timestamp NULL DEFAULT NULL,
  `refresh_expiry_date` timestamp NULL DEFAULT NULL,
  `revoked_date_time` timestamp NULL DEFAULT NULL,
  `reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Registered Users Tokens';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES ('2739de0d-32ea-4577-9daa-a2195114c94f','admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fYWRtaW4iLCJpYXQiOjE3NDM5NjcwNjYsImV4cCI6MTc0Mzk3NDI2Nn0.5xcdIL1myp_HoPdV-OAsisSMFntuM_1b15Wn-X2XHhg','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX2FkbWluIiwiaWF0IjoxNzQzOTY3MDY2LCJleHAiOjE3NDQwNTM0NjZ9.1_kbtW130BgpbjmMMN8Npj9uPAl3RgHEtoEwoE4wrd4','YWRtaW4=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-06 21:17:46','2025-04-07 19:17:46','2025-04-06 19:18:03','LOGOUT','2025-04-06 19:17:46'),('379bb313-8c78-4c21-87a8-69059b2f0906','admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fYWRtaW4iLCJpYXQiOjE3NDM5NjMyNjgsImV4cCI6MTc0Mzk3MDQ2OH0.ETB92wJGZpnojYod3P1dnTeEsCjwm4fTCtvkeN59hF8','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX2FkbWluIiwiaWF0IjoxNzQzOTYzMjY4LCJleHAiOjE3NDQwNDk2Njh9.i8qBydUknfmYaVJwCNoyeZP1DtbY4UtH4qu_rs7Ke_s','YWRtaW4=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/134.0.0.0 Safari/537.36','::1','2025-04-06 20:14:28','2025-04-07 18:14:28','2025-04-06 19:01:08','LOGOUT','2025-04-06 18:14:28'),('3ef47730-3c4b-4e07-addc-93c8ccea2ac5','admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fYWRtaW4iLCJpYXQiOjE3NDM5NjYwOTQsImV4cCI6MTc0Mzk3MzI5NH0.Yf55hEkt7Nhb5eVNkTa1Kjp6mst5KMvgXIxd3zEPBOo','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX2FkbWluIiwiaWF0IjoxNzQzOTY2MDk0LCJleHAiOjE3NDQwNTI0OTR9.ObHt6WZP3496hPtCDQTNsuMI4T7wNB0ajCJmpDdiIj4','YWRtaW4=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-06 21:01:35','2025-04-07 19:01:35','2025-04-06 19:01:41','LOGOUT','2025-04-06 19:01:35'),('537cccf5-3878-4e73-b8e0-31374f166e15','admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fYWRtaW4iLCJpYXQiOjE3NDI2Mjk5NzgsImV4cCI6MTc0MjYzNzE3OH0.zvCH--TdszVBK6XsYgmfaTD-RCzibaxwlbrX3p-h8Bc','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX2FkbWluIiwiaWF0IjoxNzQyNjI5OTc4LCJleHAiOjE3NDI3MTYzNzh9.NPyrrf-7HSM5jyooNKZOJ7HBz7a3lem-2HLqAt9rCEA','YWRtaW4=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/134.0.0.0 Safari/537.36','::1','2025-03-22 09:52:59','2025-03-23 07:52:59',NULL,NULL,'2025-03-22 07:52:59'),('5c0dbe24-0c53-45d0-b0ed-aa680c4b64eb','su','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fc3UiLCJpYXQiOjE3NDQzMTIzODYsImV4cCI6MTc0NDMxOTU4Nn0.9S6JN6otz9YDUCLF9LhKKeYBfjOGqjlSBYP3tC8IhGc','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX3N1IiwiaWF0IjoxNzQ0MzEyMzg2LCJleHAiOjE3NDQzOTg3ODZ9.YZHe0gP7zuJNCEtqNwIfMLh9I3Y7_RB-65MrNWzUCWA','c3U=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-10 21:13:07','2025-04-11 19:13:07','2025-04-10 19:37:41','LOGOUT','2025-04-10 19:13:07'),('6c9221f2-0cc6-4eac-ba39-976dd4ad6835','su','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fc3UiLCJpYXQiOjE3NDQzMTM4NjgsImV4cCI6MTc0NDMyMTA2OH0.sx88OLKe80IAOa4DGoD9hgYqspkDhA6TtOMx4RzYdk4','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX3N1IiwiaWF0IjoxNzQ0MzEzODY4LCJleHAiOjE3NDQ0MDAyNjh9.Y6xa3hl18FR68moXUbmksErUQt_toa0ts7vNOI5M8jk','c3U=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-10 21:37:48','2025-04-11 19:37:48',NULL,NULL,'2025-04-10 19:37:48'),('98b1ef9a-ba47-4012-a6c5-cb920a666dbf','su','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fc3UiLCJpYXQiOjE3NDM5NjcxMjcsImV4cCI6MTc0Mzk3NDMyN30._434ERUqIK4HGixxd2VGvHHzZIblu59JE_MhqFgpbmA','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX3N1IiwiaWF0IjoxNzQzOTY3MTI3LCJleHAiOjE3NDQwNTM1Mjd9.ViqfR561QUlaZuLjr8Hk7IYs6T-m_1z3PuB_C-hU-y4','c3U=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-06 21:18:48','2025-04-07 19:18:48',NULL,NULL,'2025-04-06 19:18:48'),('c0f2e25e-19ff-4444-a1af-b9db2c9bba11','su','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fc3UiLCJpYXQiOjE3NDUwODYzNzQsImV4cCI6MTc0NTA5MzU3NH0.RDDcQPHtCy2bJpPqCeyXCBxSoxFnHET9RIV0ipWtprg','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX3N1IiwiaWF0IjoxNzQ1MDg2Mzc0LCJleHAiOjE3NDUxNzI3NzR9.XogxUtXekKkVtT5S-Bld52RaLrUcegJiRF52yssvdCE','c3U=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-19 20:12:54','2025-04-20 18:12:54',NULL,NULL,'2025-04-19 18:12:54'),('d3c4f2f3-b78a-4318-82e2-fe82be3a2c59','su','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fc3UiLCJpYXQiOjE3NDQ5MjQ2MDQsImV4cCI6MTc0NDkzMTgwNH0.KBVFoIr7XdiknSJEvJBMoGQdntLns4I_n0e0GV9OvhA','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX3N1IiwiaWF0IjoxNzQ0OTI0NjA0LCJleHAiOjE3NDUwMTEwMDR9.0C1ov5DGL6bGRcavoxjAMK3516UXnZp510Vxrr5ImiY','c3U=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/135.0.0.0 Safari/537.36','::1','2025-04-17 23:16:44','2025-04-18 21:16:44',NULL,NULL,'2025-04-17 21:16:44'),('d7d3596c-0fee-46a9-9e61-24a4942f9a71','admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBQ0NFU1NfVE9LRU5fYWRtaW4iLCJpYXQiOjE3NDMxMDU4MjIsImV4cCI6MTc0MzExMzAyMn0.4gFj0gk_sOpCHuocZokkOWnaBlXorMKtCGs-uAKSino','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSRUZSRVNIX1RPS0VOX2FkbWluIiwiaWF0IjoxNzQzMTA1ODIyLCJleHAiOjE3NDMxOTIyMjJ9.b74QOEVMjvjMyl8IwUQAAh65eRXPzteffLCnR84wUKY','YWRtaW4=','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/134.0.0.0 Safari/537.36','::1','2025-03-27 22:03:42','2025-03-28 20:03:42',NULL,NULL,'2025-03-27 20:03:42');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `last_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `user_role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `hire_date` datetime(6) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `first_name_ar` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `last_name_ar` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `otp` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `changepasswordcode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `changeemailcode` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `changemobilecode` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enable` bit(1) DEFAULT b'1',
  `locked` bit(1) DEFAULT b'0',
  `invalidattempts` int DEFAULT '0',
  `bypass3rdpartyauth` bit(1) DEFAULT b'0',
  `lattitude` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `longitude` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Registered Users with LDAP connection and GRC users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tsadmin','','admin','$2a$10$8dwMI0sQnOnnro/xk8WrpOYfNGMBtOuv/1.ELoNZ38ypyhypQ4b1S','Admin','touficsleiman.uae@gmail.com','0566114217','2025-01-12 00:00:00.000000',NULL,'','','2025-01-12 09:04:01','231187','070601',NULL,NULL,_binary '',_binary '\0',3,_binary '\0',NULL,NULL),(2,'tsuser','','user','$2a$10$8dwMI0sQnOnnro/xk8WrpOYfNGMBtOuv/1.ELoNZ38ypyhypQ4b1S','User','ts.charity25@gmail.com','0566114217',NULL,NULL,'tsuser','','2025-01-12 09:04:01',NULL,NULL,NULL,NULL,_binary '',_binary '\0',0,_binary '\0',NULL,NULL),(3,'Super','User','su','$2a$10$u6/K.AjMvnfjUbzY8uCCPeEID46FKanzh3m/mvpcXYHUgTqmKMuvy','SuperAdmin','touficsleyman@gmail.com','',NULL,NULL,'Super','User','2025-04-06 19:00:42',NULL,NULL,NULL,NULL,_binary '',_binary '\0',1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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

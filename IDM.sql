-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: IDM
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `Downloads`
--

DROP TABLE IF EXISTS `Downloads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Downloads` (
  `DownloadID` int(11) DEFAULT NULL,
  `TotalConnections` int(11) DEFAULT NULL,
  `FileURL` varchar(500) DEFAULT NULL,
  `FilePath` varchar(100) DEFAULT NULL,
  `FileName` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `FileSize` bigint(20) DEFAULT NULL,
  `BytesDownloaded` bigint(20) DEFAULT NULL,
  `AssociatedTime` bigint(20) DEFAULT NULL,
  `ScheduleDate` date DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `SubDownloadFile` varchar(50) DEFAULT NULL,
  `FileStartLocation` bigint(20) DEFAULT NULL,
  `FileEndLocation` bigint(20) DEFAULT NULL,
  `complete` bit(1) DEFAULT NULL,
  `SubDownloadID` int(11) DEFAULT NULL,
  `SubBytesDownloaded` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Downloads`
--

LOCK TABLES `Downloads` WRITE;
/*!40000 ALTER TABLE `Downloads` DISABLE KEYS */;
INSERT INTO `Downloads` VALUES (1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_0.txt',0,811082,'',0,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_1.txt',811083,1622165,'',1,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_2.txt',1622166,2433248,'',2,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_3.txt',2433249,3244331,'',3,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_4.txt',3244332,4055414,'',4,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_5.txt',4055415,4866497,'',5,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_6.txt',4866498,5677580,'',6,811083),(1,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444499438064,'2015-10-10','','SubSec1_7.txt',5677581,6488666,'',7,811086),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_0.txt',0,811082,'',0,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_1.txt',811083,1622165,'',1,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_2.txt',1622166,2433248,'',2,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_3.txt',2433249,3244331,'',3,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_4.txt',3244332,4055414,'',4,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_5.txt',4055415,4866497,'',5,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_6.txt',4866498,5677580,'',6,811083),(3,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444500035847,'2015-10-10','','SubSec3_7.txt',5677581,6488666,'',7,811086),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_0.txt',0,16778,'',0,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_1.txt',16779,33557,'',1,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_2.txt',33558,50336,'',2,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_3.txt',50337,67115,'',3,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_4.txt',67116,83894,'',4,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_5.txt',83895,100673,'',5,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_6.txt',100674,117452,'',6,16779),(8,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec8_7.txt',117453,134238,'',7,16786),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_0.txt',0,811082,'\0',0,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_1.txt',811083,1622165,'\0',1,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_2.txt',1622166,2433248,'\0',2,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_3.txt',2433249,3244331,'\0',3,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_4.txt',3244332,4055414,'\0',4,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_5.txt',4055415,4866497,'\0',5,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_6.txt',4866498,5677580,'\0',6,0),(5,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',1,6488666,0,1444506976370,'2015-10-11','','SubSec5_7.txt',5677581,6488666,'\0',7,0),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_0.txt',0,811082,'',0,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_1.txt',811083,1622165,'',1,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_2.txt',1622166,2433248,'',2,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_3.txt',2433249,3244331,'',3,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_4.txt',3244332,4055414,'',4,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_5.txt',4055415,4866497,'',5,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_6.txt',4866498,5677580,'',6,811083),(0,8,'http://norvig.com/big.txt','/home/somil/Desktop/c+','big.txt',2,6488666,6488666,1444541098378,'2015-10-11','','SubSec0_7.txt',5677581,6488666,'',7,811086),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_0.txt',0,16778,'',0,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_1.txt',16779,33557,'',1,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_2.txt',33558,50336,'',2,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_3.txt',50337,67115,'',3,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_4.txt',67116,83894,'',4,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_5.txt',83895,100673,'',5,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_6.txt',100674,117452,'',6,16779),(7,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','dfs.jpg',2,134238,134238,0,'1970-01-01','','SubSec7_7.txt',117453,134238,'',7,16786),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_0.txt',0,16778,'',0,16779),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_1.txt',16779,33557,'\0',1,13745),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_2.txt',33558,50336,'\0',2,13745),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_3.txt',50337,67115,'',3,16779),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_4.txt',67116,83894,'',4,16779),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_5.txt',83895,100673,'',5,16779),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_6.txt',100674,117452,'',6,16779),(9,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',3,134238,128171,1444547241382,'2015-10-11','','SubSec9_7.txt',117453,134238,'',7,16786),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_0.txt',0,16778,'',0,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_1.txt',16779,33557,'',1,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_2.txt',33558,50336,'',2,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_3.txt',50337,67115,'',3,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_4.txt',67116,83894,'',4,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_5.txt',83895,100673,'',5,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_6.txt',100674,117452,'',6,16779),(2,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','head.jpg',2,134238,134238,0,'1970-01-01','','SubSec2_7.txt',117453,134238,'',7,16786),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_0.txt',0,16778,'',0,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_1.txt',16779,33557,'',1,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_2.txt',33558,50336,'',2,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_3.txt',50337,67115,'',3,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_4.txt',67116,83894,'',4,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_5.txt',83895,100673,'',5,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_6.txt',100674,117452,'',6,16779),(10,8,'http://210.212.49.5/ecell/img/ecell/head.jpg','/home/somil/Desktop/c+','1.jpg.jpg',2,134238,134238,0,'1970-01-01','','SubSec10_7.txt',117453,134238,'',7,16786);
/*!40000 ALTER TABLE `Downloads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Schedules`
--

DROP TABLE IF EXISTS `Schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Schedules` (
  `DownloadID` int(11) DEFAULT NULL,
  `FileURL` varchar(500) DEFAULT NULL,
  `FilePath` varchar(100) DEFAULT NULL,
  `FileName` varchar(50) DEFAULT NULL,
  `AssociatedTime` bigint(20) DEFAULT NULL,
  `ScheduledDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Schedules`
--

LOCK TABLES `Schedules` WRITE;
/*!40000 ALTER TABLE `Schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `Schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Settings`
--

DROP TABLE IF EXISTS `Settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Settings` (
  `ProxyPort` int(11) DEFAULT NULL,
  `ProxyAddress` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `BufferSize` int(11) DEFAULT NULL,
  `TotalConnections` int(11) DEFAULT NULL,
  `DestinationFolder` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Settings`
--

LOCK TABLES `Settings` WRITE;
/*!40000 ALTER TABLE `Settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `Settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-11 13:04:28

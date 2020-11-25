-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: coaching
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.04.2

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
-- Table structure for table `Attendance`
--

DROP TABLE IF EXISTS `Attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Attendance` (
  `date` date NOT NULL,
  `employeeId` int NOT NULL,
  `isPresent` tinyint(1) NOT NULL DEFAULT '1',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`date`,`employeeId`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `Attendance_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
INSERT INTO `Attendance` VALUES ('2020-11-20',100025,1,''),('2020-11-21',100025,1,''),('2020-11-21',100030,1,''),('2020-11-24',100025,1,''),('2020-11-24',100027,0,''),('2020-11-25',100025,1,''),('2020-11-25',100027,1,''),('2020-11-25',100030,1,''),('2020-11-25',100031,0,''),('2020-11-25',100039,1,''),('2020-11-25',100044,1,'');
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Batch`
--

DROP TABLE IF EXISTS `Batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Batch` (
  `batchId` varchar(255) NOT NULL,
  `courseId` varchar(255) NOT NULL,
  `batchName` varchar(255) NOT NULL,
  `fee` int NOT NULL,
  `roomNumber` int NOT NULL,
  `startTime` time DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  PRIMARY KEY (`batchId`,`courseId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `Batch_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `Course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Batch`
--

LOCK TABLES `Batch` WRITE;
/*!40000 ALTER TABLE `Batch` DISABLE KEYS */;
INSERT INTO `Batch` VALUES ('B1','C01','Pro Batch',10000,3,'16:00:00','17:00:00'),('B1','C02','Pro Batch',12000,2,'16:00:00','17:00:00'),('B1','C03','Pro Batch',6100,5,'16:00:00','17:00:00'),('B1','C04','Pro Batch',4000,1,'16:00:00','17:00:00'),('B1','C05','Pro Batch',15000,4,'16:00:00','17:00:00'),('B2','C01','Achievers Batch',11000,5,'18:30:00','19:30:00'),('B2','C02','Achievers Batch',13000,2,'18:30:00','19:30:00'),('B2','C03','Achievers Batch',6500,4,'18:30:00','19:30:00'),('B2','C04','Achievers Batch',5000,3,'18:30:00','19:30:00'),('B2','C05','Achievers Batch',16000,1,'18:30:00','19:30:00'),('B3','C05','Nurture Batch',17000,5,'17:00:00','18:30:00'),('BS','C01','Star Batch',10000,1,'17:00:00','18:30:00'),('BS','C02','Star Batch',12000,3,'17:00:00','18:30:00'),('BS','C03','Star Batch',6000,4,'17:00:00','18:30:00'),('BS','C05','Star Batch',15000,2,'17:00:00','18:30:00');
/*!40000 ALTER TABLE `Batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Complaint`
--

DROP TABLE IF EXISTS `Complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Complaint` (
  `complaintId` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `subject` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `response` varchar(255) DEFAULT NULL,
  `isResolved` tinyint(1) NOT NULL DEFAULT '0',
  `studentId` int NOT NULL,
  PRIMARY KEY (`complaintId`),
  KEY `studentId` (`studentId`),
  CONSTRAINT `Complaint_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Complaint`
--

LOCK TABLES `Complaint` WRITE;
/*!40000 ALTER TABLE `Complaint` DISABLE KEYS */;
INSERT INTO `Complaint` VALUES (1,'2020-11-21','20:33:38','No electricity','There is no electricity in our classroom.','Fixed it',1,100001),(2,'2020-11-24','19:19:34','Staff Absent','The staff of our batch is absent from last few days.','Ok, fixed it...',1,100061);
/*!40000 ALTER TABLE `Complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course` (
  `courseId` varchar(255) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('C01','Swift','For Science subjects'),('C02','Trigo','For Mathematics'),('C03','Zenith','For HCG subjects'),('C04','Premier','For Language subjects'),('C05','Googol','For Computer Applications');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseSubjectDetails`
--

DROP TABLE IF EXISTS `CourseSubjectDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CourseSubjectDetails` (
  `courseId` varchar(255) NOT NULL,
  `subjectId` varchar(255) NOT NULL,
  PRIMARY KEY (`courseId`,`subjectId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `CourseSubjectDetails_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `Course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CourseSubjectDetails_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseSubjectDetails`
--

LOCK TABLES `CourseSubjectDetails` WRITE;
/*!40000 ALTER TABLE `CourseSubjectDetails` DISABLE KEYS */;
INSERT INTO `CourseSubjectDetails` VALUES ('C01','BIO'),('C01','CHE'),('C05','COMP'),('C04','ENG-LANG'),('C04','ENG-LIT'),('C03','GEO'),('C04','HIN'),('C03','HNC'),('C02','MATH'),('C01','PHY');
/*!40000 ALTER TABLE `CourseSubjectDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `employeeId` int NOT NULL AUTO_INCREMENT,
  `basicSalary` int DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `panNumber` varchar(255) NOT NULL,
  `accountNumber` varchar(255) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `userId` (`userId`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100052 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (100025,1000,NULL,NULL,'AXEUU3462B','85828137980855',131),(100026,1000,NULL,NULL,'XDVTW9852M','79912524159880',135),(100027,1000,NULL,NULL,'MIVCJ5407F','86419874157251',136),(100028,1000,NULL,NULL,'YOBIB9753M','2640684184201',140),(100029,1000,NULL,NULL,'YPYGJ5241X','39668017579110',146),(100030,1000,NULL,NULL,'TJSYU6473B','34604373488038',147),(100031,1000,NULL,NULL,'ZEZPV0759D','58982187141779',154),(100032,1000,NULL,NULL,'UNHZY4039E','72658720044779',155),(100033,1000,NULL,NULL,'BFTGS9824Q','93418263059441',157),(100034,1000,NULL,NULL,'VNVDH6457J','71572851396091',166),(100035,1000,NULL,NULL,'KZPTG8188S','50946204209155',170),(100036,1000,NULL,NULL,'CJBPN0464D','49887088080535',183),(100037,1000,NULL,NULL,'TZWSP1759G','51017534259825',185),(100038,1000,NULL,NULL,'DDPRX7749P','88917784264472',192),(100039,1000,NULL,NULL,'QADZD0642G','24690857589995',199),(100040,1000,NULL,NULL,'TMZVL9165L','84108708919948',207),(100041,1000,NULL,NULL,'FYZBK1377X','89127140121090',208),(100042,1000,NULL,NULL,'ACGDI1378F','77212160475170',230),(100043,1000,NULL,NULL,'GQCXS1594G','80438761655106',243),(100044,1000,NULL,NULL,'NBOKY5273A','48197345580819',244),(100045,1000,NULL,NULL,'QGBJH2741W','76864401139415',246),(100046,1000,NULL,NULL,'ADKTP8536Q','71915572596351',247),(100047,1000,NULL,NULL,'KIWKG2162N','32009604070480',251);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enrollment`
--

DROP TABLE IF EXISTS `Enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Enrollment` (
  `enrollmentId` int NOT NULL AUTO_INCREMENT,
  `studentId` int NOT NULL,
  `batchId` varchar(255) DEFAULT NULL,
  `courseId` varchar(255) DEFAULT NULL,
  `transactionId` int NOT NULL,
  `joinDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`enrollmentId`),
  KEY `studentId` (`studentId`),
  KEY `batchId` (`batchId`,`courseId`),
  KEY `transactionId` (`transactionId`),
  CONSTRAINT `Enrollment_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Enrollment_ibfk_2` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `Batch` (`batchId`, `courseId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Enrollment_ibfk_3` FOREIGN KEY (`transactionId`) REFERENCES `Transaction` (`transactionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enrollment`
--

LOCK TABLES `Enrollment` WRITE;
/*!40000 ALTER TABLE `Enrollment` DISABLE KEYS */;
INSERT INTO `Enrollment` VALUES (1,100001,'B1','C01',1,NULL,NULL),(2,100075,'B2','C01',2,'2000-10-10',NULL),(3,100071,'B2','C01',3,NULL,NULL),(5,100001,'BS','C05',5,'2000-10-15',NULL),(6,100064,NULL,NULL,6,NULL,NULL),(7,100066,'B1','C05',7,NULL,NULL),(8,100074,'B1','C05',8,NULL,NULL),(9,100001,'B1','C02',9,NULL,NULL),(10,100065,'B1','C02',10,NULL,NULL),(11,100070,'B1','C02',11,NULL,NULL),(12,100076,'B1','C02',12,NULL,NULL),(13,100072,'B2','C02',13,NULL,NULL),(14,100114,'B2','C02',14,NULL,NULL),(15,100118,'B2','C02',15,NULL,NULL),(16,100123,'B2','C02',16,NULL,NULL),(17,100001,'B2','C03',17,NULL,NULL),(18,100063,'B2','C03',18,NULL,NULL),(19,100073,'B2','C03',19,NULL,NULL),(20,100078,'B2','C03',20,NULL,NULL),(21,100101,'B2','C03',21,NULL,NULL),(22,100090,'B2','C03',22,NULL,NULL),(23,100068,'B1','C03',23,NULL,NULL),(24,100077,'B1','C03',24,NULL,NULL),(25,100069,'B1','C05',25,NULL,NULL),(26,100073,'B1','C05',26,NULL,NULL),(27,100065,'B1','C05',27,NULL,NULL),(28,100070,'B1','C05',28,NULL,NULL),(29,100100,'B1','C05',29,NULL,NULL),(30,100114,'B1','C05',30,NULL,NULL),(31,100116,'B1','C05',31,NULL,NULL),(32,100104,'B1','C05',32,NULL,NULL),(33,100076,'BS','C05',33,NULL,NULL),(34,100088,'BS','C05',34,NULL,NULL),(35,100090,'BS','C05',35,NULL,NULL),(36,100103,'BS','C05',36,NULL,NULL),(37,100122,'BS','C05',37,NULL,NULL),(38,100113,'BS','C05',38,NULL,NULL),(39,100072,'B2','C05',39,NULL,NULL),(40,100077,'B2','C05',40,NULL,NULL),(41,100098,'B2','C05',41,NULL,NULL),(42,100109,'B2','C05',42,NULL,NULL),(43,100118,'B2','C05',43,NULL,NULL),(44,100065,'B1','C01',44,'2020-11-10',NULL),(45,100070,'B1','C01',45,'2020-11-10',NULL),(46,100078,'B1','C01',46,'2020-11-10',NULL),(47,100088,'B1','C01',47,'2020-11-12',NULL),(48,100095,'B1','C01',48,'2020-11-12',NULL),(49,100084,'B1','C01',49,'2020-11-12',NULL),(50,100097,'B1','C01',50,'2020-11-12',NULL),(51,100098,'B1','C01',51,'2020-11-12',NULL),(52,100106,'B1','C01',52,'2020-11-12',NULL),(53,100114,'B1','C01',53,'2020-11-12',NULL),(54,100121,'B1','C01',54,'2020-11-13',NULL),(55,100074,'B2','C01',55,NULL,NULL),(56,100068,'B2','C01',56,NULL,NULL),(57,100092,'B2','C01',57,'2020-10-01',NULL),(58,100100,'B2','C01',58,'2020-10-01',NULL),(59,100119,'B2','C01',59,'2020-10-01',NULL),(60,100069,'BS','C01',60,NULL,NULL),(61,100064,'BS','C01',61,NULL,NULL),(62,100113,'BS','C01',62,NULL,NULL),(63,100103,'BS','C01',63,NULL,NULL),(64,100117,'BS','C01',64,NULL,NULL),(65,100091,'BS','C01',65,NULL,NULL),(66,100070,'BS','C03',66,NULL,NULL),(67,100092,'BS','C03',67,NULL,NULL),(68,100079,'BS','C03',68,NULL,NULL),(69,100113,'BS','C03',69,NULL,NULL),(70,100096,'BS','C03',70,NULL,NULL),(71,100066,'B1','C03',71,NULL,NULL),(72,100099,'B1','C03',72,NULL,NULL),(73,100085,'B1','C03',73,NULL,NULL);
/*!40000 ALTER TABLE `Enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Feedback`
--

DROP TABLE IF EXISTS `Feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Feedback` (
  `feedbackId` int NOT NULL AUTO_INCREMENT,
  `studentId` int NOT NULL,
  `employeeId` int NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `response` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`feedbackId`),
  KEY `studentId` (`studentId`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `Feedback_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Feedback_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Feedback`
--

LOCK TABLES `Feedback` WRITE;
/*!40000 ALTER TABLE `Feedback` DISABLE KEYS */;
INSERT INTO `Feedback` VALUES (3,100001,100025,'2020-11-25','09:44:46','sdf','jlksjdfl',NULL);
/*!40000 ALTER TABLE `Feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guardian`
--

DROP TABLE IF EXISTS `Guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guardian` (
  `name` varchar(255) NOT NULL,
  `studentId` int NOT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `relationWithStudent` enum('Father','Mother','Other') NOT NULL,
  PRIMARY KEY (`name`,`studentId`),
  KEY `studentId` (`studentId`),
  CONSTRAINT `Guardian_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guardian`
--

LOCK TABLES `Guardian` WRITE;
/*!40000 ALTER TABLE `Guardian` DISABLE KEYS */;
INSERT INTO `Guardian` VALUES ('Aaron Sanchez',100098,'Other Healthcare Support Occupation','V.p.o. Churri Wala dhanna , near krishanmandir , Tehsil and District : Fazilka Abohar Punjab','aaron@gmail.com','Father'),('Andrew Golden',100116,'General and Operations Manager','b.t.i road sherpura , gali no.4 vidisha madhya pradesh','andrew@gmail.com','Father'),('Anne Gentry',100103,'Sales Supervisor','Marui (Itahi), Jakhini, Varanasi , Varanasi Uttar Pradesh','anne@gmail.com','Mother'),('Antonio Guerra',100108,'Dentist Pharmacist','1/974, GALI NO. 6, NEAR GAYATRI MANDIR , SHARDA NAGAR, SAHARANPUR SAHARANPUR UTTAR PRADESH','antonio@gmail.com','Father'),('Arthur Yoder',100090,'Insurance Sales Agent','1128 , MAHAVEER NAGAR 1st. KOTA RAJASTHAN','arthur@gmail.com','Father'),('Barbara James',100117,'Health Technologist or Technician','180 Laxman Vihar II , Kunhadi Kota Rajasthan','barbara@gmail.com','Mother'),('Benjamin Lyons',100063,'Social Scientist and Related Worker','beside IDBI bank,ward no. 5 , pali road,shahdol shahdol madhya pradesh','benjamin@gmail.com','Father'),('Brian Wise',100122,'Other Office and Administrative Support Occupation','V MURALIDHARAN, S/O R VARATHARAJU REDDY , GENGANELLORE (Vill & Post) (via) ANAICUT, Vellore (Distt) TAMIL NADU','brian@gmail.com','Father'),('Carlos Stewart',100080,'Retail Sales Worker','S/O-PRAKASH CHAND MEENA , BARAGAMA ROAD, SURAJ COLONY HINDAUN CITY RAJASTHAN','carlos@gmail.com','Father'),('Carolyn Knapp',100109,'Military','A-461,Jawahar Nagar , Near Saluja Nursing Home Bharatpur Rajasthan','carolyn@gmail.com','Other'),('Cassandra Johnston',100114,'Accountant, Auditor','673/6 KRISHAN KUTIR , PATEL NAGAR KURUKSHETRA HARYANA','cassandra@gmail.com','Mother'),('Charles Clark',100095,'Engineer','QTR 4307 A IOCL PARADIP REFINERY TOWNSHIP , JAGATSINGHPUR DISTRICT PARADIP ODISHA','charles@gmail.com','Father'),('Charles Hamilton',100106,'Secretary or Administrative Assistant','b.t.i road sherpura , gali no.4 vidisha madhya pradesh','charles@gmail.com','Father'),('Charlotte Stout',100081,'Military','A-4, Pride Galaxy, B/H Ryan International School , Itkheda, Paithan Road Aurangabad Maharashtra','charlotte@gmail.com','Mother'),('Cheryl Hill',100087,'Insurance Sales Agent','Sh 17/235-10L Malti Nagar, Indrapur , P.O Shivpur Varanasi Uttar Pradesh','cheryl@gmail.com','Mother'),('Christian Bauer',100123,'Construction Manager','C-39, LAJPAT MARG, , C-SCHEME Jaipur Rajasthan','christian@gmail.com','Father'),('Christopher Bishop',100092,'Motor Vehicle Operator','673/6 KRISHAN KUTIR , PATEL NAGAR KURUKSHETRA HARYANA','christopher@gmail.com','Father'),('Christopher Lucas',100099,'Supervisor of Administrative Support Workers','1-88-1, Gopal Nagar 4th line extenction , Ongole Andhra Pradesh','christopher@gmail.com','Other'),('Christy Hancock',100071,'Secretary or Administrative Assistant','669 R K PURAM SECTOR-A , KOTA RAJASTHAN','christy@gmail.com','Mother'),('Cody Chapman',100073,'Computer Specialist','1128 , MAHAVEER NAGAR 1st. KOTA RAJASTHAN','cody@gmail.com','Father'),('Craig Lopez',100067,'Production Occupations','6A/181 , Vrindavan Yojana-1 Lucknow Uttar Pradesh','craig@gmail.com','Father'),('Craig Velasquez',100097,'Aircraft Pilot','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli Hyderabad Telangana','craig@gmail.com','Father'),('Daniel Morrow',100061,'Lawyer','KHAIRUDDINPUR CHUNAR , CHUNAR MIRZAPUR CHUNAR UTTAR PRADESH','daniel@gmail.com','Father'),('Daniel Ramos',100082,'Therapist','Quarter No. 03/IV Campus Colony , Radhe Hari Govt. P.G. College Kashipur UTTARAKHAND','daniel@gmail.com','Father'),('David Allen',100088,'Sales Supervisor','Village: Basadhi Post:Saidupur CHAKIA CHANDAULI , Pin code:232103 CHANDAULI UTTAR PRADESH','david@gmail.com','Father'),('David Oneal',100096,'Therapist','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road gwalior madhya pradesh','david@gmail.com','Other'),('David Rivera',100119,'Dentist Pharmacist','h.no:12-3-100/5/1/4,khadarpura,siddipet , 118/3RT,sr nagar , ameerpet,hyderabad siddipet telangana','david@gmail.com','Father'),('Douglas Lopez',100112,'Computer Specialist','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road gwalior madhya pradesh','douglas@gmail.com','Father'),('Dr. Charles Cervantes',100104,'Motor Vehicle Operator','6A/181 , Vrindavan Yojana-1 Lucknow Uttar Pradesh','dr.@gmail.com','Father'),('Edward Hall',100091,'Other Services Occupation','vill-MIRDAHACHAK, p.o.-POARI, p.s.-HARNAUT, dist.-NALANDA, state-BIHAR , BIHAR SHARIF BIHAR','edward@gmail.com','Father'),('Edward Villegas',100083,'Retail Sales Worker','Sabeli Premkapura Himmat Nagar Road , Sujanganj Jaunpur Uttar Pradesh','edward@gmail.com','Father'),('Gordon Francis',100070,'Other Office and Administrative Support Occupation','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road gwalior madhya pradesh','gordon@gmail.com','Father'),('Jason Beard',100069,'Social Scientist and Related Worker','NO-26,MUNESHWARA NILAYA,1ST CROSS,SIR M.V.NAGAR , KALKERE MAIN ROAD,RAMAMURTHY NAGAR,BANGALORE BANGALORE KARNATAKA','jason@gmail.com','Father'),('Jason Maynard',100085,'Accountant, Auditor','Quarter No. 03/IV Campus Colony , Radhe Hari Govt. P.G. College Kashipur UTTARAKHAND','jason@gmail.com','Father'),('Jennifer Strickland',100118,'Healthcare Practitioners','V.p.o. Churri Wala dhanna , near krishanmandir , Tehsil and District : Fazilka Abohar Punjab','jennifer@gmail.com','Mother'),('Jesse Gibson',100120,'Business Operations or Financial Specialist','Sabeli Premkapura Himmat Nagar Road , Sujanganj Jaunpur Uttar Pradesh','jesse@gmail.com','Father'),('Jimmy Thomas',100121,'Retail Sales Worker','QR. NO. 49 , SECTOR-3/A BOKARO STEEL CITY JHARKHAND','jimmy@gmail.com','Father'),('Joe Santos',100072,'Therapist','18-A, Vishnu Garden , P.O. Gurukul kangri Haridwar Uttarakhand','joe@gmail.com','Father'),('John Bennett',100093,'Sales Supervisor','125,raja bakshar ki goth , daulatganj, lashkar gwalior madhya pradesh','john@gmail.com','Other'),('Jonathan Martinez',100075,'Supervisor of Administrative Support Workers','C-39, LAJPAT MARG, , C-SCHEME Jaipur Rajasthan','jonathan@gmail.com','Father'),('Jorge Flores',100086,'Physical Scientist','Plot No. 24, Raghunath Nagar , Mahmoorganj Varanasi Uttar Pradesh','jorge@gmail.com','Father'),('Joseph Moon',100105,'Personal Care and Service','Kanchanpur Chakand , Belaganj Gaya Gaya Bihar','joseph@gmail.com','Father'),('Joseph Walker',100102,'Supervisor of Administrative Support Workers','S/O-PRAKASH CHAND MEENA , BARAGAMA ROAD, SURAJ COLONY HINDAUN CITY RAJASTHAN','joseph@gmail.com','Father'),('Joshua Yang',100084,'Lawyer','RAMJEE CHAK YADAV GALI , BATA GANJ DIGHA PATNA BIHAR','joshua@gmail.com','Father'),('Lori Santiago',100115,'Retail Sales','6A/181 , Vrindavan Yojana-1 Lucknow Uttar Pradesh','lori@gmail.com','Mother'),('Mario Johnson',100065,'Financial Clerk','D/No:21/605-1, , Parasupeta, Machilipatnam, Krishna dist. Andhra Pradesh','mario@gmail.com','Father'),('Mark Reed',100101,'Teacher or Instructor','942 , ISLAMNAGAR SAHARANPUR UTTAR PRADESH','mark@gmail.com','Father'),('Matthew Price',100064,'Other Healthcare Support Occupation','40 ashok colony morar gwalior , gwalior mp','matthew@gmail.com','Father'),('Michael Mercer',100074,'Therapist','Plot No. 24, Raghunath Nagar , Mahmoorganj Varanasi Uttar Pradesh','michael@gmail.com','Father'),('Mrs. Debra Webb MD',100089,'Therapist','Naga road , Near jp school raxaul Raxaul Bihar','mrs.@gmail.com','Mother'),('Nancy Hall',100078,'General and Operations Manager','House No. 1150 , Sector-3 Faridabad Haryana','nancy@gmail.com','Mother'),('Nicholas George',100062,'Healthcare Practitioners','1-88-1, Gopal Nagar 4th line extenction , Ongole Andhra Pradesh','nicholas@gmail.com','Father'),('R C Prasad',100001,'Govt Employee','Patel Nagar, Sihodih, Giridih','jgbrcprasad@gmail.com','Father'),('Randy Sanchez',100077,'Supervisor of Administrative Support Workers','h.no:12-3-100/5/1/4,khadarpura,siddipet , 118/3RT,sr nagar , ameerpet,hyderabad siddipet telangana','randy@gmail.com','Father'),('Rebecca Brown',100066,'Aircraft Pilot','A-461,Jawahar Nagar , Near Saluja Nursing Home Bharatpur Rajasthan','rebecca@gmail.com','Mother'),('Richard Whitaker',100113,'Aircraft Pilot','Sanjeev Kumar Rana CO swarna kanta sood , Ward no. 5 amarpuri dehra gopipur district kangra h.p. Dehra gopipur Himachal Pradesh','richard@gmail.com','Father'),('Samantha Daniels',100079,'Secretary or Administrative Assistant','Tola: Petarpahri P/O:Chakai P/S:Chakai , District: Jamui Jamui District Bihar','samantha@gmail.com','Mother'),('Sarah Hill',100076,'Physical Scientist','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli Hyderabad Telangana','sarah@gmail.com','Mother'),('Sheila Cherry',100094,'Construction and Extraction','MEENO KI DHANI VPO- ANANTPURA , VIA- JAITPURA TEH- CHOMU JAIPUR RAJASTHAN','sheila@gmail.com','Mother'),('Tommy Ward',100110,'Other Professional Occupation','15,SUKHANAND BAGECHI , SIWANCHI GATE JODHPUR RAJASTHAN','tommy@gmail.com','Father'),('Tracy Garza',100111,'Life Scientist','Village and Post Kali Jagdishpur , Village and Post Kali Jagdishpur Khalilabad U.P','tracy@gmail.com','Other'),('Troy Conley',100107,'Production Occupations','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli Hyderabad Telangana','troy@gmail.com','Father'),('William Anderson',100100,'Business Operations or Financial Specialist','14-4-259, Joshiwadi, Begum Bazar, Near Deepa Hospital , HYDERABAD TELANGANA','william@gmail.com','Father'),('Zachary Webb',100068,'Insurance Sales Agent','Plot No. 24, Raghunath Nagar , Mahmoorganj Varanasi Uttar Pradesh','zachary@gmail.com','Father');
/*!40000 ALTER TABLE `Guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GuardianPhoneNumber`
--

DROP TABLE IF EXISTS `GuardianPhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GuardianPhoneNumber` (
  `phoneNumber` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `studentId` int NOT NULL,
  PRIMARY KEY (`phoneNumber`,`name`,`studentId`),
  KEY `name` (`name`,`studentId`),
  CONSTRAINT `GuardianPhoneNumber_ibfk_1` FOREIGN KEY (`name`, `studentId`) REFERENCES `Guardian` (`name`, `studentId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GuardianPhoneNumber`
--

LOCK TABLES `GuardianPhoneNumber` WRITE;
/*!40000 ALTER TABLE `GuardianPhoneNumber` DISABLE KEYS */;
INSERT INTO `GuardianPhoneNumber` VALUES ('8905894834','Aaron Sanchez',100098),('9386172257','Antonio Guerra',100108),('6043111322','Carlos Stewart',100080),('6569892432','Charles Clark',100095),('9367268125','Charles Hamilton',100106),('7433772224','Charlotte Stout',100081),('7150145821','Cheryl Hill',100087),('6293933423','Christopher Bishop',100092),('9315109050','Christopher Lucas',100099),('9881440528','Cody Chapman',100073),('7305573996','Craig Lopez',100067),('9514365513','Craig Lopez',100067),('9710910369','Craig Lopez',100067),('6574483507','Craig Velasquez',100097),('7057911834','Daniel Morrow',100061),('7371644143','Daniel Morrow',100061),('7445015337','Daniel Morrow',100061),('8215065534','David Allen',100088),('9365782867','David Oneal',100096),('8862788171','David Rivera',100119),('7768373147','Douglas Lopez',100112),('7582013925','Dr. Charles Cervantes',100104),('9404627740','Edward Hall',100091),('6703064288','Edward Villegas',100083),('7408324239','Edward Villegas',100083),('8499157369','Edward Villegas',100083),('8494543835','Gordon Francis',100070),('7695444606','Jason Beard',100069),('8683296900','Jason Beard',100069),('8868067776','Jason Beard',100069),('7976429813','Jason Maynard',100085),('7596176482','Jesse Gibson',100120),('8935332652','Jimmy Thomas',100121),('9944926051','Jimmy Thomas',100121),('8409227638','Joe Santos',100072),('7264526954','Jorge Flores',100086),('9705332722','Jorge Flores',100086),('7622652677','Joseph Walker',100102),('6703828352','Joshua Yang',100084),('8535793013','Joshua Yang',100084),('6523930742','Mario Johnson',100065),('7353380128','Mario Johnson',100065),('7584228215','Mark Reed',100101),('9034734926','Mark Reed',100101),('6753945544','Matthew Price',100064),('8894245136','Mrs. Debra Webb MD',100089),('9440692664','Mrs. Debra Webb MD',100089),('8739954062','Nancy Hall',100078),('9618632685','Nancy Hall',100078),('7922901863','Nicholas George',100062),('6205144592','R C Prasad',100001),('7854204179','Randy Sanchez',100077),('9640087687','Randy Sanchez',100077),('7211148153','Rebecca Brown',100066),('7627806785','Rebecca Brown',100066),('7881615713','Rebecca Brown',100066),('9003073825','Richard Whitaker',100113),('7468350276','Samantha Daniels',100079),('8869855234','Sarah Hill',100076),('7534755436','Sheila Cherry',100094),('8630572963','Sheila Cherry',100094),('8978141094','Tommy Ward',100110),('6846743725','William Anderson',100100),('9338820327','William Anderson',100100);
/*!40000 ALTER TABLE `GuardianPhoneNumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payroll`
--

DROP TABLE IF EXISTS `Payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payroll` (
  `paymentRefNo` varchar(255) NOT NULL,
  `month` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `salaryCredited` decimal(10,2) NOT NULL,
  `dateCredited` date NOT NULL,
  `employeeId` int NOT NULL,
  PRIMARY KEY (`paymentRefNo`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `Payroll_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payroll`
--

LOCK TABLES `Payroll` WRITE;
/*!40000 ALTER TABLE `Payroll` DISABLE KEYS */;
INSERT INTO `Payroll` VALUES ('DFKLDF123','1',2020,0.00,'2020-02-01',100027),('IGAD82491','1',2018,5000.50,'2020-10-10',100025);
/*!40000 ALTER TABLE `Payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Result`
--

DROP TABLE IF EXISTS `Result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Result` (
  `studentId` int NOT NULL AUTO_INCREMENT,
  `testId` int NOT NULL,
  `marksScored` int NOT NULL,
  `hasAppliedRecheck` tinyint(1) NOT NULL DEFAULT '0',
  `isDoneRecheck` tinyint(1) NOT NULL DEFAULT '0',
  `recheckComments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentId`,`testId`),
  KEY `testId` (`testId`),
  CONSTRAINT `Result_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Result_ibfk_2` FOREIGN KEY (`testId`) REFERENCES `Test` (`testId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100089 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Result`
--

LOCK TABLES `Result` WRITE;
/*!40000 ALTER TABLE `Result` DISABLE KEYS */;
INSERT INTO `Result` VALUES (100001,1,9,0,0,''),(100001,3,100,0,0,''),(100062,1,9,0,0,NULL),(100063,1,0,1,0,''),(100068,1,8,0,0,NULL),(100069,1,10,1,0,''),(100076,3,15,0,0,''),(100088,3,50,0,0,NULL);
/*!40000 ALTER TABLE `Result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `staffId` int NOT NULL AUTO_INCREMENT,
  `gender` enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
  `dateOfBirth` date NOT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `employeeId` int NOT NULL,
  PRIMARY KEY (`staffId`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `Staff_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (3,'Male','1972-04-30','781','14-4-259, Joshiwadi, Begum Bazar, Near Deepa Hospital ,','HYDERABAD','TELANGANA',100027),(4,'Male','1973-06-20','939','Village +Po-sakhua via pipra bazaar , District-supaul','Supaul','Bihar',100030),(5,'Male','1991-09-10','311','VILL:GODHIYARI, P.O. RAGHOPUR, BLOCK:MANIGACHHI DIST:DARBHANGA,BIHAR PIN CODE:847239 ,','darbhanga','Bihar',100031),(6,'Male','1975-09-04','618','KHAIRUDDINPUR CHUNAR , CHUNAR MIRZAPUR','CHUNAR','UTTAR PRADESH',100035),(7,'Male','1985-11-24','791','Agrawal service station nagar , HP petrol pump nagar','bharatpur','rajasthan',100042);
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StaffBatchDetails`
--

DROP TABLE IF EXISTS `StaffBatchDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StaffBatchDetails` (
  `staffId` int NOT NULL,
  `batchId` varchar(255) NOT NULL,
  `courseId` varchar(255) NOT NULL,
  PRIMARY KEY (`staffId`,`batchId`,`courseId`),
  KEY `batchId` (`batchId`,`courseId`),
  CONSTRAINT `StaffBatchDetails_ibfk_1` FOREIGN KEY (`staffId`) REFERENCES `Staff` (`staffId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `StaffBatchDetails_ibfk_2` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `Batch` (`batchId`, `courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StaffBatchDetails`
--

LOCK TABLES `StaffBatchDetails` WRITE;
/*!40000 ALTER TABLE `StaffBatchDetails` DISABLE KEYS */;
INSERT INTO `StaffBatchDetails` VALUES (4,'B1','C01'),(7,'B1','C01'),(4,'B1','C02'),(7,'B1','C02'),(4,'B1','C03'),(7,'B1','C03'),(4,'B1','C04'),(7,'B1','C04'),(4,'B1','C05'),(6,'B1','C05'),(7,'B1','C05'),(5,'B2','C01'),(7,'B2','C01'),(5,'B2','C02'),(7,'B2','C02'),(5,'B2','C03'),(7,'B2','C03'),(5,'B2','C04'),(7,'B2','C04'),(5,'B2','C05'),(6,'B2','C05'),(7,'B2','C05'),(6,'B3','C05'),(7,'B3','C05'),(3,'BS','C01'),(7,'BS','C01'),(7,'BS','C02'),(3,'BS','C03'),(7,'BS','C03'),(3,'BS','C05'),(6,'BS','C05'),(7,'BS','C05');
/*!40000 ALTER TABLE `StaffBatchDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Student` (
  `studentId` int NOT NULL AUTO_INCREMENT,
  `gender` enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
  `dateOfBirth` date NOT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `schoolAttending` varchar(255) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`studentId`),
  KEY `userId` (`userId`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100129 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (100001,'Male','2000-10-15','10','Patel Nagar, Sihodih','Giridih','Jharkhand','Carmel School',4),(100061,'Not Specified','2003-11-10','309','KHAIRUDDINPUR CHUNAR , CHUNAR MIRZAPUR','CHUNAR','UTTAR PRADESH','Ryan International School',127),(100062,'Female','2005-03-18','434','1-88-1, Gopal Nagar 4th line extenction ,','Ongole','Andhra Pradesh','Holy Child Central School',128),(100063,'Male','2003-10-17','277','beside IDBI bank,ward no. 5 , pali road,shahdol','shahdol','madhya pradesh','Saifee Golden Jubliee English Public School',129),(100064,'Male','2001-08-04','539','40 ashok colony morar gwalior ,','gwalior','mp','St. Aloysius\' High School',130),(100065,'Male','2006-07-02','365','D/No:21/605-1, , Parasupeta,','Machilipatnam, Krishna dist.','Andhra Pradesh','Deepti Convent School',132),(100066,'Male','2003-04-24','491','A-461,Jawahar Nagar , Near Saluja Nursing Home','Bharatpur','Rajasthan','De Nobili School',133),(100067,'Male','2005-02-24','631','6A/181 , Vrindavan Yojana-1','Lucknow','Uttar Pradesh','Indo-swiss International Convent School',138),(100068,'Male','2004-03-05','582','Plot No. 24, Raghunath Nagar , Mahmoorganj','Varanasi','Uttar Pradesh','Littlewood Public School',139),(100069,'Female','2004-10-14','557','NO-26,MUNESHWARA NILAYA,1ST CROSS,SIR M.V.NAGAR , KALKERE MAIN ROAD,RAMAMURTHY NAGAR,BANGALORE','BANGALORE','KARNATAKA','Christu Jyoti Convent School',141),(100070,'Male','2003-08-28','635','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road','gwalior','madhya pradesh','Ruhiyyih School',142),(100071,'Female','2004-11-10','353','669 R K PURAM SECTOR-A ,','KOTA','RAJASTHAN','Prem Seva Vidyalaya',143),(100072,'Male','2002-03-18','987','18-A, Vishnu Garden , P.O. Gurukul kangri','Haridwar','Uttarakhand','Bishop Sargant School',144),(100073,'Female','2002-10-23','209','1128 , MAHAVEER NAGAR 1st.','KOTA','RAJASTHAN','Christ Academy I.c.s.e. School',148),(100074,'Male','2001-08-25','152','Plot No. 24, Raghunath Nagar , Mahmoorganj','Varanasi','Uttar Pradesh','Tribeni Tissues Vidyapith',149),(100075,'Male','2002-04-30','940','C-39, LAJPAT MARG, , C-SCHEME','Jaipur','Rajasthan','Carmel Academy',152),(100076,'Female','2005-03-07','736','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli','Hyderabad','Telangana','St. Xavier\'s High School',153),(100077,'Male','2003-08-19','284','h.no:12-3-100/5/1/4,khadarpura,siddipet , 118/3RT,sr nagar , ameerpet,hyderabad','siddipet','telangana','Sahyadri  School',156),(100078,'Male','2004-08-04','160','House No. 1150 , Sector-3','Faridabad','Haryana','Guru Nanak Public School',159),(100079,'Male','2001-02-26','299','Tola: Petarpahri P/O:Chakai P/S:Chakai , District: Jamui','Jamui District','Bihar','PARIVARTHAN ELITE ENGLISH MEDIUM SCHOOL',160),(100080,'Male','2003-07-25','970','S/O-PRAKASH CHAND MEENA , BARAGAMA ROAD, SURAJ COLONY','HINDAUN CITY','RAJASTHAN','Girish Prasad Memorial College',162),(100081,'Male','2003-02-23','914','A-4, Pride Galaxy, B/H Ryan International School , Itkheda, Paithan Road','Aurangabad','Maharashtra','Vidya Vikas School',163),(100082,'Female','2003-06-03','635','Quarter No. 03/IV Campus Colony , Radhe Hari Govt. P.G. College','Kashipur','UTTARAKHAND','Vivekananda English High School',164),(100083,'Male','2001-05-05','734','Sabeli Premkapura Himmat Nagar Road , Sujanganj','Jaunpur','Uttar Pradesh','The Woods Heritage School',165),(100084,'Male','2003-12-16','944','RAMJEE CHAK YADAV GALI , BATA GANJ DIGHA','PATNA','BIHAR','Holy Family Public School',167),(100085,'Male','2003-12-25','4','Quarter No. 03/IV Campus Colony , Radhe Hari Govt. P.G. College','Kashipur','UTTARAKHAND','Kohinoor International School',168),(100086,'Male','2004-06-02','260','Plot No. 24, Raghunath Nagar , Mahmoorganj','Varanasi','Uttar Pradesh','Dr. Virendra Swarup Public School',169),(100087,'Male','2002-03-15','726','Sh 17/235-10L Malti Nagar, Indrapur , P.O Shivpur','Varanasi','Uttar Pradesh','Carmel Academy',171),(100088,'Male','2003-11-09','765','Village: Basadhi Post:Saidupur CHAKIA CHANDAULI , Pin code:232103','CHANDAULI','UTTAR PRADESH','Auxilium Convent School',172),(100089,'Female','2005-10-01','533','Naga road , Near jp school raxaul','Raxaul','Bihar','Jnanasarovara International Residential School',173),(100090,'Not Specified','2001-04-22','913','1128 , MAHAVEER NAGAR 1st.','KOTA','RAJASTHAN','International Community School & Junior College',176),(100091,'Male','2005-10-20','134','vill-MIRDAHACHAK, p.o.-POARI, p.s.-HARNAUT, dist.-NALANDA, state-BIHAR ,','BIHAR SHARIF','BIHAR','Beachwood School',178),(100092,'Male','2006-07-25','72','673/6 KRISHAN KUTIR , PATEL NAGAR','KURUKSHETRA','HARYANA','Sandal Wood School',179),(100093,'Male','2003-02-22','781','125,raja bakshar ki goth , daulatganj, lashkar','gwalior','madhya pradesh','St. Mary\'s Inter College',180),(100094,'Male','2004-12-09','920','MEENO KI DHANI VPO- ANANTPURA , VIA- JAITPURA TEH- CHOMU','JAIPUR','RAJASTHAN','Twinklers School',181),(100095,'Female','2002-10-09','48','QTR 4307 A IOCL PARADIP REFINERY TOWNSHIP , JAGATSINGHPUR DISTRICT','PARADIP','ODISHA','S.D.A. Higher Secondary School',182),(100096,'Male','2005-09-17','383','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road','gwalior','madhya pradesh','Krishna Foundation Academy',187),(100097,'Male','2003-10-12','411','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli','Hyderabad','Telangana','St. Theresa\'s School',189),(100098,'Male','2006-04-04','3','V.p.o. Churri Wala dhanna , near krishanmandir , Tehsil and District : Fazilka','Abohar','Punjab','Sai Girls Public School',190),(100099,'Not Specified','2001-12-14','341','1-88-1, Gopal Nagar 4th line extenction ,','Ongole','Andhra Pradesh','Seventh Day Adventists High School',191),(100100,'Male','2001-11-03','267','14-4-259, Joshiwadi, Begum Bazar, Near Deepa Hospital ,','HYDERABAD','TELANGANA','Rose Bud School',193),(100101,'Female','2001-02-22','368','942 , ISLAMNAGAR','SAHARANPUR','UTTAR PRADESH','Punjab International Public School',195),(100102,'Female','2004-09-14','394','S/O-PRAKASH CHAND MEENA , BARAGAMA ROAD, SURAJ COLONY','HINDAUN CITY','RAJASTHAN','Prime Academy',196),(100103,'Male','2004-03-30','676','Marui (Itahi), Jakhini, Varanasi ,','Varanasi','Uttar Pradesh','Green Lawn School',197),(100104,'Female','2003-06-09','890','6A/181 , Vrindavan Yojana-1','Lucknow','Uttar Pradesh','Ramakrishna Vivekananda Int. Eng. High School',198),(100105,'Male','2001-03-12','797','Kanchanpur Chakand , Belaganj Gaya','Gaya','Bihar','Green Park Central School',201),(100106,'Male','2006-05-01','953','b.t.i road sherpura , gali no.4','vidisha','madhya pradesh','R. J. International',202),(100107,'Female','2004-02-13','111','78, Lavender, L&T Serene County , Telecom Nagar, Gachibowli','Hyderabad','Telangana','Sacred Heart School',203),(100108,'Female','2005-03-25','344','1/974, GALI NO. 6, NEAR GAYATRI MANDIR , SHARDA NAGAR, SAHARANPUR','SAHARANPUR','UTTAR PRADESH','Jesus And Mary Convent School',204),(100109,'Male','2002-08-28','113','A-461,Jawahar Nagar , Near Saluja Nursing Home','Bharatpur','Rajasthan','St. Thomas\' School',206),(100110,'Male','2003-09-01','974','15,SUKHANAND BAGECHI , SIWANCHI GATE','JODHPUR','RAJASTHAN','Greenlawns School',210),(100111,'Female','2003-08-28','225','Village and Post Kali Jagdishpur , Village and Post Kali Jagdishpur','Khalilabad','U.P','St. Augustine\'s Day School',212),(100112,'Male','2005-08-15','643','mandil niwas ,damodar bagh colony , bhodapur , sagar tal road','gwalior','madhya pradesh','The Indian Public School',216),(100113,'Male','2002-11-01','532','Sanjeev Kumar Rana CO swarna kanta sood , Ward no. 5 amarpuri dehra gopipur district kangra h.p.','Dehra gopipur','Himachal Pradesh','Deepti Convent Higher Secondary School',218),(100114,'Male','2001-09-07','554','673/6 KRISHAN KUTIR , PATEL NAGAR','KURUKSHETRA','HARYANA','The Bishop\'s Co-ed School',219),(100115,'Male','2003-09-24','68','6A/181 , Vrindavan Yojana-1','Lucknow','Uttar Pradesh','Shishuvan English Medium School',220),(100116,'Male','2002-01-06','531','b.t.i road sherpura , gali no.4','vidisha','madhya pradesh','Playway English School',221),(100117,'Male','2000-11-30','500','180 Laxman Vihar II , Kunhadi','Kota','Rajasthan','R.B.K. School',222),(100118,'Male','2005-01-06','248','V.p.o. Churri Wala dhanna , near krishanmandir , Tehsil and District : Fazilka','Abohar','Punjab','Al Huda Model School',224),(100119,'Male','2006-05-17','448','h.no:12-3-100/5/1/4,khadarpura,siddipet , 118/3RT,sr nagar , ameerpet,hyderabad','siddipet','telangana','Siva Sivani Public School',227),(100120,'Male','2006-04-15','35','Sabeli Premkapura Himmat Nagar Road , Sujanganj','Jaunpur','Uttar Pradesh','Bishop Westcott Girls\' School',228),(100121,'Male','2001-10-23','4','QR. NO. 49 , SECTOR-3/A','BOKARO STEEL CITY','JHARKHAND','St. Mary\'s Convent School',234),(100122,'Male','2002-10-15','224','V MURALIDHARAN, S/O R VARATHARAJU REDDY , GENGANELLORE (Vill & Post)','(via) ANAICUT, Vellore (Distt)','TAMIL NADU','Don Bosco School',235),(100123,'Male','2003-09-28','940','C-39, LAJPAT MARG, , C-SCHEME','Jaipur','Rajasthan','Cathedral College',239);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StudyMaterial`
--

DROP TABLE IF EXISTS `StudyMaterial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StudyMaterial` (
  `materialId` varchar(255) NOT NULL,
  `subjectId` varchar(255) NOT NULL,
  `topicName` varchar(255) NOT NULL,
  `difficulty` enum('Easy','Medium','Hard') NOT NULL DEFAULT 'Easy',
  `filename` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`materialId`,`subjectId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `StudyMaterial_ibfk_1` FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StudyMaterial`
--

LOCK TABLES `StudyMaterial` WRITE;
/*!40000 ALTER TABLE `StudyMaterial` DISABLE KEYS */;
INSERT INTO `StudyMaterial` VALUES ('afsdf','CHE','hsjfd','Easy','18075068_AshishKumar_CSE_DataMining_End2.pdf','sdfs'),('hjslifdj','CHE','iusiof','Medium','Biology123.pdf','');
/*!40000 ALTER TABLE `StudyMaterial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subject`
--

DROP TABLE IF EXISTS `Subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Subject` (
  `subjectId` varchar(255) NOT NULL,
  `subjectName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subjectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subject`
--

LOCK TABLES `Subject` WRITE;
/*!40000 ALTER TABLE `Subject` DISABLE KEYS */;
INSERT INTO `Subject` VALUES ('BIO','Biology','Science Paper - III, comprising of both theoretical and lab component of Biology'),('CHE','Chemistry','Science Paper - II, comprising of both theoretical and practical Chemistry'),('COMP','Computer Applications','Computer Applications Paper, with theoretical, laboratory and programming assignments'),('ENG-LANG','English Language','English Paper I, comprising of the grammar portion'),('ENG-LIT','English Literature','English Paper II, comprising of  drama, one prose and one poetry'),('GEO','Geography','HCG Paper II, with both exam-oriented and map/project work.'),('HIN','Hindi','Secondary Language paper, comprising of Hindi Grammar, Short Stories & Poems'),('HNC','History & Civics','HCG Paper I, with both exam-oriented and project work component'),('MATH','Mathematics','Mathematics Paper, with both exam-oriented and project work component'),('PHY','Physics','Science Paper - I, comprising of both theoretical and practical Physics');
/*!40000 ALTER TABLE `Subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Teacher` (
  `teacherId` int NOT NULL AUTO_INCREMENT,
  `gender` enum('Male','Female','Not Specified') NOT NULL DEFAULT 'Not Specified',
  `dateOfBirth` date NOT NULL,
  `houseNumber` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `bachelorsDegree` varchar(255) DEFAULT NULL,
  `mastersDegree` varchar(255) DEFAULT NULL,
  `doctoralDegree` varchar(255) DEFAULT NULL,
  `employeeId` int NOT NULL,
  `subjectId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacherId`),
  KEY `employeeId` (`employeeId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `Teacher_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `Employee` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Teacher_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (15,'Male','1992-08-21','663','Bodhwan talab khaira road jamui ,','Jamui','Bihar','','','',100025,'CHE'),(16,'Male','1973-04-03','614','QTR 4307 A IOCL PARADIP REFINERY TOWNSHIP , JAGATSINGHPUR DISTRICT','PARADIP','ODISHA',NULL,NULL,NULL,100026,'MATH'),(17,'Male','1976-03-22','891','14-4-259, Joshiwadi, Begum Bazar, Near Deepa Hospital ,','HYDERABAD','TELANGANA','','','',100028,'BIO'),(18,'Male','1977-08-01','175','VILLAGE + POST - KANTA , Via - Keotasha Baruari','Muzaffarpur','Bihar','','','',100029,'COMP'),(19,'Male','1972-02-15','29','942 , ISLAMNAGAR','SAHARANPUR','UTTAR PRADESH','','','',100032,'ENG-LANG'),(20,'Male','1972-08-28','273','C-39, LAJPAT MARG, , C-SCHEME','Jaipur','Rajasthan','','','',100033,'ENG-LIT'),(21,'Male','1999-09-02','153','Sardarpura ward no. 1 ,','Barmer','Rajasthan','','','',100034,'GEO'),(22,'Male','1981-11-01','15','A 336/I CHANDRAVARDAI NAGAR ,','Ajmer','RAJASTHAN','','','',100036,'HIN'),(23,'Female','1996-04-18','496','Kalambagh Road ,Gannipur, Bangali Colony, Near Vypaar mandal ,','Muzaffarpur','BIHAR','','','',100037,'HNC'),(24,'Male','2000-08-21','853','Street no.-23 Qrs. no.-74/A , Chittaranjan','Chittaranjan','West Bengal','','','',100038,'PHY'),(25,'Male','1998-12-21','938','BHAKTI KUNJ, 16/6 Gundawadi Corner, , Rajkot','Rajkot','Gujarat','','','',100039,'BIO'),(26,'Male','1974-01-15','22','VAISHNAVI NAGAR COLONY CHANDPUR , INDUSTRIAL ESTATE VARANASI','Varanasi','UTTAR PRADESH','','','',100040,'ENG-LIT'),(27,'Male','1997-02-01','269','A-4, Pride Galaxy, B/H Ryan International School , Itkheda, Paithan Road','Aurangabad','Maharashtra','','','',100041,'PHY'),(28,'Male','1996-01-21','254','KHAIRUDDINPUR CHUNAR , CHUNAR MIRZAPUR','CHUNAR','UTTAR PRADESH','','','',100043,'COMP'),(29,'Not Specified','1983-10-26','818','VILL:GODHIYARI, P.O. RAGHOPUR, BLOCK:MANIGACHHI DIST:DARBHANGA,BIHAR PIN CODE:847239 ,','darbhanga','Bihar',NULL,NULL,NULL,100044,'MATH'),(30,'Male','1994-04-14','635','1832/1 DICROUZ COMPOUND MASIHA GANJ , NEAR ILAHI BABA KI MAZAR , SIPRI BAZAR , JHANSI ,','JHANSI','UTTAR PRADESH','','','',100045,'HNC'),(31,'Female','1987-06-28','238','vill-MIRDAHACHAK, p.o.-POARI, p.s.-HARNAUT, dist.-NALANDA, state-BIHAR ,','BIHAR SHARIF','BIHAR','','','',100046,'CHE'),(32,'Female','1992-12-27','78','14-4-259, Joshiwadi, Begum Bazar, Near Deepa Hospital ,','HYDERABAD','TELANGANA','','','',100047,'COMP');
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TeacherBatchDetails`
--

DROP TABLE IF EXISTS `TeacherBatchDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TeacherBatchDetails` (
  `teacherId` int NOT NULL,
  `batchId` varchar(255) NOT NULL,
  `courseId` varchar(255) NOT NULL,
  PRIMARY KEY (`teacherId`,`batchId`,`courseId`),
  KEY `batchId` (`batchId`,`courseId`),
  CONSTRAINT `TeacherBatchDetails_ibfk_1` FOREIGN KEY (`teacherId`) REFERENCES `Teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TeacherBatchDetails_ibfk_2` FOREIGN KEY (`batchId`, `courseId`) REFERENCES `Batch` (`batchId`, `courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TeacherBatchDetails`
--

LOCK TABLES `TeacherBatchDetails` WRITE;
/*!40000 ALTER TABLE `TeacherBatchDetails` DISABLE KEYS */;
INSERT INTO `TeacherBatchDetails` VALUES (15,'B1','C01'),(17,'B1','C01'),(24,'B1','C01'),(27,'B1','C01'),(31,'B1','C01'),(16,'B1','C02'),(29,'B1','C02'),(21,'B1','C03'),(23,'B1','C03'),(30,'B1','C03'),(19,'B1','C04'),(20,'B1','C04'),(22,'B1','C04'),(26,'B1','C04'),(15,'B2','C01'),(17,'B2','C01'),(24,'B2','C01'),(31,'B2','C01'),(29,'B2','C02'),(23,'B2','C03'),(30,'B2','C03'),(19,'B2','C04'),(20,'B2','C04'),(22,'B2','C04'),(26,'B2','C04'),(18,'B2','C05'),(28,'B2','C05'),(32,'B2','C05'),(18,'B3','C05'),(28,'B3','C05'),(32,'B3','C05'),(25,'BS','C01'),(27,'BS','C01'),(16,'BS','C02'),(21,'BS','C03');
/*!40000 ALTER TABLE `TeacherBatchDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Test`
--

DROP TABLE IF EXISTS `Test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Test` (
  `testId` int NOT NULL AUTO_INCREMENT,
  `testName` varchar(255) NOT NULL,
  `roomNumber` int DEFAULT NULL,
  `testDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `maximumMarks` int NOT NULL,
  `difficulty` enum('Easy','Medium','Hard') NOT NULL DEFAULT 'Easy',
  `courseId` varchar(255) NOT NULL,
  PRIMARY KEY (`testId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `Test_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `Course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test`
--

LOCK TABLES `Test` WRITE;
/*!40000 ALTER TABLE `Test` DISABLE KEYS */;
INSERT INTO `Test` VALUES (1,'Sample Test',10,'2020-11-24','10:20:00','10:30:00',10,'Easy','C01'),(2,'Sample Test',3,'2020-11-24','10:00:00','12:00:00',50,'Easy','C02'),(3,'First test',10,'2020-11-26','23:10:00','23:30:00',100,'Easy','C05'),(4,'Temp123',2,'2020-11-10','08:10:00','08:20:00',110,'Hard','C02'),(5,'Full Course Test',5,'2020-11-25','05:10:00','05:20:00',90,'Medium','C05'),(6,'Mid Term Test',4,'2020-11-20','01:20:00','05:20:00',50,'Easy','C03');
/*!40000 ALTER TABLE `Test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transaction` (
  `transactionId` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `transactionMode` enum('Offline','Online') NOT NULL DEFAULT 'Online',
  `isSuccess` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
INSERT INTO `Transaction` VALUES (1,10000.00,'2020-11-24','18:34:05','Offline',0),(2,11000.00,'2020-11-25','09:16:03','Offline',0),(3,11000.00,'2020-11-25','09:16:48','Offline',0),(4,15000.00,'2020-11-25','09:30:05','Online',1),(5,15000.00,'2020-11-25','09:31:47','Offline',0),(6,0.00,'2020-11-25','10:10:23','Offline',0),(7,15000.00,'2020-11-25','10:13:49','Offline',0),(8,15000.00,'2020-11-25','10:14:02','Offline',0),(9,12000.00,'2020-11-25','10:31:32','Offline',0),(10,12000.00,'2020-11-25','10:31:35','Offline',0),(11,12000.00,'2020-11-25','10:31:38','Offline',0),(12,12000.00,'2020-11-25','10:31:41','Offline',0),(13,13000.00,'2020-11-25','10:31:50','Offline',0),(14,13000.00,'2020-11-25','10:31:53','Offline',0),(15,13000.00,'2020-11-25','10:31:56','Offline',0),(16,13000.00,'2020-11-25','10:32:10','Offline',0),(17,6500.00,'2020-11-25','10:32:17','Offline',0),(18,6500.00,'2020-11-25','10:32:19','Offline',0),(19,6500.00,'2020-11-25','10:32:21','Offline',0),(20,6500.00,'2020-11-25','10:32:24','Offline',0),(21,6500.00,'2020-11-25','10:32:28','Offline',0),(22,6500.00,'2020-11-25','10:32:31','Offline',0),(23,6100.00,'2020-11-25','10:32:36','Offline',0),(24,6100.00,'2020-11-25','10:32:40','Offline',0),(25,15000.00,'2020-11-25','10:33:23','Offline',0),(26,15000.00,'2020-11-25','10:33:25','Offline',0),(27,15000.00,'2020-11-25','10:33:27','Offline',0),(28,15000.00,'2020-11-25','10:33:29','Offline',0),(29,15000.00,'2020-11-25','10:33:32','Offline',0),(30,15000.00,'2020-11-25','10:33:34','Offline',0),(31,15000.00,'2020-11-25','10:33:37','Offline',0),(32,15000.00,'2020-11-25','10:33:39','Offline',0),(33,15000.00,'2020-11-25','10:33:52','Offline',0),(34,15000.00,'2020-11-25','10:33:54','Offline',0),(35,15000.00,'2020-11-25','10:33:57','Offline',0),(36,15000.00,'2020-11-25','10:33:59','Offline',0),(37,15000.00,'2020-11-25','10:34:02','Offline',0),(38,15000.00,'2020-11-25','10:34:04','Offline',0),(39,16000.00,'2020-11-25','10:34:17','Offline',0),(40,16000.00,'2020-11-25','10:34:19','Offline',0),(41,16000.00,'2020-11-25','10:34:28','Offline',0),(42,16000.00,'2020-11-25','10:34:30','Offline',0),(43,16000.00,'2020-11-25','10:34:36','Offline',0),(44,10000.00,'2020-11-25','10:42:00','Offline',0),(45,10000.00,'2020-11-25','10:42:04','Offline',0),(46,10000.00,'2020-11-25','10:42:06','Offline',0),(47,10000.00,'2020-11-25','10:42:11','Offline',0),(48,10000.00,'2020-11-25','10:42:13','Offline',0),(49,10000.00,'2020-11-25','10:42:15','Offline',0),(50,10000.00,'2020-11-25','10:42:20','Offline',0),(51,10000.00,'2020-11-25','10:42:23','Offline',0),(52,10000.00,'2020-11-25','10:42:25','Offline',0),(53,10000.00,'2020-11-25','10:42:28','Offline',0),(54,10000.00,'2020-11-25','10:44:00','Offline',0),(55,11000.00,'2020-11-25','10:44:24','Offline',0),(56,11000.00,'2020-11-25','10:44:26','Offline',0),(57,11000.00,'2020-11-25','10:44:37','Offline',0),(58,11000.00,'2020-11-25','10:44:40','Offline',0),(59,11000.00,'2020-11-25','10:44:42','Offline',0),(60,10000.00,'2020-11-25','10:44:51','Offline',0),(61,10000.00,'2020-11-25','10:44:53','Offline',0),(62,10000.00,'2020-11-25','10:44:57','Offline',0),(63,10000.00,'2020-11-25','10:44:58','Offline',0),(64,10000.00,'2020-11-25','10:45:00','Offline',0),(65,10000.00,'2020-11-25','10:45:07','Offline',0),(66,6000.00,'2020-11-25','10:46:29','Offline',0),(67,6000.00,'2020-11-25','10:46:32','Offline',0),(68,6000.00,'2020-11-25','10:46:34','Offline',0),(69,6000.00,'2020-11-25','10:46:41','Offline',0),(70,6000.00,'2020-11-25','10:46:46','Offline',0),(71,6100.00,'2020-11-25','10:47:00','Offline',0),(72,6100.00,'2020-11-25','10:47:05','Offline',0),(73,6100.00,'2020-11-25','10:47:07','Offline',0);
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `dateCreated` date NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '0',
  `isEmailVerified` tinyint(1) NOT NULL DEFAULT '0',
  `lastLoginDate` date DEFAULT NULL,
  `lastLoginTime` time DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_STAFF','ROLE_TEACHER','ROLE_STUDENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `emailAddress` (`emailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'admin','$2b$12$VXku8PbYuTLF8QrKQNmZw.TYK90aEUYOGwHH30GMsma0ZKHj.naiS','Admin','','User','admin@balajistudycoaching.com','2020-10-20',1,1,'2020-11-25','10:33:00','ROLE_ADMIN'),(4,'ashish','$2a$10$xVqd/GodFINFjwMBvcHFU.eBUY.HeaWvCatSOvXZmqo5zDV9INEeC','Ashish','','Kumar','ashishkr23438@gmail.com','2020-10-20',1,1,'2020-11-25','10:48:25','ROLE_STUDENT'),(127,'student1','$2b$12$eYxPxAE34bTT0w.lJ0m9HO6mpJ9a/GUDlt62KjLQurhzr9Bm6aJlC','Sara','Mcclain','Turner','sara@gmail.com','2020-10-22',1,1,'2020-11-24','19:18:19','ROLE_STUDENT'),(128,'student2','$2b$12$JnOvy8oldXA3i4mNyhQkcObOtLHRojhmZSBFaczrNrhPvyu/IZv5q','Ellen','','Kelly','ellen@gmail.com','2020-10-22',0,1,'2020-11-17','22:05:45','ROLE_STUDENT'),(129,'student3','$2b$12$lrYoACJ7usemOguQRc9GtefTNEr1wAdTSxAvHTMgIwA8UdGtTutFK','Nicholas','','Bradley','nicholas@gmail.com','2020-10-22',1,0,NULL,NULL,'ROLE_STUDENT'),(130,'student4','$2b$12$tZPSmuhmZTwwTcFRzZE33eO.6Dze8RzT/0/M4HR.k.T1ecMDx4Xwe','Joseph','','Griffith','joseph@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(131,'teacher1','$2b$12$b/C2YjiL/ygIoryxsJPiu.n/ITI4tuIqkNUEU/3IdngYaOp27Pere','Edward','','Clark','edward@gmail.com','2020-10-22',1,1,'2020-11-25','09:27:42','ROLE_TEACHER'),(132,'student5','$2b$12$.JKpTXIW6D2qzFPBS8iGluzxMBID3e98.x6MoTy4jd/h7ZDTZEArK','Robert','','Daniel','robert@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(133,'student6','$2b$12$mHRRH7q9UgaynJU7muGKF.RayNoU6E6/Rx4sX09HFtqfRT6mnCEJC','Spencer','','Huang','spencer@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(135,'teacher2','$2b$12$HwEIIAQg62G6KXZcQ7aUD.HppBXmxGnVqfDN9Q1FwVtysjppIntr.','Stephen','','Wallace','stephen@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(136,'staff1','$2b$12$phaBAUnkq0G0ta88ulgTU.0DykxQ.32XRq5GeCV9cd32.qa96m9Jy','Russell','','Hayes','russell@gmail.com','2020-10-22',1,1,'2020-11-25','10:48:46','ROLE_STAFF'),(138,'student7','$2b$12$7uqORnoXQEzLLOne7AD73en02fREv4Wv5pYygy50mu5KkuFAh1azy','Gary','','Dalton','gary@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(139,'student8','$2b$12$0wD3BORB76hs0qgR01ifv.jlnpMPxA9GOcRSuLkYhqemnz0XWD4RC','William','','Williams','william@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(140,'teacher3','$2b$12$8zmsZoKmW0RsJBx8WBtJbeDsrsU2M/5baBu/cmwOvSpONGfwgkMlO','Joshua','','Mccoy','joshua@gmail.com','2020-10-22',0,1,'2020-11-17','22:06:30','ROLE_TEACHER'),(141,'student9','$2b$12$doUq9E7kRgbGzrUuIlxKb.7UWs13cs1MTk6UMfTFEILgIkn5Q77PO','Carrie','Richardson','Klein','carrie@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(142,'michael','$2b$12$v9MNiYz9WE1K2zkDxdtzZOKca390vRrh4xggoypoeC8cuaWZESJMK','Michael','','Jones','michael@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(143,'amanda','$2b$12$vHmz74rFzULV6NwYzoXK/.Ri2AZaWbRnRArfSohf7tSvm4Jj0ZoAS','Amanda','Moore','Jennings','amanda@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(144,'jack','$2b$12$O8aAvikpyUEQ8KnmXQfIBe1nCpOkw/tkkgtsbRRJtV/CKbo/BhuIS','Jack','','Garcia','jack@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(146,'teacher4','$2b$12$sG0rjsXuO0wShzUsrHqh6ecYTpl1VkJOdqvvvaS4bbwZsIA4k2dAG','Louis','','Morton','louis@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(147,'staff2','$2b$12$zyy3sxyrLXF75KJch/eO/Oe/FFf.crIfEsrshIFWl6FQ6/SVeQO2S','Bradley','','Barnes','bradley@gmail.com','2020-10-22',1,1,'2020-11-17','22:06:50','ROLE_STAFF'),(148,'cheryl','$2b$12$TScbcegGNlvXr7sPMQd9a.sbhCNeWDe6X07WC551ANzuJ23scn.Le','Cheryl','Harrell','Cardenas','cheryl@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(149,'kevin','$2b$12$Lg4qtpuUHa5MW2bFSiLRSeJAN4zjdHVrYb4iBNBWFCqq8xe5q2aiy','Kevin','','Campbell','kevin@gmail.com','2020-10-22',0,0,NULL,NULL,'ROLE_STUDENT'),(150,'maria','$2b$12$zTj6grvbEXeu61ahOeKIzuPCtrxJ.jIohO.sE0Z25AIAqhOPV9icq','Maria','Barr','Smith','maria@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(152,'wesley','$2b$12$I4LUjKNmmcuD2OBh/88mX.HhwgcT65Dryfr6kjsur7LYF9N5an61O','Wesley','','Moore','wesley@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(153,'rebecca','$2b$12$ACGq2GGGSRNDU9rzQH.VuuR2inhtm/HiGZBUC9OqjJMQGyhNkPhWK','Rebecca','May','Randall','rebecca@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(154,'staff3','$2b$12$yC49T4eO7Lfddcw/N6Iw7eE5WUsmIzG/032TGa4nDX2xuNmSwjCla','John','','Ball','john@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STAFF'),(155,'teacher5','$2b$12$bvrkl44qOkQK1GJc/KUAU.jnr77eRfYhrk5FrWzERas3BxzsfhuNC','Tommy','','Kidd','tommy@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(156,'nathan','$2b$12$gjwvihKlnUvhM1qsGoRIuuVc4EZi9bK3XNef8xmduhpffuklMLly.','Nathan','','Daniels','nathan@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(157,'teacher6','$2b$12$nsLtxHMBT.o8j44sdoeT3.doVbMgIqS8t7nR6vbQhxh1JWDRLiXM6','Samuel','','Cameron','samuel@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_TEACHER'),(159,'eric','$2b$12$VZCGtm/p/ytYNeQ5aOkOdOWEzcGSf6JZcGgLY.nMCgWjmmDCruGaW','Eric','','Parrish','eric@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(160,'wyatt','$2b$12$wjRHjw3rayDaZsX3ftIdo.ivoZKmuEw2whHgkn/a3HNbM.D3/8z1C','Wyatt','','Mayer','wyatt@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(162,'tyler','$2b$12$IiDq8EljGVlhyrBkT7VCHeoJjuJanANGsAhfYiBtSjMjw6VRSnDfi','Tyler','','Lewis','tyler@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(163,'scott','$2b$12$k0SQXRn4n27dQECMD9mSo.20iKJbsznP/4RKGbLFcD/xeYqfjKf5O','Scott','','Wright','scott@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(164,'tammy','$2b$12$TF/SB5wm7XM7x/9Y8IzVQ.nDj.ag8infuHFMBUexQYtfEwIWhaJ0i','Tammy','Snow','Mitchell','tammy@gmail.com','2020-10-22',1,0,NULL,NULL,'ROLE_STUDENT'),(165,'donald','$2b$12$UvkblIFS9GiImVdTju5TO.nqTwNypJPHYmjFQHT9Slr/wyhy64ai6','Donald','','Chavez','donald@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(166,'teacher7','$2b$12$w3MGlQN0VtzGBlQzI8y5teKyuWopkF.X6m5dPD0wUggAdZEjf2HvW','Blake','','Williams','blake@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(167,'daniel','$2b$12$Wm0/VyUslyhlU45NrAjh.uQLLJWovot78crGCJ1khKNhB2tiou1CO','Daniel','','Dean','daniel@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(168,'mark','$2b$12$fTAMxOkUIem0j/wFr..1euHQ8J7rx03OaXTpWF4g8lbvJl37.uw5y','Mark','','Jimenez','mark@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(169,'anthony','$2b$12$TaxH2QjlymyP18Rsb9eSHuYVnOH298zWWuTEZl5w6iLtJo9KQyP6S','Anthony','','Pierce','anthony@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(170,'staff4','$2b$12$wHeAZePc669fhuI5v.UisOplhccxJ/3FxN8/zn6.UU.ooFkeZR.Pa','Steven','','Mcfarland','steven@gmail.com','2020-10-22',0,0,NULL,NULL,'ROLE_STAFF'),(171,'james','$2b$12$0BPwne5dHKgvgl3Y0cM.9eOBQZ4hstYS3TxkdDQ/6nNh8nj.KT3QK','James','','Mccarthy','james@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(172,'thomas','$2b$12$.34FAkkmpRlLK/5ROXxZKevAnfU5A0aBw.BldD1KROzqYjLbGJnse','Thomas','','Graham','thomas@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(173,'ashley','$2b$12$0TD8IGLLBxxcN.RPcbcDveneM8DCFwLi0rFoMu2zS96NS2U1GedVO','Ashley','Lambert','Munoz','ashley@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(175,'shane','$2b$12$KikFmDA5KDkbDVI1g3UfOOiLLz.QVTY5w7QzvannR6AZCFKnkNA0e','Shane','','Medina','shane@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(176,'diana','$2b$12$XiFGL6AekWAwlX.BXI03Du.kKuuBDwijxqUS/1T6icFrhUEHhdwiO','Diana','Torres','Jenkins','diana@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(178,'gregory','$2b$12$Chh6HJyfyk4MLsy.GLKO3.GviJ2DPlnVbgo5t6kuze7ftwh6MPKHu','Gregory','','Barton','gregory@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(179,'aaron','$2b$12$V40CwAjZaBpVvGCEtVlq1O5tfOEwgR4/iWxCLE62g6tLp6cGJXNea','Aaron','','Torres','aaron@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(180,'george','$2b$12$9rapXpJoEHLcS08bHmMg7eS3hNlBLTfGnz8YgAptmV12twHgo4JD6','George','','Whitaker','george@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(181,'randall','$2b$12$srmNeisiNQCofvO/Esnq6ukuAiqdwoVHTrIbwy9U791UKZdhjU8T6','Randall','','Madden','randall@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(182,'pamela','$2b$12$t4/Hokyao7fCwueEmhIeBeZFCTRV60Il3NdkV4i2vHkxikQnePMeW','Pamela','','Ramsey','pamela@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(183,'teacher8','$2b$12$GHbRHoN1aIY5RC.in4JFbeYPiOaMt0MjqFieZUgROuioIG/yJ9FrG','Chris','','Hernandez','chris@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(185,'teacher9','$2b$12$f6YUfUaxdE3Lc7CcKWgbqemsi5Q5kAaVIoi58iRbi7w9A4JVV/kOa','Nicole','Thomas','Carter','nicole@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(187,'troy','$2b$12$iXi6qDPA2ZS48S4hYacSKuPn5ycXfMD1C7C2dK.YkLysDrm.mqryi','Troy','','Owen','troy@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(189,'cameron','$2b$12$A2dceB03SHYnPZb1s6YjwuFN/aNZn3aQCCctprJhshWnmbW4/0uxO','Cameron','','Ramirez','cameron@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(190,'caleb','$2b$12$HmR7ly82WEt.p1mMU5ODWewSaasE0k4X2nV/j6dBLt9z.b49k5SyK','Caleb','','Richardson','caleb@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(191,'virginia','$2b$12$CMTJoOgBvooz0xURJNYjJ.tyYD7t.vePk4XPudwIbAaatiNiD5xGO','Virginia','Shields','Smith','virginia@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(192,'travis','$2b$12$LAfiaVXjnPJTEFa6vDGlNe4yoB06RWlWTdmDSfyAjfLfk7jQTzLbq','Travis','','White','travis@gmail.com','2020-10-22',0,0,NULL,NULL,'ROLE_TEACHER'),(193,'david','$2b$12$vy/tW6mEURHugHn6DX.vXO8RSRVQA4itDruNW6uFXxwdiO4UvbTry','David','','Mcneil','david@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(195,'jessica','$2b$12$NLpzMM4g3749lqDiS8e.XeDykzcD2fLAaFYQ1LVyVFEQ1FPz16aqm','Jessica','Harrison','Herrera','jessica@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(196,'lisa','$2b$12$bCZjz1S3/YfIvXPnS.9eyuOzOXMC30D6gmDTU/nibjdfc683HOMfK','Lisa','','Fleming','lisa@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(197,'jeffrey','$2b$12$y68JRokUEPjY9RBCjhhB.Od3Rjd7vwmZQYTJMwMQkaQHM86AAtPGS','Jeffrey','','King','jeffrey@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(198,'patricia','$2b$12$JBd0wfF259GkSsEchCTI8eFTrMGqXQkPbjgeNg4CtKWGFl0y.hgIi','Patricia','Stone','Shaw','patricia@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(199,'ryan','$2b$12$ggOhae6zjksEOBaRA5KWje/ibCmYAJhScqcmH.EaqMid1.Bz3mZFC','Ryan','','Galloway','ryan@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(201,'christopher','$2b$12$qOY8gInUmEeqeBAtdY.D9eQdM8cRUs0Rh0z1gk2OwhVx2PaOFfvyi','Christopher','','Jones','christopher@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(202,'paul','$2b$12$K88Yk3UzI4526hVtB7LE2.y6RSGQAPiC7HMYrMbph2FeKdxDtdF3W','Paul','','Freeman','paul@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(203,'renee','$2b$12$BcF7CUxLBUjo6z8K50Awz.M8j5Zm34E5YjGh2IoqSfuGVmGxG2qVi','Renee','Brown','Shaw','renee@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(204,'emma','$2b$12$6J.bFAZkk5vI8DhpHT2.JudN5jwACiOS8kV0MpgpsiD6VnaFd88qW','Emma','Rodriguez','Wise','emma@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(206,'shannon','$2b$12$Xt4RMXaqhfAyBMIwTVtKAu7lptY.ZlKBI1oMKD0gP78T.Jst8f6e.','Shannon','','Cole','shannon@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(207,'mitchell','$2b$12$8mBmYHxwBzHO8tvwgAHLguZLQ5H8dHF.7HCKRusyk62o93PWGukMy','Mitchell','','Horn','mitchell@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(208,'austin','$2b$12$IbrysxYAknvlkmp/kO9ZXOx2zMdV9AZfi1R/TfubHV4rJd5Ceath2','Austin','','Wells','austin@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(210,'charles','$2b$12$9aNV38LB9n1iFR74ZDObm.XR2DQLTP1vavfWYGVQt7mR5JSeXXvbW','Charles','','Dawson','charles@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(212,'jennifer','$2b$12$PibdT1Ccy0OA8c62xhpK/eTq/MWKvdOwVU8mEZAN6xo8MStli2y0e','Jennifer','Gilbert','James','jennifer@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(216,'richard','$2b$12$JulSwc/1W3LNmuKtOhwt/OVrpOhweWAikfGDOSOQ.4dnGTeD29xTa','Richard','','Swanson','richard@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_STUDENT'),(218,'jeffery','$2b$12$CfL8rIsvQB9ZSrKieTu1B.LP2nddrVRI1mUGIe6vzj8MJ19i5c9Ji','Jeffery','','Davis','jeffery@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(219,'kent','$2b$12$TwZfR7nLSVpEBGPcdbJJJudoi6n175oWqtvN5kNwx/QqHDVSAj1P2','Kent','','Roberts','kent@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(220,'andre','$2b$12$kK624pjgymqGBjWp8yXIF.21b8rou8oTQi9jTKrRp0PgUknaqMMIO','Andre','','Watson','andre@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(221,'brandon','$2b$12$D3izNRhJq/FA8DLsIYtx8uYeUc20dScUIEktfYCnih8kj7PwRNt3y','Brandon','','Baker','brandon@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(222,'perry','$2b$12$voBM8PeiDcZ2apkeBu7.Nu9fMRtPkZtRmMHflDbT.seNKl8egbxw6','Perry','','Callahan','perry@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(224,'phillip','$2b$12$MEuyFR0iqDxeD8RQ/ccFOusEaoHWRlyCMTX7IWkDCTLHJe.i.7ETO','Phillip','','Beasley','phillip@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(227,'terry','$2b$12$zZy989n92S2UNG6nkykAhONRRyi0FUXKkMCPQzxbDpMdQDRLDSppy','Terry','','Martin','terry@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(228,'ronald','$2b$12$h6sgrO/.FGL6IQyMTwHki.GEYrK6NNV.6wYkvdfc.8YPC1g7Se032','Ronald','','Woods','ronald@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(230,'staff5','$2b$12$vkF8QUG6IBH3OCbhF42iueghU.Zr9SpeUwvGFTe8C.G4e3MPQNdVq','Dylan','','Robinson','dylan@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STAFF'),(234,'joel','$2b$12$UnceaYoKxb3jfKd7r7gxNelND.tE/neWZitpRqGggG8wEJ4g..K7S','Joel','','Mann','joel@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(235,'julian','$2b$12$M9nMy.KgPrWvpjzXLzgrGuPm6pjrBgEaUwkKd34PbJ51AIl9G18VS','Julian','','Coleman','julian@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(239,'bryan','$2b$12$txpgidBm8mKcXt6ek701eOUTIrhcHtqPZ67xzMIkD7wy/OUBopEO2','Bryan','','Ochoa','bryan@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_STUDENT'),(243,'shawn','$2b$12$aojFmZq4mHac4G5XwBHKO.B3D2CW3lEyHk6VkNEQOFo6u5YpalALy','Shawn','','Huff','shawn@gmail.com','2020-10-22',1,0,NULL,NULL,'ROLE_TEACHER'),(244,'karen','$2b$12$7QEVQ1ivnmrClapN7hVJxusLyyKz76.7.u8B4vT8iEAfV5uYaoIm2','Karen','Hall','Davis','karen@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_TEACHER'),(246,'ricky','$2b$12$MqME4WijZLjLLfPJUabV9eqbaZQqQnABWvrY7hcwwyNSbFtGWu7Pa','Ricky','','Bell','ricky@gmail.com','2020-10-22',0,1,NULL,NULL,'ROLE_TEACHER'),(247,'julie','$2b$12$MVpltaidG4J639FXUYFLDes8g4VfvWD5pbdURM2GNfom7QyD0TIH2','Julie','Gill','Adams','julie@gmail.com','2020-10-22',1,1,NULL,NULL,'ROLE_TEACHER'),(251,'brandi','$2b$12$kPCdQBVWLwNBhDnk0ow4s.U7WEy2Gim89nvHklEFo1a1mKlOLUhMq','Brandi','Keller','Martinez','brandi@gmail.com','2020-10-22',1,0,NULL,NULL,'ROLE_TEACHER');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPhoneNumber`
--

DROP TABLE IF EXISTS `UserPhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserPhoneNumber` (
  `phoneNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`phoneNumber`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `UserPhoneNumber_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPhoneNumber`
--

LOCK TABLES `UserPhoneNumber` WRITE;
/*!40000 ALTER TABLE `UserPhoneNumber` DISABLE KEYS */;
INSERT INTO `UserPhoneNumber` VALUES ('7328333943',1),('9589631858',1),('9911369198',1),('6205144592',4),('6568143277',4),('8965593094',4),('9453701827',4),('9731917196',127),('8873697405',128),('7800154539',129),('9487807850',129),('9972345604',130),('7020937071',131),('6738679263',133),('7204099809',133),('7220215311',135),('6846766230',138),('7444733681',138),('7459952923',141),('8397485038',142),('6290023627',143),('8627799216',143),('8911908889',143),('8508505597',144),('8779435690',146),('6423064562',149),('6546099953',149),('9556027987',149),('7178639502',150),('8076789082',153),('9302774311',154),('9531837576',154),('6955365663',156),('7459003615',156),('9339272257',156),('7533357075',159),('8200724638',162),('9885441667',162),('7345214663',163),('8696508636',165),('7142936073',166),('8516481906',168),('9081938209',170),('9629086749',170),('6405430900',171),('9365656642',171),('8596516484',172),('9406393555',172),('9100948094',175),('7535991601',176),('6986777349',180),('8924089827',180),('8570383299',182),('9569029420',185),('7984523522',190),('8279360503',190),('8795623637',191),('6200858585',192),('7606693161',192),('8729286444',192),('8866457092',192),('6086042846',193),('7094058711',193),('9226817174',198),('9971829298',198),('9728761493',199),('6533521214',201),('8594026924',201),('8778374622',201),('7023481397',202),('8702829876',203),('7737150835',206),('7211670547',207),('9227116015',207),('8284607263',212),('9864880461',216),('7092046150',218),('6584206415',219),('9961774926',219),('6203363547',221),('7658356980',221),('8803064919',222),('9356273841',222),('8002425723',224),('9821739677',228),('7220931966',235),('7071333018',243),('9131769856',243),('6093919592',246),('9634638699',247),('7068890887',251);
/*!40000 ALTER TABLE `UserPhoneNumber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserToken`
--

DROP TABLE IF EXISTS `UserToken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserToken` (
  `userId` int NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `token` (`token`),
  CONSTRAINT `UserToken_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserToken`
--

LOCK TABLES `UserToken` WRITE;
/*!40000 ALTER TABLE `UserToken` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserToken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-25 10:55:06

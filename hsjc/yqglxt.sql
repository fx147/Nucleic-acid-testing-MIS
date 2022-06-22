-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: yqglxt
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Temporary view structure for view `vw`
--

DROP TABLE IF EXISTS `vw`;
/*!50001 DROP VIEW IF EXISTS `vw`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw` AS SELECT 
 1 AS `样本号`,
 1 AS `化验结果`,
 1 AS `姓名`,
 1 AS `检测序列`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `化验中心`
--

DROP TABLE IF EXISTS `化验中心`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `化验中心` (
  `化验中心名` varchar(45) NOT NULL,
  `地址` varchar(45) NOT NULL,
  `联系电话` char(8) NOT NULL,
  PRIMARY KEY (`化验中心名`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `化验中心`
--

LOCK TABLES `化验中心` WRITE;
/*!40000 ALTER TABLE `化验中心` DISABLE KEYS */;
INSERT INTO `化验中心` VALUES ('南京市中医院','建邺区','85463102'),('南京市人民医院','鼓楼区','86596012');
/*!40000 ALTER TABLE `化验中心` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `医生`
--

DROP TABLE IF EXISTS `医生`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `医生` (
  `医生号` char(10) NOT NULL,
  `姓名` varchar(45) NOT NULL,
  `电话号码` char(11) NOT NULL,
  `化验中心名` varchar(45) NOT NULL,
  `密码` varchar(18) NOT NULL,
  PRIMARY KEY (`医生号`),
  KEY `化验中心名` (`化验中心名`),
  CONSTRAINT `医生_ibfk_1` FOREIGN KEY (`化验中心名`) REFERENCES `化验中心` (`化验中心名`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `医生`
--

LOCK TABLES `医生` WRITE;
/*!40000 ALTER TABLE `医生` DISABLE KEYS */;
INSERT INTO `医生` VALUES ('0313190812','黄青','15689021536','南京市人民医院','1111'),('0313190921','洪承畴','18895846359','南京市中医院','1111'),('0313190922','张三','17435357019','南京市人民医院','1234');
/*!40000 ALTER TABLE `医生` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `居民`
--

DROP TABLE IF EXISTS `居民`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `居民` (
  `居民号` char(18) NOT NULL,
  `姓名` varchar(45) NOT NULL,
  `电话号码` char(11) DEFAULT NULL,
  `住址` varchar(45) DEFAULT NULL,
  `密码` varchar(18) NOT NULL,
  PRIMARY KEY (`居民号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `居民`
--

LOCK TABLES `居民` WRITE;
/*!40000 ALTER TABLE `居民` DISABLE KEYS */;
INSERT INTO `居民` VALUES ('36040320010710212X','魏然','18151711518','锁金二村','123456'),('370306200103306710','崔哲魁','18896931257','锁金一村','123456'),('411234322867165112','冯玉骐','17335357020','','123'),('411323244523456783','清风','12345456789','月球','123456'),('41162720010716511','阿萨德','17335357012','','12222'),('422312233232134225','范旭旭','17335357019','南林九栋','123');
/*!40000 ALTER TABLE `居民` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `样本信息表`
--

DROP TABLE IF EXISTS `样本信息表`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `样本信息表` (
  `样本号` char(12) NOT NULL,
  `化验结果` varchar(20) NOT NULL,
  `居民号` char(18) NOT NULL,
  `检测号` char(8) NOT NULL,
  PRIMARY KEY (`样本号`),
  KEY `居民号` (`居民号`),
  KEY `检测号` (`检测号`),
  CONSTRAINT `样本信息表_ibfk_1` FOREIGN KEY (`居民号`) REFERENCES `居民` (`居民号`),
  CONSTRAINT `样本信息表_ibfk_2` FOREIGN KEY (`检测号`) REFERENCES `检测序列` (`检测号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `样本信息表`
--

LOCK TABLES `样本信息表` WRITE;
/*!40000 ALTER TABLE `样本信息表` DISABLE KEYS */;
INSERT INTO `样本信息表` VALUES ('324500004255','阴性','422312233232134225','32450000'),('324500005112','阴性','411234322867165112','32450000'),('324500006783','阴性','411323244523456783','32450000'),('324500106783','阴性','411323244523456783','32450010'),('324500114225','阴性','422312233232134225','32450011'),('324500115112','阴性','411234322867165112','32450011'),('324500214255','阴性','422312233232134225','32450021'),('324500215112','阴性','411234322867165112','32450021'),('324500216783','阳性','411323244523456783','32450021'),('324500314225','阴性','422312233232134225','32450031'),('324500336783','阴性','411323244523456783','32450033'),('324500414225','阴性','422312233232134225','32450041'),('324500436783','阳性','411323244523456783','32450043'),('324500526783','阴性','411323244523456783','32450052');
/*!40000 ALTER TABLE `样本信息表` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `样本医生对照表`
--

DROP TABLE IF EXISTS `样本医生对照表`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `样本医生对照表` (
  `样本号` char(12) NOT NULL,
  `医生号` char(10) NOT NULL,
  `采样方式` varchar(45) NOT NULL,
  KEY `样本号` (`样本号`),
  KEY `医生号` (`医生号`),
  CONSTRAINT `样本医生对照表_ibfk_1` FOREIGN KEY (`样本号`) REFERENCES `样本信息表` (`样本号`),
  CONSTRAINT `样本医生对照表_ibfk_2` FOREIGN KEY (`医生号`) REFERENCES `医生` (`医生号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `样本医生对照表`
--

LOCK TABLES `样本医生对照表` WRITE;
/*!40000 ALTER TABLE `样本医生对照表` DISABLE KEYS */;
INSERT INTO `样本医生对照表` VALUES ('324500004255','0313190812','鼻拭子'),('324500114225','0313190921','咽拭子'),('324500414225','0313190921','咽拭子'),('324500314225','0313190812','咽拭子'),('324500005112','0313190921','咽拭子'),('324500006783','0313190921','咽拭子'),('324500106783','0313190921','咽拭子'),('324500215112','0313190921','咽拭子'),('324500115112','0313190921','咽拭子'),('324500115112','0313190921','咽拭子'),('324500214255','0313190921','咽拭子'),('324500216783','0313190921','咽拭子'),('324500526783','0313190921','咽拭子'),('324500336783','0313190921','咽拭子'),('324500436783','0313190921','鼻拭子');
/*!40000 ALTER TABLE `样本医生对照表` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `检测序列`
--

DROP TABLE IF EXISTS `检测序列`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `检测序列` (
  `检测号` char(8) NOT NULL,
  `检测点` varchar(45) NOT NULL,
  `检测时间` varchar(45) NOT NULL,
  `检测序列` varchar(10) NOT NULL,
  PRIMARY KEY (`检测号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `检测序列`
--

LOCK TABLES `检测序列` WRITE;
/*!40000 ALTER TABLE `检测序列` DISABLE KEYS */;
INSERT INTO `检测序列` VALUES ('32450000','国展中心','2021/7/21','1'),('32450001','体育馆','2021/7/21','1'),('32450002','老操场','2021/7/21','1'),('32450003','锁金村医院','2021/7/21','1'),('32450010','体育馆','2021/7/24','2'),('32450011','锁金村医院','2021/7/24','2'),('32450012','国展中心','2021/7/24','2'),('32450013','南京中医院','2021/7/24','2'),('32450020','体育馆','2021/7/27','3'),('32450021','锁金村医院','2021/7/27','3'),('32450022','国展中心','2021/7/27','3'),('32450023','南京中医院','2021/7/27','3'),('32450030','体育馆','2021/7/31','4'),('32450031','锁金村医院','2021/7/31','4'),('32450032','国展中心','2021/7/31','4'),('32450033','体育馆','2021/7/31','4'),('32450040','体育馆','2021/8/2','5'),('32450041','锁金村医院','2021/8/2','5'),('32450042','国展中心','2021/8/2','5'),('32450043','南京中医院','2021/8/2','5'),('32450052','南林大北一门','2021/9/22','6');
/*!40000 ALTER TABLE `检测序列` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `vw`
--

/*!50001 DROP VIEW IF EXISTS `vw`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw` AS select `样本医生对照表`.`样本号` AS `样本号`,`样本信息表`.`化验结果` AS `化验结果`,`居民`.`姓名` AS `姓名`,`检测序列`.`检测序列` AS `检测序列` from (((`样本医生对照表` join `样本信息表`) join `居民`) join `检测序列`) where ((`样本医生对照表`.`样本号` = `样本信息表`.`样本号`) and (`样本信息表`.`居民号` = `居民`.`居民号`) and (`样本信息表`.`检测号` = `检测序列`.`检测号`) and (`样本医生对照表`.`医生号` = (select `医生`.`医生号` from `医生` where (`医生`.`电话号码` = 18895846359)))) order by `检测序列`.`检测序列` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-24 18:50:59

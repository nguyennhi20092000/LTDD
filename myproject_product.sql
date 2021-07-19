-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: myproject
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `featured` varchar(50) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Dua tac','20000','NuocGiaiKhat','yes','img/duatac.jpg'),(2,'Ca phe sua da','15000','NuocGiaiKhat','yes','img/caphesuada.jpg'),(3,'Chanh day','13000','NuocGiaiKhat','yes','img/chanhday.jpg'),(4,'Da me','15000','NuocGiaiKhat','yes','img/dame.jpg'),(5,'Rau ma dau xanh','13000','NuocGiaiKhat','yes','img/raumadauxanh.jpg'),(6,'Soda Chanh','15000','NuocGiaiKhat','yes','img/sodachanh.jpg'),(7,'Tra tac','13000','NuocGiaiKhat','yes','img/tratac.jpg'),(8,'Tra sua truyen thong','20000','TraSua','yes','img/trasuatruyenthong.jpg'),(9,'Sua Chua Dam Tran Chau','23000','TraSua','yes','img/suachuadamtranchau.jpg'),(10,'Tran Chau Dam Ca Cao','25000','TraSua','yes','img/tranchaudamcacao.jpg'),(11,'Hong Tra Tran Chau','18000','TraSua','yes','img/hongtratranchau.jpg'),(12,'Tra Sua Matcha','25000','TraSua','yes','img/trasuamatcha.jpg'),(13,'Sua tuoi Tran Chau Duong Den','25000','TraSua','yes','img/suatuoitranchauduongden.jpg'),(14,'Nuoc Ep Oi','18000','NuocEp','yes','img/nuocepoi.jpg'),(18,'Nuoc Ep Cam','18000','NuocEp','yes','img/nuocepcam.jpg'),(26,'Nuoc Ep Cam - Carot','20000','NuocEp','yes','img/nuocepcamcarot.jpg'),(27,'Nuoc Ep Cam-Mat ong','25000','NuocEp','yes','img/nuocepcammatong.jpg'),(28,'Nuoc Ep Thom','20000','NuocEp','yes','img/nuocepthom.jpg'),(29,'Nuoc Ep Thom - mat ong','30000','NuocEp','yes','img/nuocepthommatong.jpg'),(30,'Luu Tach Hot','28000','NuocEp','yes','img/luutachhot.jpg'),(31,'Nuoc Ep Nho','20000','NuocEp','yes','img/nuocepnho.jpg'),(32,'Khoai mo chien','20000','AnVat','yes','img/khoaimochien.jpg'),(33,'Coc-Xoai Lac','18000','AnVat','yes','img/cocxoailac.jpg'),(34,'Khoai lang chien','15000','AnVat','yes','img/khoailangchien.jpg'),(35,'Trai cay dam thap cam','30000','AnVat','yes','img/traicaydamthapcam.jpg'),(36,'Thom lac khong lo','18000','AnVat','yes','img/thomlackhonglo.jpg'),(37,'Banh trang tron','18000','AnVat','yes','img/banhtrangtron.jpg'),(38,'Dua Dam','15000','SinhTo','yes','img/duadam.jpg'),(39,'Sinh To Bo','20000','SinhTo','yes','img/sinhtobo.jpg'),(40,'Sinh To Chanh Day','20000','SinhTo','yes','img/sinhtochanhday.jpg'),(41,'Sinh To Dau','20000','SinhTo','yes','img/sinhtodau.jpg'),(42,'Sinh To Dua Milo','25000','SinhTo','yes','img/sinhtoduamilo.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-20 13:21:29

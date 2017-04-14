-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `ADRESS`
--

DROP TABLE IF EXISTS `ADRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADRESS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADRES` varchar(255) DEFAULT NULL,
  `ADRES_KULLANICI_TIPI` int(11) DEFAULT NULL,
  `ADRES_TIPI` int(11) DEFAULT NULL,
  `REGAIN` bigint(20) DEFAULT NULL,
  `MUSTERI_ADRES` bigint(20) DEFAULT NULL,
  `KEFIL_ADRES` bigint(20) DEFAULT NULL,
  `USER_ADRES` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKe9v7owskqv3ypxvebfs51nv2b` (`REGAIN`),
  KEY `FKm4hg9yps4jtb4m9u4fq0ayknv` (`MUSTERI_ADRES`),
  KEY `FK6g9dwq9rytnkadw78i21j1r73` (`KEFIL_ADRES`),
  KEY `FK53o4nwhxrwx38qcbqi01fnqcy` (`USER_ADRES`),
  CONSTRAINT `FK53o4nwhxrwx38qcbqi01fnqcy` FOREIGN KEY (`USER_ADRES`) REFERENCES `CALISAN` (`ID`),
  CONSTRAINT `FK6g9dwq9rytnkadw78i21j1r73` FOREIGN KEY (`KEFIL_ADRES`) REFERENCES `KEFIL` (`ID`),
  CONSTRAINT `FKe9v7owskqv3ypxvebfs51nv2b` FOREIGN KEY (`REGAIN`) REFERENCES `REGAIN` (`ID`),
  CONSTRAINT `FKm4hg9yps4jtb4m9u4fq0ayknv` FOREIGN KEY (`MUSTERI_ADRES`) REFERENCES `MUSTERI` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BORC`
--

DROP TABLE IF EXISTS `BORC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BORC` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `KALAN_BORC` decimal(19,2) DEFAULT NULL,
  `ODEME_TIPI` int(11) DEFAULT NULL,
  `TOPLAM_BORC` decimal(19,2) DEFAULT NULL,
  `MUSTERI` bigint(20) DEFAULT NULL,
  `ODEME_SUBE` bigint(20) DEFAULT NULL,
  `ODEME_USTLENICI_SUBE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK7qehdbno254gbrbuontwr2r0o` (`MUSTERI`),
  KEY `FKbpikdy2bm41dqgtd7mf127ue3` (`ODEME_SUBE`),
  KEY `FKnyfmyaj9dqolk0oq70suwx2qn` (`ODEME_USTLENICI_SUBE`),
  CONSTRAINT `FK7qehdbno254gbrbuontwr2r0o` FOREIGN KEY (`MUSTERI`) REFERENCES `MUSTERI` (`ID`),
  CONSTRAINT `FKbpikdy2bm41dqgtd7mf127ue3` FOREIGN KEY (`ODEME_SUBE`) REFERENCES `SUBE` (`ID`),
  CONSTRAINT `FKnyfmyaj9dqolk0oq70suwx2qn` FOREIGN KEY (`ODEME_USTLENICI_SUBE`) REFERENCES `SUBE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BORC_DETAY`
--

DROP TABLE IF EXISTS `BORC_DETAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BORC_DETAY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `KALAN_BORC` decimal(19,2) DEFAULT NULL,
  `ONCEKI_BORC` decimal(19,2) DEFAULT NULL,
  `ORTALAMA_GECIKILEN_GUN_SAYISI` double DEFAULT NULL,
  `TOPLAM_AKSATMA_SAYISI` int(11) DEFAULT NULL,
  `TOPLAM_GECIKILEN_GUN_SAYISI` int(11) DEFAULT NULL,
  `BORC` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4wmj0c30nls4inyjyfjdheumq` (`BORC`),
  CONSTRAINT `FK4wmj0c30nls4inyjyfjdheumq` FOREIGN KEY (`BORC`) REFERENCES `BORC` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CALISAN`
--

DROP TABLE IF EXISTS `CALISAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CALISAN` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ISE_GIRIS_TARIHI` datetime DEFAULT NULL,
  `ISTEN_CIKIS_TARIHI` datetime DEFAULT NULL,
  `EMP_SUBE` bigint(20) DEFAULT NULL,
  `EMP_USER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKs4j8d1ocxqnxu7aunoqrp27o7` (`EMP_SUBE`),
  KEY `FK5igqsk0v11rm0586ks3y9ps64` (`EMP_USER`),
  CONSTRAINT `FK5igqsk0v11rm0586ks3y9ps64` FOREIGN KEY (`EMP_USER`) REFERENCES `KULLANICI` (`ID`),
  CONSTRAINT `FKs4j8d1ocxqnxu7aunoqrp27o7` FOREIGN KEY (`EMP_SUBE`) REFERENCES `SUBE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CONDUCT`
--

DROP TABLE IF EXISTS `CONDUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONDUCT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CONDUCT_TIPI` int(11) DEFAULT NULL,
  `DEGER` varchar(255) DEFAULT NULL,
  `USER_CONTACT` bigint(20) DEFAULT NULL,
  `MUSTERI_CONTACT` bigint(20) DEFAULT NULL,
  `KEFIL_CONTACT` bigint(20) DEFAULT NULL,
  `DEPO_CONT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKr1u1ocquvxxb79vx74kv5cl9l` (`USER_CONTACT`),
  KEY `FKo4pkhdb4av2o9xl0k81vmw1t0` (`MUSTERI_CONTACT`),
  KEY `FK1yl8tpnqck6takmxykam5abkj` (`KEFIL_CONTACT`),
  KEY `FKcit3s9jx1rok4qgabwh4vnlbh` (`DEPO_CONT`),
  CONSTRAINT `FK1yl8tpnqck6takmxykam5abkj` FOREIGN KEY (`KEFIL_CONTACT`) REFERENCES `KEFIL` (`ID`),
  CONSTRAINT `FKcit3s9jx1rok4qgabwh4vnlbh` FOREIGN KEY (`DEPO_CONT`) REFERENCES `DEPO` (`ID`),
  CONSTRAINT `FKo4pkhdb4av2o9xl0k81vmw1t0` FOREIGN KEY (`MUSTERI_CONTACT`) REFERENCES `MUSTERI` (`ID`),
  CONSTRAINT `FKr1u1ocquvxxb79vx74kv5cl9l` FOREIGN KEY (`USER_CONTACT`) REFERENCES `KULLANICI` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DEPO`
--

DROP TABLE IF EXISTS `DEPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEPO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `DEPO_ADR` bigint(20) DEFAULT NULL,
  `DEPO_FIRM` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKbf1myibwtcjwpalxem3bqpfq8` (`DEPO_ADR`),
  KEY `FKhcngo0lf98uvsd3efk2idjje4` (`DEPO_FIRM`),
  CONSTRAINT `FKbf1myibwtcjwpalxem3bqpfq8` FOREIGN KEY (`DEPO_ADR`) REFERENCES `ADRESS` (`ID`),
  CONSTRAINT `FKhcngo0lf98uvsd3efk2idjje4` FOREIGN KEY (`DEPO_FIRM`) REFERENCES `FIRMA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DOVIZ`
--

DROP TABLE IF EXISTS `DOVIZ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DOVIZ` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `BIRIM` int(11) DEFAULT NULL,
  `DOVIZ_ALIS` decimal(19,2) DEFAULT NULL,
  `DOVIZ_CINSI` varchar(255) DEFAULT NULL,
  `DOVIZ_KODU` varchar(255) DEFAULT NULL,
  `DOVIZ_SATIS` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FIRMA`
--

DROP TABLE IF EXISTS `FIRMA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FIRMA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `FIRM_ADI` varchar(255) DEFAULT NULL,
  `USER_ADRES` bigint(20) DEFAULT NULL,
  `FIRM_USER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKomoru720g68kwnvk4xxo9y53s` (`USER_ADRES`),
  KEY `FKnsgl0wejchvllkpeydnwu1502` (`FIRM_USER`),
  CONSTRAINT `FKnsgl0wejchvllkpeydnwu1502` FOREIGN KEY (`FIRM_USER`) REFERENCES `KULLANICI` (`ID`),
  CONSTRAINT `FKomoru720g68kwnvk4xxo9y53s` FOREIGN KEY (`USER_ADRES`) REFERENCES `ADRESS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FIYAT`
--

DROP TABLE IF EXISTS `FIYAT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FIYAT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACIKLAMA` varchar(255) DEFAULT NULL,
  `FIYATI` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FIYAT_DETAYI`
--

DROP TABLE IF EXISTS `FIYAT_DETAYI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FIYAT_DETAYI` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ONCEKI_FIYAT` decimal(19,2) DEFAULT NULL,
  `SONRAKI_FIYAT` decimal(19,2) DEFAULT NULL,
  `PRICE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKdibb2303dpwie3roovfss63h9` (`PRICE`),
  CONSTRAINT `FKdibb2303dpwie3roovfss63h9` FOREIGN KEY (`PRICE`) REFERENCES `FIYAT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `IL`
--

DROP TABLE IF EXISTS `IL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `PLAKA_KODU` int(11) DEFAULT NULL,
  `regainIl` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKgddon4on74o52bijmta55kwsh` (`regainIl`),
  CONSTRAINT `FKgddon4on74o52bijmta55kwsh` FOREIGN KEY (`regainIl`) REFERENCES `REGAIN` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ILCE`
--

DROP TABLE IF EXISTS `ILCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ILCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `il_id` bigint(20) DEFAULT NULL,
  `regainIlce` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKhruucovq4766ldb383oa684dl` (`il_id`),
  KEY `FKtn7q5rekn9foq3mlpbu328j94` (`regainIlce`),
  CONSTRAINT `FKhruucovq4766ldb383oa684dl` FOREIGN KEY (`il_id`) REFERENCES `IL` (`ID`),
  CONSTRAINT `FKtn7q5rekn9foq3mlpbu328j94` FOREIGN KEY (`regainIlce`) REFERENCES `REGAIN` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `KEFIL`
--

DROP TABLE IF EXISTS `KEFIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KEFIL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `SOYADI` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `MUSTERI_KEFIL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ehf6l1mwwbx48q8pqx7v6rubv` (`email`),
  KEY `FKlbwjoe02ju6q022if77p1lc31` (`MUSTERI_KEFIL`),
  CONSTRAINT `FKlbwjoe02ju6q022if77p1lc31` FOREIGN KEY (`MUSTERI_KEFIL`) REFERENCES `MUSTERI` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `KULLANICI`
--

DROP TABLE IF EXISTS `KULLANICI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KULLANICI` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SOYADI` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_TYPE` int(11) DEFAULT NULL,
  `YETKISI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_f6p8g3tqin58yn82pcpo3o8sf` (`USER_NAME`),
  KEY `FKee1ny1973wvuodfh6w0gdskpi` (`YETKISI`),
  CONSTRAINT `FKee1ny1973wvuodfh6w0gdskpi` FOREIGN KEY (`YETKISI`) REFERENCES `YETKI` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MALZEME`
--

DROP TABLE IF EXISTS `MALZEME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MALZEME` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ACIKLAMA` varchar(255) DEFAULT NULL,
  `IBAN_NO` varchar(255) DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `SON_KULLANMA_TARIHI` datetime DEFAULT NULL,
  `UNIT_TYPE` int(11) DEFAULT NULL,
  `MALZEME_FIYATI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKoi8tns55kyw8rt8vtkufjydph` (`MALZEME_FIYATI`),
  CONSTRAINT `FKoi8tns55kyw8rt8vtkufjydph` FOREIGN KEY (`MALZEME_FIYATI`) REFERENCES `FIYAT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MUSTERI`
--

DROP TABLE IF EXISTS `MUSTERI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MUSTERI` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `SOYADI` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_bjqnax23ttxl0xlltrsai0tfh` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PAYMENT`
--

DROP TABLE IF EXISTS `PAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAYMENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `OdemeTutari` decimal(19,2) DEFAULT NULL,
  `BEKLENEN_ODEME_TARIHI` datetime DEFAULT NULL,
  `GERCEKLESEN_ODEME_TARIHI` datetime DEFAULT NULL,
  `ODEME_TIPI` int(11) DEFAULT NULL,
  `MUSTERI` bigint(20) DEFAULT NULL,
  `SATICI_SUBE` bigint(20) DEFAULT NULL,
  `USTLENICI_SUBE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKem61i16k5cjjwkexp0yit3nld` (`MUSTERI`),
  KEY `FKt0bldpr45wakjikjyt4asgg8n` (`SATICI_SUBE`),
  KEY `FK7bk0v1liwcb7brgr4vir0g1rx` (`USTLENICI_SUBE`),
  CONSTRAINT `FK7bk0v1liwcb7brgr4vir0g1rx` FOREIGN KEY (`USTLENICI_SUBE`) REFERENCES `SUBE` (`ID`),
  CONSTRAINT `FKem61i16k5cjjwkexp0yit3nld` FOREIGN KEY (`MUSTERI`) REFERENCES `MUSTERI` (`ID`),
  CONSTRAINT `FKt0bldpr45wakjikjyt4asgg8n` FOREIGN KEY (`SATICI_SUBE`) REFERENCES `SUBE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `REGAIN`
--

DROP TABLE IF EXISTS `REGAIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REGAIN` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SATIS`
--

DROP TABLE IF EXISTS `SATIS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SATIS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `HEDEFLENEN_TARIH` datetime DEFAULT NULL,
  `TOPLAM_TUTAR` decimal(19,2) DEFAULT NULL,
  `EMPLOYEE` bigint(20) DEFAULT NULL,
  `MUSTERI` bigint(20) DEFAULT NULL,
  `SATIS_SUBESI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKkuvte7vdj2wi76mnndko3hn6j` (`EMPLOYEE`),
  KEY `FK49kiwr1iwagkoyv6dvel9uojr` (`MUSTERI`),
  KEY `FKgkl61l31pnxdjcc73e1giy1au` (`SATIS_SUBESI`),
  CONSTRAINT `FK49kiwr1iwagkoyv6dvel9uojr` FOREIGN KEY (`MUSTERI`) REFERENCES `MUSTERI` (`ID`),
  CONSTRAINT `FKgkl61l31pnxdjcc73e1giy1au` FOREIGN KEY (`SATIS_SUBESI`) REFERENCES `SUBE` (`ID`),
  CONSTRAINT `FKkuvte7vdj2wi76mnndko3hn6j` FOREIGN KEY (`EMPLOYEE`) REFERENCES `CALISAN` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SATIS_DETAY`
--

DROP TABLE IF EXISTS `SATIS_DETAY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SATIS_DETAY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADET` int(11) DEFAULT NULL,
  `SATIS` bigint(20) DEFAULT NULL,
  `SATIS_DETAY_MALZEME` bigint(20) DEFAULT NULL,
  `STOK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4tcqbrhnuqy6ib54si3o4llq1` (`SATIS`),
  KEY `FKab2txk2h1xdg2rd4k5gnwko0q` (`SATIS_DETAY_MALZEME`),
  KEY `FKrp9d8eodyo26ej1smf2ish41p` (`STOK`),
  CONSTRAINT `FK4tcqbrhnuqy6ib54si3o4llq1` FOREIGN KEY (`SATIS`) REFERENCES `SATIS` (`ID`),
  CONSTRAINT `FKab2txk2h1xdg2rd4k5gnwko0q` FOREIGN KEY (`SATIS_DETAY_MALZEME`) REFERENCES `MALZEME` (`ID`),
  CONSTRAINT `FKrp9d8eodyo26ej1smf2ish41p` FOREIGN KEY (`STOK`) REFERENCES `STOK` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `STOK`
--

DROP TABLE IF EXISTS `STOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STOK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADET` int(11) DEFAULT NULL,
  `GELIS_TARIHI` datetime DEFAULT NULL,
  `PRODUCT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1ei4yithehavw0qo4tikfncr7` (`PRODUCT`),
  CONSTRAINT `FK1ei4yithehavw0qo4tikfncr7` FOREIGN KEY (`PRODUCT`) REFERENCES `MALZEME` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SUBE`
--

DROP TABLE IF EXISTS `SUBE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUBE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `FIRM_ADI` varchar(255) DEFAULT NULL,
  `FIRMA_TIPI` int(11) DEFAULT NULL,
  `SUBE_ADRES` bigint(20) DEFAULT NULL,
  `FIRMA` bigint(20) DEFAULT NULL,
  `USTLENICI_FIRMA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK1imhk9bkyg3olhm7nskpgvx0n` (`SUBE_ADRES`),
  KEY `FKgeqpdkt5ykj2uefv1ni7uf97q` (`FIRMA`),
  KEY `FKotlnlphk9plx9sliw1g75hnos` (`USTLENICI_FIRMA`),
  CONSTRAINT `FK1imhk9bkyg3olhm7nskpgvx0n` FOREIGN KEY (`SUBE_ADRES`) REFERENCES `ADRESS` (`ID`),
  CONSTRAINT `FKgeqpdkt5ykj2uefv1ni7uf97q` FOREIGN KEY (`FIRMA`) REFERENCES `FIRMA` (`ID`),
  CONSTRAINT `FKotlnlphk9plx9sliw1g75hnos` FOREIGN KEY (`USTLENICI_FIRMA`) REFERENCES `USTLENICI_FIRMA` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SUBE_CONDUCT`
--

DROP TABLE IF EXISTS `SUBE_CONDUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUBE_CONDUCT` (
  `Sube_ID` bigint(20) NOT NULL,
  `lstConduct_ID` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pmjfps1b9rim0s0kxm75fqh9l` (`lstConduct_ID`),
  KEY `FK1y9lq8ffd66fclhiy6p4xxwbp` (`Sube_ID`),
  CONSTRAINT `FK1y9lq8ffd66fclhiy6p4xxwbp` FOREIGN KEY (`Sube_ID`) REFERENCES `SUBE` (`ID`),
  CONSTRAINT `FKqpl0vex3721hlb5wsvvu1k5jx` FOREIGN KEY (`lstConduct_ID`) REFERENCES `CONDUCT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TAKSIT`
--

DROP TABLE IF EXISTS `TAKSIT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAKSIT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `BEKLENEN_ODEME_TARIHI` datetime DEFAULT NULL,
  `TAKSIT_NO` int(11) DEFAULT NULL,
  `TAKSIT_TUTARI` decimal(19,2) DEFAULT NULL,
  `BORC` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKf0f6d692dx6l2r5fomudj3llq` (`BORC`),
  CONSTRAINT `FKf0f6d692dx6l2r5fomudj3llq` FOREIGN KEY (`BORC`) REFERENCES `BORC` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USTLENICI_FIRMA`
--

DROP TABLE IF EXISTS `USTLENICI_FIRMA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USTLENICI_FIRMA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `ADI` varchar(255) DEFAULT NULL,
  `adress_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKo1782tgm3tsq2khhfdeb2n6mg` (`adress_ID`),
  CONSTRAINT `FKo1782tgm3tsq2khhfdeb2n6mg` FOREIGN KEY (`adress_ID`) REFERENCES `ADRESS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USTLENICI_FIRMA_CONDUCT`
--

DROP TABLE IF EXISTS `USTLENICI_FIRMA_CONDUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USTLENICI_FIRMA_CONDUCT` (
  `UstleniciFirma_ID` bigint(20) NOT NULL,
  `lstConduct_ID` bigint(20) NOT NULL,
  UNIQUE KEY `UK_t45o61twlyc237ekgyoyxa38w` (`lstConduct_ID`),
  KEY `FK9ximhs5vipb2p4eebpaxcxgwf` (`UstleniciFirma_ID`),
  CONSTRAINT `FK9ximhs5vipb2p4eebpaxcxgwf` FOREIGN KEY (`UstleniciFirma_ID`) REFERENCES `USTLENICI_FIRMA` (`ID`),
  CONSTRAINT `FKthnkvfr9j6ogmr0u6aprqu513` FOREIGN KEY (`lstConduct_ID`) REFERENCES `CONDUCT` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `YETKI`
--

DROP TABLE IF EXISTS `YETKI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `YETKI` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREAYED_DATE` datetime DEFAULT NULL,
  `DURUM` int(11) DEFAULT NULL,
  `LAST_UPDATED_VERSION` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `YETKI_ACIKLAMASI` varchar(255) DEFAULT NULL,
  `YETKI_ADI` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-14 14:59:14

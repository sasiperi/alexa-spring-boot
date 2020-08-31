-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: us-cdbr-iron-east-03.cleardb.net    Database: ad_6c8971fa5f71c05
-- ------------------------------------------------------
-- Server version	5.5.56-log

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  `customercol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'2017-03-31 02:20:18','sasi','2017-03-31 02:20:18','2009-12-12 00:00:00','a@b.com','Sasi','Peri','$2a$10$IJ7KSbJdcvY/DjYzJioIsecYO7kQq6k7JJPj1KDdeOHjV.Z66sa.a',NULL),(2,'2017-03-31 02:20:18','sasi','2017-03-31 02:20:18','2017-03-31 02:20:18','b@c.com','kala','peri','$2a$10$OToDAGW0V08lqulhgDilwuptT0Hq3JrvKQ5Vg..RhgnjLkxHJCvQ6',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order`
--

DROP TABLE IF EXISTS `customer_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_order` (
  `order_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `order_status` varchar(25) NOT NULL,
  `order_status_reason` varchar(200) DEFAULT NULL,
  `order_total` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_number`),
  KEY `FKf9abd30bhiqvugayxlpq8ryq9` (`customer_id`),
  CONSTRAINT `FKf9abd30bhiqvugayxlpq8ryq9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=790 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order`
--

LOCK TABLES `customer_order` WRITE;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` VALUES (123,'2017-02-01 00:00:00','sasi','2017-03-31 02:34:11','SHIPPED','This order is shipped on 2017-Feb-20',200,1),(555,'2017-03-01 00:00:00','sasi','2017-03-31 02:34:46','SHIPPED','This order is shipped on 2017-March-10',150,1),(568,'2017-02-28 00:00:00','sasi','2017-03-31 02:34:46','PENDING','This order is waiting to for inurance verification',10,1),(789,'2017-03-31 02:34:46','sasi','2017-03-31 02:34:46','CANCELLED','This order was cancelled by customer on 2017-March-05',100,2);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_order_detail`
--

DROP TABLE IF EXISTS `customer_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_order_detail` (
  `line_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `product_code` varchar(25) NOT NULL,
  `product_desc` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`line_item_id`),
  KEY `FKovdiqxj2s9jx8b2ksdls7issw` (`order_number`),
  CONSTRAINT `FKovdiqxj2s9jx8b2ksdls7issw` FOREIGN KEY (`order_number`) REFERENCES `customer_order` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_order_detail`
--

LOCK TABLES `customer_order_detail` WRITE;
/*!40000 ALTER TABLE `customer_order_detail` DISABLE KEYS */;
INSERT INTO `customer_order_detail` VALUES (1,'2017-03-31 03:00:16','sasi','2017-03-31 03:00:16',50,'5253676','Similac Sensitive Ready t',2,123),(2,'2017-03-31 03:00:16','sasi','2017-03-31 03:00:16',100,'75869353','Enfamil Gentlease Powder',1,123),(3,'2017-03-31 03:00:16','sasi','2017-03-31 03:00:16',150,'ZPL12000','Cardinal Health Essential',1,555),(4,'2017-03-31 03:00:16','sasi','2017-03-31 03:00:16',10,'70022136','OneTouch Delica Lancet',5,568),(5,'2017-03-31 03:00:16','sasi','2017-03-31 03:00:16',10,'70022136','OneTouch Delica Lancet',10,789),(6,'2017-03-31 03:00:17','sasi','2017-03-31 03:00:17',100,'75869353','Enfamil Gentlease Powder',1,789),(7,'2017-03-31 03:02:49','sasi','2017-03-31 03:02:49',400,'ZPL12000','Cardinal Health Essentials Lancng Device',1,789);
/*!40000 ALTER TABLE `customer_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('4835fe8ddde29dab9134e8da9d6d4dce','¨\Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙\Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0jtit\0$62eaf98c-3dac-49a8-9552-626585d091a4x\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0qπ\ﬁYxpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearertTeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc5NjEyODgsInVzZXJfbmFtZSI6ImFAYi5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNjJlYWY5OGMtM2RhYy00OWE4LTk1NTItNjI2NTg1ZDA5MWE0IiwiY2xpZW50X2lkIjoiYWxleGFfb21zIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.eE6SZZPpG_2MeLx2yQAh3OKy8P3JdTGwbud0N0VbUGBdkG0SvM7yjRFctpy68rSqWTC1w77m513NAbihd4315AiNw0FyyZe7-ytK2v0IFKtB0zYxc2RZViRju8qymQz3Jsv6CgSiiX5Xhwgm8XO9EXJbVLNrnkT4YBLv_jwsD_JflQlJqCM2jkK_BFX7Wtxt9m3evFZD7a2I_4pa5AGgvFzh2dWFaOobaL9uv3xSE9sjyfkBXUiG-1U-NAFhnHCBB5w1r913u6vZqLJYANXwq0ulRKLJ7zIxGbVgRZR6OtBZUWwfLzsw0-f9CZ7TyGaL32XLnsuh0xH_4I8OmZzMMg','0cdefc661cf856d8654993b469875946','a@b.com','alexa_oms','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0§\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0	alexa_omssr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0mBmw7zt\0\ngrant_typet\0authorization_codet\0scopet\0\nread writet\0\rresponse_typet\0codet\0statet°A2SAAEAEHU2STBaJ396BhM4zRk4AX4B4HNLPsMEDiddlykTT-LORyAGU9ORa7QU-RdoGNnAPPbmIk0u-G3HhL9BvOwiwlMk1YcMWutQFcocifgScdqXXZxJZTYv1vSEgf_Suk1QfdZCdJvuFVwrWiYU_5rr3IisJzoVfqsiRTWt_iMGPkH47Fkp0MzVzUDMbFlrk7FUdI5Hkvi7IJhWEdrj9xPScg0yEvV8mYkiW47xNOqTaU7IpvN0HIHlqni3GP6blXhtgHN9RynehpC-sL_h6Ms1qqMMD-MPyaPsdcw_9DhtnliKafeiyKvGeIMgamFjiTbg4Ly5UoUDV699c4Dvn6GqdCJagVjsN4HT-6AuwytFvCIE1xqaz5b3yI-_v2_FfKuqspHNPf0BoEV3OFLvOOlyUD2uzvpwyfZnWAQqj24vimjpiRQZwdFuL8bBU0SRAZsDyL8pf-jj1q8jU906ONACiLfDbL0ArYPeIyMCLL3j0s3toagSiJ136B9pVqJFiL1TEG1kHbOtIeoej2CblDn4_5qpLpIokYMRPa7IZ791FMGDxXJ57hj2y0OBq6MmcFu5kdqUUhZlswXgd_nuJBy4MiAvV-zGu8bA3QEYEkwjXEdJv2T5sbN6L8AjW3w-D-Vusv5QOLl5CuUWG12PKJuhTigi-wt\0redirect_urit\08https://pitangui.amazon.com/api/skill/link/MMD6TSJZ97HYTt\0\rclient_secrett\0alexa_OMS_c001_s3cr@tt\0	client_idt\0	alexa_omsxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0/w\0\0\0?@\0\0\0\0\0sq\0~\0\rq\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\08https://pitangui.amazon.com/api/skill/link/MMD6TSJZ97HYTpsq\0~\0/w\0\0\0?@\0\0\0\0\0\0xsq\0~\0/w\0\0\0?@\0\0\0\0\0q\0~\0#xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0§\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0<sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0§\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0204.210.200.183t\0 C87FE177281C2BDC8C49A0222FFEFE9Epsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0§\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0,sr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0§\0\0xpw\0\0\0q\0~\0xpt\0a@b.com',NULL),('120ebea3afb8bf89403f94ef3b8d3e96','¨\Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙\Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.LinkedHashMap4¿N\\l¿˚\0Z\0accessOrderxr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0jtit\0$d805dddb-599f-415c-a34c-b1230bac210ax\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0[F”ìáxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valueq\0~\0xptãeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhQGIuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImQ4MDVkZGRiLTU5OWYtNDE1Yy1hMzRjLWIxMjMwYmFjMjEwYSIsImV4cCI6MTQ5NDExOTMzNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjU3ODc4MzVhLTU1MWYtNDk4OC1hNGY0LTY3YzQ0NTMxNzljYyIsImNsaWVudF9pZCI6ImFsZXhhIn0.Tm1tDX0H8tEhzRSMryWje5J530J3RMFwVju5sp6YQYhNSt59AvDhRq4_lGrzUGQnojXY8Rf04CfZw5iGEWXU7787dP4x6yhQPBHZMk_5WLO5J6Lyf248ETJ6xwmqOKhyBOf0Foj10e4tA1U-1QuRAHyscvBketBEnVPQAuRUS4fFa_nPqXJd2M18fm4z6Yesg1vPtHnFJ5Pokq95CSlmxH1v86wSQ4qJ8i905nMz6MTbnziuHvoNIuglj35las1lSchTWXUXtc5bCdfK-5mJPO6tgHqPg9PKjgl2g30Kwv-jsy9BKMUfyo3DI3BNv--OOr-ntqUCIOwtBCJT7onFUAsq\0~\0w\0\0[\‡s\ÕCxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearertOeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTE1NDE5MjIsInVzZXJfbmFtZSI6ImFAYi5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZDgwNWRkZGItNTk5Zi00MTVjLWEzNGMtYjEyMzBiYWMyMTBhIiwiY2xpZW50X2lkIjoiYWxleGEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.ZC1zR8CRoAaXVspgbLvg0eculp8wl31tBU-zfWhzo9RASZT_GxkTU-eUYUbrv3tnEaXmn2zz9YZ71nOxskFDqJHBWKxjBdCOY9Kxy9zm_PXfNM3IF4hGrZC6azdIKV5rd1cjIFEv9WCe8xEhhNh9W0EQodOT-v1olCnMETFxMSgTwffDqFzcNf168yg-VjnJu32tNLFykZK_jH-0Yy8CczXwL4k-52fntOKc6nN_RlUyCRZovrN4R7dQU4wGuT1ZgsVmPD6-3fGnMor6oaqdNqxiMakbCp3o1fW5GoRe8BrXw5aZL1whdN-6OK0vnUHxukA3-SHZH-OIqVByXVZX2g','721ce6e92cd34b9fb13d1996464f93d2','a@b.com','alexa','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0§\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0alexasr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0JIku4Wt\0\ngrant_typet\0authorization_codet\0\rresponse_typet\0codet\0\rclient_secrett\0alexa_secrett\0redirect_urit\0/successt\0	client_idt\0alexaxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0+w\0\0\0?@\0\0\0\0\0sq\0~\0\rq\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0/successpsq\0~\0+w\0\0\0?@\0\0\0\0\0\0xsq\0~\0+w\0\0\0?@\0\0\0\0\0q\0~\0!xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0§\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\08sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0§\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 7113C2EB3536BDB8321FB512CC5CB89Apsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0§\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0(sr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0§\0\0xpw\0\0\0q\0~\0xpt\0a@b.com','7640602c7af3e3c01eb245e386e96db4'),('8cd7d4ad7430039a2b74d7d345ddb7e3','¨\Ì\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken≤û6$˙\Œ\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6ÖZ\‹\Á\–\0\0xpsr\0java.util.DatehjÅKYt\0\0xpw\0\0[F8\ﬂxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valueq\0~\0xpt\0$9940ef22-e9c5-44d6-88c3-1e16f12c3534sq\0~\0	w\0\0[\‡uâüxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writext\0bearert\0$3b5d44ba-a3ea-4495-a8c8-3a867e44ae6c','9ba6c259c4e69b554ace7afd1633040b',NULL,'alexa','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0§\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0alexasr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp','1ff4b5769b8ed09ab5c9fcde8ed8ba9f');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS `oauth_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` VALUES ('1ff4b5769b8ed09ab5c9fcde8ed8ba9f','¨\Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valuet\0Ljava/lang/String;xpt\0$9940ef22-e9c5-44d6-88c3-1e16f12c3534sr\0java.util.DatehjÅKYt\0\0xpw\0\0[\‡uâüx','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0§\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0alexasr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('7640602c7af3e3c01eb245e386e96db4','¨\Ì\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\ﬂGcù\–…∑\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\·\ncT\‘^\0L\0valuet\0Ljava/lang/String;xptãeyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhQGIuY29tIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImQ4MDVkZGRiLTU5OWYtNDE1Yy1hMzRjLWIxMjMwYmFjMjEwYSIsImV4cCI6MTQ5NDExOTMzNywiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjU3ODc4MzVhLTU1MWYtNDk4OC1hNGY0LTY3YzQ0NTMxNzljYyIsImNsaWVudF9pZCI6ImFsZXhhIn0.Tm1tDX0H8tEhzRSMryWje5J530J3RMFwVju5sp6YQYhNSt59AvDhRq4_lGrzUGQnojXY8Rf04CfZw5iGEWXU7787dP4x6yhQPBHZMk_5WLO5J6Lyf248ETJ6xwmqOKhyBOf0Foj10e4tA1U-1QuRAHyscvBketBEnVPQAuRUS4fFa_nPqXJd2M18fm4z6Yesg1vPtHnFJ5Pokq95CSlmxH1v86wSQ4qJ8i905nMz6MTbnziuHvoNIuglj35las1lSchTWXUXtc5bCdfK-5mJPO6tgHqPg9PKjgl2g30Kwv-jsy9BKMUfyo3DI3BNv--OOr-ntqUCIOwtBCJT7onFUAsr\0java.util.DatehjÅKYt\0\0xpw\0\0[\‡s\ÕCx','¨\Ì\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2AuthenticationΩ@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0§\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>£qiΩ\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0alexasr\0%java.util.Collections$UnmodifiableMapÒ•®˛tıB\0L\0mq\0~\0xpsr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0codet\0JIku4Wt\0\ngrant_typet\0authorization_codet\0\rresponse_typet\0codet\0\rclient_secrett\0alexa_secrett\0redirect_urit\0/successt\0	client_idt\0alexaxsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0	sr\0java.util.LinkedHashSet\ÿl\◊Zï\›*\0\0xr\0java.util.HashSet∫DÖïñ∏∑4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writexsq\0~\0+w\0\0\0?@\0\0\0\0\0sq\0~\0\rq\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xt\0/successpsq\0~\0+w\0\0\0?@\0\0\0\0\0\0xsq\0~\0+w\0\0\0?@\0\0\0\0\0q\0~\0!xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0§\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\08sr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0§\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0 7113C2EB3536BDB8321FB512CC5CB89Apsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0§\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0(sr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0§\0\0xpw\0\0\0q\0~\0xpt\0a@b.com');
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `dob` datetime DEFAULT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-27  0:42:28

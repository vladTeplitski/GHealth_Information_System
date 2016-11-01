CREATE DATABASE  IF NOT EXISTS `ghealth` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ghealth`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `abstract_person`
--

DROP TABLE IF EXISTS `abstract_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstract_person` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `familyName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=499037840 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstract_person`
--

LOCK TABLES `abstract_person` WRITE;
/*!40000 ALTER TABLE `abstract_person` DISABLE KEYS */;
INSERT INTO `abstract_person` VALUES (111111111,'shay','zafran','shayzaf25@gmail.com','braude','Male'),(123456789,'ytzik','hazan','shayzaf25@gmaill.com','da','male'),(200543871,'oz','elimeleh','shayzaf25@gmail.com','hatzav','male'),(201143093,'or','cohen','vl4d89@gmail.com','tirush','male'),(201345789,'David','Rotshild','rotshild@mail.ru','Ben Ami Haifa','Male'),(210908764,'bar','regev','yafit.aronovich@gmail.com','lulav','female'),(222222222,'yafit','avram','yafit.aronovich@gmail.com','braude','Male'),(300234323,'avi','reshef','rotshild@mail.ru','be gurion','male'),(300897561,'shron','noah','shayzaf25@gmail.com','shoshanim','male'),(301121542,'eti','levi','asaf11108@gmail.com','hativat golani','female'),(302628649,'asaf','regev','asaf11108@gmail.com','havazelet','Male'),(309098999,'dudu','aharon','asaf11108@gmail.com','shoshanim','male'),(309197119,'Vlad','Teplitski','vl4d89@gmail.com','Mordechay Gour Acco','Male'),(346565356,'moshe','peretz','vl4d89@gmail.com','goshrim','male'),(409098765,'karin','goren','yafit.aronovich@gmail.com','shoshanim','female'),(499037839,'eyal','golan','shayzaf25@gmail.com','savion','male');
/*!40000 ALTER TABLE `abstract_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abstract_referral`
--

DROP TABLE IF EXISTS `abstract_referral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abstract_referral` (
  `ReferralID` int(10) NOT NULL,
  `refPatientID` int(11) DEFAULT NULL,
  `Date` varchar(255) DEFAULT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ReferralID`),
  KEY `refPatientID_idx` (`refPatientID`),
  CONSTRAINT `refPatientID` FOREIGN KEY (`refPatientID`) REFERENCES `patient` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstract_referral`
--

LOCK TABLES `abstract_referral` WRITE;
/*!40000 ALTER TABLE `abstract_referral` DISABLE KEYS */;
INSERT INTO `abstract_referral` VALUES (2,309197119,'2016-06-09',1,''),(3,309098999,'2016-06-10',0,'dfrf'),(4,309197119,'2016-06-10',0,'hello'),(5,499037839,'2016-06-10',0,'ok'),(6,499037839,'2016-06-10',0,'ok'),(7,499037839,'2016-06-10',0,'hi'),(8,346565356,'2016-06-10',0,'project'),(9,346565356,'2016-06-10',0,'patient ok'),(1122,309197119,'1.12.2015',0,'Blood count');
/*!40000 ALTER TABLE `abstract_referral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appID` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `hour` varchar(45) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `BloodPresure` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `diagnosis` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`appID`),
  CONSTRAINT `appID` FOREIGN KEY (`appID`) REFERENCES `operator_appointment_creation` (`apcID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2009','08:00',60,'60','gggg','ddd'),(2,'2016-06-13','10:00',70,'50','ffff','dddd');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinic` (
  `ClinicID` int(11) NOT NULL,
  `ClinicName` varchar(45) DEFAULT NULL,
  `NumOfPatient` int(11) DEFAULT NULL,
  `ManagerID` int(11) DEFAULT NULL,
  `LabID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ClinicID`),
  KEY `LabID_idx` (`LabID`),
  KEY `ManagerID_idx` (`ManagerID`),
  CONSTRAINT `LabID` FOREIGN KEY (`LabID`) REFERENCES `lab` (`LabID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ManagerID` FOREIGN KEY (`ManagerID`) REFERENCES `user` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (3001,'Golani',NULL,NULL,NULL);
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hmo`
--

DROP TABLE IF EXISTS `hmo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hmo` (
  `HMO` varchar(10) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`HMO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hmo`
--

LOCK TABLES `hmo` WRITE;
/*!40000 ALTER TABLE `hmo` DISABLE KEYS */;
INSERT INTO `hmo` VALUES ('clalit','ghealthbraude@gmail.com');
/*!40000 ALTER TABLE `hmo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `imageid` int(11) DEFAULT NULL,
  `image` blob,
  `imgSerial` int(11) NOT NULL,
  PRIMARY KEY (`imgSerial`),
  KEY `imageid_idx` (`imageid`),
  CONSTRAINT `imageid` FOREIGN KEY (`imageid`) REFERENCES `test_referral` (`testReferralNum`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (2,'ÿØÿà\0JFIF\0\0\0\0\0\0ÿÛ\0„\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z\Z-% &+/-1+--------.-05---5-0--+---------+----+-----+---ÿÀ\0\0·\"\0ÿÄ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\0B\0\0\0\0\0!1AQaq\"2‘¡±ÁÑðBb#Rr‚áñ3’¢²ò$4DsÒÿÄ\0\Z\0\0\0\0\0\0\0\0\0\0\0\0ÿÄ\00\0\0	\0\0\0\0\0\0\0!1AQ2q‘¡±Ñð\"#aRÁáñÿÚ\0\0\0?\0÷$Bä\n„ˆ@*„\0„!H!B\0BçºÕÖšx&åê¤w[°qvà¼Ç¤zÅ_f¥Wü –°H·ª¦Ë”v7é¸u—.nˆöðP¼+Òµ)§Qí?¥Ä{hWEÑÝxÅ23¹•ël÷6=Á\\­Bî‹¬á6GÕiüSBä0=}¤ëU¦öql=¿#ìº,JÑ¬&VžÐÝ[#.ŒÁfžÚýh—P„.ÊAB\0B€„ !B\0B€„ !¡\\€J‘BD)¡\"\n„!\0,N¶u¸\Z%Ú½Öc~g‚Ù{ÃA$À\0’w©^×.š8¼Cœ| ÃFà4óò%UlùQ¿‡é}=Ÿ«¢ëö3±Ø÷Ö{ª=ÅÎq’OÃp\nV>þåWiÛ÷÷èœÁ·ïïÍcÁõ)cdYcáXÃKÌ\ršƒêx*La·O½>*õz¢“ Ü~Ÿ%ËÀoÁg‹m1küODt­WëDn\0•B‰íÞ:Ê¶ÊáÏîÊ#–W.XöË;Ž¥uª½7¶fÔì\0—5å¬›5Ña:5ê‹Ãº¿Ó¥•\0.-i0`Ä}W¬áÍV\0Zàöþ­}²ÝKÛù¾!ÜÏ.3ó6¨·¤#ÆÂ8‹¯²³K×hà~>ŠãÏ%BIFd¡0ÔÓ\rp€™\n«±CrØÃ¹y8ã\noâÎô”¢VYÄé¦©Þ€Ö”«$T;ÕŠ8’5@]J£ §RÏ%É3¹N¦1A<b½dv…©NR2m\n£z\\ãzÅNä¢±Üœ£&Öa½±»s¹<V0£šÒ‰Y-®w\'¶±S€d~Ñzcðø\\­=ê§/ô‹¸éü£ú—‰¼®ËöŽ5q6Sho™ï8û·Ñqä,Ë3gÖðÚ=>ï·ÈlýýýTÀÇßÏû¨Ú.¤_¿5^MØ.à©êóÈ|þî¨×qªðš™W1ŽÉM­{O¼¬ú5‹*©<Ë+£—À¿O£€pÊÿ\0Qó\nl3Û6‘úoµO‡Ç†Ép¼[‰)ƒ¤÷Á_²e’žH(Ö‡/ :¦|=\"vÓoÁxu:Í~¡®¸^ÁÐU#H`1±è´T÷<ž\'¼c·s kw¨ªá\ZíŠ‘Ä8\'7\Zw«ðxÄæƒÛáyÆãßê£5Þ<MóiùªOÇÁ¸OüpÜ›ŸˆB\'s»§ßT{·By©MÚ¦8üŽ\0Ç¶ŠS{·¨Äí*Ãé¼jÐyXû[ÙG™»e¼Å½Bë™‚8•#_\nVÒ<®œ(¦FÚòS²ê@ÄèQ’F\0ž¥\n\0©… ˆÒâši©fv ó@V,¶ÔŸh)r©ÈÁW\'­dmVr£/$ÈÁZx\'†ž\nL¼ù \ZÑ½:I¯u\" /Ö\Z½¦&«·½Þ€=€Yy>þþŠæ/ÆéÞT\rÍ{ŸuË—d@Ð¦¤.›U% ¹gMcêK€Ü¿Ø@Ü¢qÌòxŸk÷.*Î2U/²³Öê3„\'FŸ \\=5KL‹ªê®×yi$\0<M$ò>Š/»ÑEÉ”Yc‚É•Ð]Z©UÍqî²u¸\'È¯\\£…kZÝ\Z\0KŸFÕa–ÖÍ>cè¯·]¾*`ò¿ÂþËÏ¯]	6äý‡‹«²W5àÐ.pNí7…M,Ýûâ¬7MÛGÁm†«ûd`uùDÀ‚—³LÈ‡æ“!\Z{¦:¹®¨áÁ-H›Ú‘ýÓ»}á]\\SžBFÔpÐ”ÿ\0Ä j\réÞjåleÑ‘¢Êˆ©ùnqgbx¹u=2§©+c>‡K¼TëS\r&À‚bN“;õ«%ftå\0æhZuæ>°®P.p\'P¨S	nâÎ¦“I¢ÅÑQ(hV‰méQ	P\0¤Fî)JTl$Êw¡#ZÂ9\'“Á‡zBÇo@(o‘ú’;xôIà€0ïFR£—pNk´ $„ÚŒ±äRÊY@xV1¹^á¹ÄzP1nuÏ£Í]AyÎ9;_yXmÎkÛÕ5:Ô—t2©[ßà–÷O\"¸ŸFtÊB‘ÖI…Ô§ÔJýTq.¢á›\'•×¡õ	’‰~×‚à°cÜÂöŠèàÊ4À±Ê\'™ºò¸µ¸‚—ô<í|ñyPžúdj<Â‡)Ùq÷ê¼3É&-Q<Õj¸FÌÄÍzx(›@¬0¤]¯>wø\'S}Q°/‡ˆø\'eÈ*Øê,F\ZL`ÅÇ‰¤\'‡Óvñ÷Á<Á²ˆS´GˆZºîráÁØà‘®\0óÜ‡}Uwµ´á³ã;‡…±:o%£ÍmÒk¶a¯åþ/c‡_ƒDJ*B¢÷¹Ûùiì>ª:´\\ëf1ÂßéËVòñ±Ê«Èì~\'µsi7x\'Êð´(T\rcFæ‚¡ƒÀäqvæŸU¬ÖBÝ¦ßõù9»	(Ä@ùã±<ö…¨ `.à…4!\0èNQš¢H‘G.t’›™\nSe¨ûE’J\\Ê³±-€\'IN/¡	2zÝÐ?Œ§ÝU’XN‡{O¼§‡u\'e¨ÒÇ\rŽé¿É{Y¨±ºz»Â×ÓkÇ\nªÊÔ·==ºu.F²¾‡“”úw¶ô½!„-yÊ2‰°ÔTV#P²JÐFØMlÊí\\AØa=âéqw9†ÝS)U›uEriaKÉ©Ðôæ¥1Ä{•ìT]\0r^=‚vWµÜ£ÉvXn±Ugú´ó7ø›¹xüB©Y$ây\ZýÚgh˜ê énKÖ\n¼/\0îuŠÕeUäJ¹Aá¬qZlž!EÉ]Îšæ¡KÈ+¥m´N}Ïâ¢ûºëf	Ú€ªåÇû¦`•“‰ÄVû!£Êäú˜þ´šýúžCR¹Jõ‰3¾Ió¿Í{<.ŸÒæýŸûòN••lHºF‰`W9Gè#aZxh\0ä·z³’¹lŽ€2Üà|Ê–0â@å>¿ÙJBöê,2Éî\rR4(ÓÚU‡#Ò¦ÊTY;rÿ\0¸\'fÖÛ-q~	Åÿ\0£S{T¥­þèc·ˆç	ò¡íA±aÙ«m÷d¬¦Ç_/´\0”éUó°MŽÙî»|%ieµ¾ž% ™ÎæÀ\\“`\0Ô•Çc:ù†Êç5ò%ÁcCª¾-˜Ïv“IÓ5È½”ß´\\Gg‚{H5Ofn|&s‹ïó^HÖEÖ[îpÙÏáð¾.Ë:g¡èýYé\nøÇº³ŸQ”š`4šU\Zó©hš@€-$ltqu\ZIy iñkÚáÎñæ¸~ªõŠ•\Z\"…RXZ\\Zè%®quÈ ¸ëh…Ñ7K,êU[¸»Ûbî©æ=LÚú%_éåŠé…¶=½ÎŽ•v8XÐàáìªb0T6_ð²\rF\\¥ºÄ^&NÙÚsFÓ¬«½®“T¹±f¸hIsLZ\0¦åY“\Zmñ][¦âHø¬œWSë‡9¼—dkˆ$¶c`*£:F™vG±ìvšflË5½@&\"A¤ð÷.Ž¢QîqÎêoë>Jü¢Æ™92»ú]•HÉU¦@ Äè}äçà6ÈýVk)”‹Ö¶xÆN+\rÑ1bâFâ¹B½O„ŽV[Õp›Ùíó\n ÂÆ“;¦@Xì¡¾»‘é²câ:4:î`wÝw«uVðôßKÁQÍvú«}ƒµ¿+_Š˜j|‡Éb·LÚØç›$ÔzR£|lÚ¯Pé:nüÐw:Ë0²G²“±@?UæÙ¦k·ÀdÛJâ«\r´Ë|.s~\nvcj7ÄÐá¼XýWW€hš[ªcšGÝ”ºE‡nSú­ï¢¶¹i® ¥Œ«’›ÈÚÒÛ~«.eÓ+¥ésû³K~!dQÁ¹Ú	_I¢’Žš>ÿ\0«-§yóþ\nôéÏ{£è’ö‚ jO\0£e\ZŒq–h*~Ž{A:FÈVU|gj‚Ü®Ø¥ÐêhTµößè¦ÌªÝ8ÎÂ½Ô`, (sð‘£‰BR…d¨“¥~\ZFõª/â´þS³r‰Ô5ÊÚqÉÍõ-òNxq‹LEG·•¢öŸ³i»Q¼ë\Zbok#ñ\r,ßÎ/¥ôQw;Éí¼ž;¿²BÓ\0÷ô¸î¡×dòÜ ’q]¿Ä,cÎb9Êxª7êcÏXæª¹æ</Ó`dŸ”nþ(±\0Ói–ŽSçè\'í3ê˜<ÍÖF8ÿ\0+»„òÀ¯\"kç^ð^-|¹®îi¼\rˆ:ˆ+Ë:ãÕŸÃ—U¤ìÔÌ—7+³0Éòß‚¢úù·G¹Âµq¯öåî9ÉI]£nßU<K@ù§:¦à°¸3èU‘ÆÌÐÃtµz~\nÏÄç˜Z˜n·Öo”ßË3Ì{Ëº©Ü“¶;—jS]šÊ4özÐ_|Ñè^¹Ð>1RŸôçñ¿²ÝÁtÅ\nýÖÖ¦ùü¥Â|Øëû/\"€‚ðuƒî­²]Q‚ÎD½I5óüøžÝKL88ÓÂã[NØÒxð\nãëX€`Á‚n‹½xŽ¥kPµ*Õ7¿ì2ßeµ„ëÖ)ž1N¨âÜõm½•Šäú˜,á6ÇÕiü¿>\'£:®%„å‡·`üÐ6“´Ÿ»¤`V•ÍÍ¬Ñx¿!}ÖÞ¹,íƒ U¥R™Þ^ßYÙtN±a«@mv_eIa>U\0Ÿ%fS1OKt:Åý~†­<3ÐàÜÀ“7C¦;£ÎÊ†ÛÀ÷R¶¦Ý›ÒT‡×huÔ{.¨¾Å<Ì¥N›]\'3\\&$o€bG*_À„‚Ì:¨öe¥HE! ‡—:vã.‚A‹†Å“¥1Ï£H9Ï\rªðâ]–Ì\\@À\'¼@˜\n‰i!\"yÙ«øg\r€¨A»AiU©bÝ†¢\rG¹õNPâl6Z		Ž*Ö¤Ë›.h;âÞÅyšž¾Kc6AS#c•nÀ³Bæü>ü”½3ìÀa¼è8,ê]5Px€>Šcý=ª”9¢ãìyû=mjXy4›Qî§P:	0#lLùè¨`qOh˜ó÷	ôú^œÉiiß\n\n5Ë\\{\'°Ó:5Ægf†B‰ðÝdjPuôötÎ{{M4j©y\\Ëß±r®5ïï½Ç+l8ðVº\Z“QÂçA¸*Âsƒ‰4hð-ªë+¶ètj•™-Î,Ÿ6Ñ5ANY­Åfž4m^žLøe”&ŒP)ÁáH)„ «O`RªÓÙK®Ò\Z@#œ‚@	Ov8\0NWÀ íÄíÅÉ²m¦Š¨Â¹ N¶Žÿ\0äfcºs;Äwì ÞòUÌp$F&-~Õ„¢Ðæÿ\0æ’Y­ŽcÛ†<¶ä³É¤)æ-“˜f¶WIÉ¨¹Ý\ZìUè8°Ïþ¥ÂH‡exö¼q×àŸˆ–8œõÈ±†·´m‰1\riÛ,AÔHWô Üù­ Xn$Z$huOê}ÞøïN]o\ZÅ¶nTN%Ñ=¦$wœ?öònmnÈØDfÝ¬›§\n®¿~¯äñPmƒœYàA’àj †!‡Gw6×ï6Cš7‘ÃqU+áEA˜\\_ØÁääšüSLCÜœ3ï¨ÚÛ\r‚·¤–é´ãÝ àNSscxÝª’sƒÕ¼5GCé·1“Ýî¸ï6Õaâº‘Dæ4«—\\Ðè10Hˆ¶õÚÔªÇ¹¡õ(8\"ƒ¤¶îaÌFŽô%VÄ2‹Évl;…A“—\08C…Ý­¯¥×­3]:ÛjÙ3Í1]S®Ñ™…µ°µÀÏÁbâpU)˜s;¡^ºì	£h4ºæS½ßk,¼v“Çï;UÐÉÎÎð±¹‹xªÝ(Ù)>ç–½0®óÑ^LŠSÞÒ‹ÇóIÝ\"O%›S )’\Z×Áv‘Míá›4ð7U½;ìÍ0â0—¬ŽW1NkÖ¶/«µ)þptüŽóº¡ˆÀ:Ÿ‹–Ø˜_f£]ãz­×$i†¦¹tdY¹Ç+{h¢u{×&‡öÕ4Swåp<ƒèTa¢î|ôÜÑÀôZ&iÔs•Îgý&˜]®ø†ÀuF»ÿ\0µ€ùff_R¸²÷7VŸKz„ƒÞ±ñ]\'$W5TöšYþVç©aºî×Zµ´Ü?Òà>+Põ¿ZI{¿”Ó~oHx^>Ê±pâ=•ªx§m2ºô­ß\r¢oÇ±ýòvØžŸ8Š¹Ž@Ý\ZÒîðvI?M‹ èìM0Âã¸0\r…¶ú/5£T;tñÕt½ÞÎ:gy#\0pãæ­ÓÖ­±F[÷1ñZj¢‡8löKóØ^é‘ÏPÎÏ‰¹Uq½Vegð#È¦»ßÌÁåo„/ PÁò9-öèí†ÐÔÎŽp÷JY¹àúª8’^f&\r‰kG\rÒ0Ò33ò+HÕktQqÑ¢nªœ[¢c&žÌé(¾[RNå3è¨YÝ^®*<9¦A×‚è²ËÎºµa3rŽYJ™‹)ÙUMØ5…Ê¯bŠ©þŠDÚ•ÚÓÀ›,5<®¥EZˆv­i 3	Ö$yÀô\n³ðÓÒi=Ó9È¸¼ƒ­¢ÊAq¨Œû A.tˆ—Î~ìEó:×Õ%<(/dNX\0šš”ƒÌzØï@_(T\n\rDF|ßêl 8zitá@fÍØå0˜<@ð‹kít›jP\nƒaª½w1ÀÑ.8Ë!ÀÈ-¹ˆ#|j£8<æË«‰=ÒÒÎ»øì7öcÅ77B¶\r 5Ž&|!Ã»š3C],\Zë\Z™AK)kƒ*æhËbÂç$æ.ñh<µ*|¦–JÊf ¸âFf‹‡~AÙ‰Î5™¶Ã±IK\0ÒÃ\"±‚ò—‚Í— Ø©·Ñ•V®Š\nØ½Â¡n = 489­0í\Z;­ñ€$DÌ\0ö]ÎËŠ9ÁA\0åË`Ë\rõw‚’½NŒÜ©ÖèÎ´×ÜAÞ`îês¶[ÝwêÔ[K+xF’Ûö’MFµ®:_ºO—:Shãñ]MÞ&‚Ê­ÕfÄ0ˆþ4»Ê½L»0hiŒ½ÀÖï¼ ÷Á\Z¬ÜWDåÞU†dÛ÷Ž¤\"4¾ÇA\\¸\Z!©’îpµú¼[­#Î“ý&~\n…^†k´¨\'uVAósü®ëšÄWËã±.ú$Íˆ³·y²§dI/©L°åñÞ	Ú×Aù®hÕ\r|×Ïyçõ:¹TxiæãJ wü]öYÕ°f™‡Ãº£K¼Jôwõ~Z×S©”’I#¼Â×FY\Z€[_zŒà1¬\nŒª?…ñe¥„\\E§]ë—Qtuëº_OôyæZþÆWgÑuœì-2âI‡ÌØøœ>I¸œ-ÀÄáÉqýãê¹d1ÎÒÜÏc³¤Úa´ç0Oˆ´¸f$Á \0E÷-ZòÝ¿ƒ½]§I\'³÷w\"‹iêß›SIÜ}³“\Zèÿ\0ð„=ÛþGâ¶|Ð×ày´·Ü(1²|œú©&8rÌ>¡0™ãþÓî2A^é\nžÆµÚcú¼_ñUh«7‡/§@\r•\'Ìµà|WìJêoõ\"”‰àãê@]Qd,Þ§á2S&74H“îVã˜¼ÍCæ›7Ò±V=®N,M!R\\>P™(B)ÈHB5	Fï¡.a¼ T2 íHk6&DhN sÜ€ƒ†k¯“3¬<YL¿„ŸR6¨]‡¿úO¹™`Hƒ¦anèír¯9àL‘a&û7òIÚ·xÙ·~ˆ\nt˜ZdR|™™ª£Bâ ÀÓ®®FØ7º|“[ˆa$	<Æ£šQU§Bn¾ˆ\"{fÀ3c§ìT‹e»Âó¾{¶6ÖÈ¹—›’6f ziæ£©KW\0s—ûOçb˜5pÐ#¡S +¶–RKZë›Ëí¾Z	rw|Ð•šZžR +W LCˆƒ6‹ ƒkªn¢K³gp0,4õ‰6:ÌEÁ Æ‚ájÂHA“(á°\"ß~ªU¦iøê6à\ZHË6\'MÑ¥ù…¿	Pœ³)Õ,.$\"$kï£©Ñ$¸R`qÕÍhkŒë$kæº#Dnãç½ES\nî/\'2m¬uè\Zì$Óp{w¾^Ë\"©«LÃÙ£â½F®	Q­‚Þ%kŽ©÷3:ç#6‚†%§ó»GASwåo‚Î«ÕfêÓSôW-BenƒQ½tøIsZ´E¸•S\rÐa™Èyê¶ðx|—\0B6\\š\"4¼\nŸfÆ°ù›Ÿu`U<M,Y\ZÝ\\¥‰bóÞs“j-ö‰	Li:$‚T„’R!	 ¨2œÁ²b54æ£¥iŠq2|BåBº”7“ç<åFÊ.\0Œïô¸v}„µ¨È(#X“¨ˆòHæ¼Ÿ4Û3;6h„ #µŠsÈ§–¦Q¾Ú¡HÖÒpþ&íMžäÙ=Ò9¡†:Ò[F_…ì¥B„\"„ !„!!	!B•\"i¨ªÑ\0IÑSRÆá,×eˆÔKH\'B5¼ì„ÏÃ”Á!Úˆ±Ò×¼{¡\nrÈ)ÿ\0áõ®\ra‚`8ÝÅ„Øà	ƒ0D¸v‡¸3aØ‘6\0›‘¶äBe‚f\0í9iµU¥‡¬psKb`4‡Aï\0”yÎÂ!b•:¡¬‡Œà4>|1ßÙ\"óü-*N1xžG¬ˆP	„($ÿÙ$ltqu\ZIy iñkÚáÎñæ¸~ªõŠ•\Z\"…RXZ\\Zè%®quÈ ¸ëh…Ñ7K,êU[¸»Ûbî©æ=LÚú%_éåŠé…¶=½ÎŽ•v8XÐàáìªb0T6_ð²\rF\\¥ºÄ^&NÙÚsFÓ¬«½®“T¹±f¸hIsLZ\0¦åY“\Zmñ][¦âHø¬œWSë‡9¼—dkˆ$¶c`*£:F™vG±ìvšflË5½@&\"A¤ð÷.Ž¢QîqÎêoë>Jü¢Æ™92»ú]•HÉU¦@ Äè}äçà6ÈýVk)”‹Ö¶xÆN+\rÑ1bâFâ¹B½O„ŽV[Õp›Ùíó\n ÂÆ“;¦@Xì¡¾»‘é²câ:4:î`wÝw«uVðôßKÁQÍvú«}ƒµ¿+_Š˜j|‡Éb·LÚØç›$ÔzR£|lÚ¯Pé:nüÐw:Ë0²G²“±@?UæÙ¦k·ÀdÛJâ«\r´Ë|.s~\nvcj7ÄÐá¼XýWW€hš[ªcšGÝ”ºE‡nSú­ï¢¶¹i® ¥Œ«’›ÈÚÒÛ~«.eÓ+¥ésû³K~!dQÁ¹Ú	_I¢’Žš>ÿ\0«-§yóþ\nôéÏ{£è’ö‚ jO\0£e\ZŒq–h*~Ž{A:FÈVU|gj‚Ü®Ø¥ÐêhTµößè¦ÌªÝ8ÎÂ½Ô`, (sð‘£‰BR…d¨“¥~\ZFõª/â´þS³r‰Ô5ÊÚqÉÍõ-òNxq‹LEG·•¢öŸ³i»Q¼ë\Zbok#ñ\r,ßÎ/¥ôQw;Éí¼ž;¿²BÓ\0÷ô¸î¡×dòÜ ’q]¿Ä,cÎb9Êxª7êcÏXæª¹æ</Ó`dŸ”nþ(±\0Ói–ŽSçè\'í3ê˜<ÍÖF8ÿ\0+»„òÀ¯\"kç^ð^-|¹®îi¼\rˆ:ˆ+Ë:ãÕŸÃ—U¤ìÔÌ—7+³0Éòß‚¢úù·G¹Âµq¯öåî9ÉI]£nßU<K@ù§:¦à°¸3èU‘ÆÌÐÃtµz~\nÏÄç˜Z˜n·Öo”ßË3Ì{Ëº©Ü“¶;—jS]šÊ4özÐ_|Ñè^¹Ð>1RŸôçñ¿²ÝÁtÅ\nýÖÖ¦ùü¥Â|Øëû/\"€‚ðuƒî­²]Q‚ÎD½I5óüøžÝKL88ÓÂã[NØÒxð\nãëX€`Á‚n‹½xŽ¥kPµ*Õ7¿ì2ßeµ„ëÖ)ž1N¨âÜõm½•Šäú˜,á6ÇÕiü¿>\'£:®%„å‡·`üÐ6“´Ÿ»¤`V•ÍÍ¬Ñx¿!}ÖÞ¹,íƒ U¥R™Þ^ßYÙtN±a«@mv_eIa>U\0Ÿ%fS1OKt:Åý~†­<3ÐàÜÀ“7C¦;£ÎÊ†ÛÀ÷R¶¦Ý›ÒT‡×huÔ{.¨¾Å<Ì¥N›]\'3\\&$o€bG*_À„‚Ì:¨öe¥HE! ‡—:vã.‚A‹†Å“¥1Ï£H9Ï\rªðâ]–Ì\\@À\'¼@˜\n‰i!\"yÙ«øg\r€¨A»AiU©bÝ†¢\rG¹õNPâl6Z		Ž*Ö¤Ë›.h;âÞÅyšž¾Kc6AS#c•nÀ³Bæü>ü”½3ìÀa¼è8,ê]5Px€>Šcý=ª”9¢ãìyû=mjXy4›Qî§P:	0#lLùè¨`qOh',1);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `LabID` int(11) NOT NULL,
  `LaboratorianID` int(11) DEFAULT NULL,
  PRIMARY KEY (`LabID`),
  KEY `LaboratorianID_idx` (`LaboratorianID`),
  CONSTRAINT `ClinicID` FOREIGN KEY (`LabID`) REFERENCES `clinic` (`ClinicID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `LaboratorianID` FOREIGN KEY (`LaboratorianID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator_appointment_creation`
--

DROP TABLE IF EXISTS `operator_appointment_creation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator_appointment_creation` (
  `apcID` int(11) NOT NULL,
  `patientID` int(11) DEFAULT NULL,
  `docID` int(11) DEFAULT NULL,
  `ClinicID_OAC` int(11) DEFAULT NULL COMMENT 'the clinic ID in operatorAppointmentCreation relation.',
  `CreationDate` varchar(45) DEFAULT NULL,
  `appointmentDate` varchar(45) DEFAULT NULL,
  `appointmentHour` varchar(45) DEFAULT NULL,
  `waitingTimeService` int(11) DEFAULT '-1',
  PRIMARY KEY (`apcID`),
  KEY `ClinicID_OAC_idx` (`ClinicID_OAC`),
  KEY `patientID_idx` (`patientID`),
  KEY `doctorID_idx` (`docID`),
  CONSTRAINT `ClinicID_OAC` FOREIGN KEY (`ClinicID_OAC`) REFERENCES `clinic` (`ClinicID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `docID` FOREIGN KEY (`docID`) REFERENCES `specialist_doctor` (`specDocID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientID` FOREIGN KEY (`patientID`) REFERENCES `patient` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator_appointment_creation`
--

LOCK TABLES `operator_appointment_creation` WRITE;
/*!40000 ALTER TABLE `operator_appointment_creation` DISABLE KEYS */;
INSERT INTO `operator_appointment_creation` VALUES (1,309197119,2001,3001,'2008','2009','08:00',-1),(2,309098999,2006,3001,'2016-06-10','2016-06-13','09:30',-1),(3,309197119,2001,3001,'2016-06-10','2016-06-14','16:30',-1),(4,499037839,2005,3001,'2016-06-10','2016-06-14','16:00',-1),(5,346565356,2006,3001,'2016-06-15','2016-06-14','12:00',-1),(6,346565356,2006,3001,'2016-06-15','2016-06-17','14:00',-1),(7,309197119,2001,3001,'2016-06-16','2016-06-17','11:00',-1),(8,309197119,2001,3001,'2016-06-20','2016-06-27','08:30',-1),(9,309197119,2001,3001,'2016-06-20','2016-06-27','08:30',-1);
/*!40000 ALTER TABLE `operator_appointment_creation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `personID` int(11) NOT NULL COMMENT 'person ID -> abstract person (cascade)',
  `doctorID` int(11) DEFAULT NULL,
  `HMOName` varchar(10) DEFAULT NULL,
  `height` double DEFAULT NULL,
  PRIMARY KEY (`personID`),
  KEY `doctorID_idx` (`doctorID`),
  KEY `personID_idx` (`personID`),
  KEY `hmo_name_idx` (`HMOName`),
  CONSTRAINT `doctorID` FOREIGN KEY (`doctorID`) REFERENCES `user` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `personID` FOREIGN KEY (`personID`) REFERENCES `abstract_person` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (123456789,2005,'clalit',179),(309098999,2005,'clalit',175),(309197119,2001,'clalit',170),(346565356,2006,'clalit',180),(409098765,2001,'clalit',167),(499037839,2005,'clalit',178);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral`
--

DROP TABLE IF EXISTS `referral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referral` (
  `ReferralNum` int(11) NOT NULL,
  `doc_specialization` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ReferralNum`),
  CONSTRAINT `ReferralNum` FOREIGN KEY (`ReferralNum`) REFERENCES `abstract_referral` (`ReferralID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral`
--

LOCK TABLES `referral` WRITE;
/*!40000 ALTER TABLE `referral` DISABLE KEYS */;
INSERT INTO `referral` VALUES (3,'Orthopedist'),(4,'dentist'),(5,'kids'),(6,'kids'),(7,'Otolaryngology'),(8,'Orthopedist'),(9,'Orthopedist');
/*!40000 ALTER TABLE `referral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist_doctor`
--

DROP TABLE IF EXISTS `specialist_doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialist_doctor` (
  `specDocID` int(11) NOT NULL,
  `Specialization` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`specDocID`),
  CONSTRAINT `specDocID` FOREIGN KEY (`specDocID`) REFERENCES `user` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist_doctor`
--

LOCK TABLES `specialist_doctor` WRITE;
/*!40000 ALTER TABLE `specialist_doctor` DISABLE KEYS */;
INSERT INTO `specialist_doctor` VALUES (2001,'dentist'),(2005,'kids'),(2006,'Orthopedist'),(2010,'women'),(2011,'Orthopedist');
/*!40000 ALTER TABLE `specialist_doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_referral`
--

DROP TABLE IF EXISTS `test_referral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_referral` (
  `testReferralNum` int(11) NOT NULL,
  `Test_Kind` varchar(255) DEFAULT NULL,
  `doctorID` int(11) DEFAULT NULL,
  PRIMARY KEY (`testReferralNum`),
  KEY `dID_idx` (`doctorID`),
  CONSTRAINT `dID` FOREIGN KEY (`doctorID`) REFERENCES `specialist_doctor` (`specDocID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `testReferralNum` FOREIGN KEY (`testReferralNum`) REFERENCES `abstract_referral` (`ReferralID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_referral`
--

LOCK TABLES `test_referral` WRITE;
/*!40000 ALTER TABLE `test_referral` DISABLE KEYS */;
INSERT INTO `test_referral` VALUES (2,'[eyes, piss]',2001),(7,'[eyes, piss]',2001),(1122,'Hemoglobin, Calcium, Minerals, Vitamins count',2001);
/*!40000 ALTER TABLE `test_referral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_result`
--

DROP TABLE IF EXISTS `test_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_result` (
  `testID` int(11) NOT NULL,
  `testResultReferralID` int(11) DEFAULT NULL,
  `test_result_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`testID`),
  KEY `ReferralID_idx` (`testResultReferralID`),
  CONSTRAINT `testResultReferralID` FOREIGN KEY (`testResultReferralID`) REFERENCES `test_referral` (`testReferralNum`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_result`
--

LOCK TABLES `test_result` WRITE;
/*!40000 ALTER TABLE `test_result` DISABLE KEYS */;
INSERT INTO `test_result` VALUES (1,1122,'Everthing is fine 123'),(2,7,'Everything is fine 123'),(3,1122,'Everthing is fine 123'),(4,1122,'Everthing is fine 123'),(5,1122,'Everthing is fine 123'),(6,1122,'Everthing is fine 123'),(7,1122,'Everthing is fine 123'),(8,1122,'Everthing is fine 123'),(9,1122,'Everthing is fine 123'),(10,1122,'Everthing is fine 123'),(11,1122,'Everthing is fine 123'),(12,1122,'Everthing is fine 123'),(13,1122,'Everthing is fine 123'),(14,1122,'Everthing is fine 123'),(15,1122,'Everthing is fine 123'),(16,1122,'Everthing is fine 123'),(17,1122,'Everthing is fine 123'),(18,1122,'Everthing is fine 123'),(19,1122,'Everthing is fine 123'),(20,1122,'Everthing is fine 123'),(21,1122,'Everthing is fine 123');
/*!40000 ALTER TABLE `test_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL COMMENT 'user id -> abstract person (cascade)',
  `worker_num` int(11) NOT NULL,
  `role` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `cID` int(11) DEFAULT NULL,
  PRIMARY KEY (`worker_num`),
  KEY `ID_idx` (`userID`),
  KEY `clinicID_idx` (`cID`),
  KEY `cID_idx` (`cID`),
  CONSTRAINT `cID` FOREIGN KEY (`cID`) REFERENCES `clinic` (`ClinicID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `abstract_person` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (201345789,2001,'doctor',0,'12345',3001),(302628649,2002,'operator',0,'12345',3001),(111111111,2003,'laboratorian',0,'12345',3001),(309197119,2004,'manager',0,'12345',3001),(200543871,2005,'doctor',0,'12345',3001),(201143093,2006,'doctor',0,'12345',3001),(300234323,2007,'operator',0,'12345',3001),(300897561,2008,'laboratorian',0,'12345',3001),(301121542,2009,'manager',0,'12345',3001),(210908764,2010,'doctor',0,'12345',3001),(222222222,2011,'doctor',0,'12345',3001);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-01 15:30:45

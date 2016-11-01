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
INSERT INTO `image` VALUES (2,'����\0JFIF\0\0\0\0\0\0��\0�\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z\Z-% &+/-1+--------.-05---5-0--+---------+----+-----+---��\0\0�\"\0��\0\0\0\0\0\0\0\0\0\0\0\0\0\0��\0B\0\0\0\0\0!1AQaq\"2�������Bb#Rr���3����$4Ds���\0\Z\0\0\0\0\0\0\0\0\0\0\0\0��\00\0\0	\0\0\0\0\0\0\0!1AQ2q������\"#aR�����\0\0\0?\0�$B�\n��@*�\0�!H!B\0B��֚x&��w[�qv�Ǥz�_f�W� ��H���˔v7�u�.n���P�+ҵ)�Q�?��{hWE��x�23���l�6=�\\�B�6G�i��SB�0=}��U��ql=�#�,JѬ&�V���[#.��f���h�P�.�AB\0B�� !B\0B�� !�\\�J�BD)�\"\n�!\0,N�u��\Z%ڽ�c~g��{�A$�\0�w�^�.�8�C�| �F�4��%Ul�Q���}=�����3����{�=��q�O�p\nV>��Wi���������c��)cdYc�X�K�\r����x*La�O�>*�z�� �~�%��o�g�m1k�ODt�W�Dn\0�B���:ʶ�����#�W.X��;��u��7��f��\0�5嬛5�a:�5�ú�ӥ�\0.-i0`�}W���V\0Z����}��K���!��.3�6���#��8�����K�h�~>���%BIFd�0��\rp��\n��Cr��ùy8�\no�����VY�馩ހ֔�$T;Պ8�5@]J� �R�%�3�N�1A<b�dv��NR2m\n�z\\�z�N䢱ܜ�&�a���s�<V0��҉Y-�w\'��S�d~�zc��\\�=�/�������������5q6Sho��8���q�,�3g���=�>���l���T�������.��_�5^M�.����|���q����W1��M�{O���5��*�<�+����O��p��\0Q�\n�l3�6��o�O�ǆ�p�[�)����_�e��H(և/�:�|=\"v�o�xu:�~���^��U#H`1��T�<�\'�c�s�kw���\Z튑�8\'7\Zw��x����y�����5�<M�i��O���O�pܛ���B\'s���T�{�By�Mڦ8���\0Ƕ�S{����*��j�yX�[�G��e�ŽB��8�#_\nVҝ<��(�F��S���@��Q�F\0��\n\0�� ���i�fv��@V,�ԝ�h)r���W\'�dmVr�/$��Zx\'��\nL�� \Zѽ:I�u�\"�/�\Z��&���ހ�=�Yy>����/���T\r�{�u��d@Ц�.�U% �gM�c�K����@ܢq��x�k�.*�2U/����3�\'F� \\=5KL�����yi$\0<M$�>�/��EɔYc�ɕ�]Z�U�q�u�\'ȯ\\��kZ�\Z\0K�F�a���>c请]�*`����ϯ]	6������W5��.pN�7�M�,���7M�G�m���d`u�D����L���!\Z{�:�����-H�ڑ�ӻ}�]\\S�BF�pД�\0��j\r��j�leё������nqgbx�u=2��+c>�K�T�S\r&��bN�;��%ft�\0�hZu�>��P.p\'P�S	n�Φ�I���Q(hV�m�Q	P\0�F�)JT�l$�w�#Z�9\'���zB�o@(o���;x�I���0�FR��pNk�� $�ڌ��R�Y@xV1�^��zP1nuϣ�]Ay�9;_yXm�k��5:ԗt2�[����O\"��Ft�B��I�ԧ�J�Tq.��\'�ס�	��~���c�������4���\'��򸵸����<�|�yP��dj<)�q��3�&-Q<�j�F���zx(�@�0�]�>w�\'S}Q�/���\'e�*��,�F\ZL`�ǉ�\'��v���<���S�G�Z��r�������\0���}Uw����;���:o%��m�k�a���/c�_�DJ*B�����i�>�:�\\�f1����V��ʫ��~\'�si7x\'��(T\rcF揂����qv�U��Bݦ���9�	(�@��<����`.��4!\0�NQ���H�G�.t����\nSe��E�J\\ʳ�-�\'IN/��	2z��?��݁U�XN�{O���u\'e���\r���{Y���z����k�\n��Է==�u.F������w���!�-y�2���TV#P�J�F�Ml��\\A�a=��qw9��S)U�uEria�Kɩ���1�{��T]\0r^=�vW�ܣ�vXn�Ug���7���x�B�Y$�y\Z��gh�� �nK�\n�/\0�u��eU�J�A�qZl�!E�]Κ��K�+�m�N}�����f	�������`���āV�!������������CR�J��3�I��{<.�������N��lH�F�`�W9G�#aZxh\0�z���l��2��|ʖ0�@�>��JB��,2��\rR4(��U�#Ҧ�TY;r�\0�\'f��-q~	��\0�S{T�����c���	��A�a٫m�d���_/�\0��U�M���|%ie���% ����\\�`\0ԕ�c:����5�%��cC��-��v�I�5Ƚ�ߴ\\Gg�{H5Ofn|&s���^H�E�[�p����.�:g���Y�\n�Ǻ��Q��`4�U\Z�h�@�-$ltqu\ZIy�i�k�����~����\Z\"�RXZ\\Z�%�qu� ��h��7K,�U[���b��=L��%_��酶=�Ύ�v8X����b0T�6�_�\rF\\���^&N��sFӬ����T��f�hIsLZ\0���Y�\Zm�][��H���WS�9���dk�$�c`*�:F�vG��v�fl�5�@&\"A���.��Q�q��o�>J��ƙ9�2��]�H�U�@ ��}���6��Vk)��ֶx�N+\r�1b�F��B�O��V[�p����\n��Ɠ;�@X졾���c�:4:�`w�w�uV���K�Q��v��}���+_��j|��b�L���$�zR�|lگP�:n��w:�0�G����@?U�٦k��d�J��\r��|.s~\nvcj7���X�WW�h�[��c�Gݔ�E�nS���i�����������~�.e�+��s��K~!dQ���	_I����>�\0�-�y��\n���{���� jO\0�e\Z�q�h�*~�{�A:�F�VU|gj�ܮإ��hT����̪�8�½�`, (s𑣉BR�d����~\ZF��/��S�r��5��q���-�Nxq�LEG������i�Q��\Zbok#�\r,��/��Q�w;��;��B�\0������d�ܠ�q]��,c�b9�x�7�c�X檹�</�`d���n�(�\0�i���S��\'�3�<�֝F8�\0+�����\"k�^�^-|���i�\r�:�+�:�՟×U���̗7+�0ɝ�߂����G�µq����9�I]�n�U<K@��:�స3�U�����t�z~\nύ����Z�n��o����3�{˺�ܓ�;�jS]��4�z�_|��^��>1R������t�\n��֦����|���/\"���u��]Q��D�I5�����KL88���[N��x�\n��X�`��n��x��kP�*�7��2�e����)�1N��܏�m������,�6��i��>\'�:�%�凷`��6������`V��ͬ�x�!}�޹,���U�R��^�Y�tN�a�@mv_eIa>U\0�%fS1OKt:��~��<3�����7C�;��ʆ���R��ݛ�T��hu�{�.���<̥N�]\'3\\&$o�bG*_�������:���e�HE! ��:v�.�A��œ�1ϣH9�\r���]��\\@���\'�@�\n�i!\"y٫�g\r���A�AiU�b݆�\rG��NP�l6Z		�*��˛.h;���y���Kc6AS#c�n��B��>���3�����a��8,�]5Px�>�c�=��9���y�=mjXy4�Q�P:	0#lL��`qOh���	��^��ii�\n\n5�\\{\'��:5�gf�B���djPu��t�{{M4j�y\\�߱r�5���+l8�V�\Z��Q��A�*s��4h��-��+���tj��-�,�6�5ANY��f�4m^�L�e�&�P)��H)� �O`R���K��\Z@#��@�	Ov8\0NW� ����ɲm���¹��N���\0�fc�s;�w� ��U�p$F&-~Մ����\0��Y��c��<�����)�-��f�WIɨ��\Z�U�8�����H�ex���q�����8��ȱ���m�1\ri��,A�HW�� ��� Xn$Z$huO�}���N]o\ZŶnTN%�=�$w�?��nmn��Dfݬ��\n��~���Pm���Y�A��j��!�Gw6��6C�7��qU+�EA�\\_�����SLC��3���\r����鴐�ݠ�NSscxݪ�s�ռ5GC�1����6�a⺑D�4��\\��10H����Ԫǹ��(8\"����a�F��%V�2��vl;�A���\08C�ݭ����3]:�j�3�1]S�љ�������b�pU)�s;���^��	�h4��S��k,�v���;U����𱹋�x��(�)>��0���^L�S�ҋ��I�\"O%�S�)�\Z��v�M���4�7U�;��0�0���W1Nkֶ/��)�pt��󺡈�:���ؘ_f�]�z��$i���tdY��+{h�u{�&���4Sw�p<��Ta��|������Z&i�s��g�&�]����uF��\0���ff_R���7V�Kz�����]\'$W5T��Y�V�a���Z���?��>+P��ZI{���~oH�x^>ʱp�=��x�m2����\r�oǱ��v؞�8���@�\Z���vI?M����M0���0\r���/5�T;t��t��΁:gy#�\0p���֭�F[�1�Zj��8l�K��^���P�ω�U�q�Veg�#Ȧ�����o�/�P��9-�����Ύp�JY�����8�^f&\r�kG\r�0�33�+�H�ktQqѢn��[�c&���(�[RN�3��Y�^�*<9�Aׂ貍�κ�a�3r�YJ��)�UM�5�ʯb����Dڕ�����,5<��EZ�v�i 3	�$y��\n����i=�9ȸ�����Aq����A.t���~�E�:��%<(/dNX\0������z��@_(T\n\r�DF|��l 8zit�@f���0�<@��k�t�jP\n�a��w1��.8�!��-��#|j�8<�˫�=��λ��7�c�77B�\r�5�&|!û�3C],\Z�\Z�AK)k�*�h�b��$�.�h<�*|��J�f���Ff��~Aى�5��ñIK\0��\"���͗ ة�ѕV��\n��¡n =�489�0�\Z;���$D�\0�]�ˊ9�A\0��`�\r�w���N�ܩ������A�`��s�[�w��[K+xF����MF��:_�O�:Sh��]M�&�ʭ�f�0��4�ʽL�0hi����！ ���\Z��WD��U��d����\"4��A\\�\Z!���p���[�#Γ��&~\n�^�k��\'uVA�s�����W��.�$͈��y��dI/�L����	��A��h�\r|��y��:�Txi��J�w�]�Yհf��ú�K�J�w�~Z�S���I#���FY\Z�[_z��1�\n��?��e��\\E�]�Qtu�_O�y�Z���Wg�u��-2�I�����>I��-����q����d1����c���a��0O���f$� \0E�-Z�ݿ��]�I\'��w\"�i�ߛSI�}��\Z��\0��=��G��|�׍�y���(�1�|���&8r�>�0�����2A�^�\n��Ƶ�c��_�Uh��7�/�@\r�\'̵�|W�J�o�\"�����@]Qd,ާ�2S&74H��V㘼�C�7ұV=�N,M!R\\>P�(B)�HB5	F��.a� �T2 �Hk6&DhN�s܀��k��3�<YL���R6�]���O��`H��an��r�9�L�a&�7�Iڷxٷ~�\nt�ZdR|����B� �ӝ��F�7��|�[�a$	<ƣ�QU�Bn��\"{f�3c��T�e���{�6������6f zi棩KW\0s��O�b�5p�#�S +��RKZ���Z	�rw|Е��Z�R +W�LC��6����k�n�K�gp0,4��6:�E� Ƃ�j�HA�(���\"�~��U�i��6�\ZH�6\'Mѥ���	P���)�,.$�\"$k����$�R`q��hk��$k�#Dn��ES\n�/\'2m�u��\Z�$�p{w�^�\"��L����F�	Q���%k���3:�#6��%���GASw�o���Ϋ�f��S�W-Ben��Q�t�IsZ�E��S\r�a��y��x|�\0B6\\�\"4��\n�fư���u`U<M,Y\Z�\\��b��s�j-��	Li:$�T���R!	 �2���b5�4棥i�q2|B�B��7��<�F�.\0������v}����(#X����H漟4�3;6h� #��sȧ��Q�ڡH��p�&�M���=�9��:�[F_��B�\"� !�!!	!B�\"i���\0I�S�R��,�e��KH\'B5������!ڈ��׼{�\nr�)�\0���\ra�`8�ń��	�0D�v��3aؑ6\0����Be�f\0�9i�U���psKb`4�A�\0�y��!b�:�����4>|1��\"��-*N1x�G��P	��($��$ltqu\ZIy�i�k�����~����\Z\"�RXZ\\Z�%�qu� ��h��7K,�U[���b��=L��%_��酶=�Ύ�v8X����b0T�6�_�\rF\\���^&N��sFӬ����T��f�hIsLZ\0���Y�\Zm�][��H���WS�9���dk�$�c`*�:F�vG��v�fl�5�@&\"A���.��Q�q��o�>J��ƙ9�2��]�H�U�@ ��}���6��Vk)��ֶx�N+\r�1b�F��B�O��V[�p����\n��Ɠ;�@X졾���c�:4:�`w�w�uV���K�Q��v��}���+_��j|��b�L���$�zR�|lگP�:n��w:�0�G����@?U�٦k��d�J��\r��|.s~\nvcj7���X�WW�h�[��c�Gݔ�E�nS���i�����������~�.e�+��s��K~!dQ���	_I����>�\0�-�y��\n���{���� jO\0�e\Z�q�h�*~�{�A:�F�VU|gj�ܮإ��hT����̪�8�½�`, (s𑣉BR�d����~\ZF��/��S�r��5��q���-�Nxq�LEG������i�Q��\Zbok#�\r,��/��Q�w;��;��B�\0������d�ܠ�q]��,c�b9�x�7�c�X檹�</�`d���n�(�\0�i���S��\'�3�<�֝F8�\0+�����\"k�^�^-|���i�\r�:�+�:�՟×U���̗7+�0ɝ�߂����G�µq����9�I]�n�U<K@��:�స3�U�����t�z~\nύ����Z�n��o����3�{˺�ܓ�;�jS]��4�z�_|��^��>1R������t�\n��֦����|���/\"���u��]Q��D�I5�����KL88���[N��x�\n��X�`��n��x��kP�*�7��2�e����)�1N��܏�m������,�6��i��>\'�:�%�凷`��6������`V��ͬ�x�!}�޹,���U�R��^�Y�tN�a�@mv_eIa>U\0�%fS1OKt:��~��<3�����7C�;��ʆ���R��ݛ�T��hu�{�.���<̥N�]\'3\\&$o�bG*_�������:���e�HE! ��:v�.�A��œ�1ϣH9�\r���]��\\@���\'�@�\n�i!\"y٫�g\r���A�AiU�b݆�\rG��NP�l6Z		�*��˛.h;���y���Kc6AS#c�n��B��>���3�����a��8,�]5Px�>�c�=��9���y�=mjXy4�Q�P:	0#lL��`qOh',1);
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

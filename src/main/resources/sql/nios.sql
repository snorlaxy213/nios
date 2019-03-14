/*
Navicat MySQL Data Transfer

Source Server         : cmis
Source Server Version : 50640
Source Host           : 192.168.101.153:3306
Source Database       : nios

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-03-14 18:05:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `appointment_id` varchar(12) NOT NULL,
  `appointment_time` datetime NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `duration` varchar(12) NOT NULL,
  `user_id` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_clinic` int(11) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `patient_key` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  KEY `FKdpwbysnn3ohblfovgj0tl21qx` (`user_id`) USING BTREE,
  KEY `FK31928uuiqwkw36jrkvs8fdatq` (`patient_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('APP0001', '2019-03-10 18:52:43', '耳朵', '30', 'USR0001', '1', '1', '2019-03-12 19:12:06', '1', '1', '2019-03-12 19:12:15', '2019-03-12 19:12:20', null);
INSERT INTO `appointment` VALUES ('APP0002', '2019-03-11 15:29:59', 'test', '30', 'USR0001', '1', '1', '2019-03-11 15:29:59', '1', '1', '2019-03-11 15:29:59', '2019-03-11 15:29:59', null);
INSERT INTO `appointment` VALUES ('APP0003', '2019-03-12 19:00:29', 'test', '30', 'USR0001', '1', '1', '2019-03-12 19:00:30', '1', '1', '2019-03-12 19:00:30', '2019-03-12 19:00:30', null);
INSERT INTO `appointment` VALUES ('APP0004', '2018-07-02 00:00:00', 'test', '30', 'USR0001', '1', '1', '2019-03-13 16:39:12', '1', '1', '2019-03-13 16:39:12', '2019-03-13 16:39:12', null);

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic` (
  `clinic_code` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_clinic` int(11) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`clinic_code`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of clinic
-- ----------------------------

-- ----------------------------
-- Table structure for diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis` (
  `diagnosis_id` varchar(12) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `appointment_id` varchar(12) DEFAULT NULL,
  `patient_key` varchar(12) DEFAULT NULL,
  `user_id` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`diagnosis_id`),
  KEY `FKe86rgx4u8dh6ksjt3cno14qp4` (`appointment_id`),
  KEY `FK255u0041juhrlf3gqons94ofj` (`patient_key`),
  KEY `FKr92o2857xqoqst84kgpf6sy3f` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diagnosis
-- ----------------------------
INSERT INTO `diagnosis` VALUES ('DIA0001', 'test', null, 'PAT0001', 'USR0001', '1', '1', '2019-03-13 17:08:35', '1', '1', '2019-03-13 17:08:35', '2019-03-13 17:08:35');
INSERT INTO `diagnosis` VALUES ('DIA0002', 'test', 'APP0001', 'PAT0001', 'USR0001', '1', '1', '2019-03-13 17:47:10', '1', '1', '2019-03-13 17:47:10', '2019-03-13 17:47:56');

-- ----------------------------
-- Table structure for drug_profile
-- ----------------------------
DROP TABLE IF EXISTS `drug_profile`;
CREATE TABLE `drug_profile` (
  `drug_id` varchar(12) NOT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `drug_name` varchar(50) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `drug_type` varchar(12) DEFAULT NULL,
  `unit` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drug_profile
-- ----------------------------
INSERT INTO `drug_profile` VALUES ('CCM0001', '1', '1', '2019-03-13 15:38:28', '1', '1', '2019-03-13 15:59:17', 'Test', '八仙草', 'A', '2019-03-13 15:59:17', '配方颗粒', 'Gra');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `patient_key` varchar(12) NOT NULL,
  `age` varchar(3) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`patient_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('PAT0001', '20', '1', '1', '2019-03-13 16:22:57', '1', '1', '2019-03-13 16:22:57', '1658895307@qq.com', 'M', '1658895307', 'chan', '2019-03-13 16:22:57');
INSERT INTO `patient` VALUES ('PAT0002', '20', '1', '1', '2019-03-14 18:03:59', '1', '1', '2019-03-14 18:03:59', '1658895307@qq.com', 'M', '1658895307', 'chan', '2019-03-14 18:03:59');

-- ----------------------------
-- Table structure for table_identity
-- ----------------------------
DROP TABLE IF EXISTS `table_identity`;
CREATE TABLE `table_identity` (
  `table_name` varchar(50) NOT NULL,
  `identity_prefix` varchar(3) DEFAULT NULL,
  `key_length` int(11) NOT NULL,
  `next_identity` bigint(20) NOT NULL,
  PRIMARY KEY (`table_name`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of table_identity
-- ----------------------------
INSERT INTO `table_identity` VALUES ('USER', 'USR', '4', '1');
INSERT INTO `table_identity` VALUES ('USER_ROLE', 'ROL', '4', '1');
INSERT INTO `table_identity` VALUES ('APPOINTMENT', 'APP', '4', '1');
INSERT INTO `table_identity` VALUES ('DIAGNOSIS', 'DIA', '4', '1');
INSERT INTO `table_identity` VALUES ('DRUG', 'CCM', '4', '2');
INSERT INTO `table_identity` VALUES ('PATIENT', 'PAT', '4', '3');

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` varchar(12) NOT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_clinic` int(11) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `password_hash` varchar(200) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', 'admin', '1', '2019-03-08 15:53:31', 'admin', '1', '2019-03-08 15:53:38', '1658895307@qq.com', '16620375709', 'admin', '5f10a737f989ccb8fe5951ecf68a4e2b', '2019-03-08 15:54:10');
INSERT INTO `user_profile` VALUES ('USR0002', 'user', '1', '2019-03-08 17:27:49', '1', '1', '2019-03-10 18:29:13', '1658895307@qq.com', '1658895307', 'user', '123456', '2019-03-10 18:29:13');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` varchar(12) NOT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_clinic` int(11) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `user_role_name` varchar(30) NOT NULL,
  `status` varchar(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ROL0001', 'admin', '1', '2019-03-08 15:56:23', 'admin', '1', '2019-03-08 15:56:30', 'admin', 'A', '2019-03-08 15:56:36');
INSERT INTO `user_role` VALUES ('ROL0002', 'user', '1', '2019-03-08 17:28:56', 'user', '1', '2019-03-08 17:29:01', 'user', 'A', '2019-03-08 17:29:23');

-- ----------------------------
-- Table structure for user_user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_user_role`;
CREATE TABLE `user_user_role` (
  `user_id` varchar(12) NOT NULL,
  `user_role_id` varchar(12) NOT NULL,
  KEY `FKak8topdb5d9ms6ml76er9vd3l` (`user_role_id`) USING BTREE,
  KEY `FKrgnmroub6nysks1400e0scmev` (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_user_role
-- ----------------------------
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0001');

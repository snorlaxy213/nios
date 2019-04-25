/*
Navicat MySQL Data Transfer

Source Server         : cmis
Source Server Version : 50640
Source Host           : 192.168.101.153:3306
Source Database       : nios

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-04-25 18:50:37
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
  `sequence` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` varchar(12) DEFAULT NULL,
  `patient_key` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  KEY `FKdpwbysnn3ohblfovgj0tl21qx` (`user_id`) USING BTREE,
  KEY `FK31928uuiqwkw36jrkvs8fdatq` (`patient_key`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('APP0012', '2019-04-13 08:00:00', 'TEST', '1', 'N', 'USR0001', 'PAT0001', 'USR0001', '2019-04-13 20:49:45', 'USR0001', '2019-04-13 20:49:45', '2019-04-14 14:47:03');
INSERT INTO `appointment` VALUES ('APP0013', '2019-04-13 08:00:00', 'TEST', '2', 'N', 'USR0001', 'PAT0001', 'USR0001', '2019-04-13 20:52:03', 'USR0001', '2019-04-13 20:52:03', '2019-04-14 14:45:58');
INSERT INTO `appointment` VALUES ('APP0014', '2019-04-19 15:05:00', 'SA', '1', 'N', 'USR0001', 'PAT0003', 'USR0001', '2019-04-14 14:47:48', 'USR0001', '2019-04-14 14:47:48', '2019-04-19 15:10:00');
INSERT INTO `appointment` VALUES ('APP0015', '2019-04-25 10:05:00', '123', '1', 'N', 'USR0002', 'PAT0008', 'USR0001', '2019-04-25 18:48:43', 'USR0001', '2019-04-25 18:48:43', '2019-04-25 18:49:00');

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic` (
  `clinic_code` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(60) DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
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
  `status` varchar(5) DEFAULT NULL,
  `user_id` varchar(12) DEFAULT NULL,
  `patient_key` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`diagnosis_id`) USING BTREE,
  KEY `FK255u0041juhrlf3gqons94ofj` (`patient_key`) USING BTREE,
  KEY `FKr92o2857xqoqst84kgpf6sy3f` (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of diagnosis
-- ----------------------------
INSERT INTO `diagnosis` VALUES ('DIA0001', 'test', 'Y', 'USR0001', 'PAT0001', '1', '2019-03-13 17:08:35', '1', '2019-03-13 17:08:35', '2019-04-24 17:49:51');
INSERT INTO `diagnosis` VALUES ('DIA0002', 'test', 'Y', 'USR0001', 'PAT0001', '1', '2019-03-13 17:47:10', '1', '2019-03-13 17:47:10', '2019-04-24 17:49:52');
INSERT INTO `diagnosis` VALUES ('DIA0003', 'sad', 'Y', 'USR0001', 'PAT0002', '1', '2019-03-19 17:10:02', '1', '2019-03-19 17:10:02', '2019-04-24 17:49:52');
INSERT INTO `diagnosis` VALUES ('DIA0004', 'sad', 'Y', 'USR0001', 'PAT0002', '1', '2019-03-19 17:16:05', '1', '2019-03-19 17:16:05', '2019-04-24 17:49:53');
INSERT INTO `diagnosis` VALUES ('DIA0005', 'test', 'Y', 'sadaass', 'PAT0002', '1', '2019-03-21 17:27:14', '1', '2019-03-21 17:27:14', '2019-04-24 17:49:53');
INSERT INTO `diagnosis` VALUES ('DIA0006', 'test', 'Y', 'sadaass', 'PAT0002', '1', '2019-03-21 17:27:39', '1', '2019-03-21 17:27:39', '2019-04-24 17:49:54');
INSERT INTO `diagnosis` VALUES ('DIA0007', 'test', 'Y', 'USR0003', 'PAT0001', '1', '2019-03-21 17:35:05', '1', '2019-03-21 17:35:05', '2019-04-24 17:49:54');
INSERT INTO `diagnosis` VALUES ('DIA0008', 'test', 'Y', 'USR0001', 'PAT0002', '1', '2019-03-21 17:35:29', '1', '2019-03-21 17:35:29', '2019-04-24 17:49:55');
INSERT INTO `diagnosis` VALUES ('DIA0009', 'test', 'Y', 'sadaass', 'PAT0002', '1', '2019-03-21 17:43:54', '1', '2019-03-21 17:43:54', '2019-04-24 17:49:55');
INSERT INTO `diagnosis` VALUES ('DIA0010', 'test', 'Y', 'USR0003', 'PAT0001', '1', '2019-03-21 17:45:06', '1', '2019-03-21 17:45:06', '2019-04-24 17:49:56');
INSERT INTO `diagnosis` VALUES ('DIA0011', 'test', 'Y', 'USR0003', 'PAT0001', '1', '2019-03-21 17:45:30', '1', '2019-03-21 17:45:30', '2019-04-24 17:49:56');
INSERT INTO `diagnosis` VALUES ('DIA0012', 'test', 'Y', 'USR0003', 'PAT0001', 'USR0001', '2019-04-11 11:33:55', 'USR0001', '2019-04-11 11:33:55', '2019-04-24 17:49:57');
INSERT INTO `diagnosis` VALUES ('DIA0013', 'sad', 'Y', 'USR0001', 'PAT0001', 'USR0001', '2019-04-14 14:43:57', 'USR0001', '2019-04-14 14:43:57', '2019-04-24 17:49:57');
INSERT INTO `diagnosis` VALUES ('DIA0014', 'jjjj', 'Y', 'USR0001', 'PAT0001', 'USR0001', '2019-04-14 14:45:58', 'USR0001', '2019-04-14 14:45:58', '2019-04-24 17:49:58');
INSERT INTO `diagnosis` VALUES ('DIA0015', 'ssss', 'Y', 'USR0001', 'PAT0001', 'USR0001', '2019-04-14 14:47:03', 'USR0001', '2019-04-14 14:47:03', '2019-04-24 17:49:59');
INSERT INTO `diagnosis` VALUES ('DIA0016', 'SAD', 'N', 'USR0001', 'PAT0003', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', '2019-04-24 17:50:02');
INSERT INTO `diagnosis` VALUES ('DIA0017', 'test', 'Y', 'USR0002', 'PAT0008', 'USR0001', '2019-04-25 18:49:09', 'USR0001', '2019-04-25 18:49:09', '2019-04-25 18:49:09');

-- ----------------------------
-- Table structure for drug_profile
-- ----------------------------
DROP TABLE IF EXISTS `drug_profile`;
CREATE TABLE `drug_profile` (
  `drug_id` varchar(12) NOT NULL,
  `drug_name` varchar(50) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `drug_type` varchar(12) DEFAULT NULL,
  `default_quantity` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `unit` varchar(12) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`drug_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of drug_profile
-- ----------------------------
INSERT INTO `drug_profile` VALUES ('CCM0001', '八仙草', 'Y', 'Sheet', '6', '1004', '10.01', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:38', '2019-04-24 16:59:38');
INSERT INTO `drug_profile` VALUES ('CCM0002', '鬼针草', 'Y', 'Sheet', '6', '104', '10.00', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:39', '2019-04-24 16:59:39');
INSERT INTO `drug_profile` VALUES ('CCM0003', '狗尾巴草', 'Y', 'Sheet', '6', '10', '20.00', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:39', '2019-04-24 17:00:42');
INSERT INTO `drug_profile` VALUES ('CCM0004', '节节草', 'Y', 'Sheet', '6', '10', '15.00', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:39', '2019-04-24 17:00:44');
INSERT INTO `drug_profile` VALUES ('CCM0005', '鱼腥草', 'Y', 'Sheet', '6', '10', '11.00', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:39', '2019-04-24 17:00:46');
INSERT INTO `drug_profile` VALUES ('CCM0006', '金银花', 'Y', 'Sheet', '6', '10', '30.00', 'Gra', 'Test', null, '1', '2019-03-13 15:38:28', 'USR0001', '2019-04-24 16:59:39', '2019-04-24 17:00:48');
INSERT INTO `drug_profile` VALUES ('CCM0007', '茵陈草', 'Y', 'Granule', '6', '0', '100.12', 'Jra', 'test', null, 'USR0001', '2019-04-18 17:20:57', 'USR0001', '2019-04-18 17:22:14', '2019-04-18 17:22:14');

-- ----------------------------
-- Table structure for drug_restock
-- ----------------------------
DROP TABLE IF EXISTS `drug_restock`;
CREATE TABLE `drug_restock` (
  `restock_id` varchar(12) NOT NULL,
  `amount` int(11) NOT NULL,
  `expiry` datetime NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `drug_id` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`restock_id`) USING BTREE,
  KEY `FKncsijjda2w3nyn8y2hihgvvxf` (`drug_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of drug_restock
-- ----------------------------
INSERT INTO `drug_restock` VALUES ('DST0001', '1000', '2019-04-19 08:00:00', '100', 'CCM0001', 'USR0001', '2019-04-18 16:52:03', 'USR0001', '2019-04-18 16:52:03', '2019-04-18 16:52:10');
INSERT INTO `drug_restock` VALUES ('DST0002', '100', '2019-04-18 08:00:00', '100', 'CCM0002', 'USR0001', '2019-04-18 17:02:57', 'USR0001', '2019-04-18 17:02:57', '2019-04-18 17:02:57');

-- ----------------------------
-- Table structure for drug_stock
-- ----------------------------
DROP TABLE IF EXISTS `drug_stock`;
CREATE TABLE `drug_stock` (
  `stock_id` varchar(12) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `drug_id` varchar(12) DEFAULT NULL,
  `diagnosis_id` varchar(12) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`stock_id`) USING BTREE,
  KEY `FKh1ybfmhr5s6tuibmoxba5c9hw` (`diagnosis_id`) USING BTREE,
  KEY `FKggmpsw78rbroffb2trx88qcws` (`drug_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of drug_stock
-- ----------------------------
INSERT INTO `drug_stock` VALUES ('DRS0001', '6', 'CCM0001', 'DIA0012', 'USR0001', '2019-04-14 14:39:58', 'USR0001', '2019-04-14 14:39:58', null);
INSERT INTO `drug_stock` VALUES ('DRS0002', '6', 'CCM0001', 'DIA0012', 'USR0001', '2019-04-14 14:40:37', 'USR0001', '2019-04-14 14:40:37', null);
INSERT INTO `drug_stock` VALUES ('DRS0003', '6', 'CCM0001', 'DIA0012', 'USR0001', '2019-04-14 14:42:06', 'USR0001', '2019-04-14 14:42:06', null);
INSERT INTO `drug_stock` VALUES ('DRS0004', '6', 'CCM0001', 'DIA0014', 'USR0001', '2019-04-14 14:43:57', 'USR0001', '2019-04-14 14:45:58', null);
INSERT INTO `drug_stock` VALUES ('DRS0005', '6', 'CCM0006', 'DIA0014', 'USR0001', '2019-04-14 14:43:57', 'USR0001', '2019-04-14 14:45:58', null);
INSERT INTO `drug_stock` VALUES ('DRS0006', '6', 'CCM0003', 'DIA0015', 'USR0001', '2019-04-14 14:47:03', 'USR0001', '2019-04-14 14:47:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0007', '6', 'CCM0001', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0008', '6', 'CCM0002', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0009', '6', 'CCM0003', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0010', '6', 'CCM0004', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0011', '6', 'CCM0005', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0012', '6', 'CCM0006', 'DIA0016', 'USR0001', '2019-04-14 14:48:03', 'USR0001', '2019-04-14 14:48:03', null);
INSERT INTO `drug_stock` VALUES ('DRS0013', '6', 'CCM0001', 'DIA0017', 'USR0001', '2019-04-25 18:49:09', 'USR0001', '2019-04-25 18:49:09', '2019-04-25 18:49:09');
INSERT INTO `drug_stock` VALUES ('DRS0014', '6', 'CCM0002', 'DIA0017', 'USR0001', '2019-04-25 18:49:09', 'USR0001', '2019-04-25 18:49:09', '2019-04-25 18:49:09');

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter` (
  `parameter` varchar(100) NOT NULL,
  `value` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`parameter`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES ('AppointmentSequence', '0', '2019-03-21 15:01:29');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `patient_key` varchar(12) NOT NULL,
  `p_age` varchar(3) DEFAULT NULL,
  `p_email` varchar(20) DEFAULT NULL,
  `p_gender` varchar(2) DEFAULT NULL,
  `p_mobile` varchar(20) DEFAULT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `p_status` varchar(1) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) NOT NULL,
  `update_dtm` datetime NOT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`patient_key`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('PAT0001', '21', '1658895307@qq.com', 'F', '1658895307', '李梅熬', 'Y', '1', '2019-03-13 16:22:57', 'USR0001', '2019-04-25 17:18:12', '2019-04-25 17:18:12');
INSERT INTO `patient` VALUES ('PAT0002', '23', '1658895307@qq.com', 'M', '1658895307', '深ლ(′◉❥◉｀ლ)', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:18:27', '2019-04-25 17:18:27');
INSERT INTO `patient` VALUES ('PAT0003', '20', '1658895307@qq.com', 'F', '1658895307', '丰盛', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:18:37', '2019-04-25 17:18:37');
INSERT INTO `patient` VALUES ('PAT0004', '56', '1658895307@qq.com', 'F', '1658895307', '听人', 'N', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:24:32', '2019-04-25 17:24:32');
INSERT INTO `patient` VALUES ('PAT0005', '12', '1658895307@qq.com', 'M', '1658895307', '二娃', 'N', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:24:49', '2019-04-25 17:24:49');
INSERT INTO `patient` VALUES ('PAT0006', '26', '1658895307@qq.com', 'F', '1658895307', '囖为', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:20:58', '2019-04-25 17:20:58');
INSERT INTO `patient` VALUES ('PAT0007', '20', '1658895307@qq.com', 'F', '1658895307', '额我是', 'N', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:21:14', '2019-04-25 17:21:14');
INSERT INTO `patient` VALUES ('PAT0008', '20', '1658895307@qq.com', 'F', '1658895307', '食发鬼', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:23:57', '2019-04-25 17:23:57');
INSERT INTO `patient` VALUES ('PAT0009', '20', '1658895307@qq.com', 'F', '1658895307', '二维', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:24:25', '2019-04-25 17:24:25');
INSERT INTO `patient` VALUES ('PAT0010', '20', '1658895307@qq.com', 'M', '1658895307', '法定', 'Y', '1', '2019-03-14 18:03:59', 'USR0001', '2019-04-25 17:24:11', '2019-04-25 17:24:11');

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
INSERT INTO `table_identity` VALUES ('USER', 'USR', '4', '11');
INSERT INTO `table_identity` VALUES ('USER_ROLE', 'ROL', '4', '6');
INSERT INTO `table_identity` VALUES ('APPOINTMENT', 'APP', '4', '16');
INSERT INTO `table_identity` VALUES ('DIAGNOSIS', 'DIA', '4', '18');
INSERT INTO `table_identity` VALUES ('DRUG', 'CCM', '4', '8');
INSERT INTO `table_identity` VALUES ('PATIENT', 'PAT', '4', '11');
INSERT INTO `table_identity` VALUES ('DRUGSTOCK', 'DRS', '4', '15');
INSERT INTO `table_identity` VALUES ('DRUGRESTOCK', 'DST', '4', '3');

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `password_hash` varchar(200) NOT NULL,
  `office` varchar(200) NOT NULL,
  `current_num` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', '1658895307@qq.com', '16620375709', '管理员', '9fc3dae6ad418ff1f14b34abba261f6d', '鼻科', '-1', '10', 'admin', '2019-03-08 15:53:31', 'USR0001', '2019-04-25 16:14:20', '2019-04-25 16:14:20');
INSERT INTO `user_profile` VALUES ('USR0002', '1658895307@qq.com', '1658895307', '骨科医师', '123456', '骨科', '-1', '10', 'user', '2019-03-08 17:27:49', 'USR0001', '2019-04-25 16:14:52', '2019-04-25 18:49:09');
INSERT INTO `user_profile` VALUES ('USR0004', '1658895307@qq.com', '16620375709', '诊所工作人员', '835f409e9fac9d721567bee16ac9e387', '鼻科', '0', '10', '1', '2019-03-17 18:09:14', 'USR0001', '2019-04-25 16:15:32', '2019-04-25 16:15:32');
INSERT INTO `user_profile` VALUES ('NIOS_TEST', 'juleschen@asl.com.cn', '16620375709', '测试', 'a5d6f2491e17f00f8b0724d3d44ab8e2', '骨科', '0', '10', 'USR0001', '2019-04-25 16:39:38', 'USR0001', '2019-04-25 16:40:35', '2019-04-25 16:40:35');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` varchar(12) NOT NULL,
  `user_role_name` varchar(30) NOT NULL,
  `status` varchar(1) NOT NULL,
  `create_by` varchar(100) NOT NULL,
  `create_dtm` datetime NOT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_dtm` datetime DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ROL0001', '管理员', 'Y', 'USR0001', '2019-03-08 15:56:23', 'USR0001', '2019-04-18 17:45:36', '2019-04-25 14:09:34');
INSERT INTO `user_role` VALUES ('ROL0002', '医师', 'N', 'USR0001', '2019-03-20 15:39:41', 'USR0001', '2019-04-25 16:46:03', '2019-04-25 16:46:03');
INSERT INTO `user_role` VALUES ('ROL0003', '诊所工作人员', 'Y', 'USR0001', '2019-03-20 15:50:47', 'USR0001', '2019-04-25 16:42:24', '2019-04-25 16:42:24');

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
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR0004', 'ROL0003');
INSERT INTO `user_user_role` VALUES ('NIOS_TEST', 'ROL0001');

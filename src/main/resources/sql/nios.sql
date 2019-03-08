/*
Navicat MySQL Data Transfer

Source Server         : cmis
Source Server Version : 50640
Source Host           : 192.168.101.153:3306
Source Database       : nios

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-03-08 18:09:22
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`clinic_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clinic
-- ----------------------------

-- ----------------------------
-- Table structure for table_identity
-- ----------------------------
DROP TABLE IF EXISTS `table_identity`;
CREATE TABLE `table_identity` (
  `table_name` varchar(50) NOT NULL,
  `identity_prefix` varchar(3) DEFAULT NULL,
  `key_length` int(11) NOT NULL,
  `next_identity` bigint(20) NOT NULL,
  PRIMARY KEY (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_identity
-- ----------------------------

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
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', 'admin', '1', '2019-03-08 15:53:31', 'admin', '1', '2019-03-08 15:53:38', '1658895307@qq.com', '16620375709', 'admin', '5f10a737f989ccb8fe5951ecf68a4e2b', '2019-03-08 15:54:10');
INSERT INTO `user_profile` VALUES ('USR0002', 'user', '1', '2019-03-08 17:27:49', 'user', '1', '2019-03-08 17:27:55', '1658895307', '1658895307@qq.com', 'user', '123456', '2019-03-08 17:28:20');

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
  PRIMARY KEY (`user_role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  KEY `FKak8topdb5d9ms6ml76er9vd3l` (`user_role_id`),
  KEY `FKrgnmroub6nysks1400e0scmev` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_user_role
-- ----------------------------
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0002');

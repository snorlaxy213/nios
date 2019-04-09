/*
 Navicat Premium Data Transfer

 Source Server         : vino
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : nios

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/04/2019 00:21:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `appointment_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appointment_time` datetime(0) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  INDEX `FKdpwbysnn3ohblfovgj0tl21qx`(`user_id`) USING BTREE,
  INDEX `FK31928uuiqwkw36jrkvs8fdatq`(`patient_key`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('APP0001', '2019-03-10 18:52:43', '耳朵', 0, 'N', 'USR0001', 'PAT0002', '1', '2019-03-12 19:12:06', '1', '2019-03-12 19:12:15', '2019-03-12 19:12:20');
INSERT INTO `appointment` VALUES ('APP0002', '2019-03-11 15:29:59', 'test', 0, 'N', 'USR0001', 'PAT0002', '1', '2019-03-11 15:29:59', '1', '2019-03-11 15:29:59', '2019-03-11 15:29:59');
INSERT INTO `appointment` VALUES ('APP0003', '2019-03-12 19:00:29', 'test', 0, 'N', 'USR0001', 'PAT0002', '1', '2019-03-12 19:00:30', '1', '2019-03-12 19:00:30', '2019-03-12 19:00:30');
INSERT INTO `appointment` VALUES ('APP0004', '2018-07-02 00:00:00', 'test', 0, 'N', 'USR0001', 'PAT0002', '1', '2019-03-13 16:39:12', '1', '2019-03-13 16:39:12', '2019-03-13 16:39:12');
INSERT INTO `appointment` VALUES ('APP0005', '2019-12-31 08:00:00', 'test', 1, 'N', 'USR0001', 'PAT0002', '1', '2019-03-17 17:53:33', '1', '2019-03-17 17:53:33', '2019-03-21 17:35:29');
INSERT INTO `appointment` VALUES ('APP0007', '2019-03-21 08:00:00', 'test', 1, 'N', 'USR0003', 'PAT0001', '1', '2019-03-21 16:48:17', '1', '2019-03-21 16:48:17', '2019-03-21 17:35:06');
INSERT INTO `appointment` VALUES ('APP0008', '2019-03-21 08:00:00', 'test', 0, 'N', 'sadaass', 'PAT0002', '1', '2019-03-21 16:49:36', '1', '2019-03-21 16:49:36', '2019-03-21 16:49:36');
INSERT INTO `appointment` VALUES ('APP0009', '2019-03-21 08:00:00', 'test', 1, 'N', 'sadaass', 'PAT0002', '1', '2019-03-21 17:40:50', '1', '2019-03-21 17:40:50', '2019-03-21 17:44:40');
INSERT INTO `appointment` VALUES ('APP0010', '2019-03-21 08:00:00', '123', 1, 'N', 'USR0003', 'PAT0001', '1', '2019-03-21 17:44:57', '1', '2019-03-21 17:44:57', '2019-03-21 17:45:06');
INSERT INTO `appointment` VALUES ('APP0011', '2019-03-21 08:00:00', 'test', 1, 'Y', 'USR0003', 'PAT0001', '1', '2019-03-21 17:45:21', '1', '2019-03-21 17:45:21', '2019-03-21 17:45:30');

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic`  (
  `clinic_code` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`clinic_code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis`  (
  `diagnosis_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`diagnosis_id`) USING BTREE,
  INDEX `FK255u0041juhrlf3gqons94ofj`(`patient_key`) USING BTREE,
  INDEX `FKr92o2857xqoqst84kgpf6sy3f`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diagnosis
-- ----------------------------
INSERT INTO `diagnosis` VALUES ('DIA0001', 'test', 'USR0001', 'PAT0001', '1', '2019-03-13 17:08:35', '1', '2019-03-13 17:08:35', '2019-03-13 17:08:35');
INSERT INTO `diagnosis` VALUES ('DIA0002', 'test', 'USR0001', 'PAT0001', '1', '2019-03-13 17:47:10', '1', '2019-03-13 17:47:10', '2019-03-13 17:47:56');
INSERT INTO `diagnosis` VALUES ('DIA0003', 'sad', 'USR0001', 'PAT0002', '1', '2019-03-19 17:10:02', '1', '2019-03-19 17:10:02', '2019-03-19 17:10:02');
INSERT INTO `diagnosis` VALUES ('DIA0004', 'sad', 'USR0001', 'PAT0002', '1', '2019-03-19 17:16:05', '1', '2019-03-19 17:16:05', '2019-03-19 17:16:05');
INSERT INTO `diagnosis` VALUES ('DIA0005', 'test', 'sadaass', 'PAT0002', '1', '2019-03-21 17:27:14', '1', '2019-03-21 17:27:14', '2019-03-21 17:27:14');
INSERT INTO `diagnosis` VALUES ('DIA0006', 'test', 'sadaass', 'PAT0002', '1', '2019-03-21 17:27:39', '1', '2019-03-21 17:27:39', '2019-03-21 17:27:39');
INSERT INTO `diagnosis` VALUES ('DIA0007', 'test', 'USR0003', 'PAT0001', '1', '2019-03-21 17:35:05', '1', '2019-03-21 17:35:05', '2019-03-21 17:35:05');
INSERT INTO `diagnosis` VALUES ('DIA0008', 'test', 'USR0001', 'PAT0002', '1', '2019-03-21 17:35:29', '1', '2019-03-21 17:35:29', '2019-03-21 17:35:29');
INSERT INTO `diagnosis` VALUES ('DIA0009', 'test', 'sadaass', 'PAT0002', '1', '2019-03-21 17:43:54', '1', '2019-03-21 17:43:54', '2019-03-21 17:43:54');
INSERT INTO `diagnosis` VALUES ('DIA0010', 'test', 'USR0003', 'PAT0001', '1', '2019-03-21 17:45:06', '1', '2019-03-21 17:45:06', '2019-03-21 17:45:06');
INSERT INTO `diagnosis` VALUES ('DIA0011', 'test', 'USR0003', 'PAT0001', '1', '2019-03-21 17:45:30', '1', '2019-03-21 17:45:30', '2019-03-21 17:45:30');

-- ----------------------------
-- Table structure for drug_profile
-- ----------------------------
DROP TABLE IF EXISTS `drug_profile`;
CREATE TABLE `drug_profile`  (
  `drug_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`drug_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_profile
-- ----------------------------
INSERT INTO `drug_profile` VALUES ('CCM0001', 'Test', '八仙草', 'Y', 'Sheet', 'Gra', '1', '2019-03-13 15:38:28', '1', '2019-03-18 21:19:08', '2019-03-18 21:19:08');

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `parameter` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` int(11) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`parameter`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES ('AppointmentSequence', 0, '2019-03-21 15:01:29');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_age` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`patient_key`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('PAT0001', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-13 16:22:57', '1', '2019-03-13 16:22:57', '2019-03-13 16:22:57');
INSERT INTO `patient` VALUES ('PAT0002', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0003', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0004', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0005', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0006', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0007', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0008', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0009', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');
INSERT INTO `patient` VALUES ('PAT0010', '20', '1658895307@qq.com', 'M', '1658895307', 'chan', NULL, '1', '2019-03-14 18:03:59', '1', '2019-03-14 18:03:59', '2019-03-14 18:03:59');

-- ----------------------------
-- Table structure for table_identity
-- ----------------------------
DROP TABLE IF EXISTS `table_identity`;
CREATE TABLE `table_identity`  (
  `table_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identity_prefix` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_length` int(11) NOT NULL,
  `next_identity` bigint(20) NOT NULL,
  PRIMARY KEY (`table_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_identity
-- ----------------------------
INSERT INTO `table_identity` VALUES ('USER', 'USR', 4, 5);
INSERT INTO `table_identity` VALUES ('USER_ROLE', 'ROL', 4, 5);
INSERT INTO `table_identity` VALUES ('APPOINTMENT', 'APP', 4, 12);
INSERT INTO `table_identity` VALUES ('DIAGNOSIS', 'DIA', 4, 12);
INSERT INTO `table_identity` VALUES ('DRUG', 'CCM', 4, 5);
INSERT INTO `table_identity` VALUES ('PATIENT', 'PAT', 4, 11);

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password_hash` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `office` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `current_num` int(11) NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', '1658895307@qq.com', '16620375709', 'admin', '9fc3dae6ad418ff1f14b34abba261f6d', '骨科', 0, 10, 'admin', 1, '2019-03-08 15:53:31', '1', '2019-03-21 16:21:55', '2019-03-21 16:21:55');
INSERT INTO `user_profile` VALUES ('USR0002', '1658895307@qq.com', '1658895307', 'user', '123456', '骨科', 0, 10, 'user', 1, '2019-03-08 17:27:49', '1', '2019-03-21 16:24:08', '2019-03-21 16:24:08');
INSERT INTO `user_profile` VALUES ('USR0003', '1658895307@qq.com', '16620375709', 'happy', '835f409e9fac9d721567bee16ac9e387', '咽喉科', 0, 10, '1', 1, '2019-03-17 18:09:14', '1', '2019-03-21 16:22:36', '2019-03-21 17:45:30');
INSERT INTO `user_profile` VALUES ('USR0004', '16620375709@qq.com', '16620375709', 'lunwen', '3c36b47cb74566d95d94b94c4e19bd16', '骨科', 0, 10, '1', 1, '2019-04-02 00:22:10', '1', '2019-04-02 00:22:10', '2019-04-02 00:23:09');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ROL0001', 'admin', 'A', 'admin', '2019-03-08 15:56:23', 'admin', '2019-03-08 15:56:30', '2019-03-08 15:56:36');
INSERT INTO `user_role` VALUES ('ROL0002', 'user', 'Y', '1', '2019-03-20 15:39:41', '1', '2019-03-20 15:39:41', '2019-03-20 15:39:41');
INSERT INTO `user_role` VALUES ('ROL0003', 'user', 'Y', '1', '2019-03-20 15:50:47', '1', '2019-03-20 15:50:47', '2019-03-20 15:50:47');
INSERT INTO `user_role` VALUES ('ROL0004', 'test', 'Y', '1', '2019-03-20 15:52:51', '1', '2019-03-20 15:52:51', '2019-03-20 15:52:51');

-- ----------------------------
-- Table structure for user_user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_user_role`;
CREATE TABLE `user_user_role`  (
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `FKak8topdb5d9ms6ml76er9vd3l`(`user_role_id`) USING BTREE,
  INDEX `FKrgnmroub6nysks1400e0scmev`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user_role
-- ----------------------------
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0003', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR000', 'ROL0002');

SET FOREIGN_KEY_CHECKS = 1;

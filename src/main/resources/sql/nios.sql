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

 Date: 17/03/2019 18:14:52
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
  `duration` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`appointment_id`) USING BTREE,
  INDEX `FKdpwbysnn3ohblfovgj0tl21qx`(`user_id`) USING BTREE,
  INDEX `FK31928uuiqwkw36jrkvs8fdatq`(`patient_key`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('APP0001', '2019-03-10 18:52:43', '耳朵', '30', 'USR0001', '1', 1, '2019-03-12 19:12:06', '1', 1, '2019-03-12 19:12:15', '2019-03-12 19:12:20', NULL);
INSERT INTO `appointment` VALUES ('APP0002', '2019-03-11 15:29:59', 'test', '30', 'USR0001', '1', 1, '2019-03-11 15:29:59', '1', 1, '2019-03-11 15:29:59', '2019-03-11 15:29:59', NULL);
INSERT INTO `appointment` VALUES ('APP0003', '2019-03-12 19:00:29', 'test', '30', 'USR0001', '1', 1, '2019-03-12 19:00:30', '1', 1, '2019-03-12 19:00:30', '2019-03-12 19:00:30', NULL);
INSERT INTO `appointment` VALUES ('APP0004', '2018-07-02 00:00:00', 'test', '30', 'USR0001', '1', 1, '2019-03-13 16:39:12', '1', 1, '2019-03-13 16:39:12', '2019-03-13 16:39:12', NULL);
INSERT INTO `appointment` VALUES ('APP0005', '2019-12-31 08:00:00', 'test', '30', 'USR0001', '1', 1, '2019-03-17 17:53:33', '1', 1, '2019-03-17 17:53:33', '2019-03-17 17:53:33', NULL);
INSERT INTO `appointment` VALUES ('APP0006', '2019-12-31 08:00:00', 'test', '30', 'USR0001', '1', 1, '2019-03-17 17:56:31', '1', 1, '2019-03-17 17:56:31', '2019-03-17 17:56:31', 'PAT0002');

-- ----------------------------
-- Table structure for clinic
-- ----------------------------
DROP TABLE IF EXISTS `clinic`;
CREATE TABLE `clinic`  (
  `clinic_code` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`clinic_code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis`  (
  `diagnosis_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appointment_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`diagnosis_id`) USING BTREE,
  INDEX `FKe86rgx4u8dh6ksjt3cno14qp4`(`appointment_id`) USING BTREE,
  INDEX `FK255u0041juhrlf3gqons94ofj`(`patient_key`) USING BTREE,
  INDEX `FKr92o2857xqoqst84kgpf6sy3f`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diagnosis
-- ----------------------------
INSERT INTO `diagnosis` VALUES ('DIA0001', 'test', NULL, 'PAT0001', 'USR0001', '1', 1, '2019-03-13 17:08:35', '1', 1, '2019-03-13 17:08:35', '2019-03-13 17:08:35');
INSERT INTO `diagnosis` VALUES ('DIA0002', 'test', 'APP0001', 'PAT0001', 'USR0001', '1', 1, '2019-03-13 17:47:10', '1', 1, '2019-03-13 17:47:10', '2019-03-13 17:47:56');

-- ----------------------------
-- Table structure for drug_profile
-- ----------------------------
DROP TABLE IF EXISTS `drug_profile`;
CREATE TABLE `drug_profile`  (
  `drug_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drug_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  `drug_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`drug_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug_profile
-- ----------------------------
INSERT INTO `drug_profile` VALUES ('CCM0001', '1', 1, '2019-03-13 15:38:28', '1', 1, '2019-03-13 15:59:17', 'Test', '八仙草', 'A', '2019-03-13 15:59:17', '配方颗粒', 'Gra');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `patient_key` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_clinic` int(11) NOT NULL,
  `update_dtm` datetime(0) NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`patient_key`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('PAT0001', '20', '1', 1, '2019-03-13 16:22:57', '1', 1, '2019-03-13 16:22:57', '1658895307@qq.com', 'M', '1658895307', 'chan', '2019-03-13 16:22:57', NULL);
INSERT INTO `patient` VALUES ('PAT0002', '20', '1', 1, '2019-03-14 18:03:59', '1', 1, '2019-03-14 18:03:59', '1658895307@qq.com', 'M', '1658895307', 'chan', '2019-03-14 18:03:59', NULL);

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
INSERT INTO `table_identity` VALUES ('USER', 'USR', 4, 1);
INSERT INTO `table_identity` VALUES ('USER_ROLE', 'ROL', 4, 1);
INSERT INTO `table_identity` VALUES ('APPOINTMENT', 'APP', 4, 7);
INSERT INTO `table_identity` VALUES ('DIAGNOSIS', 'DIA', 4, 1);
INSERT INTO `table_identity` VALUES ('DRUG', 'CCM', 4, 2);
INSERT INTO `table_identity` VALUES ('PATIENT', 'PAT', 4, 3);

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `user_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password_hash` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('USR0001', 'admin', 1, '2019-03-08 15:53:31', 'admin', 1, '2019-03-08 15:53:38', '1658895307@qq.com', '16620375709', 'admin', '5f10a737f989ccb8fe5951ecf68a4e2b', '2019-03-08 15:54:10');
INSERT INTO `user_profile` VALUES ('USR0002', 'user', 1, '2019-03-08 17:27:49', '1', 1, '2019-03-10 18:29:13', '1658895307@qq.com', '1658895307', 'user', '123456', '2019-03-10 18:29:13');
INSERT INTO `user_profile` VALUES ('USR0003', '1', 1, '2019-03-17 18:09:14', '1', 1, '2019-03-17 18:09:14', '1658895307@qq.com', '16620375709', 'happy', '835f409e9fac9d721567bee16ac9e387', '2019-03-17 18:09:14');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_clinic` int(11) NOT NULL,
  `create_dtm` datetime(0) NOT NULL,
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_clinic` int(11) NULL DEFAULT NULL,
  `update_dtm` datetime(0) NULL DEFAULT NULL,
  `user_role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ROL0001', 'admin', 1, '2019-03-08 15:56:23', 'admin', 1, '2019-03-08 15:56:30', 'admin', 'A', '2019-03-08 15:56:36');
INSERT INTO `user_role` VALUES ('ROL0002', 'user', 1, '2019-03-08 17:28:56', 'user', 1, '2019-03-08 17:29:01', 'user', 'A', '2019-03-08 17:29:23');

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
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0002');
INSERT INTO `user_user_role` VALUES ('USR0001', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0002', 'ROL0001');
INSERT INTO `user_user_role` VALUES ('USR0003', 'ROL0002');

SET FOREIGN_KEY_CHECKS = 1;

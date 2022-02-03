/*
 Navicat Premium Data Transfer

 Source Server         : 密码123456789
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : lyf_iron_man

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 03/02/2022 17:44:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand` (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` char(50) DEFAULT NULL COMMENT '品牌名',
  `logo` varchar(2000) DEFAULT NULL COMMENT '品牌logo地址',
  `descript` longtext COMMENT '介绍',
  `show_status` tinyint(4) DEFAULT NULL COMMENT '显示状态[0-不显示；1-显示]',
  `first_letter` char(1) DEFAULT NULL COMMENT '检索首字母',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌';

-- ----------------------------
-- Records of pms_brand
-- ----------------------------
BEGIN;
INSERT INTO `pms_brand` VALUES (1, '1234', 'http://www.baidu.com', '', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (2, '华为', NULL, '华为', NULL, NULL, NULL);
INSERT INTO `pms_brand` VALUES (3, '当当', 'http://www.baidu.com', '的订单', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (4, '1234', 'http://www.baidu.com', '', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (5, '1234', 'http://www.baidu.com', '2', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (6, '1234', 'http://www.baidu.com', '2', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (7, '1234', 'http://www.baidu.com', '2', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (8, '1234', 'http://www.baidu.com', '2', 1, 'w', 1);
INSERT INTO `pms_brand` VALUES (9, '1234', 'http://www.baidu.com', '', 1, 'w', 1);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `s_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, '小明', 'lily');
INSERT INTO `student` VALUES (2, '小红', 'lucy');
INSERT INTO `student` VALUES (3, '小刚', 'dada');
INSERT INTO `student` VALUES (4, '肛门', 'haha');
INSERT INTO `student` VALUES (6, '小红', 'haha');
COMMIT;

-- ----------------------------
-- Table structure for table_logic_test
-- ----------------------------
DROP TABLE IF EXISTS `table_logic_test`;
CREATE TABLE `table_logic_test` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `flag` int(1) DEFAULT NULL COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
BEGIN;
INSERT INTO `tbl_employee` VALUES (1, 'Tom', 'tom@atguigu.com', '1', 22);
INSERT INTO `tbl_employee` VALUES (2, 'Jerry', 'jerry@atguigu.com', '0', 25);
INSERT INTO `tbl_employee` VALUES (3, 'Black', 'black@atguigu.com', '1', 30);
INSERT INTO `tbl_employee` VALUES (4, 'White', 'white@atguigu.com', '0', 35);
INSERT INTO `tbl_employee` VALUES (5, '234', '123', '1', 1);
INSERT INTO `tbl_employee` VALUES (6, '234', '123', NULL, 1);
INSERT INTO `tbl_employee` VALUES (7, '234', '1', '1', 1);
INSERT INTO `tbl_employee` VALUES (8, '234', 'www.baidu.com', '1', 1);
INSERT INTO `tbl_employee` VALUES (9, '234', '123', '1', 1);
INSERT INTO `tbl_employee` VALUES (10, '234', '123', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for tbl_employee_ar
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee_ar`;
CREATE TABLE `tbl_employee_ar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tbl_employee_ar
-- ----------------------------
BEGIN;
INSERT INTO `tbl_employee_ar` VALUES (1, 'Tom', 'tom@atguigu.com', '1', 22);
INSERT INTO `tbl_employee_ar` VALUES (2, 'Jerry', 'jerry@atguigu.com', '0', 25);
INSERT INTO `tbl_employee_ar` VALUES (3, 'Black', 'black@atguigu.com', '1', 30);
INSERT INTO `tbl_employee_ar` VALUES (4, 'White', 'white@atguigu.com', '0', 35);
INSERT INTO `tbl_employee_ar` VALUES (5, '234', '123', '1', 1);
INSERT INTO `tbl_employee_ar` VALUES (6, '宋老师', 'sls@atguigu.com', NULL, 35);
INSERT INTO `tbl_employee_ar` VALUES (7, '宋老师', 'sls@atguigu.com', NULL, 35);
INSERT INTO `tbl_employee_ar` VALUES (8, '宋老师', 'sls@atguigu.com', NULL, 35);
COMMIT;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `logic_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;

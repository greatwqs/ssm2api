/*
 Navicat Premium Data Transfer

 Source Server         : develop
 Source Server Type    : MySQL
 Source Server Version : 50545
 Source Schema         : ssm2product

 Target Server Type    : MySQL
 Target Server Version : 50545
 File Encoding         : 65001

 Date: 03/03/2018 22:25:37
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_productlist
-- ----------------------------
DROP TABLE IF EXISTS `demo_productlist`;
CREATE TABLE `demo_productlist` (
  `ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL DEFAULT '',
  `CreateTime` datetime NOT NULL DEFAULT '2013-04-11 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of demo_productlist
-- ----------------------------
BEGIN;
INSERT INTO `demo_productlist` VALUES (1, '我是 greatwqs', '2018-03-03 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

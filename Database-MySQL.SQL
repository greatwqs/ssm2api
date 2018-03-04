
-------------------------------数据库 ssm2product-----------------------------
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
INSERT INTO `demo_productlist` VALUES (1, '中文商品名字测试', '2018-03-03 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-------------------------------数据库 ssm2user-----------------------------
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_userlist
-- ----------------------------
DROP TABLE IF EXISTS `demo_userlist`;
CREATE TABLE `demo_userlist` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) NOT NULL,
  `CreateTime` datetime NOT NULL DEFAULT '2013-04-11 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of demo_userlist
-- ----------------------------
BEGIN;
INSERT INTO `demo_userlist` VALUES (1, '中文名字测试', '2018-03-03 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
Navicat MySQL Data Transfer

Source Server         : localhost_root_root
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : paco

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2015-04-15 11:58:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pagedata`
-- ----------------------------
DROP TABLE IF EXISTS `pagedata`;
CREATE TABLE `pagedata` (
  `refrenceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `sourceUrl` varchar(250) DEFAULT NULL,
  `fileUrl` varchar(250) DEFAULT NULL,
  `title` varchar(250) DEFAULT NULL,
  `textContent` text,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pagedata
-- ----------------------------

/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50734
File Encoding         : 65001

Date: 2022-06-10 12:16:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for forum_page
-- ----------------------------
DROP TABLE IF EXISTS `forum_page`;
CREATE TABLE `forum_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `preface` varchar(255) DEFAULT NULL,
  `title` longtext,
  `topic` varchar(255) DEFAULT NULL,
  `content` longtext,
  `time` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for forum_reply
-- ----------------------------
DROP TABLE IF EXISTS `forum_reply`;
CREATE TABLE `forum_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `forumId` int(11) DEFAULT NULL,
  `content` longtext,
  `replyId` int(11) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for snippet
-- ----------------------------
DROP TABLE IF EXISTS `snippet`;
CREATE TABLE `snippet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `content` longtext,
  `note` longtext,
  `img` longtext,
  `type` int(11) unsigned zerofill DEFAULT '00000000000',
  PRIMARY KEY (`id`),
  KEY `fuser` (`userid`),
  CONSTRAINT `fuser` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t1_1622081810
-- ----------------------------
DROP TABLE IF EXISTS `t1_1622081810`;
CREATE TABLE `t1_1622081810` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_1` varchar(255) DEFAULT NULL,
  `c_2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `type` int(255) DEFAULT NULL,
  `grade` int(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `identity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone` (`phone`),
  UNIQUE KEY `user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_api_table_info
-- ----------------------------
DROP TABLE IF EXISTS `user_api_table_info`;
CREATE TABLE `user_api_table_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(255) NOT NULL,
  `tableName` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `sel` int(255) DEFAULT NULL,
  `upd` int(11) DEFAULT NULL,
  `ins` int(255) DEFAULT NULL,
  `del` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `_userid` (`userid`),
  CONSTRAINT `_userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_api_table_item
-- ----------------------------
DROP TABLE IF EXISTS `user_api_table_item`;
CREATE TABLE `user_api_table_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableId` int(11) NOT NULL,
  `tableItemName` varchar(255) NOT NULL,
  `realItemName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `_tableid` (`tableId`),
  CONSTRAINT `_tableid` FOREIGN KEY (`tableId`) REFERENCES `user_api_table_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

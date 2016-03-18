/*
Navicat MySQL Data Transfer

Source Server         : jzdy2
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : fuli

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-03-18 20:12:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for inactive_account
-- ----------------------------
DROP TABLE IF EXISTS `inactive_account`;
CREATE TABLE `inactive_account` (
  `token` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registerTime` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inactive_account
-- ----------------------------
INSERT INTO `inactive_account` VALUES ('2127e2f388e9b5c8b1609a2c6b6c79cb', 'heijiw@163.com', '1995826', '1457107025870');

-- ----------------------------
-- Table structure for unaudited_welfare
-- ----------------------------
DROP TABLE IF EXISTS `unaudited_welfare`;
CREATE TABLE `unaudited_welfare` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `publishTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unaudited_welfare
-- ----------------------------
INSERT INTO `unaudited_welfare` VALUES ('4', '的点点滴滴大声道大发多少', '<p style=\"text-align:left;\"><span style=\"font-size:10.5pt\">6.</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">视图命名规范</span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">视图的命名请遵循以下命名规范：</span></span><span style=\"font-size:10.5pt\">UV _ +</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">系统模块缩写（与表前缀类似）</span></span><span style=\"font-size:10.5pt\">+_ +</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">功能标识</span></span><span style=\"font-size:10.5pt\"> +</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">代表视图查询的主要表名（不带前缀）或功能的英文单词或英文单词缩写。</span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">如果一个视图只对一个表进行查询，建议视图的名称就用视图所查询的表的表名（不带前缀）。这样有利于根据表名找到相应的视图。</span></span><br/></p><p style=\"text-align:left;\"><span style=\"color:#0b050\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">注：</span></span></span><span style=\"color:#0b050\"><span style=\"font-size:10.5pt\">UV</span></span><span style=\"color:#0b050\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">是</span></span></span><span style=\"color:#0b050\"><span style=\"font-size:10.5pt\">userView</span></span><span style=\"color:#0b050\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">缩写</span></span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-size:10.5pt\">7.</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">视图设计规范</span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">在视图中必须说明以下内容：</span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-size:10.5pt\">(1)</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">目的：说明此视图的作用。</span></span><br/></p><p style=\"text-align:left;\"><span style=\"font-size:10.5pt\">(2)</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">创建者：首次创建此视图的人的姓名。在此请使用中文全名，不允许使用英文简称。</span></span><br/></p><p><span style=\"font-size:10.5pt\">(3)</span><span style=\"font-family:宋体\"><span style=\"font-size:10.5pt\">修改者、修改日期、修改原因：如果有人对此视图进行了修改，则必须在此视图的前面加注修改者姓名、修改日期及修改原因。</span></span></p><p><br/></p>', '2016-03-07 16:47:55', '2');
INSERT INTO `unaudited_welfare` VALUES ('5', '答复', '阿道夫', '2016-03-09 16:08:21', '2');
INSERT INTO `unaudited_welfare` VALUES ('6', '阿道夫', '阿道夫', '2016-02-29 16:08:34', '2');
INSERT INTO `unaudited_welfare` VALUES ('7', '阿道夫', '大师傅', '2016-03-09 16:08:45', '2');
INSERT INTO `unaudited_welfare` VALUES ('8', '爱的', '阿道夫', '2016-03-07 16:08:57', '2');
INSERT INTO `unaudited_welfare` VALUES ('9', '大师傅', '爱的', '2016-03-14 16:09:13', '2');

-- ----------------------------
-- Table structure for unaudited_welfare_own_tag
-- ----------------------------
DROP TABLE IF EXISTS `unaudited_welfare_own_tag`;
CREATE TABLE `unaudited_welfare_own_tag` (
  `welfareId` varchar(255) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unaudited_welfare_own_tag
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '1003031335@qq.com', 'e2e56824cf960fe1444d9a53c4b4c111');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `nickname` varchar(255) DEFAULT NULL,
  `imageUri` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `registerTime` datetime DEFAULT NULL,
  KEY `user_fk` (`userId`),
  CONSTRAINT `user_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('修改', '/store/images/avatar/2016/2/18/2.jpg', 'hejiw@163.com', '18813260085', '屌爆了掉', '2', null);

-- ----------------------------
-- Table structure for welfare
-- ----------------------------
DROP TABLE IF EXISTS `welfare`;
CREATE TABLE `welfare` (
  `wid` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `viewCount` int(10) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`wid`),
  KEY `userid_fk` (`userId`),
  CONSTRAINT `userid_fk` FOREIGN KEY (`userId`) REFERENCES `user_info` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of welfare
-- ----------------------------
INSERT INTO `welfare` VALUES ('1', '不错', '瑞星公司发布针对互联网网民密码强度的专业研究结果：国内互联网用户在密码使用存在种种疏漏，一些极其简单的密码被广泛应用于各种使用互联网环境中，给用户带来极大安全风险： 明文 16位MD5密码 32位MD5密码 1 8f00b204e9800998 d41d8cd98f00b204e9800998ecf8427e abc123 28cb38d5f2608536 e99a18c428cb38d5f260853678922e03 123456 49b', '10', '2016-03-07 20:29:23', '2');

-- ----------------------------
-- Table structure for welfare_own_tag
-- ----------------------------
DROP TABLE IF EXISTS `welfare_own_tag`;
CREATE TABLE `welfare_own_tag` (
  `welfareId` int(11) NOT NULL,
  `tagId` int(11) NOT NULL,
  PRIMARY KEY (`welfareId`,`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of welfare_own_tag
-- ----------------------------
INSERT INTO `welfare_own_tag` VALUES ('3', '1');
INSERT INTO `welfare_own_tag` VALUES ('3', '2');
INSERT INTO `welfare_own_tag` VALUES ('4', '1');
INSERT INTO `welfare_own_tag` VALUES ('4', '2');

-- ----------------------------
-- Table structure for welfare_tag
-- ----------------------------
DROP TABLE IF EXISTS `welfare_tag`;
CREATE TABLE `welfare_tag` (
  `wtid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wtid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of welfare_tag
-- ----------------------------
INSERT INTO `welfare_tag` VALUES ('1', '流量', '。。。。。');
INSERT INTO `welfare_tag` VALUES ('2', '是打发', null);

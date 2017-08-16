/*
Navicat MySQL Data Transfer

Source Server         : b2b
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-16 18:16:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for w_request_info
-- ----------------------------
DROP TABLE IF EXISTS `w_request_info`;
CREATE TABLE `w_request_info` (
  `MsgId` varchar(255) NOT NULL COMMENT '消息id，64位整型',
  `toUserName` varchar(255) DEFAULT NULL COMMENT '开发者微信号',
  `FromUserName` varchar(255) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `CreateTime` double DEFAULT NULL COMMENT '消息创建时间 （整型）',
  `MsgType` varchar(255) DEFAULT NULL COMMENT '消息类型',
  `Content` varchar(255) DEFAULT NULL COMMENT '文本消息内容',
  PRIMARY KEY (`MsgId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

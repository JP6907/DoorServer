/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-02-16 22:03:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `content` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `title` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('19', '2018-01-20 22:53:15', '明天早上集合！！！', '集合');
INSERT INTO `announcement` VALUES ('20', '2018-01-20 22:53:37', '测试内容！！！', '测试标题');

-- ----------------------------
-- Table structure for `bbs`
-- ----------------------------
DROP TABLE IF EXISTS `bbs`;
CREATE TABLE `bbs` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(10) DEFAULT NULL,
  `title` char(14) DEFAULT NULL,
  `content` char(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bbs
-- ----------------------------
INSERT INTO `bbs` VALUES ('4', '小明', '哈哈', '嘎嘎', '2016-06-02 19:06:21');
INSERT INTO `bbs` VALUES ('5', '小明', '你好', '你好', '2016-06-05 11:45:34');

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL,
  `remarkName` char(32) CHARACTER SET utf8 DEFAULT '-',
  `group` char(32) CHARACTER SET utf8 DEFAULT '-',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('1', '17', '11', 'xiaoming', '-');
INSERT INTO `friends` VALUES ('2', '17', '12', '-', '-');

-- ----------------------------
-- Table structure for `noticereply`
-- ----------------------------
DROP TABLE IF EXISTS `noticereply`;
CREATE TABLE `noticereply` (
  `replyId` int(20) NOT NULL AUTO_INCREMENT,
  `replyNoticeId` int(20) NOT NULL,
  `replyJudge` int(20) NOT NULL,
  `replyDateTime` datetime(6) DEFAULT NULL,
  `replyResponder` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `replyPublisher` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `replyFloor` int(20) DEFAULT NULL,
  `replyText` varchar(1024) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of noticereply
-- ----------------------------
INSERT INTO `noticereply` VALUES ('3', '7', '1', '2016-09-30 20:47:27.000000', '小红', '小明', '1', '这是回复内容');

-- ----------------------------
-- Table structure for `postprofile`
-- ----------------------------
DROP TABLE IF EXISTS `postprofile`;
CREATE TABLE `postprofile` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `post_text` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `post_phone` varchar(24) CHARACTER SET utf8 DEFAULT NULL,
  `post_publisher` varchar(24) CHARACTER SET utf8 DEFAULT NULL,
  `post_replynum` int(11) DEFAULT '0',
  `post_publishdt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_newdt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture1` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture2` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture4` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture5` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture6` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture7` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture8` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `post_picture9` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of postprofile
-- ----------------------------
INSERT INTO `postprofile` VALUES ('1', '1', null, null, null, '0', '2016-10-08 22:41:00', '2016-10-20 22:41:04', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('2', '2', null, null, null, '0', '2016-10-08 22:41:00', '2016-10-20 22:41:05', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('3', '3', null, null, null, '0', '2016-10-08 22:41:00', '2016-10-20 22:41:06', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('4', '4', null, null, null, '0', '2016-10-08 22:41:27', '2016-10-20 22:41:07', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('5', '5', null, null, null, '0', '2016-10-08 22:41:27', '2016-10-20 22:41:08', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('6', '6', null, null, null, '0', '2016-10-08 22:41:27', '2016-10-20 22:41:09', null, null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('36', '测试标题', '测试内容测试内容', '15521374237', 'peng', '0', '2016-10-08 22:41:27', '2016-10-08 22:41:27', 'test.jpg', null, null, null, null, null, null, null, null);
INSERT INTO `postprofile` VALUES ('38', '测', '哈哈', '15521374237', '鹏哥', '0', '2016-10-08 23:45:11', '2016-10-08 23:45:11', '0IMG_20160927_120040_HDR.jpg', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reply_postid` bigint(20) DEFAULT NULL,
  `reply_judge` smallint(6) DEFAULT NULL,
  `reply_datetime` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `reply_responder` varchar(24) CHARACTER SET utf8 DEFAULT NULL,
  `reply_publisher` varchar(24) CHARACTER SET utf8 DEFAULT NULL,
  `reply_floor` int(11) DEFAULT NULL,
  `reply_text` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '1', '2011-10-30 10:30:10', null, null, null, null);
INSERT INTO `reply` VALUES ('2', '2', '0', '2011-11-30 10:30:10', null, null, null, null);
INSERT INTO `reply` VALUES ('3', '3', '1', '2010-9-30 10:30:10', null, null, null, null);
INSERT INTO `reply` VALUES ('4', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('5', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('6', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('7', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('8', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('9', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('10', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('11', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('12', '1', '0', null, null, null, '1', null);
INSERT INTO `reply` VALUES ('13', '2', '0', null, null, null, '0', '测试帖子回复内容');
INSERT INTO `reply` VALUES ('14', '2', '0', null, null, null, '0', '测试帖子回复内容');

-- ----------------------------
-- Table structure for `t_dooruser`
-- ----------------------------
DROP TABLE IF EXISTS `t_dooruser`;
CREATE TABLE `t_dooruser` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `dName` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '-',
  `dPassword` varchar(20) CHARACTER SET utf8 NOT NULL,
  `dPhone` varchar(15) CHARACTER SET utf8 NOT NULL,
  `Building` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `DoorID` varchar(10) DEFAULT NULL,
  `dImagePath` varchar(128) CHARACTER SET utf8 DEFAULT '-',
  `dODPass` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '000000',
  `friend` varchar(1024) CHARACTER SET utf8 DEFAULT ' ',
  PRIMARY KEY (`ID`,`dName`,`dPhone`),
  UNIQUE KEY `Name` (`dName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_dooruser
-- ----------------------------
INSERT INTO `t_dooruser` VALUES ('11', 'Leon', '123456', '18826245497', 'B2', 'door01', 'Upload/Leon/headImage.jpg', '231012', '17');
INSERT INTO `t_dooruser` VALUES ('12', 'lin', '123456', '18826245496', 'B2', 'door03', 'Upload/lin/headImage.jpg', '123456', ' ');
INSERT INTO `t_dooruser` VALUES ('13', 'long', '123456', '15689326578', 'B1', 'door03', 'Upload/long/headImage.jpg', '123456', ' ');
INSERT INTO `t_dooruser` VALUES ('14', '11', 'yudawei1996', '15521197165', null, null, 'Upload/default/headImage.jpg', '123456', ' ');
INSERT INTO `t_dooruser` VALUES ('16', 'Hello', '159357', '112112', 'B13', 'a1', '-', '000000', ' ');
INSERT INTO `t_dooruser` VALUES ('17', '鹏', 'huikaimeng', '15521374237', null, null, 'Upload/HeadImage/15521374237/headImage.jpg', '888888', '11');

-- ----------------------------
-- Table structure for `t_employees`
-- ----------------------------
DROP TABLE IF EXISTS `t_employees`;
CREATE TABLE `t_employees` (
  `NO` int(10) unsigned NOT NULL,
  `eName` varchar(20) NOT NULL,
  `eSal` int(10) unsigned DEFAULT NULL,
  `DeptNO` int(11) unsigned DEFAULT NULL,
  `LeaderNO` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_employees
-- ----------------------------
INSERT INTO `t_employees` VALUES ('0', 'df', '8332', null, null);
INSERT INTO `t_employees` VALUES ('2001', 'Leo', '7909', '10', '1000');
INSERT INTO `t_employees` VALUES ('2002', 'Kitty', '6894', '10', '1000');
INSERT INTO `t_employees` VALUES ('2003', 'Jhon', '9834', '20', '1001');
INSERT INTO `t_employees` VALUES ('2004', 'Miller', '5098', '30', '1002');
INSERT INTO `t_employees` VALUES ('2005', 'Smith', '9087', '30', '1002');
INSERT INTO `t_employees` VALUES ('2006', 'Adams', '9087', '30', '1002');
INSERT INTO `t_employees` VALUES ('2007', 'Martin', '4982', '50', null);

-- ----------------------------
-- Table structure for `t_fc`
-- ----------------------------
DROP TABLE IF EXISTS `t_fc`;
CREATE TABLE `t_fc` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dName` varchar(20) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL DEFAULT '',
  `dPass` varchar(20) NOT NULL,
  `dImagePath` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`,`dName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_fc
-- ----------------------------
INSERT INTO `t_fc` VALUES ('2', 'Leo', '123456', 'Upload/Leo/headImage.jpg');
INSERT INTO `t_fc` VALUES ('5', 'leon', '123456', 'Upload/leon/headImage.jpg');
INSERT INTO `t_fc` VALUES ('6', 'dong', '123456', 'Upload/default/headImage.jpg');
INSERT INTO `t_fc` VALUES ('7', '123', '123456', 'Upload/default/headImage.jpg');

-- ----------------------------
-- Table structure for `t_images`
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `NO` int(11) unsigned NOT NULL,
  `Path` varchar(20) NOT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_images
-- ----------------------------
INSERT INTO `t_images` VALUES ('10', 'images/test.jpg');

-- ----------------------------
-- Table structure for `t_leaders`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaders`;
CREATE TABLE `t_leaders` (
  `NO` int(10) unsigned NOT NULL,
  `lName` varchar(20) NOT NULL,
  `Dept` varchar(20) DEFAULT NULL,
  `DeptNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_leaders
-- ----------------------------
INSERT INTO `t_leaders` VALUES ('999', 'Boss', 'Manager', '0');
INSERT INTO `t_leaders` VALUES ('1000', 'Kobe', 'Analyst', '10');
INSERT INTO `t_leaders` VALUES ('1001', 'James', 'Cleak', '20');
INSERT INTO `t_leaders` VALUES ('1002', 'Jordon', 'Salesman', '30');

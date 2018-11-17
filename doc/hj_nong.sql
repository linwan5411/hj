/*
Navicat MySQL Data Transfer

Source Server         : jvv-127.0.0.1
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : hj_nong

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-17 10:48:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hj_ad
-- ----------------------------
DROP TABLE IF EXISTS `hj_ad`;
CREATE TABLE `hj_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_site_id` bigint(20) DEFAULT NULL,
  `ad_id` bigint(20) DEFAULT NULL COMMENT '广告ID',
  `ad_name` varchar(20) DEFAULT '' COMMENT '名称',
  `ad_sort` int(11) DEFAULT NULL COMMENT '排序',
  `ad_status` int(1) DEFAULT '1' COMMENT '1：显示，0：不显示',
  `ad_url` varchar(120) DEFAULT NULL COMMENT '跳转地址',
  `ad_time` int(11) DEFAULT '0' COMMENT '点击次数',
  `ad_image` varchar(120) DEFAULT NULL COMMENT '尺寸',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
-- Records of hj_ad
-- ----------------------------

-- ----------------------------
-- Table structure for hj_ad_site
-- ----------------------------
DROP TABLE IF EXISTS `hj_ad_site`;
CREATE TABLE `hj_ad_site` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ad_site_id` bigint(20) DEFAULT NULL,
  `ad_site_name` varchar(30) DEFAULT NULL COMMENT '广告位置',
  `ad_type` int(1) DEFAULT '1' COMMENT '1:图片，2：文字',
  `ad_size` varchar(30) DEFAULT NULL COMMENT '尺寸',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告位置';

-- ----------------------------
-- Records of hj_ad_site
-- ----------------------------

-- ----------------------------
-- Table structure for hj_area
-- ----------------------------
DROP TABLE IF EXISTS `hj_area`;
CREATE TABLE `hj_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_id` bigint(20) DEFAULT NULL,
  `area_name` varchar(30) DEFAULT NULL,
  `area_code` varchar(300) DEFAULT NULL COMMENT '地址编码',
  `parent_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Records of hj_area
-- ----------------------------

-- ----------------------------
-- Table structure for hj_article
-- ----------------------------
DROP TABLE IF EXISTS `hj_article`;
CREATE TABLE `hj_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `article_title` varchar(60) DEFAULT NULL COMMENT '文章标题',
  `article_type` varchar(30) DEFAULT NULL COMMENT '文章类型',
  `article_catgory` bigint(20) DEFAULT NULL COMMENT '文章类型',
  `article_image` varchar(120) DEFAULT NULL COMMENT '图片',
  `create_info` varchar(1200) DEFAULT NULL COMMENT '详情',
  `article_sort` int(11) DEFAULT NULL COMMENT '排序',
  `article_ok` int(11) DEFAULT '0' COMMENT '点赞量',
  `article_read_num` int(11) DEFAULT '0' COMMENT '阅读量',
  `article_from` varchar(60) DEFAULT '原创' COMMENT '来源',
  `article_show` int(11) DEFAULT '1' COMMENT '1:展示，0：不展示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of hj_article
-- ----------------------------

-- ----------------------------
-- Table structure for hj_hacienda_info
-- ----------------------------
DROP TABLE IF EXISTS `hj_hacienda_info`;
CREATE TABLE `hj_hacienda_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hacienda_id` bigint(20) DEFAULT NULL COMMENT '农场主ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `hacienda_name` varchar(160) DEFAULT NULL COMMENT '农场名称',
  `link_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `link_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `hacienda_remark` varchar(1200) DEFAULT NULL COMMENT '农场介绍',
  `hacienda_email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `hacienda_scope` varchar(600) DEFAULT NULL COMMENT '附着种植范围',
  `hacienda_image` varchar(600) DEFAULT NULL COMMENT '图片地址，采用,进行分割',
  `hacienda_land` varchar(200) DEFAULT NULL COMMENT '土地性质',
  `server_category` varchar(300) DEFAULT NULL COMMENT '所需要服务的领域',
  `server_max` double(20,2) DEFAULT '0.00' COMMENT '土地面积',
  `server_use_max` double(20,2) DEFAULT NULL COMMENT '托管可使用面积',
  `detail_addr` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `provice` bigint(20) DEFAULT NULL COMMENT '省',
  `city` bigint(20) DEFAULT NULL COMMENT '市',
  `district` bigint(20) DEFAULT NULL COMMENT '区',
  `area_code` varchar(300) DEFAULT NULL COMMENT '最小地址编码',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `hacienda_show` int(11) DEFAULT '1' COMMENT '1:展示，0：不展示',
  `hacienda_sort` int(11) DEFAULT '0' COMMENT '排序',
  `auth_status` int(11) DEFAULT '0' COMMENT '0:待审核，1：审核中，2：审核通过',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='农场主';

-- ----------------------------
-- Records of hj_hacienda_info
-- ----------------------------

-- ----------------------------
-- Table structure for hj_invitation
-- ----------------------------
DROP TABLE IF EXISTS `hj_invitation`;
CREATE TABLE `hj_invitation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `invitation_id` bigint(20) DEFAULT NULL,
  `user_header` varchar(120) DEFAULT NULL COMMENT '头像',
  `user_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `invitation_type` bigint(20) DEFAULT NULL COMMENT '帖子类型',
  `invitation_info` varchar(600) DEFAULT NULL COMMENT '内容',
  `answer_num` int(11) DEFAULT '0' COMMENT '回复数量',
  `invitation_status` int(11) DEFAULT '1' COMMENT '1:显示，0：不显示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子';

-- ----------------------------
-- Records of hj_invitation
-- ----------------------------

-- ----------------------------
-- Table structure for hj_invitation_list
-- ----------------------------
DROP TABLE IF EXISTS `hj_invitation_list`;
CREATE TABLE `hj_invitation_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invitation_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_header` varchar(120) DEFAULT NULL COMMENT '头像',
  `invitation_info` varchar(600) DEFAULT NULL COMMENT '回复类容',
  `answer_num_ok` int(11) DEFAULT '0' COMMENT '点赞数量',
  `invitation_status` int(11) DEFAULT '1' COMMENT '1:显示，0：不显示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回复列表';

-- ----------------------------
-- Records of hj_invitation_list
-- ----------------------------

-- ----------------------------
-- Table structure for hj_message
-- ----------------------------
DROP TABLE IF EXISTS `hj_message`;
CREATE TABLE `hj_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `message_tpye` int(11) DEFAULT '1' COMMENT '1:系统消息，2：特别消息',
  `mesage_title` varchar(60) DEFAULT NULL,
  `message_info` varchar(300) DEFAULT NULL COMMENT '消息内容',
  `read_count` int(11) DEFAULT '0' COMMENT '0:未读，1：已读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的消息通知';

-- ----------------------------
-- Records of hj_message
-- ----------------------------

-- ----------------------------
-- Table structure for hj_order
-- ----------------------------
DROP TABLE IF EXISTS `hj_order`;
CREATE TABLE `hj_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(20) DEFAULT NULL COMMENT '联系人ID',
  `from_user_name` varchar(30) DEFAULT NULL COMMENT '联系人名称',
  `from_user_type` int(1) DEFAULT NULL COMMENT '联系人类型',
  `from_user_mobile` varchar(30) DEFAULT NULL COMMENT '联系人电话',
  `to_user_id` int(11) DEFAULT NULL COMMENT '被联系人',
  `to_user_name` varchar(20) DEFAULT NULL COMMENT '被联系人',
  `to_user_type` int(1) DEFAULT NULL COMMENT '被联系人',
  `to_user_mobile` varchar(30) DEFAULT NULL COMMENT '被联系人',
  `need_question` varchar(1200) DEFAULT NULL COMMENT '需要的服务咨询描述',
  `opt_status` int(1) DEFAULT '1' COMMENT '0:待处理，1：已处理',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系表';

-- ----------------------------
-- Records of hj_order
-- ----------------------------

-- ----------------------------
-- Table structure for hj_server_case
-- ----------------------------
DROP TABLE IF EXISTS `hj_server_case`;
CREATE TABLE `hj_server_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `server_id` bigint(20) DEFAULT NULL,
  `case_intro` varchar(300) DEFAULT NULL COMMENT '案例简介',
  `case_info` varchar(1200) DEFAULT NULL COMMENT '案例详情',
  `case_max` varchar(30) DEFAULT NULL COMMENT '服务的亩数',
  `case_time` varchar(30) DEFAULT NULL COMMENT '服务时间',
  `case_show` int(11) DEFAULT '1' COMMENT '1:展示，0：不展示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务商案例';

-- ----------------------------
-- Records of hj_server_case
-- ----------------------------

-- ----------------------------
-- Table structure for hj_server_code
-- ----------------------------
DROP TABLE IF EXISTS `hj_server_code`;
CREATE TABLE `hj_server_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `server_category` varchar(30) DEFAULT NULL,
  `server_remark` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `category_code` varchar(200) DEFAULT NULL COMMENT '编码',
  `data_status` int(11) DEFAULT '1' COMMENT '0:停用，1：启用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务类别编码';

-- ----------------------------
-- Records of hj_server_code
-- ----------------------------

-- ----------------------------
-- Table structure for hj_server_info
-- ----------------------------
DROP TABLE IF EXISTS `hj_server_info`;
CREATE TABLE `hj_server_info` (
  `id` bigint(20) NOT NULL,
  `server_id` bigint(20) DEFAULT NULL COMMENT '服务商ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `company_name` varchar(160) DEFAULT NULL COMMENT '企业名称',
  `link_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `link_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `company_remark` varchar(1200) DEFAULT NULL COMMENT '服务商介绍',
  `company_email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `company_scope` varchar(600) DEFAULT NULL COMMENT '经营范围',
  `company_image` varchar(600) DEFAULT NULL COMMENT '图片地址，采用,进行分割',
  `company_website` varchar(120) DEFAULT NULL COMMENT '网站介绍',
  `server_category` varchar(300) DEFAULT NULL COMMENT '服务的领域',
  `server_reg_time` varchar(20) DEFAULT NULL COMMENT '注册时间',
  `server_registration` varchar(120) DEFAULT NULL COMMENT '工商注册码',
  `server_type` int(11) DEFAULT '1' COMMENT '1:个人，2：企业',
  `server_max` double DEFAULT '0' COMMENT '服务的亩数，单位亩',
  `server_codes` varchar(300) DEFAULT NULL COMMENT '服务领域编码',
  `regist_capital` varchar(30) DEFAULT NULL COMMENT '注册资本',
  `detail_addr` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `case_num` int(1) DEFAULT '0' COMMENT '案例数量',
  `client_num` int(1) DEFAULT '0' COMMENT '客户数量',
  `provice` bigint(20) DEFAULT NULL COMMENT '省',
  `city` bigint(20) DEFAULT NULL COMMENT '市',
  `district` bigint(20) DEFAULT NULL COMMENT '区',
  `area_code` varchar(300) DEFAULT NULL COMMENT '最小地址编码',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `server_show` int(11) DEFAULT '1' COMMENT '1:展示，0：不展示',
  `server_sort` int(11) DEFAULT '0' COMMENT '排序',
  `auth_status` int(11) DEFAULT '0' COMMENT '0:待审核，1：审核中，2：审核通过',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务商详请';

-- ----------------------------
-- Records of hj_server_info
-- ----------------------------

-- ----------------------------
-- Table structure for hj_user
-- ----------------------------
DROP TABLE IF EXISTS `hj_user`;
CREATE TABLE `hj_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '会员ID',
  `user_mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `open_id` varchar(32) DEFAULT NULL COMMENT '微信OPEN_ID',
  `user_type` int(1) DEFAULT '0' COMMENT '0:普通，1：农场主，2：服务商',
  `auth_type` int(1) DEFAULT '0' COMMENT '0:未认证，1：已认证',
  `data_status` int(1) DEFAULT '1' COMMENT '1:启用，0：禁用',
  `user_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `user_portrait` varchar(120) DEFAULT NULL COMMENT '头像',
  `login_pwd` varchar(60) DEFAULT NULL COMMENT '登陆密码',
  `login_salt` varchar(4) DEFAULT NULL COMMENT '密码盐',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `data_version` int(1) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_moblie_idx` (`user_mobile`),
  UNIQUE KEY `user_open_idx` (`open_id`),
  KEY `use_id_idx` (`user_id`,`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理表';

-- ----------------------------
-- Records of hj_user
-- ----------------------------

-- ----------------------------
-- Table structure for hj_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `hj_user_collect`;
CREATE TABLE `hj_user_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `collect_type` int(11) DEFAULT NULL COMMENT '1:服务商，2：农场，3：文章',
  `object_id` bigint(20) DEFAULT NULL COMMENT '对于的ID',
  `object_title` varchar(120) DEFAULT NULL COMMENT '标题',
  `object_image` varchar(120) DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的收藏表';

-- ----------------------------
-- Records of hj_user_collect
-- ----------------------------

-- ----------------------------
-- Table structure for hj_user_study
-- ----------------------------
DROP TABLE IF EXISTS `hj_user_study`;
CREATE TABLE `hj_user_study` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `collect_type` int(11) DEFAULT NULL COMMENT '1:服务商，2：农场，3：文章',
  `object_id` bigint(20) DEFAULT NULL COMMENT '对于的ID',
  `object_title` varchar(120) DEFAULT NULL COMMENT '标题',
  `object_image` varchar(120) DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户对文章的学习记录';

-- ----------------------------
-- Records of hj_user_study
-- ----------------------------

-- ----------------------------
-- Table structure for pf_notice
-- ----------------------------
DROP TABLE IF EXISTS `pf_notice`;
CREATE TABLE `pf_notice` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `creater` bigint(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统通知';

-- ----------------------------
-- Records of pf_notice
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'jeefast', '0 0/10 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/10 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '中国区', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '北京分公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '上海分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('100', '0', '工作台', null, null, '0', 'fa fa-desktop', '0');
INSERT INTO `sys_menu` VALUES ('101', '100', '系统通知', 'modules/platform/notice.html', 'platform:notice:list', '1', null, '0');
INSERT INTO `sys_menu` VALUES ('102', '101', '查看', null, 'platform:notice:list,platform:notice:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('103', '101', '新增', null, 'platform:notice:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('104', '101', '修改', null, 'platform:notice:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('105', '101', '删除', null, 'platform:notice:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('200', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('201', '200', '部门管理', 'modules/sys/dept.html', null, '1', 'fa fa-file-code-o', '1');
INSERT INTO `sys_menu` VALUES ('202', '201', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('203', '201', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('204', '201', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('205', '201', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('206', '200', '用户管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('207', '206', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('208', '206', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('209', '206', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('210', '206', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('211', '206', '导出', null, 'sys:user:exportExcel', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('212', '200', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('213', '212', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('214', '212', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('215', '212', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('216', '212', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('217', '200', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('218', '217', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('219', '217', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('220', '217', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('221', '217', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('222', '200', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('223', '200', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('224', '200', '定时任务', 'modules/job/schedule.html', null, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` VALUES ('225', '224', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('226', '224', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('227', '224', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('228', '224', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('229', '224', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('230', '224', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('231', '224', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('232', '224', '日志列表', null, 'sys:schedule:log', '2', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'admin@jeefast.cn', '13812345678', '1', null, '2017-11-01 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Records of tb_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'apiadmin', '13812345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

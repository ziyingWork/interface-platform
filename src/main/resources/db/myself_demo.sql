/*
 Navicat Premium Data Transfer

 Source Server         : 43.142.28.232
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : 43.142.28.232:3307
 Source Schema         : myself_demo

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/01/2024 16:28:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '接口名称',
  `description` varchar(256) COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '接口描述',
  `url` varchar(512) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '接口地址',
  `requestHeader` text COLLATE utf8mb3_unicode_ci COMMENT '请求头',
  `responseHeader` text COLLATE utf8mb3_unicode_ci COMMENT '响应头',
  `status` int NOT NULL DEFAULT '0' COMMENT '接口状态（0-关闭，1-开启）',
  `methodType` varchar(256) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '请求类型',
  `userId` bigint NOT NULL COMMENT '创建人',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删，1-已删）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci COMMENT='接口信息';

-- ----------------------------
-- Records of interface_info
-- ----------------------------
BEGIN;
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (1, 'Interface_1', 'Description for Interface_1', 'http://example.com/api/interface_1', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 1, 'GET', 281, '2022-10-31 03:27:04', '2021-12-15 16:10:40', 0);
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (2, 'Interface_2', 'Description for Interface_2', 'http://example.com/api/interface_2', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 1, 'POST', 418, '2022-10-31 03:27:04', '2024-01-26 11:35:01', 0);
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (3, 'Interface_3', 'Description for Interface_3', 'http://example.com/api/interface_3', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 1, 'POST', 133, '2022-10-31 03:27:04', '2024-01-26 11:35:03', 0);
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (4, 'Interface_4', 'Description for Interface_4', 'http://example.com/api/interface_4', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 0, 'PUT', 547, '2022-10-31 03:27:04', '2024-01-26 11:35:38', 1);
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (5, '示例接口', '这是一个示例接口描述', 'http://example.com/api', 'Content-Type: application/json', 'Content-Type: application/json', 1, 'GET', 888, '2024-01-27 07:44:01', '2024-01-27 07:44:01', 0);
INSERT INTO `interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `methodType`, `userId`, `createTime`, `updateTime`, `isDelete`) VALUES (6, '示例接口1', '这是一个示例接口描述1', 'http://example.com/api/one', 'Content-Type: application/json', 'Content-Type: application/json', 0, 'POST', 888, '2024-01-27 08:00:17', '2024-01-27 08:00:17', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) COLLATE utf8mb3_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '密码',
  `accessKey` varchar(512) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT 'accessKey',
  `secretKey` varchar(512) COLLATE utf8mb3_unicode_ci NOT NULL COMMENT 'secretKey',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_userAccount` (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint NOT NULL COMMENT '调用用户 id',
  `interfaceInfoId` bigint NOT NULL COMMENT '接口 id',
  `totalNum` int NOT NULL DEFAULT '0' COMMENT '总调用次数',
  `leftNum` int NOT NULL DEFAULT '0' COMMENT '剩余调用次数',
  `status` int NOT NULL DEFAULT '0' COMMENT '0-正常，1-禁用',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除(0-未删, 1-已删)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci COMMENT='用户调用接口关系';

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

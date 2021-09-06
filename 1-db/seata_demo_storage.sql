/*
 Navicat Premium Data Transfer

 Source Server         : zsk
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : seata_demo_storage

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/09/2021 00:34:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for storage_info
-- ----------------------------
DROP TABLE IF EXISTS `storage_info`;
CREATE TABLE `storage_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NULL DEFAULT NULL COMMENT '产品id',
  `total` int NULL DEFAULT NULL COMMENT '总库存',
  `used` int NULL DEFAULT NULL COMMENT '已用库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage_info
-- ----------------------------
INSERT INTO `storage_info` VALUES (1, 1, 89, 11);
INSERT INTO `storage_info` VALUES (2, 2, 98, 2);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

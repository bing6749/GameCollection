/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : gamecollection

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 29/06/2021 18:17:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game2048
-- ----------------------------
DROP TABLE IF EXISTS `game2048`;
CREATE TABLE `game2048`  (
  `id` int NOT NULL,
  `score` int NOT NULL COMMENT '成绩',
  `num` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `FK_user_game2048` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of game2048
-- ----------------------------

-- ----------------------------
-- Table structure for planegame
-- ----------------------------
DROP TABLE IF EXISTS `planegame`;
CREATE TABLE `planegame`  (
  `id` int NOT NULL,
  `time` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `FK_user_planegame` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of planegame
-- ----------------------------

-- ----------------------------
-- Table structure for snakegame
-- ----------------------------
DROP TABLE IF EXISTS `snakegame`;
CREATE TABLE `snakegame`  (
  `id` int NOT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `length` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of snakegame
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '001', '001');
INSERT INTO `user` VALUES (8, '111', '111');

SET FOREIGN_KEY_CHECKS = 1;

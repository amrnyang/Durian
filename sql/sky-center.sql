/*
 Navicat MySQL Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : sky-center

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 15/09/2020 18:09:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for center_course
-- ----------------------------
DROP TABLE IF EXISTS `center_course`;
CREATE TABLE `center_course`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程号',
  `college_id` bigint(20) NULL DEFAULT NULL COMMENT '开课单位（学院）',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `course_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程类型（A 通识必修 B 通识选修 C 专业必修 D 专业选修）',
  `credit` int(3) NULL DEFAULT NULL COMMENT '课程学分',
  `teacher` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任课老师',
  `is_use` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否使用（1 使用，0 停用）',
  `order_num` int(4) NULL DEFAULT NULL COMMENT '显示顺序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_couse_code`(`course_code`) USING BTREE COMMENT '唯一课程号'
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '榴莲题库-课程表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of center_course
-- ----------------------------
INSERT INTO `center_course` VALUES (104, '3245643211', 101, '电力电子', 'D', 3, '王丁', 1, 1, 'swing', '2020-08-08 19:43:17', 'swing', '2020-08-08 20:18:50', '这科很难');
INSERT INTO `center_course` VALUES (105, '4123123452', 101, '电工学', 'C', 3, '窦寅丰', 1, 2, 'swing', '2020-08-08 20:20:17', 'swing', '2020-08-12 13:59:36', '这科我挂过');
INSERT INTO `center_course` VALUES (106, '3221675564', 101, '自控原理', 'C', 4, '高媛', 1, 1, 'swing', '2020-08-08 20:21:16', 'swing', '2020-08-08 20:21:16', '');
INSERT INTO `center_course` VALUES (107, '2671726176', 265, '高等数学（上）', 'A', 5, '老王', 1, 2, 'swing', '2020-08-08 20:25:26', 'swing', '2020-08-08 20:25:46', '我过了');
INSERT INTO `center_course` VALUES (108, '4232324443', 102, '期货管理学', 'B', 1, '小李', 1, 5, 'swing', '2020-08-08 20:42:21', 'swing', '2020-08-08 20:42:33', '这科儿老师特别好');
INSERT INTO `center_course` VALUES (109, '3245643219', 265, '数学分析', 'C', 3, '老王', 1, 3, 'swing', '2020-08-11 11:51:24', 'swing', '2020-08-11 11:51:24', '数学分析');
INSERT INTO `center_course` VALUES (110, '3244323442', 112, '数据结构B', 'A', 3, '高媛', 1, 1, 'swing', '2020-08-12 14:07:16', 'swing', '2020-08-12 14:07:16', '');
INSERT INTO `center_course` VALUES (111, '6345435434', 265, '线性代数', 'A', 4, '李秋', 1, 1, 'swing', '2020-08-12 14:08:45', 'swing', '2020-08-12 14:11:32', '');
INSERT INTO `center_course` VALUES (112, '3546372876', 280, '我是谁', 'C', 3, '柏拉图', 1, 1, 'swing', '2020-08-17 22:33:49', 'swing', '2020-08-17 22:33:49', '');
INSERT INTO `center_course` VALUES (114, '634536734', 316, '阿拉伯语', 'C', 5, '小芳', 1, 1, '', NULL, '', NULL, '');

-- ----------------------------
-- Table structure for center_dept
-- ----------------------------
DROP TABLE IF EXISTS `center_dept`;
CREATE TABLE `center_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父部门id',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `is_use` tinyint(1) NOT NULL DEFAULT 1 COMMENT '数据是否（1 使用，0停用）',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_parent_id_dept_name`(`parent_id`, `dept_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 377 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of center_dept
-- ----------------------------
INSERT INTO `center_dept` VALUES (100, 0, '黑龙江大学', 'swing', '18845124018', '18845124018@163.com', 1, 0, 'swing', '2018-03-16 11:33:00', 'test', '2020-05-09 12:29:55', NULL);
INSERT INTO `center_dept` VALUES (101, 100, '电子工程学院', 'swing', '18845124023', '18845124018@163.com', 1, 1, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-12 21:22:27', '');
INSERT INTO `center_dept` VALUES (102, 100, '经济与工商管理院', 'swing', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-12 21:39:01', '');
INSERT INTO `center_dept` VALUES (103, 101, '自动化', 'swing', '18845124018', '18845124018@163.com', 1, 3, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-05 12:05:04', '');
INSERT INTO `center_dept` VALUES (104, 101, '集成电路设计与集成系统', 'qianqian', '18845124018', '18845124018@163.com', 1, 2, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-12 12:42:55', '');
INSERT INTO `center_dept` VALUES (108, 102, '经济学', 'swing', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-12 13:12:10', '');
INSERT INTO `center_dept` VALUES (109, 102, '国际经济与贸易', 'swing', '18845124018', '18845124018@163.com', 1, 2, 'swing', '2018-03-16 11:33:00', 'swing', '2020-08-12 13:12:17', '');
INSERT INTO `center_dept` VALUES (112, 100, '软件学院', 'swing', '18845124019', '18845124018@163.com', 1, 1, 'swing', '2020-04-15 16:12:50', 'swing', '2020-08-05 12:04:50', '');
INSERT INTO `center_dept` VALUES (263, 101, '电子科学与技术', 'swing', '18845124018', '12678126@172.com', 1, 3, 'swing', '2020-08-05 12:05:41', 'swing', '2020-08-12 12:43:07', '');
INSERT INTO `center_dept` VALUES (264, 112, '软件工程', 'swing', '18845124876', '177543622@43.com', 1, 3, 'swing', '2020-08-05 12:07:44', 'swing', '2020-08-12 12:45:54', '');
INSERT INTO `center_dept` VALUES (265, 100, '数学科学学院', 'swing', '17765653425', '17765653425@163.com', 1, 1, 'swing', '2020-08-08 20:24:06', 'swing', '2020-08-12 13:40:48', '');
INSERT INTO `center_dept` VALUES (266, 100, '艺术学院', 'swing', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-11 23:59:27', 'swing', '2020-08-11 23:59:27', '');
INSERT INTO `center_dept` VALUES (267, 101, '电子信息科学与技术', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:43:50', 'swing', '2020-08-12 12:43:50', '');
INSERT INTO `center_dept` VALUES (268, 101, '电子信息工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:44:28', 'swing', '2020-08-12 12:44:28', '');
INSERT INTO `center_dept` VALUES (269, 101, '通信工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:44:46', 'swing', '2020-08-12 12:44:46', '');
INSERT INTO `center_dept` VALUES (270, 101, '物联网工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:45:03', 'swing', '2020-08-12 12:45:03', '');
INSERT INTO `center_dept` VALUES (271, 100, '数据科学与技术学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:46:20', 'swing', '2020-08-12 12:46:20', '');
INSERT INTO `center_dept` VALUES (272, 271, '网络工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:46:58', 'swing', '2020-08-12 12:46:58', '');
INSERT INTO `center_dept` VALUES (273, 100, '中俄学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:47:42', 'swing', '2020-08-12 12:47:42', '');
INSERT INTO `center_dept` VALUES (274, 273, '数学与应用数学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:50:06', 'swing', '2020-08-20 09:32:10', '');
INSERT INTO `center_dept` VALUES (275, 273, '应用物理学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:51:19', 'swing', '2020-08-12 12:51:19', '');
INSERT INTO `center_dept` VALUES (276, 273, '化学工程与工艺', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:51:55', 'swing', '2020-08-12 12:51:55', '');
INSERT INTO `center_dept` VALUES (277, 273, '生物技术', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:52:50', 'swing', '2020-08-12 12:52:50', '');
INSERT INTO `center_dept` VALUES (278, 273, '金融学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:53:06', 'swing', '2020-08-12 12:53:06', '');
INSERT INTO `center_dept` VALUES (279, 273, '法学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:53:23', 'swing', '2020-08-12 12:53:23', '');
INSERT INTO `center_dept` VALUES (280, 100, '哲学学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:53:37', 'swing', '2020-08-12 12:53:37', '');
INSERT INTO `center_dept` VALUES (281, 280, '哲学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:54:06', 'swing', '2020-08-12 12:54:06', '');
INSERT INTO `center_dept` VALUES (282, 100, '政府管理学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:54:58', 'swing', '2020-08-12 12:54:58', '');
INSERT INTO `center_dept` VALUES (283, 282, '社会学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 12:55:27', 'swing', '2020-08-12 12:55:27', '');
INSERT INTO `center_dept` VALUES (284, 282, '政治学与行政学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:10:48', 'swing', '2020-08-12 13:10:48', '');
INSERT INTO `center_dept` VALUES (285, 282, '行政管理', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:11:10', 'swing', '2020-08-12 13:11:10', '');
INSERT INTO `center_dept` VALUES (286, 282, '社会工作', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:11:26', 'swing', '2020-08-12 13:11:26', '');
INSERT INTO `center_dept` VALUES (287, 282, '土地资源管理', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:11:42', 'swing', '2020-08-12 13:11:42', '');
INSERT INTO `center_dept` VALUES (288, 102, '金融学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:12:36', 'swing', '2020-08-12 13:12:36', '');
INSERT INTO `center_dept` VALUES (289, 102, '工商管理', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:13:08', 'swing', '2020-08-12 13:13:08', '');
INSERT INTO `center_dept` VALUES (290, 102, '市场营销', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:13:54', 'swing', '2020-08-12 13:13:54', '');
INSERT INTO `center_dept` VALUES (291, 102, '会计学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:14:10', 'swing', '2020-08-12 13:14:10', '');
INSERT INTO `center_dept` VALUES (292, 100, '法学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:14:36', 'swing', '2020-08-12 13:14:36', '');
INSERT INTO `center_dept` VALUES (293, 292, '法学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:14:54', 'swing', '2020-08-12 13:14:54', '');
INSERT INTO `center_dept` VALUES (294, 100, '教育科学研究学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:15:19', 'swing', '2020-08-12 13:15:19', '');
INSERT INTO `center_dept` VALUES (295, 294, '教育学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:15:36', 'swing', '2020-08-12 13:15:36', '');
INSERT INTO `center_dept` VALUES (296, 294, '应用心理学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:15:52', 'swing', '2020-08-12 13:15:52', '');
INSERT INTO `center_dept` VALUES (297, 100, '文学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:16:11', 'swing', '2020-08-12 13:16:11', '');
INSERT INTO `center_dept` VALUES (298, 297, '汉语言文学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:16:53', 'swing', '2020-08-12 13:16:53', '');
INSERT INTO `center_dept` VALUES (299, 297, '汉语言文学（满语）', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:17:08', 'swing', '2020-08-12 13:17:08', '');
INSERT INTO `center_dept` VALUES (300, 297, '汉语国际教育', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:17:23', 'swing', '2020-08-12 13:17:23', '');
INSERT INTO `center_dept` VALUES (301, 100, '新闻传播学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:17:39', 'swing', '2020-08-12 13:17:39', '');
INSERT INTO `center_dept` VALUES (302, 301, '新闻学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:17:53', 'swing', '2020-08-12 13:17:53', '');
INSERT INTO `center_dept` VALUES (303, 301, '广告学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:18:08', 'swing', '2020-08-12 13:18:08', '');
INSERT INTO `center_dept` VALUES (304, 301, '广播电视编导（含节目主持人）', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:18:24', 'swing', '2020-08-12 13:18:24', '');
INSERT INTO `center_dept` VALUES (305, 301, '传播学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:18:37', 'swing', '2020-08-12 13:18:37', '');
INSERT INTO `center_dept` VALUES (306, 102, '人力资源管理', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:20:11', 'swing', '2020-08-12 13:20:11', '');
INSERT INTO `center_dept` VALUES (307, 100, '西语学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:35:00', 'swing', '2020-08-12 13:35:00', '');
INSERT INTO `center_dept` VALUES (308, 307, '英语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:35:17', 'swing', '2020-08-12 13:35:17', '');
INSERT INTO `center_dept` VALUES (309, 307, '德语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:35:32', 'swing', '2020-08-12 13:35:32', '');
INSERT INTO `center_dept` VALUES (310, 307, '法语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:35:49', 'swing', '2020-08-12 13:35:49', '');
INSERT INTO `center_dept` VALUES (311, 307, '西班牙语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:36:01', 'swing', '2020-08-12 13:36:01', '');
INSERT INTO `center_dept` VALUES (312, 307, '翻译（英语）', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:36:17', 'swing', '2020-08-12 13:36:17', '');
INSERT INTO `center_dept` VALUES (313, 100, '俄语学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:36:34', 'swing', '2020-08-12 13:36:34', '');
INSERT INTO `center_dept` VALUES (314, 313, '俄语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:36:50', 'swing', '2020-08-12 13:36:50', '');
INSERT INTO `center_dept` VALUES (315, 313, '翻译（俄语）', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:37:03', 'swing', '2020-08-12 13:37:03', '');
INSERT INTO `center_dept` VALUES (316, 100, '东语学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:37:15', 'swing', '2020-08-12 13:37:15', '');
INSERT INTO `center_dept` VALUES (317, 316, '日语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:37:31', 'swing', '2020-08-12 13:37:31', '');
INSERT INTO `center_dept` VALUES (318, 316, '朝鲜语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:37:44', 'swing', '2020-08-12 13:37:44', '');
INSERT INTO `center_dept` VALUES (319, 316, '阿拉伯语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:37:59', 'swing', '2020-08-12 13:37:59', '');
INSERT INTO `center_dept` VALUES (320, 266, '视觉传达设计', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:38:17', 'swing', '2020-08-12 13:38:17', '');
INSERT INTO `center_dept` VALUES (321, 266, '环境设计', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:38:35', 'swing', '2020-08-12 13:38:35', '');
INSERT INTO `center_dept` VALUES (322, 266, '服装与服装设计', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:38:49', 'swing', '2020-08-12 13:38:49', '');
INSERT INTO `center_dept` VALUES (323, 266, '音乐表演', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:39:13', 'swing', '2020-08-12 13:39:13', '');
INSERT INTO `center_dept` VALUES (324, 266, '绘画', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:39:24', 'swing', '2020-08-12 13:39:24', '');
INSERT INTO `center_dept` VALUES (325, 100, '历史文化旅游学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:39:52', 'swing', '2020-08-12 13:39:52', '');
INSERT INTO `center_dept` VALUES (326, 325, '历史学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:40:07', 'swing', '2020-08-12 13:40:07', '');
INSERT INTO `center_dept` VALUES (327, 325, '旅游管理', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:40:20', 'swing', '2020-08-12 13:40:20', '');
INSERT INTO `center_dept` VALUES (328, 325, '考古学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:40:33', 'swing', '2020-08-12 13:40:33', '');
INSERT INTO `center_dept` VALUES (329, 265, '数学与应用数学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:41:02', 'swing', '2020-08-12 13:41:02', '');
INSERT INTO `center_dept` VALUES (330, 265, '信息与计算科学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:41:25', 'swing', '2020-08-12 13:41:25', '');
INSERT INTO `center_dept` VALUES (331, 265, '统计学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:41:41', 'swing', '2020-08-12 13:41:41', '');
INSERT INTO `center_dept` VALUES (332, 100, '物理科学与技术学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:41:55', 'swing', '2020-08-12 13:41:55', '');
INSERT INTO `center_dept` VALUES (333, 332, '物理学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:42:09', 'swing', '2020-08-12 13:42:09', '');
INSERT INTO `center_dept` VALUES (334, 332, '应用物理学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:42:21', 'swing', '2020-08-12 13:42:21', '');
INSERT INTO `center_dept` VALUES (335, 100, '化学化工与材料学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:42:38', 'swing', '2020-08-12 13:42:38', '');
INSERT INTO `center_dept` VALUES (336, 335, '化学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:42:51', 'swing', '2020-08-12 13:42:51', '');
INSERT INTO `center_dept` VALUES (337, 335, '应用化学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:43:03', 'swing', '2020-08-12 13:43:03', '');
INSERT INTO `center_dept` VALUES (338, 335, '材料化学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:43:15', 'swing', '2020-08-12 13:43:15', '');
INSERT INTO `center_dept` VALUES (339, 335, '环境科学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:43:31', 'swing', '2020-08-12 13:43:31', '');
INSERT INTO `center_dept` VALUES (340, 335, '高分子材料与工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:43:43', 'swing', '2020-08-12 13:43:43', '');
INSERT INTO `center_dept` VALUES (341, 335, '化学工程与工艺', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:43:55', 'swing', '2020-08-12 13:43:55', '');
INSERT INTO `center_dept` VALUES (342, 335, '制药工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:44:07', 'swing', '2020-08-12 13:44:07', '');
INSERT INTO `center_dept` VALUES (343, 100, '生命科学学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:44:22', 'swing', '2020-08-12 13:44:22', '');
INSERT INTO `center_dept` VALUES (344, 343, '生物技术', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:44:50', 'swing', '2020-08-12 13:44:50', '');
INSERT INTO `center_dept` VALUES (345, 343, '生物工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:45:01', 'swing', '2020-08-12 13:45:01', '');
INSERT INTO `center_dept` VALUES (346, 343, '生物制药', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:45:17', 'swing', '2020-08-12 13:45:17', '');
INSERT INTO `center_dept` VALUES (347, 343, '食品科学与工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:45:33', 'swing', '2020-08-12 13:45:33', '');
INSERT INTO `center_dept` VALUES (348, 100, '机电工程学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:45:45', 'swing', '2020-08-12 13:45:45', '');
INSERT INTO `center_dept` VALUES (349, 348, '机械设计制造及其自动化', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:45:56', 'swing', '2020-08-12 13:45:56', '');
INSERT INTO `center_dept` VALUES (350, 348, '电气工程及其自动化', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:46:07', 'swing', '2020-08-12 13:46:07', '');
INSERT INTO `center_dept` VALUES (351, 100, '计算机科学技术学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:47:02', 'swing', '2020-08-12 13:47:02', '');
INSERT INTO `center_dept` VALUES (352, 351, '计算机科学与技术', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:47:33', 'swing', '2020-08-12 13:47:33', '');
INSERT INTO `center_dept` VALUES (353, 351, '物联网工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:47:49', 'swing', '2020-08-12 13:47:49', '');
INSERT INTO `center_dept` VALUES (354, 100, '建筑工程学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:48:30', 'swing', '2020-08-12 13:48:30', '');
INSERT INTO `center_dept` VALUES (355, 354, '土木工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:48:43', 'swing', '2020-08-12 13:48:43', '');
INSERT INTO `center_dept` VALUES (356, 354, '给排水科学与工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:48:55', 'swing', '2020-08-12 13:48:55', '');
INSERT INTO `center_dept` VALUES (357, 100, '水利电力学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:49:06', 'swing', '2020-08-12 13:49:06', '');
INSERT INTO `center_dept` VALUES (358, 357, '水利水电工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:49:19', 'swing', '2020-08-12 13:49:19', '');
INSERT INTO `center_dept` VALUES (359, 357, '农业水利工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:49:29', 'swing', '2020-08-12 13:49:29', '');
INSERT INTO `center_dept` VALUES (360, 357, '水文与水资源工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:49:44', 'swing', '2020-08-12 13:49:44', '');
INSERT INTO `center_dept` VALUES (361, 100, '现代农业与生态环境学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:50:09', 'swing', '2020-08-12 13:50:09', '');
INSERT INTO `center_dept` VALUES (362, 361, '农业资源与环境', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:50:22', 'swing', '2020-08-12 13:50:22', '');
INSERT INTO `center_dept` VALUES (363, 361, '种子科学与工程', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:50:38', 'swing', '2020-08-12 13:50:38', '');
INSERT INTO `center_dept` VALUES (364, 361, '植物保护', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:50:48', 'swing', '2020-08-12 13:50:48', '');
INSERT INTO `center_dept` VALUES (365, 100, '信息管理学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:51:01', 'swing', '2020-08-12 13:51:01', '');
INSERT INTO `center_dept` VALUES (366, 365, '编辑出版学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:51:16', 'swing', '2020-08-12 13:51:16', '');
INSERT INTO `center_dept` VALUES (367, 365, '信息管理与信息系统', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:51:32', 'swing', '2020-08-12 13:51:32', '');
INSERT INTO `center_dept` VALUES (368, 365, '图书馆学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:51:42', 'swing', '2020-08-12 13:51:42', '');
INSERT INTO `center_dept` VALUES (369, 365, '电子商务', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:51:59', 'swing', '2020-08-12 13:51:59', '');
INSERT INTO `center_dept` VALUES (370, 365, '档案学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:52:12', 'swing', '2020-08-12 13:52:12', '');
INSERT INTO `center_dept` VALUES (371, 100, '应用外语学院', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:52:28', 'swing', '2020-08-12 13:52:28', '');
INSERT INTO `center_dept` VALUES (372, 371, '商务英语', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:52:49', 'swing', '2020-08-12 13:52:49', '');
INSERT INTO `center_dept` VALUES (373, 371, '俄语（商务俄语）', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:53:00', 'swing', '2020-08-12 13:53:00', '');
INSERT INTO `center_dept` VALUES (374, 100, '俄语语言文学中心', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:53:10', 'swing', '2020-08-12 13:53:10', '');
INSERT INTO `center_dept` VALUES (375, 374, '外国语言文学及应用语言学', '#', '18845124018', '18845124018@163.com', 1, 1, 'swing', '2020-08-12 13:53:22', 'swing', '2020-08-12 13:53:22', '');

-- ----------------------------
-- Table structure for center_dept_course
-- ----------------------------
DROP TABLE IF EXISTS `center_dept_course`;
CREATE TABLE `center_dept_course`  (
  `dept_id` bigint(20) NOT NULL COMMENT '专业id',
  `course_id` bigint(20) NOT NULL COMMENT '课程id',
  PRIMARY KEY (`dept_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '榴莲题库-课程与专业关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of center_dept_course
-- ----------------------------
INSERT INTO `center_dept_course` VALUES (103, 104);
INSERT INTO `center_dept_course` VALUES (103, 105);
INSERT INTO `center_dept_course` VALUES (103, 106);
INSERT INTO `center_dept_course` VALUES (103, 107);
INSERT INTO `center_dept_course` VALUES (103, 110);
INSERT INTO `center_dept_course` VALUES (103, 111);
INSERT INTO `center_dept_course` VALUES (103, 112);
INSERT INTO `center_dept_course` VALUES (104, 105);
INSERT INTO `center_dept_course` VALUES (104, 106);
INSERT INTO `center_dept_course` VALUES (108, 108);
INSERT INTO `center_dept_course` VALUES (109, 108);
INSERT INTO `center_dept_course` VALUES (267, 112);
INSERT INTO `center_dept_course` VALUES (269, 112);
INSERT INTO `center_dept_course` VALUES (288, 110);
INSERT INTO `center_dept_course` VALUES (323, 108);

-- ----------------------------
-- Table structure for center_user
-- ----------------------------
DROP TABLE IF EXISTS `center_user`;
CREATE TABLE `center_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号（这里使用学生的学号）',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户校内地址',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '用户性别（M男 W女 N未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 (1 删除，0 未删除）',
  `is_use` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否使用（1 使用，0 停用）',
  `order_num` int(4) NULL DEFAULT NULL COMMENT '显示顺序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of center_user
-- ----------------------------
INSERT INTO `center_user` VALUES (1, 101, 'swing', '$2a$10$UbDw5O/bKeuIS7iiqM1.Te61KmfuDUte4bBCzano7DKULRBX6K.De', '阿赛', '中国', 'swing_for@126.com', '18845124453', '0', 'https://swing-durian.oss-cn-beijing.aliyuncs.com/f3/7f/f37f626ed41345b6952751d17704e50b.png', 0, 1, 1, 'xiaomi', '2020-09-08 20:50:38', 'swing', '2020-09-14 20:40:01', '哈哈哈哈哈哈嘻嘻嘻');

SET FOREIGN_KEY_CHECKS = 1;

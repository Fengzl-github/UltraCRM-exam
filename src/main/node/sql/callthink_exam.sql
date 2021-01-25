/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : callthink_exam

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2021-01-25 18:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_menu_bs
-- ----------------------------
DROP TABLE IF EXISTS `crm_menu_bs`;
CREATE TABLE `crm_menu_bs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `icon` varchar(50) DEFAULT '' COMMENT '图标',
  `level` int(4) unsigned DEFAULT '3' COMMENT '等级：1 全部展示 2 管理员账号 3 普通账号展示菜单',
  `m_id` varchar(10) DEFAULT '0' COMMENT '当前id',
  `menu_sort` int(4) unsigned DEFAULT '0' COMMENT '排序',
  `p_id` varchar(10) DEFAULT '0' COMMENT '上级id',
  `title` varchar(50) NOT NULL COMMENT '菜单名',
  `url` varchar(80) DEFAULT '' COMMENT '连接地址',
  `visible` int(4) unsigned DEFAULT '1' COMMENT '是否可见：0 隐藏，1 可见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of crm_menu_bs
-- ----------------------------
INSERT INTO `crm_menu_bs` VALUES ('1', '2021-01-05 14:16:25', 'el-icon-s-platform', '3', '1', '0', '0', '系统管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('2', '2021-01-05 14:17:13', 'el-icon-position', '3', '1-1', '0', '1', '测试', '/exam/test1', '1');
INSERT INTO `crm_menu_bs` VALUES ('3', '2021-01-05 14:18:02', 'el-icon-s-custom', '3', '2', '0', '0', '用户管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('4', '2021-01-05 14:18:59', 'el-icon-position', '3', '2-1', '0', '2', '用户列表', '/user/userlist', '1');
INSERT INTO `crm_menu_bs` VALUES ('5', '2021-01-05 14:19:49', 'el-icon-position', '3', '2-2', '1', '2', '用户组列表', '/user/groupslist', '1');
INSERT INTO `crm_menu_bs` VALUES ('6', '2021-01-05 14:16:25', 'el-icon-s-platform', '3', '3', '0', '0', '题库管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('7', '2021-01-05 14:17:13', 'el-icon-position', '3', '3-1', '0', '3', '题库', '/page/exam/topiclist', '1');
INSERT INTO `crm_menu_bs` VALUES ('8', '2021-01-05 14:18:02', 'el-icon-s-custom', '3', '4', '0', '0', '考试管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('9', '2021-01-05 14:18:59', 'el-icon-position', '3', '5', '0', '0', '阅卷管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('10', '2021-01-05 14:19:49', 'el-icon-position', '3', '6', '0', '0', '试后管理', '', '1');
INSERT INTO `crm_menu_bs` VALUES ('11', '2021-01-05 14:17:13', 'el-icon-position', '3', '4-1', '0', '4', '自主练习', '/page/exam/selftraning', '1');
INSERT INTO `crm_menu_bs` VALUES ('12', '2021-01-05 14:17:13', 'el-icon-position', '3', '4-2', '0', '4', '考试计划', '/page/exam/plan', '1');

-- ----------------------------
-- Table structure for t_exam_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_plan`;
CREATE TABLE `t_exam_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ghid` varchar(30) NOT NULL COMMENT '创建人工号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0:正常,1:删除',
  `operator` varchar(50) NOT NULL COMMENT '创建人姓名',
  `plan_id` varchar(20) NOT NULL COMMENT '考试计划编号',
  `plan_name` varchar(64) NOT NULL COMMENT '计划名称',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0:未发布,1:已发布,2:待公布成绩,3:已公布成绩',
  `test_end_time` varchar(20) NOT NULL COMMENT '考试结束时间',
  `test_form` tinyint(1) NOT NULL DEFAULT '0' COMMENT '考试形式：0:闭卷,1:开卷',
  `test_start_time` varchar(20) NOT NULL COMMENT '考试开始时间',
  `test_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发卷类型：0:随机,1:轮询',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3a504nhodguumadfi5otp2dbo` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_plan
-- ----------------------------
INSERT INTO `t_exam_plan` VALUES ('1', '2021-01-14 17:06:59', '8600', '0', '管理员', 'PL20210114170525', '第一个考试', '0', '2021-01-14 17:00:00', '1', '2021-01-31 17:00:00', '0');
INSERT INTO `t_exam_plan` VALUES ('2', '2021-01-15 10:34:08', '8600', '0', '管理员', 'PL20210115103408', '第二个考试', '0', '2021-01-22 00:00:00', '0', '2021-01-15 10:33:39', '1');

-- ----------------------------
-- Table structure for t_exam_test_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_test_paper`;
CREATE TABLE `t_exam_test_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0:正常,1:删除',
  `is_used` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否使用：0:失效,1:使用',
  `paper_id` varchar(20) NOT NULL COMMENT '试卷编号',
  `paper_name` varchar(64) NOT NULL COMMENT '试卷名称',
  `plan_id` varchar(20) NOT NULL COMMENT '考试计划编号',
  `plan_name` varchar(64) NOT NULL COMMENT '计划名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bmb0qykrbb3iphev9py7h2sbr` (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_test_paper
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_test_person
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_test_person`;
CREATE TABLE `t_exam_test_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ghid` varchar(30) NOT NULL COMMENT '参考人工号',
  `name` varchar(64) NOT NULL COMMENT '参考人姓名',
  `paper_id` varchar(20) DEFAULT NULL COMMENT '试卷编号',
  `paper_name` varchar(64) DEFAULT NULL COMMENT '试卷名称',
  `plan_id` varchar(20) NOT NULL COMMENT '考试计划编号',
  `plan_name` varchar(64) NOT NULL COMMENT '计划名称',
  `scoring_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '阅卷状态：1:未阅卷,1:已阅卷',
  `submit_time` varchar(64) DEFAULT NULL COMMENT '提交试卷时间',
  `test_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '答卷状态：1:未作答,1:已作答',
  `total_score` double(5,1) DEFAULT NULL COMMENT '考试总分',
  `used_time` varchar(64) DEFAULT NULL COMMENT '考试用时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_test_person
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_test_ques
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_test_ques`;
CREATE TABLE `t_exam_test_ques` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `correct_answer` text COMMENT '正确答案',
  `difficulty_level` tinyint(1) NOT NULL DEFAULT '3' COMMENT '难度等级：1:困难,2:较难,3:一般,4:较易,5:容易',
  `paper_id` varchar(20) NOT NULL COMMENT '试卷编号',
  `paper_name` varchar(64) NOT NULL COMMENT '试卷名称',
  `plan_id` varchar(20) NOT NULL COMMENT '考试计划编号',
  `plan_name` varchar(64) NOT NULL COMMENT '计划名称',
  `topic_content` text COMMENT '选项内容',
  `topic_des` text NOT NULL COMMENT '题干',
  `topic_id` varchar(20) NOT NULL COMMENT '试题编号',
  `topic_que_content` text COMMENT '选项内容,用于考试页面',
  `topic_score` double(5,1) NOT NULL DEFAULT '5.0' COMMENT '题目分值',
  `topic_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9bx713nkkl8jbinqvk5pj9cft` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_test_ques
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_test_result
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_test_result`;
CREATE TABLE `t_exam_test_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `correct_answer` text COMMENT '正确答案',
  `difficulty_level` tinyint(1) NOT NULL DEFAULT '3' COMMENT '难度等级：1:困难,2:较难,3:一般,4:较易,5:容易',
  `ep_replay` text COMMENT '考生答案',
  `ep_score` double(5,1) DEFAULT NULL COMMENT '考生得分',
  `ghid` varchar(30) NOT NULL COMMENT '参考人工号',
  `name` varchar(64) NOT NULL COMMENT '参考人姓名',
  `paper_id` varchar(20) DEFAULT NULL COMMENT '试卷编号',
  `paper_name` varchar(64) DEFAULT NULL COMMENT '试卷名称',
  `topic_content` text COMMENT '选项内容',
  `topic_des` text NOT NULL COMMENT '题干',
  `topic_id` varchar(20) NOT NULL COMMENT '试题编号',
  `topic_que_content` text COMMENT '选项内容,用于考试页面',
  `topic_score` double(5,1) NOT NULL DEFAULT '5.0' COMMENT '题目分值',
  `topic_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_test_result
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_topic`;
CREATE TABLE `t_exam_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `correct_answer` text COMMENT '正确答案',
  `difficulty_level` tinyint(1) NOT NULL DEFAULT '3' COMMENT '难度等级：1:困难,2:较难,3:一般,4:较易,5:容易',
  `ghid` varchar(30) NOT NULL COMMENT '创建人工号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0:正常,1:删除',
  `operator` varchar(50) NOT NULL COMMENT '创建人姓名',
  `topic_content` text COMMENT '选项内容',
  `topic_des` text NOT NULL COMMENT '题干',
  `topic_id` varchar(20) NOT NULL COMMENT '题目编号',
  `topic_score` double(5,1) NOT NULL DEFAULT '5.0' COMMENT '题目分值',
  `topic_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题',
  `topic_que_content` text COMMENT '选项内容,用于考试页面',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fkvakllnpgfrdo8khdkum8anc` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_exam_topic
-- ----------------------------
INSERT INTO `t_exam_topic` VALUES ('1', '2021-01-08 13:39:09', 'A', '3', '8600', '0', '管理员', '{\"A\":\"平壤\",\"B\":\"咸兴\",\"C\":\"开城\",\"D\":\"清津\"}', '朝鲜的首都是？', 'TP20210108133900', '5.0', '1', '[{\"CH\":false,\"V\":\"平壤\",\"K\":\"A\"},{\"CH\":false,\"V\":\"咸兴\",\"K\":\"B\"},{\"CH\":false,\"V\":\"开城\",\"K\":\"C\"},{\"CH\":false,\"V\":\"清津\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('2', '2021-01-08 13:39:09', 'B', '4', '8600', '0', '管理员', '{\"A\":\"釜山\",\"B\":\"首尔\",\"C\":\"仁川\",\"D\":\"大田\"}', '韩国的首都是？', 'TP20210108162800', '5.0', '1', '[{\"CH\":false,\"V\":\"釜山\",\"K\":\"A\"},{\"CH\":false,\"V\":\"首尔\",\"K\":\"B\"},{\"CH\":false,\"V\":\"仁川\",\"K\":\"C\"},{\"CH\":false,\"V\":\"大田\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('3', '2021-01-08 18:37:28', 'C', '5', '8600', '0', '管理员', '{\"A\":\"上海\",\"B\":\"南京\",\"C\":\"北京\",\"D\":\"深圳\"}', '中国的首都是？', 'TP20210108183728', '5.0', '1', '[{\"CH\":false,\"V\":\"上海\",\"K\":\"A\"},{\"CH\":false,\"V\":\"南京\",\"K\":\"B\"},{\"CH\":false,\"V\":\"北京\",\"K\":\"C\"},{\"CH\":false,\"V\":\"深圳\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('4', '2021-01-12 10:39:38', 'B', '2', '8600', '0', '管理员', '{\"A\":\"新山市\",\"B\":\"吉隆坡\",\"C\":\"沙亚南\",\"D\":\"古晋\"}', '\"马来西亚\"的首都是__?', 'TP20210112103938', '1.5', '1', '[{\"CH\":false,\"V\":\"新山市\",\"K\":\"A\"},{\"CH\":false,\"V\":\"吉隆坡\",\"K\":\"B\"},{\"CH\":false,\"V\":\"沙亚南\",\"K\":\"C\"},{\"CH\":false,\"V\":\"古晋\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('5', '2021-01-12 11:14:30', 'C', '2', '8600', '0', '管理员', '{\"A\":\"塞维利亚\",\"B\":\"加利西亚\",\"C\":\"马德里\",\"D\":\"巴伦西亚\"}', '西班牙的首都是__?', 'TP20210112111430', '5.0', '1', '[{\"CH\":false,\"V\":\"塞维利亚\",\"K\":\"A\"},{\"CH\":false,\"V\":\"加利西亚\",\"K\":\"B\"},{\"CH\":false,\"V\":\"马德里\",\"K\":\"C\"},{\"CH\":false,\"V\":\"巴伦西亚\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('6', '2021-01-13 17:59:00', '金边', '1', '8600', '0', '管理员', '{\"A\":\"金边\"}', '柬埔寨的首都是？', 'TP20210113175900', '5.0', '3', '[{\"V\":\"金边\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('7', '2021-01-13 18:00:18', '新加坡', '2', '8600', '0', '管理员', '{\"A\":\"新加坡\"}', '新加坡的首都是__?', 'TP20210113180018', '5.0', '3', '[{\"V\":\"新加坡\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('8', '2021-01-13 18:01:45', '伦敦', '3', '8600', '0', '管理员', '{\"A\":\"伦敦\"}', '英国的首都是？', 'TP20210113180145', '5.0', '3', '[{\"V\":\"伦敦\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('9', '2021-01-13 18:02:12', '巴黎', '4', '8600', '0', '管理员', '{\"A\":\"巴黎\"}', '法国的首都是？', 'TP20210113180212', '5.0', '3', '[{\"V\":\"巴黎\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('10', '2021-01-13 18:03:05', '雅典', '3', '8600', '0', '管理员', '{\"A\":\"雅典\"}', '希腊的首都是？', 'TP20210113180305', '5.0', '3', '[{\"V\":\"雅典\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('11', '2021-01-13 18:03:33', '莫斯科', '5', '8600', '0', '管理员', '{\"A\":\"莫斯科\"}', '俄罗斯的首都是？', 'TP20210113180333', '5.0', '3', '[{\"V\":\"莫斯科\",\"K\":\"A\"}]');
INSERT INTO `t_exam_topic` VALUES ('12', '2021-01-14 14:31:49', 'BC', '1', '8600', '0', '管理员', '{\"A\":\"缅甸的首都是多哈\",\"B\":\"老挝的首都是万象\",\"C\":\"乌克兰的首都是基辅\",\"D\":\"白俄罗斯的首都是莫斯科\"}', '下列选项正确的是？', 'TP20210114143149', '5.0', '2', '[{\"CH\":false,\"V\":\"缅甸的首都是多哈\",\"K\":\"A\"},{\"CH\":false,\"V\":\"老挝的首都是万象\",\"K\":\"B\"},{\"CH\":false,\"V\":\"乌克兰的首都是基辅\",\"K\":\"C\"},{\"CH\":false,\"V\":\"白俄罗斯的首都是莫斯科\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('13', '2021-01-14 14:34:30', 'BC', '2', '8600', '0', '管理员', '{\"A\":\"俄罗斯的首都是明斯克\",\"B\":\"芬兰的首都是赫尔辛基\",\"C\":\"丹麦的首都是哥本哈根\",\"D\":\"土耳其的首都是卢森堡\"}', '一下正确的是', 'TP20210114143430', '5.0', '4', '[{\"CH\":false,\"V\":\"俄罗斯的首都是明斯克\",\"K\":\"A\"},{\"CH\":false,\"V\":\"芬兰的首都是赫尔辛基\",\"K\":\"B\"},{\"CH\":false,\"V\":\"丹麦的首都是哥本哈根\",\"K\":\"C\"},{\"CH\":false,\"V\":\"土耳其的首都是卢森堡\",\"K\":\"D\"}]');
INSERT INTO `t_exam_topic` VALUES ('14', '2021-01-14 14:35:03', '圣诞节付货款健康健康哈克龙放假哈里斯看到就烦哈卡死了京东方好可怜', '3', '8600', '0', '管理员', '{}', '说说你对生活的看法', 'TP20210114143503', '5.0', '5', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `addr` varchar(100) DEFAULT NULL COMMENT '地址',
  `age` int(3) NOT NULL DEFAULT '18' COMMENT '年龄',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `ghid` varchar(30) NOT NULL COMMENT '工号',
  `heard_url` varchar(100) DEFAULT NULL COMMENT '头像',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0：正常；1：删除',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `pass` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0：男；1：女',
  `level` tinyint(1) DEFAULT '1' COMMENT '权限：1：最大权限；2：管理员；3：普通账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '2020-12-21 13:38:02', '亦庄经济开发区', '24', '18233521351@163.com', '8600', null, '0', '18233521351', '管理员', null, '1234', '0', '1');

-- ----------------------------
-- Table structure for t_words_10
-- ----------------------------
DROP TABLE IF EXISTS `t_words_10`;
CREATE TABLE `t_words_10` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `founder` varchar(64) NOT NULL COMMENT '创建人',
  `ghid` varchar(64) NOT NULL COMMENT '创建人ghid',
  `key_word` varchar(64) NOT NULL COMMENT '关键词',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态:0-正常;1-失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_words_10
-- ----------------------------
INSERT INTO `t_words_10` VALUES ('1', '2021-01-25 15:11:43', '管理员', '8600', '你好', '0');
INSERT INTO `t_words_10` VALUES ('2', '2021-01-25 15:12:00', '管理员', '8600', '您好', '0');
INSERT INTO `t_words_10` VALUES ('3', '2021-01-25 15:12:30', '管理员', '8600', '尊敬', '0');
INSERT INTO `t_words_10` VALUES ('4', '2021-01-25 15:13:39', '管理员', '8600', '阳光', '0');
INSERT INTO `t_words_10` VALUES ('5', '2021-01-25 15:13:55', '管理员', '8600', '开朗', '0');
INSERT INTO `t_words_10` VALUES ('6', '2021-01-25 15:16:23', '管理员', '8600', '明亮', '0');
INSERT INTO `t_words_10` VALUES ('7', '2021-01-25 15:16:49', '管理员', '8600', '笑容', '0');

-- ----------------------------
-- Table structure for t_words_20
-- ----------------------------
DROP TABLE IF EXISTS `t_words_20`;
CREATE TABLE `t_words_20` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `founder` varchar(64) NOT NULL COMMENT '创建人',
  `ghid` varchar(64) NOT NULL COMMENT '创建人ghid',
  `key_word` varchar(64) NOT NULL COMMENT '关键词',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态:0-正常;1-失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_words_20
-- ----------------------------

-- ----------------------------
-- Table structure for t_words_tables
-- ----------------------------
DROP TABLE IF EXISTS `t_words_tables`;
CREATE TABLE `t_words_tables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `w_id` varchar(6) NOT NULL DEFAULT '10' COMMENT '表后缀ID',
  `w_name` varchar(64) NOT NULL COMMENT '词类型',
  `w_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态:0-正常;1-失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_words_tables
-- ----------------------------
INSERT INTO `t_words_tables` VALUES ('1', '2021-01-25 13:46:58', '10', '关键词', '0');
INSERT INTO `t_words_tables` VALUES ('2', '2021-01-25 13:47:14', '20', '敏感词', '0');

-- ----------------------------
-- Table structure for t_words_temp
-- ----------------------------
DROP TABLE IF EXISTS `t_words_temp`;
CREATE TABLE `t_words_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `founder` varchar(64) NOT NULL COMMENT '创建人',
  `ghid` varchar(64) NOT NULL COMMENT '创建人ghid',
  `key_word` varchar(64) NOT NULL COMMENT '关键词',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态:0-正常;1-失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_words_temp
-- ----------------------------

-- ----------------------------
-- Table structure for t_words_update
-- ----------------------------
DROP TABLE IF EXISTS `t_words_update`;
CREATE TABLE `t_words_update` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `w_id` varchar(6) NOT NULL DEFAULT '10' COMMENT '表后缀ID',
  `w_name` varchar(64) NOT NULL COMMENT '词类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_words_update
-- ----------------------------

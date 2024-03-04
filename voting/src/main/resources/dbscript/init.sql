
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_candidate
-- ----------------------------
DROP TABLE IF EXISTS `t_candidate`;
CREATE TABLE `t_candidate`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `candidate_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `candidate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `vote_counting` int(11) NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE INDEX `UK_jato7jb7jcn0mkcdkxjtg2yd2`(`candidate_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recorder
-- ----------------------------
DROP TABLE IF EXISTS `t_recorder`;
CREATE TABLE `t_recorder`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `candidate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                               `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                               `vote_time` datetime(6) NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE INDEX `UK_ahs9mj6tx9ivc60j5qiomm627`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `UK_bkpm7njy2ort1yoiddc7jg8gj`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `account_non_expired` bit(1) NOT NULL,
                           `account_non_locked` bit(1) NOT NULL,
                           `credentials_non_expired` bit(1) NOT NULL,
                           `enabled` bit(1) NOT NULL,
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           `user_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                           `is_voted` bit(1) NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `UK_3ol67qm82xfpw0uqrjma8vi4q`(`user_number`) USING BTREE,
                           UNIQUE INDEX `UK_jhib4legehrm4yscx9t3lirqi`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `user_id` bigint(20) NOT NULL,
                                `role_id` bigint(20) NOT NULL,
                                INDEX `FKa9c8iiy6ut0gnx491fqx4pxam`(`role_id`) USING BTREE,
                                INDEX `FKq5un6x7ecoef5w1n39cop66kl`(`user_id`) USING BTREE,
                                CONSTRAINT `FKa9c8iiy6ut0gnx491fqx4pxam` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                CONSTRAINT `FKq5un6x7ecoef5w1n39cop66kl` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `t_role` VALUES (1, 'ROLE_user', 'Eligible voting general user');
INSERT INTO `t_role` VALUES (2, 'ROLE_admin', 'Administrator with permissions to vote and view voting details');


INSERT INTO `t_candidate` VALUES (1, 'Candidate_A is from UnitA','Candidate_A', 0);
INSERT INTO `t_candidate` VALUES (2, 'Candidate_B is from UnitB','Candidate_B', 0);
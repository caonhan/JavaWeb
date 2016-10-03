-- modify records for test perpose
DELETE FROM `role` WHERE `role_id` = 'TT';
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('PGD', 'Phó Giám Đốc', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id`= 'PGD';
DELETE FROM `role` WHERE `role_id` = 'LT';
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('DL', 'Delete', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id` ='DL';
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('CS1', 'Case 1', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id` ='CS1';
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('CS2', 'Case 2', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id` ='CS2';
INSERT INTO `serp`.`role_function` (`role_id`, `function_id`) VALUES ('CS2', 'FT1') ON DUPLICATE KEY UPDATE `role_id` ='CS2';
INSERT INTO `serp`.`role_function` (`role_id`, `function_id`) VALUES ('CS2', 'FT2') ON DUPLICATE KEY UPDATE `role_id` ='CS2';
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('CS3', 'Case 3', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id` ='CS3';
DELETE FROM `role_function` WHERE `role_id` = 'CS3';-- reset số function về 0 sau khi chạy test
INSERT INTO `serp`.`role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES ('CS4', 'Case 4', NULL, NULL, NULL, NULL, NULL) ON DUPLICATE KEY UPDATE `role_id` ='CS4';
INSERT INTO `serp`.`role_function` (`role_id`, `function_id`) VALUES ('CS4', 'FT1') ON DUPLICATE KEY UPDATE `role_id` ='CS4';
INSERT INTO `serp`.`role_function` (`role_id`, `function_id`) VALUES ('CS4', 'FT2') ON DUPLICATE KEY UPDATE `role_id` ='CS4';
DELETE FROM `role_function` WHERE `role_id` = 'CS4' and `function_id` = 'FT3';


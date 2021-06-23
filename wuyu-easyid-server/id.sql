CREATE TABLE `t_id_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `biz_type` int(2) NOT NULL,
  `max_id` bigint(20) NOT NULL,
  `step` int(10) NOT NULL,
  `version` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
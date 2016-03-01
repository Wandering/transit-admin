CREATE TABLE `zgk_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(256) NOT NULL COMMENT '明层',
  `telphone` varchar(256) NOT NULL COMMENT '电话',
  `address` varchar(256) NOT NULL COMMENT '地址',
  `areaId` bigint(20) NOT NULL COMMENT '区域Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='服务商信息| 服务商信息|服务商模块|CreateBaseDomain\r\r\n 服务商信息';
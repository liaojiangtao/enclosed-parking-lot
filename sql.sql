DROP TABLE IF EXISTS parking_lot_info;
CREATE TABLE `parking_lot_info` (
`id` INT NOT NULL AUTO_INCREMENT COMMENT '编号',
`parking_lot_id` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '停车场ID',
`parking_lot_name` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '停车场名称',
`effect_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '停车后场有效时间',
`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '更新时间',
`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '创建时间',
PRIMARY KEY ( `id` ),
UNIQUE KEY `UK_PARINGLOTID` ( parking_lot_id )
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '停车场信息表';

DROP TABLE IF EXISTS parking_real;
CREATE TABLE `parking_real` (
`id` INT NOT NULL AUTO_INCREMENT COMMENT '编号',
`parking_order` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '停车订单',
`plate_number` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '车牌',
`plate_color` INT NOT NULL DEFAULT '0' COMMENT '车牌颜色',
`plate_type` INT NOT NULL DEFAULT '0' COMMENT '车牌类型',
`car_color` INT NOT NULL DEFAULT '0' COMMENT '车辆颜色',
`car_type` INT NOT NULL DEFAULT '0' COMMENT '车辆类型',
`arrive_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '入场时间',
`entrance_num` INT NOT NULL DEFAULT '0' COMMENT '停车场入口编号',
`arrive_pic_url` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '入场图片url',
`arrive_pic_upStatus` INT NOT NULL DEFAULT '0' COMMENT '入场图片上传状态',
`arrive_up_status` INT NOT NULL DEFAULT '0' COMMENT '入场记录上传状态',
`enter_type` INT NOT NULL DEFAULT '0' COMMENT '入场类型 1:自动抬杆 2:手动抬杆',
`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '更新时间',
`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '创建时间',
PRIMARY KEY ( `id` ),
UNIQUE KEY `UK_PARING_R_ORDER` ( parking_order )
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '停车场实时停车信息表';

DROP TABLE IF EXISTS parking_history;
CREATE TABLE `parking_history` (
`id` INT NOT NULL AUTO_INCREMENT COMMENT '编号',
`parking_order` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '停车订单',
`should_pay` INT NOT NULL DEFAULT '0' COMMENT '应缴金额',
`actual_pay` INT NOT NULL DEFAULT '0' COMMENT '实收金额',
`plate_number` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '车牌',
`plate_color` INT NOT NULL DEFAULT '0' COMMENT '车牌颜色',
`plate_type` INT NOT NULL DEFAULT '0' COMMENT '车牌类型',
`car_color` INT NOT NULL DEFAULT '0' COMMENT '车辆颜色',
`car_type` INT NOT NULL DEFAULT '0' COMMENT '车辆类型',
`arrive_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '入场时间',
`entrance_num` INT NOT NULL DEFAULT '0' COMMENT '停车场入口编号',
`arrive_pic_url` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '入场图片url',
`arrive_pic_upStatus` INT NOT NULL DEFAULT '0' COMMENT '入场图片上传状态',
`arrive_up_status` INT NOT NULL DEFAULT '0' COMMENT '入场记录上传状态',
`leave_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '出场时间',
`exit_num` INT NOT NULL DEFAULT '0' COMMENT '停车场出口编号',
`leave_pic_url` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '出场图片url',
`leave_pic_upStatus` INT NOT NULL DEFAULT '0' COMMENT '出场图片上传状态',
`leave_up_status` INT NOT NULL DEFAULT '0' COMMENT '出场记录上传状态',
`enter_type` INT NOT NULL DEFAULT '0' COMMENT '入场类型 1:自动抬杆 2:手动抬杆',
`leave_type` INT NOT NULL DEFAULT '0' COMMENT '出场类型 1:自动抬杆 2:手动抬杆',
`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '更新时间',
`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ( ) COMMENT '创建时间',
PRIMARY KEY ( `id` ),
UNIQUE KEY `UK_PARING_H_ORDER` ( parking_order )
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '停车场历史停车信息表';
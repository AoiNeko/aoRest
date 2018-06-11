create user 'aoineko'@'%' identified by '456123';
create database aoineko;
use aoineko;
grant all privileges on aoineko to 'aoineko'@'%' with grant option;

CREATE TABLE `post` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NULL DEFAULT NULL,
	`abstract_text` VARCHAR(200) NULL DEFAULT NULL,
	`content` TEXT NULL,
	`tag` VARCHAR(50) NULL DEFAULT NULL,
	`gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`gmt_update` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
COMMENT='blog post'
ENGINE=InnoDB
;




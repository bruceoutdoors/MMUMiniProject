-- MySQL Workbench Synchronization
-- Generated: 2016-01-22 21:24
-- Model: MMU Mini Project Model
-- Version: 1.0
-- Project: MMU Mini Project Model
-- Author: Emyliana

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `user` 
CHANGE COLUMN `user_name` `user_name` VARCHAR(45) NOT NULL ,
ADD COLUMN `user_password` VARCHAR(45) NOT NULL AFTER `user_active`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

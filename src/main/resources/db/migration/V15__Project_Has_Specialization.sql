-- MySQL Workbench Synchronization
-- Generated: 2016-01-23 16:18
-- Model: MMU Mini Project Model
-- Version: 1.0
-- Project: MMU Mini Project Model
-- Author: Emyliana

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `project` 
ADD COLUMN `spec_id` INT(11) NOT NULL AFTER `student_id`,
ADD INDEX `fk_project_specialization1_idx` (`spec_id` ASC);

ALTER TABLE `project` 
ADD CONSTRAINT `fk_project_specialization1`
  FOREIGN KEY (`spec_id`)
  REFERENCES `specialization` (`spec_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

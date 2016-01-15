-- MySQL Workbench Synchronization
-- Generated: 2016-01-16 03:13
-- Model: MMU Mini Project Model
-- Version: 1.0
-- Project: MMU Mini Project Model
-- Author: Emyliana

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `mmuminiproject`.`student` 
DROP FOREIGN KEY `fk_STUDENT_PROJECT1`;

ALTER TABLE `mmuminiproject`.`student` 
DROP COLUMN `project_id`,
DROP INDEX `fk_STUDENT_PROJECT1_idx` ;

ALTER TABLE `mmuminiproject`.`faculty` 
CHANGE COLUMN `fac_name` `fac_name` VARCHAR(40) NOT NULL ;

ALTER TABLE `mmuminiproject`.`specialization` 
CHANGE COLUMN `spec_name` `spec_name` VARCHAR(45) NOT NULL ;

ALTER TABLE `mmuminiproject`.`project` 
ADD COLUMN `student_id` INT(11) NOT NULL AFTER `lecturer_id`,
ADD INDEX `fk_project_student1_idx` (`student_id` ASC);

ALTER TABLE `mmuminiproject`.`project` 
ADD CONSTRAINT `fk_project_student1`
  FOREIGN KEY (`student_id`)
  REFERENCES `mmuminiproject`.`student` (`student_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

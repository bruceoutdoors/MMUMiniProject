-- MySQL Workbench Synchronization
-- Generated: 2016-01-16 02:10
-- Model: MMU Mini Project Model
-- Version: 1.0
-- Project: MMU Mini Project Model
-- Author: Emyliana

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE `user` (
  `user_id` INT(11) NOT NULL,
  `user_name` VARCHAR(45) NULL DEFAULT NULL,
  `user_email` VARCHAR(45) NULL DEFAULT NULL,
  `user_tel` VARCHAR(45) NULL DEFAULT NULL,
  `user_lastSignIn` DATETIME NULL DEFAULT NULL,
  `user_status` VARCHAR(45) NULL DEFAULT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_USER_ROLE1_idx` (`role_id` ASC),
  CONSTRAINT `fk_USER_ROLE1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `lecturer` (
  `lecturer_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`lecturer_id`),
  INDEX `fk_LECTURER_USER1_idx` (`user_id` ASC),
  CONSTRAINT `fk_LECTURER_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `student` (
  `student_id` INT(11) NOT NULL,
  `fac_id` VARCHAR(10) NOT NULL,
  `spec_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `project_id` INT(11) NOT NULL,
  PRIMARY KEY (`student_id`),
  INDEX `fk_STUDENT_FACULTY1_idx` (`fac_id` ASC),
  INDEX `fk_STUDENT_SPECIALIZATION1_idx` (`spec_id` ASC),
  INDEX `fk_STUDENT_USER1_idx` (`user_id` ASC),
  INDEX `fk_STUDENT_PROJECT1_idx` (`project_id` ASC),
  CONSTRAINT `fk_STUDENT_FACULTY1`
    FOREIGN KEY (`fac_id`)
    REFERENCES `faculty` (`fac_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STUDENT_SPECIALIZATION1`
    FOREIGN KEY (`spec_id`)
    REFERENCES `specialization` (`spec_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STUDENT_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STUDENT_PROJECT1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `admin` (
  `admin_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  INDEX `fk_ADMIN_USER1_idx` (`user_id` ASC),
  CONSTRAINT `fk_ADMIN_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `faculty` (
  `fac_id` VARCHAR(10) NOT NULL,
  `fac_name` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`fac_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `specialization` (
  `spec_id` INT(11) NOT NULL,
  `spec_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`spec_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `project` (
  `project_id` INT(11) NOT NULL,
  `project_title` VARCHAR(45) NULL DEFAULT NULL,
  `project_grade` VARCHAR(2) NULL DEFAULT NULL,
  `start date` DATETIME NULL DEFAULT NULL,
  `due_date` DATETIME NULL DEFAULT NULL,
  `sub_date` DATETIME NULL DEFAULT NULL,
  `project_description` VARCHAR(80) NULL DEFAULT NULL,
  `project_status` VARCHAR(15) NULL DEFAULT NULL,
  `eva_comment` VARCHAR(50) NULL DEFAULT NULL,
  `lecturer_id` INT(11) NOT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `fk_PROJECT_LECTURER1_idx` (`lecturer_id` ASC),
  CONSTRAINT `fk_PROJECT_LECTURER1`
    FOREIGN KEY (`lecturer_id`)
    REFERENCES `lecturer` (`lecturer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `comment` (
  `comment_id` INT(11) NOT NULL,
  `comment_description` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_COMMENT_USER1_idx` (`user_id` ASC),
  CONSTRAINT `fk_COMMENT_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `role` (
  `role_id` INT(11) NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

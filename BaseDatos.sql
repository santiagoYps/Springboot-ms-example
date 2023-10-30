-- MySQL Script generated by MySQL Workbench
-- Wed Oct 25 17:30:07 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema banco_test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `banco_test` ;

-- -----------------------------------------------------
-- Schema banco_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `banco_test` DEFAULT CHARACTER SET utf8 ;
USE `banco_test` ;

-- -----------------------------------------------------
-- Table `banco_test`.`clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco_test`.`clients` ;

CREATE TABLE IF NOT EXISTS `banco_test`.`clients` (
  `client_id` VARCHAR(40) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `age` INT NOT NULL,
  `identification` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `state` TINYINT(1), 
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `identification_UNIQUE` (`identification` ASC) VISIBLE,
  INDEX `identification` (`identification` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco_test`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco_test`.`accounts` ;

CREATE TABLE IF NOT EXISTS `banco_test`.`accounts` (
  `number` BIGINT NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `balance` DOUBLE DEFAULT 0,
  `state` TINYINT NOT NULL,
  `client_id` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`number`),
  INDEX `client_id_fk_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `client_id_fk`
    FOREIGN KEY (`client_id`)
    REFERENCES `banco_test`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `banco_test`.`movements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `banco_test`.`movements` ;

CREATE TABLE IF NOT EXISTS `banco_test`.`movements` (
  `movement_id` VARCHAR(40) NOT NULL,
  `date` DATETIME NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `initial_balance` DOUBLE NOT NULL,
  `movement` DOUBLE NOT NULL,
  `current_balance` DOUBLE NOT NULL,
  `account_number` BIGINT NOT NULL,
  `client_id` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`movement_id`),
  INDEX `account_id_fk_idx` (`account_number` ASC) VISIBLE,
  INDEX `client_id_fk_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `account_number_fk`
    FOREIGN KEY (`account_number`)
    REFERENCES `banco_test`.`accounts` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `client_id_mv_fk`
    FOREIGN KEY (`client_id`)
    REFERENCES `banco_test`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
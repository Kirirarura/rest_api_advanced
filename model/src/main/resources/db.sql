-- -----------------------------------------------------
-- Schema gift_certificates
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gift_certificates`;

-- -----------------------------------------------------
-- Schema gift_certificates
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gift_certificates` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `gift_certificates`;

-- -----------------------------------------------------
-- Table `gift_certificates`.`gift_certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates`.`gift_certificate`;

CREATE TABLE IF NOT EXISTS `gift_certificates`.`gift_certificate`
(
    `id`               INT          NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(45)  NOT NULL,
    `description`      VARCHAR(150) NULL,
    `price`            DECIMAL      NOT NULL,
    `duration`         TINYINT      NOT NULL,
    `create_date`      DATE         NOT NULL,
    `last_update_date` DATE         NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `gift_certificates`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates`.`tag`;

CREATE TABLE IF NOT EXISTS `gift_certificates`.`tag`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `gift_certificates`.`gift_certificate_has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates`.`gift_certificate_has_tag`;

CREATE TABLE IF NOT EXISTS `gift_certificates`.`gift_certificate_has_tag`
(
    `gift_certificate_id` INT NOT NULL,
    `tag_id`              INT NOT NULL,
    PRIMARY KEY (`gift_certificate_id`, `tag_id`),
    INDEX `fk_gift_certificate_has_tag_tag1_idx` (`tag_id` ASC) VISIBLE,
    INDEX `fk_gift_certificate_has_tag_gift_certificate_idx` (`gift_certificate_id` ASC) VISIBLE,
    CONSTRAINT `fk_gift_certificate_has_tag_gift_certificate`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES `gift_certificates`.`gift_certificate` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_gift_certificate_has_tag_tag1`
        FOREIGN KEY (`tag_id`)
            REFERENCES `gift_certificates`.`tag` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

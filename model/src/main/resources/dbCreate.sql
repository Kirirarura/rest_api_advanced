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
DROP TABLE IF EXISTS `gift_certificates`.`gift_certificates`;

CREATE TABLE IF NOT EXISTS `gift_certificates`.`gift_certificates`
(
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(45)    NOT NULL,
    `description`      VARCHAR(150)   NULL,
    `price`            DECIMAL(10, 2) NOT NULL,
    `duration`         TINYINT        NOT NULL,
    `create_date`      VARCHAR(40)    NOT NULL,
    `last_update_date` VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `gift_certificates`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift_certificates`.`tags`;

CREATE TABLE IF NOT EXISTS `gift_certificates`.`tags`
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
    `id`                  INT AUTO_INCREMENT,
    `gift_certificate_id` INT,
    `tag_id`              INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`gift_certificate_id`)
        REFERENCES `gift_certificates`.`gift_certificates` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (`tag_id`)
        REFERENCES `gift_certificates`.`tags` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

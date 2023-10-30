package com.banco.test.personasms.persistence.entity;


import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;

/**
 * these class map the following table:
 * CREATE TABLE IF NOT EXISTS `pichincha_test`.`clients` (
 *   `client_id` VARCHAR(40) NOT NULL,
 *   `name` VARCHAR(100) NOT NULL,
 *   `gender` ENUM('M', 'F', 'O') NOT NULL,
 *   `age` INT NOT NULL,
 *   `identification` VARCHAR(20) NOT NULL,
 *   `address` VARCHAR(50) NOT NULL,
 *   `phone` VARCHAR(20) NOT NULL,
 *   PRIMARY KEY (`client_id`),
 *   UNIQUE INDEX `identification_UNIQUE` (`identification` ASC) VISIBLE,
 *   INDEX `identification` (`identification` ASC) VISIBLE)
 * ENGINE = InnoDB;
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "clients")
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "identification", nullable = false, unique = true)
    private String identification;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "state")
    private boolean state;
}

package com.banco.test.personasms.persistence.entity;

import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class Person {

    private String name;

    private String gender;

    private Integer age;

    private String identification;

    private String address;

    private String phone;

}

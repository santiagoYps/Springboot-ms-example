package com.banco.test.personasms.dominio.dto;

import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientUpdateDTO {

    @NotBlank(message = "El id del cliente es obligatorio")
    @NotNull(message = "El id del cliente es obligatorio")
    private String id;

    private String nombre;

    private GenderEnum genero;

    @Min(value = 10, message = "La edad debe ser mayor o igual a 10")
    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;

    private String password;

    private Boolean estado;
}

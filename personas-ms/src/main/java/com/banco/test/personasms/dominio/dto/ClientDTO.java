package com.banco.test.personasms.dominio.dto;

import com.banco.test.personasms.dominio.utils.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO {
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El genero es obligatorio")
    private GenderEnum genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 10, message = "La edad debe ser mayor o igual a 10")
    private Integer edad;

    @NotBlank(message = "El documento de identificacion es obligatorio")
    @NotNull(message = "El documento de identificacion es obligatorio")
    private String identificacion;

    @NotBlank(message = "La direccion es obligatoria")
    @NotNull(message = "La direccion es obligatoria")
    private String direccion;

    @NotBlank(message = "El telefono es obligatorio")
    @NotNull(message = "El telefono es obligatorio")
    private String telefono;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private Boolean estado;
}

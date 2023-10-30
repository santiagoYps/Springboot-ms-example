package com.banco.test.cuentasms.dominio.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private Boolean estado;
}

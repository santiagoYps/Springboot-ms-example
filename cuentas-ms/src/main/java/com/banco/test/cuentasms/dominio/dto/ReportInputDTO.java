package com.banco.test.cuentasms.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportInputDTO {
    @NotNull(message = "Debe espeficiar el numero de identificación del cliente")
    @NotBlank(message = "Debe espeficiar el numero de identificación del Cliente")
    String numeroIdCliente;
}

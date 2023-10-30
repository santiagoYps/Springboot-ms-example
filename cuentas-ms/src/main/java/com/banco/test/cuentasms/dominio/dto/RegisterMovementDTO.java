package com.banco.test.cuentasms.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterMovementDTO {

    @NotNull(message = "Debe especificar el monto del movimiento")
    private BigDecimal monto;

    @NotNull(message = "Debe escepcificar la cuenta")
    private Long cuenta;

    @NotBlank(message = "Debe especificar el cliente")
    @NotNull(message = "Debe especificar el cliente")
    private String cliente;
}

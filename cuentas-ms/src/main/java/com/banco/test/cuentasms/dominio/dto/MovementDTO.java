package com.banco.test.cuentasms.dominio.dto;

import com.banco.test.cuentasms.dominio.utils.enums.MovementTypeEnum;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementDTO {

    private String id;

    private LocalDateTime fecha;

    private MovementTypeEnum tipo;

    private BigDecimal saldoInicial;

    private BigDecimal monto;

    private BigDecimal saldoActual;

    private Long cuenta;

    private String cliente;
}

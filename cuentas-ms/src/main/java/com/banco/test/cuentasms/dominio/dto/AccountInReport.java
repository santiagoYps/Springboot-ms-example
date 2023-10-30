package com.banco.test.cuentasms.dominio.dto;

import com.banco.test.cuentasms.dominio.utils.enums.AccountTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class AccountInReport {
    private Long numero;
    private AccountTypeEnum tipo;
    private BigDecimal saldo;
    private Boolean estado;

    //JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MovementDTO> movimientos;
}

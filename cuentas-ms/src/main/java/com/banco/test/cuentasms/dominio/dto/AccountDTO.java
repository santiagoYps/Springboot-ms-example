package com.banco.test.cuentasms.dominio.dto;

import com.banco.test.cuentasms.dominio.utils.enums.AccountTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountDTO {

        @NotNull(message = "Numero de cuenta (propiedad 'numero') es obligatorio")
        private Long numero;

        @NotNull(message = "Tipo de cuenta (propiedad 'tipo') es obligatorio")
        private AccountTypeEnum tipo;

        private BigDecimal saldo;

        private Boolean estado;

        @NotNull(message = "Id del cliente (propiedad 'cliente') es obligatorio")
        @NotBlank(message = "Id del Cliente (propiedad 'cliente') es obligatorio")
        private String cliente;
}

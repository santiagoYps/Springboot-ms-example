package com.banco.test.cuentasms.dominio.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ReportDTO {

    private String numeroIdCliente;
    private String nombreCliente;

    List<AccountInReport> cuentas;

}

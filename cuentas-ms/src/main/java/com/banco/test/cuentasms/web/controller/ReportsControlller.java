package com.banco.test.cuentasms.web.controller;

import com.banco.test.cuentasms.dominio.dto.ReportDTO;
import com.banco.test.cuentasms.dominio.dto.ReportInputDTO;
import com.banco.test.cuentasms.dominio.service.MovementsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reports")
@RestController
@Slf4j
public class ReportsControlller {

    private MovementsService movementsService;

    @Autowired
    public ReportsControlller(MovementsService movementsService){
        this.movementsService = movementsService;
    }

    @PostMapping("accountsReport")
    public ResponseEntity<ReportDTO> getReport(
            @RequestBody @Valid ReportInputDTO body,
            @RequestParam() String from,
            @RequestParam() String until
        ){
        log.info("identificacion => {}", body.getNumeroIdCliente());
        log.info("fechaInicial => {}", from);
        log.info("fechaFinal => {}", until);
        return new ResponseEntity<>(
                this.movementsService.getReport(body.getNumeroIdCliente(), from, until),
                HttpStatus.OK
        );
    }
}

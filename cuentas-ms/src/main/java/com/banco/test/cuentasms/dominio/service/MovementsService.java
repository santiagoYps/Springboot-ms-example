package com.banco.test.cuentasms.dominio.service;

import com.banco.test.cuentasms.dominio.dto.*;
import com.banco.test.cuentasms.dominio.repository.ClientsRepository;
import com.banco.test.cuentasms.dominio.repository.MovementsRepository;
import com.banco.test.cuentasms.dominio.utils.enums.MovementTypeEnum;
import com.banco.test.cuentasms.web.error.InputNotFound;
import com.banco.test.cuentasms.web.error.InsufficientBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovementsService {

    private final MovementsRepository movementsRepository;
    private final ClientsRepository clientsRepository;

    private final AccountsService accountsService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Autowired
    public MovementsService(MovementsRepository movementsRepository, ClientsRepository clientsRepository,
            AccountsService accountsService){
        this.movementsRepository = movementsRepository;
        this.clientsRepository = clientsRepository;
        this.accountsService = accountsService;
    }

    public BigDecimal getBalance(Long accountNumber) {
        return this.movementsRepository.getBalance(accountNumber);
    }


    public MovementDTO registerMovement(RegisterMovementDTO movement){
        BigDecimal balance = getBalance(movement.getCuenta());
        if (balance == null) {
            throw new InputNotFound("La cuenta indicada no existe");
        }

        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setFecha(LocalDateTime.now());
        movementDTO.setCuenta(movement.getCuenta());
        movementDTO.setCliente(movement.getCliente());
        movementDTO.setSaldoInicial(balance);
        movementDTO.setMonto(movement.getMonto());
        BigDecimal newBalance = balance.add(movement.getMonto());

        Boolean isDeposit = movement.getMonto().compareTo(BigDecimal.ZERO) > 0;
        movementDTO.setTipo( isDeposit.equals(Boolean.TRUE) ? MovementTypeEnum.DEPOSITO : MovementTypeEnum.RETIRO);

        if (isDeposit.equals(Boolean.FALSE) && newBalance.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new InsufficientBalance("El saldo es insuficiente para realizar la operacion");
        }

        movementDTO.setSaldoActual(newBalance);

        this.movementsRepository.updateBalance(movementDTO.getCuenta(), newBalance );

        return this.movementsRepository.registerMovement(movementDTO);

    }

    public ReportDTO getReport(String identification, String initialDateStr, String finalDateStr) {
        LocalDateTime initialDate;
        LocalDateTime finalDate;
        try {
            initialDate = LocalDate.parse(initialDateStr, formatter).atStartOfDay();
            finalDate = LocalDate.parse(finalDateStr, formatter).atTime(23, 59, 59);
        } catch (DateTimeParseException e) {
            log.error("Error en formato de fecha", e);
            throw e;
        }


        ClientDTO client = this.clientsRepository.getClientByIdentification(identification);
        log.info("client => {}", client);
        if (client == null) {
            throw new InputNotFound("El cliente indicado no existe");
        }
        ReportDTO report = new ReportDTO();
        report.setNumeroIdCliente(client.getIdentificacion());
        report.setNombreCliente(client.getNombre());

        List<AccountDTO> clientAccounts = this.accountsService.getClientAccounts(client.getId());

        List<AccountInReport> accontsInReport = clientAccounts.stream().map(account -> {
            List<MovementDTO> movements = this.movementsRepository.getReportBetweenDates(
                    account.getNumero(), initialDate, finalDate
            );
            return AccountInReport.builder()
                    .numero(account.getNumero())
                    .tipo(account.getTipo())
                    .saldo(account.getSaldo())
                    .estado(account.getEstado())
                    .movimientos(movements)
                    .build();
        }).collect(Collectors.toList());

        report.setCuentas(accontsInReport);
        return report;
    }

}

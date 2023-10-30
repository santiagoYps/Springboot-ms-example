package com.banco.test.cuentasms.dominio.repository;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.dominio.dto.MovementDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface MovementsRepository {

    MovementDTO registerMovement(MovementDTO movementDTO);

    BigDecimal getBalance(Long accountNumber);

    void updateBalance(Long accountNumber, BigDecimal newBalance);

    List<MovementDTO> getReportBetweenDates(Long account, LocalDateTime initialDate, LocalDateTime finalDate);
}

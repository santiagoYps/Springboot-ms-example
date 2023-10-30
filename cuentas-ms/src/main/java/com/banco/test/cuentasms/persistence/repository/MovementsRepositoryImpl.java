package com.banco.test.cuentasms.persistence.repository;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.dominio.dto.MovementDTO;
import com.banco.test.cuentasms.dominio.repository.MovementsRepository;
import com.banco.test.cuentasms.persistence.mappers.MovementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MovementsRepositoryImpl implements MovementsRepository {

    private MovementsPaginationRepository movementsPaginationRepository;
    private MovementMapper mapper;

    @Autowired
    public MovementsRepositoryImpl(MovementsPaginationRepository movementsPaginationRepository, MovementMapper mapper){
        this.movementsPaginationRepository = movementsPaginationRepository;
        this.mapper = mapper;
    }

    @Override
    public BigDecimal getBalance(Long accountNumber) {
        return this.movementsPaginationRepository.getBalance( accountNumber );
    }

    @Override
    public void updateBalance(Long accountNumber, BigDecimal newBalance) {
        this.movementsPaginationRepository.updateBalance( accountNumber, newBalance );
    }

    @Override
    public MovementDTO registerMovement(MovementDTO movementDTO) {
        return  this.mapper.toDTO(
            this.movementsPaginationRepository.save( this.mapper.toEntity(movementDTO) )
        );
    }

    @Override
    public List<MovementDTO> getReportBetweenDates(Long account, LocalDateTime initialDate, LocalDateTime finalDate) {
        return this.mapper.toDTOs(this.movementsPaginationRepository.getReport(account, initialDate, finalDate));
    }

}

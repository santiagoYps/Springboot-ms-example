package com.banco.test.cuentasms.persistence.mappers;

import com.banco.test.cuentasms.dominio.dto.MovementDTO;
import com.banco.test.cuentasms.persistence.entity.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "tipo", target = "type"),
            @Mapping(source = "saldoInicial", target = "initialBalance"),
            @Mapping(source = "monto", target = "amount"),
            @Mapping(source = "saldoActual", target = "currentBalance"),
            @Mapping(source = "cuenta", target = "accountNumber"),
            @Mapping(source = "cliente", target = "clientId")
    })
    Movement toEntity(MovementDTO movementDTO);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "date", target = "fecha"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "initialBalance", target = "saldoInicial"),
            @Mapping(source = "amount", target = "monto"),
            @Mapping(source = "currentBalance", target = "saldoActual"),
            @Mapping(source = "accountNumber", target = "cuenta"),
            @Mapping(source = "clientId", target = "cliente")
    })
    MovementDTO toDTO(Movement movement);
    List<MovementDTO> toDTOs(List<Movement> movements);

}

package com.banco.test.cuentasms.persistence.mappers;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.persistence.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountsMapper {


    @Mappings({
            @Mapping(source = "numero", target = "number"),
            @Mapping(source = "tipo", target = "type"),
            @Mapping(source = "saldo", target = "balance"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "cliente", target = "clientId"),
    })
    Account toEntity(AccountDTO accountDTO);

    @Mappings({
            @Mapping(source = "number", target = "numero"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "balance", target = "saldo"),
            @Mapping(source = "state", target = "estado"),
            @Mapping(source = "clientId", target = "cliente"),
    })
    AccountDTO toDTO(Account account);
    List<AccountDTO> toDTOs(List<Account> accounts);

}

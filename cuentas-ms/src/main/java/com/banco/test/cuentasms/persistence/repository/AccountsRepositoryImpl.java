package com.banco.test.cuentasms.persistence.repository;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.dominio.repository.AccountsRepository;
import com.banco.test.cuentasms.persistence.entity.Account;
import com.banco.test.cuentasms.persistence.mappers.AccountsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountsRepositoryImpl implements AccountsRepository {

    private AccountsCrudRepository accountsCrudRepository;
    private AccountsMapper mapper;

    @Autowired
    public AccountsRepositoryImpl(AccountsCrudRepository accountsCrudRepository, AccountsMapper mapper) {
        this.accountsCrudRepository = accountsCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        return this.mapper.toDTO(
                this.accountsCrudRepository.save(mapper.toEntity(account))
        );
    }

    @Override
    public List<AccountDTO> getClientAccounts(String clientId) {
        return this.mapper.toDTOs(this.accountsCrudRepository.findAllByClientId(clientId));
    }
}

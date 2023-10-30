package com.banco.test.cuentasms.dominio.service;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.dominio.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    public AccountDTO createAccount(AccountDTO account){
        if (account.getSaldo() == null) {
            account.setSaldo(BigDecimal.ZERO);
        }
        if (account.getEstado() == null){
            account.setEstado(Boolean.TRUE);
        }
        return this.accountsRepository.createAccount(account);
    }

    public List<AccountDTO> getClientAccounts(String clientId){
        return this.accountsRepository.getClientAccounts(clientId);
    }
}

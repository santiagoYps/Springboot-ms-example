package com.banco.test.cuentasms.dominio.repository;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;

import java.util.List;

public interface AccountsRepository {

    AccountDTO createAccount(AccountDTO account);

    List<AccountDTO> getClientAccounts(String clientId);

    // AccountDTO updateBalance(Double amount);
}

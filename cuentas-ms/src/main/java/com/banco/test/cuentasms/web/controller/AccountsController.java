package com.banco.test.cuentasms.web.controller;

import com.banco.test.cuentasms.dominio.dto.AccountDTO;
import com.banco.test.cuentasms.dominio.service.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping("")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO account){
        return ResponseEntity.ok(accountsService.createAccount(account));
    }

}

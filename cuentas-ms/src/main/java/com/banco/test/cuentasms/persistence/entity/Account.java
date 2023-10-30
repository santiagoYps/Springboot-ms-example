package com.banco.test.cuentasms.persistence.entity;

import ch.qos.logback.core.net.server.Client;
import com.banco.test.cuentasms.dominio.utils.enums.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "number")
    private Long number;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "client_id")
    private String clientId;
}

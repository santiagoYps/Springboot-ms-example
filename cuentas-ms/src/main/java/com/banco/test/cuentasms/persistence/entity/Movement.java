package com.banco.test.cuentasms.persistence.entity;

import com.banco.test.cuentasms.dominio.utils.enums.MovementTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @Column(name = "movement_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "type")
    private String type;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    @Column(name = "movement")
    private BigDecimal amount;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "client_id")
    private String clientId;

}

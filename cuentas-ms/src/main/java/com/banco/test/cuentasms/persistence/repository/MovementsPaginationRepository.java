package com.banco.test.cuentasms.persistence.repository;

import com.banco.test.cuentasms.persistence.entity.Movement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementsPaginationRepository extends CrudRepository<Movement, Long> {

    @Query("select c.balance from Account c where c.number=:accountNumber")
    BigDecimal getBalance(Long accountNumber);

    @Modifying
    @Transactional
    @Query("update Account c set c.balance=:newBalance where c.number=:accountNumber")
    void updateBalance(Long accountNumber, BigDecimal newBalance);

    @Query("select m from Movement m where m.accountNumber=:accountNumber and " +
            "m.date >= :initialDate and m.date <= :finalDate order by m.date")
    List<Movement> getReport(Long accountNumber, LocalDateTime initialDate, LocalDateTime finalDate);
}

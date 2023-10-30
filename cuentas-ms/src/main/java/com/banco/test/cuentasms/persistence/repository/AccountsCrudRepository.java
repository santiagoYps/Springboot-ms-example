package com.banco.test.cuentasms.persistence.repository;

import com.banco.test.cuentasms.persistence.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsCrudRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByClientId(String clientId);

}

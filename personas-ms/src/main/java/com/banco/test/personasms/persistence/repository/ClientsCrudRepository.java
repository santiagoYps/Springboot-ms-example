package com.banco.test.personasms.persistence.repository;

import com.banco.test.personasms.persistence.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientsCrudRepository extends JpaRepository<Client, String> {

    Optional<Client> findByIdentification(String identification);

    @Modifying
    @Transactional
    @Query("delete from Client c where c.identification = :identification")
    Integer deleteByIdentification(String identification);

}

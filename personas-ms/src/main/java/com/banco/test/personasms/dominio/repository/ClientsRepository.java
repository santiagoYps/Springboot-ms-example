package com.banco.test.personasms.dominio.repository;

import com.banco.test.personasms.dominio.dto.ClientDTO;
import com.banco.test.personasms.dominio.dto.ClientUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository {

    ClientDTO createClient(ClientDTO client);

    ClientDTO updateClient(ClientUpdateDTO client);

    Optional<ClientDTO> getClientInfoByIdentification(String identification);

    Optional<List<ClientDTO>> getClientList();

    Integer delete(String identification);

}

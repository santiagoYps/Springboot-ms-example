package com.banco.test.cuentasms.dominio.repository;

import com.banco.test.cuentasms.dominio.dto.ClientDTO;

public interface ClientsRepository {

    ClientDTO getClientByIdentification(String identification);
}

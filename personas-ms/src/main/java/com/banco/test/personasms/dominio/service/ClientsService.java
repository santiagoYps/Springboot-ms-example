package com.banco.test.personasms.dominio.service;

import com.banco.test.personasms.dominio.dto.ClientDTO;
import com.banco.test.personasms.dominio.dto.ClientUpdateDTO;
import com.banco.test.personasms.dominio.repository.ClientsRepository;
import com.banco.test.personasms.dominio.utils.enums.EncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientsService {

    private ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public ClientDTO createClient(ClientDTO clientDTO) {

        clientDTO.setPassword(
                EncryptionUtility.sha256(clientDTO.getPassword())
        );
        clientDTO.setEstado( clientDTO.getEstado() == null );
        return this.clientsRepository.createClient(clientDTO);
    }

    public Optional<ClientDTO> getClient(String identity){
        return this.clientsRepository.getClientInfoByIdentification(identity);
    }


    public Optional<List<ClientDTO>> getAllClient(){
        return this.clientsRepository.getClientList();
    }


    public ClientDTO updateClient(ClientUpdateDTO clientDTO){
        if (clientDTO.getPassword() != null){
            clientDTO.setPassword(
                    EncryptionUtility.sha256(clientDTO.getPassword())
            );
        }
        return this.clientsRepository.updateClient(clientDTO);
    }


    public boolean delete(String identity){
        return getClient(identity).map(client ->{
            Integer result = this.clientsRepository.delete(identity);
            return result > 0;
        }).orElse(false);
    }
}

package com.banco.test.personasms.persistence.repository;

import com.banco.test.personasms.dominio.dto.ClientUpdateDTO;
import com.banco.test.personasms.dominio.repository.ClientsRepository;
import com.banco.test.personasms.dominio.dto.ClientDTO;
import com.banco.test.personasms.persistence.entity.Client;
import com.banco.test.personasms.persistence.mappers.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class ClientsRepositoryImpl implements ClientsRepository {

    private ClientsCrudRepository clientsCrudRepository;
    private ClientMapper mapper;

    @Autowired
    public ClientsRepositoryImpl(ClientsCrudRepository clientsCrudRepository, ClientMapper mapper) {
        this.mapper = mapper;
        this.clientsCrudRepository = clientsCrudRepository;
    }


    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = this.mapper.toClient(clientDTO);
        return this.mapper.toClientDTO(this.clientsCrudRepository.save(client));
    }

    @Override
    public Optional<ClientDTO> getClientInfoByIdentification(String identification) {
        return this.clientsCrudRepository.findByIdentification(identification).map(client -> mapper.toClientDTO(client));
    }

    @Override
    public ClientDTO updateClient(ClientUpdateDTO clientDTO) {
        Optional<Client> clientFound = this.clientsCrudRepository.findById(clientDTO.getId());
        return clientFound.map( client -> {
            if (clientDTO.getNombre() != null && clientDTO.getNombre().length() > 0)
                client.setName(clientDTO.getNombre());
            if (clientDTO.getGenero() != null)
                client.setGender(clientDTO.getGenero().name());
            if (clientDTO.getEdad() != null)
                client.setAge(clientDTO.getEdad());
            if (clientDTO.getDireccion() != null && clientDTO.getDireccion().length() > 0)
                client.setAddress(clientDTO.getDireccion());
            if (clientDTO.getTelefono() != null && clientDTO.getTelefono().length() > 0)
                client.setPhone(clientDTO.getTelefono());
            if (clientDTO.getPassword() != null && clientDTO.getPassword().length() > 0)
                client.setPassword(clientDTO.getPassword());
            if (clientDTO.getEstado() != null)
                client.setState(clientDTO.getEstado());
            return this.mapper.toClientDTO(this.clientsCrudRepository.save(client));
        }).orElse(null);
    }

    @Override
    public Optional<List<ClientDTO>> getClientList() {
        List<Client> clients = this.clientsCrudRepository.findAll();
        return Optional.of(mapper.toClients(clients));
    }

    @Override
    public Integer delete(String identification) {
        return this.clientsCrudRepository.deleteByIdentification(identification);
    }
}

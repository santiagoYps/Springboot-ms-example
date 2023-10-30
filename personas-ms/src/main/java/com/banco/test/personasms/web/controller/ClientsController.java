package com.banco.test.personasms.web.controller;

import com.banco.test.personasms.dominio.dto.ClientUpdateDTO;
import com.banco.test.personasms.dominio.service.ClientsService;
import com.banco.test.personasms.dominio.dto.ClientDTO;
import com.banco.test.personasms.web.error.ClientNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientsController {

    private ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @PostMapping("")
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return new ResponseEntity<>(this.clientsService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{identification}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String identification){
        log.info("init getClient {}",identification);
        Optional<ClientDTO> client = this.clientsService.getClient(identification);
        if (client.isEmpty()){
            throw new ClientNotFoundException("CLiente no registrado");
        }
        return new ResponseEntity<>(client.get(), HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> getAllCient(){
        log.info("init getClient all");
        return new ResponseEntity<>(this.clientsService.getAllClient().get(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody @Valid ClientUpdateDTO clientDTO){
        log.info("init update client {}",clientDTO);
        ClientDTO response = this.clientsService.updateClient(clientDTO);
        if(response==null)
            throw new ClientNotFoundException("Cliente no registrado");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<?> delete(@PathVariable String identification){
        log.info("delete client {}",identification);

        if (this.clientsService.delete(identification)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            throw new ClientNotFoundException("Cliente no encontrado");
        }
    }
}

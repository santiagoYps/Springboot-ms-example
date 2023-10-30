package com.banco.test.cuentasms.web.controller;

import com.banco.test.cuentasms.dominio.dto.MovementDTO;
import com.banco.test.cuentasms.dominio.dto.RegisterMovementDTO;
import com.banco.test.cuentasms.dominio.service.MovementsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movements")
@Slf4j
public class MovementsController {

    private MovementsService movementsService;

    public MovementsController(MovementsService movementsService){
        this.movementsService = movementsService;
    }

    @PostMapping("")
    public ResponseEntity<MovementDTO> registerMovement(@RequestBody @Valid RegisterMovementDTO movement){
        log.info("movement => {}", movement);
        return new ResponseEntity<>(movementsService.registerMovement(movement), HttpStatus.OK);
    }
}

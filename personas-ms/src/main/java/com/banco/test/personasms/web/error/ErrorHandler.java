package com.banco.test.personasms.web.error;

import com.banco.test.personasms.dominio.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorResponseDTO badRequest(MethodArgumentNotValidException exception){
        String validationMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ErrorResponseDTO(validationMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ErrorResponseDTO badRequest(HttpMessageNotReadableException exception){
        String error = exception.getLocalizedMessage();
        if (error.contains("GenderEnum")){
            return new ErrorResponseDTO("Los valores de genero admintidos son: M = Masculino, F=Femenino, O=Otro");
        }
        return new ErrorResponseDTO(error);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseBody
    public ErrorResponseDTO badRequest(SQLIntegrityConstraintViolationException exception){
        return new ErrorResponseDTO("El cliente ya ha sido registrado");
    }
}

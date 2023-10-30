package com.banco.test.cuentasms.web.error;

import com.banco.test.cuentasms.dominio.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({InsufficientBalance.class})
    @ResponseBody
    public ErrorResponseDTO handleInsufficientBalance(InsufficientBalance exception){
        return new ErrorResponseDTO(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({InputNotFound.class})
    @ResponseBody
    public ErrorResponseDTO handleAccountNotFound(InputNotFound exception){
        return new ErrorResponseDTO(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorResponseDTO badRequest(MethodArgumentNotValidException exception){
        String validationMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ErrorResponseDTO(validationMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConsumingServiceError.class})
    @ResponseBody
    public ErrorResponseDTO errorConsumingClients(ConsumingServiceError exception){
        return new ErrorResponseDTO(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DateTimeParseException.class})
    @ResponseBody
    public ErrorResponseDTO errorConsumingClients(DateTimeParseException exception){
        return new ErrorResponseDTO("La fechas deben cumplir con el formato esperado (yyyy-MM-dd).");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ErrorResponseDTO errorConsumingClients(MissingServletRequestParameterException exception){
        String message = "El parametro " + exception.getParameterName() + " es requerico";
        return new ErrorResponseDTO(message);
    }
}

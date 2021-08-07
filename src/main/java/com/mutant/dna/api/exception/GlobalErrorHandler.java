package com.mutant.dna.api.exception;

import java.security.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author npavila
 */
@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String methodArgumentNotValidException(Exception ex) {
        log.error("Ha ocurrido un error inesperado", ex);
        return "Ha ocurrido un error interno, por favor intente más tarde";
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(InvalidParameterException.class)
    public String invalidParameterException(InvalidParameterException ex) {
        return "Parámetros de entrada inválidos";
    }
}

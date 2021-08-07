package com.mutant.dna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Napoleon Avila Ochoa
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BaseDatosException extends Exception{
    
    public BaseDatosException(String message) {
        super(message);
    }
    
}

package com.mutant.dna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Napoleon Avila Ochoa
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException 
{
    public RecordNotFoundException(String exception) {
        super(exception);
    }
}

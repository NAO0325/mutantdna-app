package com.mutant.dna.exception;

/**
 *
 * @author Napoleon Avila Ochoa
 */
public class ValidacionDatosException extends Exception {

    private static final long serialVersionUID = 4436688599666525861L;

    private String description;

    public ValidacionDatosException(String message) {
        super(message);
        this.description = message;
    }

    public ValidacionDatosException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

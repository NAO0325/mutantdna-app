package com.mutant.dna.enums;

/**
 *
 * @author Napoleon Avila Ochoa
 */
public enum ETipoError {

    VALIDACION("Validación Datos", "Se ha generado un error de validacion de datos", "VA"),
    INTERNOS("Interno", "Se ha generado un error no especificado", "IN"),
    BASEDATOS("Base de Datos", "Se ha generado un error de Base de datos", "BD"),
    WEBSERVICES("Web Services", "Se ha generado un error de Servicios Web", "WS");

    private final String code;

    public String getCode() {
        return code;
    }

    private final String type;

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    private final String description;

    private ETipoError(String type, String description, String code) {
        this.type = type;
        this.description = description;
        this.code = code;
    }

    public static ETipoError getTipoError(String value) {
        for (ETipoError item : values()) {
            if (item.getType().equals(value) || item.getDescription().equals(value) || item.getCode().equals(value)) {
                return item;
            }
        }
        return INTERNOS;
    }

}


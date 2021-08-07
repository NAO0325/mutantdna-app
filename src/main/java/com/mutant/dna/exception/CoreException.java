package com.mutant.dna.exception;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import lombok.Getter;


/**
 *
 * @author Napoleon Avila Ochoa
 */
public class CoreException extends Exception implements Serializable {

    private static final long serialVersionUID = -5603867528130258910L;
    private Integer httpStatusCode;

    @Getter
    private String mensajeUsuario;

    public CoreException() {
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

    public CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CoreException(String message, Throwable cause, Integer httpStatusCode) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public CoreException(String nombreServicio, String url, Throwable causa) {
        this(nombreServicio, url, null, causa);
    }

    public CoreException(String nombreServicio, String url,
            String mensajeUsuario, Throwable causa) {
        super(String.format("No ha sido posible conectar con el servicio {%s} "
                + "en la direccion {%s} ", nombreServicio, url), causa);
        this.mensajeUsuario = StringUtils.defaultIfEmpty(mensajeUsuario,
                String.format("El servicio \"%s\" no responde, "
                        + "consulte con soporte tecnico.", nombreServicio));
    }

}

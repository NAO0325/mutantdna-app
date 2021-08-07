package com.mutant.dna.exception;

import com.mutant.dna.dto.CamposErrorDto;
import com.mutant.dna.enums.ETipoError;
import java.io.Serializable;

/**
 *
 * @author Napoleon Avila Ochoa
 */
public class GeneralException extends Exception implements Serializable {

    private static final long serialVersionUID = -828396301329364288L;

    public static final String SPLIT_BY_PISO = "_";
    private String mensajeUsuario;
    private CamposErrorDto camposError;

    public GeneralException() {

    }

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(CamposErrorDto camposError, Throwable cause) {
        super(camposError.getDescripcion(), cause);
        this.camposError = camposError;
    }

    private static StackTraceElement getStackTraceElementError(String className,
            StackTraceElement stackTraceElements[]) {

        StackTraceElement stackTraceElementReturn = null;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (stackTraceElement.getClassName().equalsIgnoreCase(className)) {
                stackTraceElementReturn = stackTraceElement;
                break;
            }
        }
        return stackTraceElementReturn;
    }

    public static Exception throwException(Object classE, Throwable thr) {

        StackTraceElement st = thr.getStackTrace()[0];

        CamposErrorDto infoError = new CamposErrorDto();

        infoError.setTipoError(ETipoError.INTERNOS);

        infoError.setDescripcion(infoError.getTipoError().getDescription());

        //infoError.setExcepcion(new Exception(thr));
        infoError.setNombreExcepcion(thr.getClass().getCanonicalName());
        infoError.setLineaError(st.getLineNumber());
        infoError.setPasoProceso(st.getMethodName());
        infoError.setArchivoError(st.getClassName());
        infoError.setDetalleError(thr.getMessage());

        return new GeneralException(infoError, thr.getCause());

    }

    public static Exception throwException(Object classE, Exception ex) {

        if (ex instanceof GeneralException) {
            return ex;
        }

        StackTraceElement st = getStackTraceElementError(
                classE.getClass().getCanonicalName().split(SPLIT_BY_PISO)[0],
                ex.getStackTrace());

        CamposErrorDto infoError = new CamposErrorDto();

        if (ex instanceof ValidacionDatosException) {
            infoError.setTipoError(ETipoError.VALIDACION);
        } else if (ex instanceof BaseDatosException
                || ex instanceof org.springframework.dao.DataIntegrityViolationException
                || ex instanceof javax.persistence.PersistenceException) {
            infoError.setTipoError(ETipoError.BASEDATOS);
        } else if (ex instanceof javax.ws.rs.ProcessingException) {
            infoError.setTipoError(ETipoError.WEBSERVICES);
        } else {
            infoError.setTipoError(ETipoError.INTERNOS);
        }

        if (ex instanceof ValidacionDatosException && ((ValidacionDatosException) ex).getDescription() != null) {
            infoError.setDescripcion(((ValidacionDatosException) ex).getDescription());
        } else if (ex instanceof org.springframework.dao.DataIntegrityViolationException) {
            infoError.setDescripcion(((org.springframework.dao.DataIntegrityViolationException) ex).getMostSpecificCause().getMessage());
        } else {
            infoError.setDescripcion(infoError.getTipoError().getDescription());
        }

        //infoError.setExcepcion(ex);
        infoError.setNombreExcepcion(ex.getClass().getCanonicalName());
        infoError.setLineaError(st.getLineNumber());
        infoError.setPasoProceso(st.getMethodName());
        infoError.setArchivoError(st.getClassName());

        infoError.setDetalleError(ex.getMessage() == null
                || ex.getMessage().trim().equals("")
                ? infoError.getDescripcion()
                : ex.getMessage());

        return new GeneralException(infoError, ex.getCause());

    }

    public static Exception throwException(Object classE, Exception ex, String description) {
        if (ex instanceof GeneralException) {
            return ex;
        }

        StackTraceElement st = getStackTraceElementError(
                classE.getClass().getCanonicalName().split(SPLIT_BY_PISO)[0],
                ex.getStackTrace());

        CamposErrorDto infoError = new CamposErrorDto();

        if (ex instanceof ValidacionDatosException) {
            infoError.setTipoError(ETipoError.VALIDACION);
        } else if (ex instanceof BaseDatosException || ex instanceof org.hibernate.exception.ConstraintViolationException
                || ex instanceof javax.persistence.PersistenceException) {
            infoError.setTipoError(ETipoError.BASEDATOS);
        } else if (ex instanceof javax.ws.rs.ProcessingException) {
            infoError.setTipoError(ETipoError.WEBSERVICES);
        } else {
            infoError.setTipoError(ETipoError.INTERNOS);
        }

        if (ex instanceof ValidacionDatosException && ((ValidacionDatosException) ex).getDescription() != null) {
            infoError.setDescripcion(((ValidacionDatosException) ex).getDescription());
        } else {
            infoError.setDescripcion(description);
        }

        //infoError.setExcepcion(ex);
        infoError.setNombreExcepcion(ex.getClass().getCanonicalName());
        infoError.setLineaError(st.getLineNumber());
        infoError.setPasoProceso(st.getMethodName());
        infoError.setArchivoError(st.getClassName());

        infoError.setDetalleError(ex.getMessage() == null
                || ex.getMessage().trim().equals("")
                ? infoError.getDescripcion()
                : ex.getMessage());

        return new GeneralException(infoError, ex.getCause());

    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public CamposErrorDto getCamposError() {
        return camposError;
    }

    public void setCamposError(CamposErrorDto camposError) {
        this.camposError = camposError;
    }

}

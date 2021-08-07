package com.mutant.dna.api.exception;


import com.mutant.dna.dto.CamposErrorDto;
import com.mutant.dna.enums.ETipoError;
import com.mutant.dna.exception.GeneralException;
import com.mutant.dna.exception.RecordNotFoundException;
import com.mutant.dna.exception.ValidacionDatosException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Napoleon Avila Ochoa
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, HandlerMethod handlerMethod, WebRequest request) {
        Class controllerClass = handlerMethod.getMethod().getDeclaringClass();
        CamposErrorDto error = getErrorDetail(ex, controllerClass.getName());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, HandlerMethod handlerMethod, WebRequest request) {
        Class controllerClass = handlerMethod.getMethod().getDeclaringClass();
        CamposErrorDto error = getErrorDetail(ex, controllerClass.getName());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CamposErrorDto error = getErrorDetail(ex, ex.getObjectName());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    private CamposErrorDto getErrorDetail(Exception ex, String methodName) {
        CamposErrorDto infoError;

        if (ex instanceof GeneralException) {
            infoError = ((GeneralException) ex).getCamposError();
        } else {
            infoError = new CamposErrorDto();
            infoError.setModuloError(methodName);
            infoError.setLineaError(ex.getStackTrace()[0].getLineNumber());
            infoError.setArchivoError(ex.getStackTrace()[0].getClassName());
            if (ex instanceof ValidacionDatosException) {
                infoError.setTipoError(ETipoError.VALIDACION);
                infoError.setDescripcion(((ValidacionDatosException) ex).getDescription());
            } else {
                infoError.setTipoError(ETipoError.INTERNOS);
                infoError.setDescripcion(infoError.getTipoError().getDescription());
            }

            infoError.setDetalleError(ex.getMessage() == null
                    ? infoError.getTipoError().getDescription()
                    : ex.getMessage());
            infoError.setPasoProceso(ex.getStackTrace()[0].getMethodName());

            infoError.setNombreExcepcion(ex.getClass().getCanonicalName());
        }
        return infoError;
    }
}

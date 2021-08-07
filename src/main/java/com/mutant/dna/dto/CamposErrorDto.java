
package com.mutant.dna.dto;


import com.mutant.dna.enums.ETipoError;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Napoleon Avila Ochoa
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "camposErrorDto")
public class CamposErrorDto {

    @XmlElement(required = false)
    private ETipoError tipoError;
    @XmlElement(required = false)
    private String moduloError;
    @XmlElement(required = false)
    private String pasoProceso;
    @XmlElement(required = false)
    private String descripcion;
    @XmlElement(required = false)
    private String archivoError;
    @XmlElement(required = false)
    private int lineaError;
    @XmlElement(required = false)
    private String detalleError;
    @XmlElement(required = false)
    private String nombreExcepcion;
}


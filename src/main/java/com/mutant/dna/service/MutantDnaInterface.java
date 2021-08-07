/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.service;

import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.StatusDto;

/**
 *
 * @author npavila
 */
public interface MutantDnaInterface {
    
    /**
     * Determina una arreglo de dna es mutante o no
     *
     * @param dto con arreglo de dna
     * @param warn valor de valicaciones inicializado vacío
     * @return boolean true if mutant
     * @throws Exception
     */
    boolean isMutant(DnaReadDto dto, StringBuilder warn) throws Exception;
    
    /**
     * Determina estadísticas de las muestras de ADN tomadas
     *
     * @return StatusDto objeto con información estadística
     * @throws Exception
     */
    StatusDto getStats() throws Exception;
}

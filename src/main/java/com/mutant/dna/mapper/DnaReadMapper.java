/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.mapper;

import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.model.DnaRead;

/**
 *
 * @author npavila
 */
public interface DnaReadMapper {

    DnaRead DnaReadDtoToDnaRead(DnaReadDto dto);
}

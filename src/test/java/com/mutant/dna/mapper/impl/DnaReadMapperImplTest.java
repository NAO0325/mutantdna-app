/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.mapper.impl;

import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author npavila
 */
@SpringBootTest
class DnaReadMapperImplTest {

    @Autowired
    Constants constants;
    
    @Autowired
    DnaReadMapperImpl mapper;

    /**
     * Test of dnaReadDtoToDnaRead method, of class DnaReadMapperImpl.
     */
    @Test
    void testDnaReadDtoToDnaRead() {
        System.out.println("dnaReadDtoToDnaRead");
        DnaReadDto dto = new DnaReadDto();
        dto.setDna(Arrays.asList(constants.getDnaMutant().split(",")));
        Assertions.assertNotNull(mapper.dnaReadDtoToDnaRead(dto));
    }
    
}

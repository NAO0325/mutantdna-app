/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.service.impl;

import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author npavila
 */
@SpringBootTest
class MutantDnaServiceTest {

    @Autowired
    Constants constants;

    @Autowired
    MutantDnaService service;

    private final DnaReadDto dnaReadDto = new DnaReadDto();

    /**
     * Test of isMutant method, of class MutantDnaService.
     */
    @Test
    void testIsMutant() throws Exception {
        System.out.println("testIsMutant");
        whenDnaChainIsMutant();
    }

    @Test
    void testIsNotMutant() throws Exception {
        System.out.println("testIsNotMutant");
        whenDtoIsNotNullAndWarnIsNull();
        whenDtoIsNullAndWarnNotNull();
        whenDtoIsNullAndWarnIsNull();
        whenDtoInstanceIsEmpty();
        whenDnaChainIsEmpty();
        whenDnaChainIsNull();
        whenDnaChainIsVoid();
        whenDnaChainIsInvalid();
        whenDnaChainIsIncomplete();
        whenDnaChainIsHuman();
    }

    /**
     * Test of getStats method, of class MutantDnaService.
     */
    @Test
    void testGetStats() throws Exception {
        System.out.println("testGetStats");
        Assertions.assertNotNull(service.getStats());
    }

    private void whenDtoIsNullAndWarnNotNull() throws Exception {
        Assertions.assertFalse(service.isMutant(null, new StringBuilder()));
    }

    private void whenDtoIsNotNullAndWarnIsNull() throws Exception {
        Assertions.assertFalse(service.isMutant(dnaReadDto, null));
    }

    private void whenDtoIsNullAndWarnIsNull() throws Exception {
        Assertions.assertFalse(service.isMutant(null, null));
    }

    private void whenDtoInstanceIsEmpty() throws Exception {
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsEmpty() throws Exception {
        dnaReadDto.setDna(new String[0]);
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsNull() throws Exception {
        dnaReadDto.setDna(null);
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsVoid() throws Exception {
        dnaReadDto.setDna(constants.getDnaVoid().split(","));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsIncomplete() throws Exception {
        dnaReadDto.setDna(constants.getDnaIncomplete().split(","));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsInvalid() throws Exception {
        dnaReadDto.setDna(constants.getDnaInvalid().split(","));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsHuman() throws Exception {
        dnaReadDto.setDna(constants.getDnaHuman().split(","));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsMutant() throws Exception {
        dnaReadDto.setDna(constants.getDnaHuman().split(","));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

}

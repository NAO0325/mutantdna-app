/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.service.impl;

import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;
import java.util.ArrayList;
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
class MutantDnaServiceTest {

    @Autowired
    Constants constants;

    @Autowired
    MutantDnaService service;

    private DnaReadDto dnaReadDto = new DnaReadDto();

    /**
     * Test of isMutant method, of class MutantDnaService.
     */
    @Test
    void testIsMutant() {
        System.out.println("testIsMutant");
        whenDnaChainIsMutant();
    }

    @Test
    void testIsNotMutant() {
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
    void testGetStats() {
        System.out.println("testGetStats");
        Assertions.assertNotNull(service.getStats());
    }

    private void whenDtoIsNullAndWarnNotNull(){
        Assertions.assertFalse(service.isMutant(null, new StringBuilder()));
    }

    private void whenDtoIsNotNullAndWarnIsNull(){
        Assertions.assertFalse(service.isMutant(dnaReadDto, null));
    }

    private void whenDtoIsNullAndWarnIsNull(){
        Assertions.assertFalse(service.isMutant(null, null));
    }

    private void whenDtoInstanceIsEmpty(){
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsEmpty(){
        dnaReadDto.setDna(new ArrayList<>());
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsNull(){
        dnaReadDto.setDna(null);
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsVoid(){
        dnaReadDto.setDna(Arrays.asList(constants.getDnaVoid().split(",")));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsIncomplete(){
        dnaReadDto.setDna(Arrays.asList(constants.getDnaIncomplete().split(",")));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsInvalid(){
        dnaReadDto.setDna(Arrays.asList(constants.getDnaInvalid().split(",")));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsHuman(){
        dnaReadDto.setDna(Arrays.asList(constants.getDnaHuman().split(",")));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }

    private void whenDnaChainIsMutant(){
        dnaReadDto.setDna(Arrays.asList(constants.getDnaHuman().split(",")));
        Assertions.assertFalse(service.isMutant(dnaReadDto, new StringBuilder()));
    }



}

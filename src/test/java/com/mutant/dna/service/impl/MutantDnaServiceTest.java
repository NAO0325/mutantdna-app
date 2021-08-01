/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.service.impl;

import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.StatusDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author npavila
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MutantDnaServiceTest {

    @Mock
    MutantDnaService service;

    @Mock
    DnaReadDto dnaReadDto;

    @Mock
    StringBuilder warn;

    @Mock
    StatusDto statusDto;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test of isMutant method, of class MutantDnaService.
     */
    @Test
    public void testIsMutant() {
        System.out.println("isMutant");
        boolean expResult = false;
        boolean result = service.isMutant(dnaReadDto, warn);
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of getStats method, of class MutantDnaService.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        StatusDto result = service.getStats();
        Assert.assertEquals(statusDto, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

}

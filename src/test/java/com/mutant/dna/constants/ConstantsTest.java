/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author npavila
 */
@SpringBootTest
class ConstantsTest {

    @Autowired
    Constants constants;

    /**
     * Test of getPatternCharacters method, of class Constants.
     */
    @Test
    void testGetPatternCharacters() {
        System.out.println("testGetPatternCharacters");
        Assertions.assertNotNull(constants.getPatternCharacters());
        Assertions.assertEquals("(A|T|C|G)+", constants.getPatternCharacters());
    }

    /**
     * Test of getMatchNumberDna method, of class Constants.
     */
    @Test
    void testGetMatchNumberDna() {
        System.out.println("getMatchNumberDna");
        Assertions.assertNotNull(constants.getMatchNumberDna());
        Assertions.assertEquals(4, constants.getMatchNumberDna());
    }

    /**
     * Test of getDnaIncomplete method, of class Constants.
     */
    @Test
    void testGetDnaVoid() {
        System.out.println("getDnaVoid");
        Assertions.assertNotNull(constants.getDnaVoid());
        Assertions.assertEquals("    ,  ,  ,   ,    ,   ", constants.getDnaVoid());
    }

    /**
     * Test of getDnaIncomplete method, of class Constants.
     */
    @Test
    void testGetDnaIncomplete() {
        System.out.println("getDnaIncomplete");
        Assertions.assertNotNull(constants.getDnaIncomplete());
        Assertions.assertEquals("ATGCGA,CAGTC,TTATGT,AGAAGG,CCCCTA,TCACTG", constants.getDnaIncomplete());
    }

    /**
     * Test of getDnaInvalid method, of class Constants.
     */
    @Test
    void testGetDnaInvalid() {
        System.out.println("getDnaInvalid");
        Assertions.assertNotNull(constants.getDnaInvalid());
        Assertions.assertEquals("ATGCGA,CAGTGC,TTNTGT,AGAAGG,CCCCTA,TCACTG", constants.getDnaInvalid());
    }

    /**
     * Test of getDnaMutant method, of class Constants.
     */
    @Test
    void testGetDnaMutant() {
        System.out.println("getDnaMutant");
        Assertions.assertNotNull(constants.getDnaMutant());
        Assertions.assertEquals("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG", constants.getDnaMutant());
    }

    /**
     * Test of getDnaHuman method, of class Constants.
     */
    @Test
    void testGetDnaHuman() {
        System.out.println("getDnaHuman");
        Assertions.assertNotNull(constants.getDnaHuman());
        Assertions.assertEquals("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG", constants.getDnaHuman());
    }

}

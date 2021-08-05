/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author npavila
 */
@Getter
@Component
public class Constants {

    @Value("${pattern.characters}")
    private String patternCharacters;

    @Value("${match.number.dna}")
    private Integer matchNumberDna;

    @Value("${dna.void}")
    private String dnaVoid;

    @Value("${dna.incomplete}")
    private String dnaIncomplete;

    @Value("${dna.invalid}")
    private String dnaInvalid;

    @Value("${dna.mutant}")
    private String dnaMutant;

    @Value("${dna.human}")
    private String dnaHuman;

}

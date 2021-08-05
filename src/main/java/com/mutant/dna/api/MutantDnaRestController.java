/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.api;

import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.MessajeOutDto;
import com.mutant.dna.dto.StatusDto;
import com.mutant.dna.service.MutantDnaInterface;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author npavila
 */
@RestController
public class MutantDnaRestController {

    private final MutantDnaInterface service;

    @Autowired
    public MutantDnaRestController(MutantDnaInterface service) {
        this.service = service;
    }

    @PostMapping(value = "/mutant")
    public ResponseEntity<MessajeOutDto> mutant(@Valid @RequestBody DnaReadDto dto) {
        StringBuilder warn = new StringBuilder();
        boolean isMutant = service.isMutant(dto, warn);
        HttpHeaders responseHeaders = new HttpHeaders();
        if (warn.length() > 0) {
            return new ResponseEntity<>(new MessajeOutDto(warn.toString()),
                    responseHeaders, HttpStatus.BAD_REQUEST);
        }
        if (isMutant) {
            return new ResponseEntity<>(new MessajeOutDto("Is Mutant.!"),
                    responseHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessajeOutDto("Human."),
                responseHeaders, HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<StatusDto> stats() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(service.getStats(),
                responseHeaders, HttpStatus.OK);
    }
}

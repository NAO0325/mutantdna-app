/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.api;

import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.MessajeOutDto;
import com.mutant.dna.service.MutantDnaInterface;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    MutantDnaInterface service;

    @PostMapping(value = "/mutant")
    public ResponseEntity mutant(@RequestBody DnaReadDto dto) {
        StringBuilder warn = new StringBuilder();
        boolean isMutant = service.isMutant(dto, warn);
        if (warn.length() > 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessajeOutDto(warn.toString()));
        }
        if (isMutant) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessajeOutDto("Is Mutant.!"));
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new MessajeOutDto("Human."));
    }

    @GetMapping(value = "/stats")
    public ResponseEntity stats() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getStats());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.mapper.impl;

import com.google.gson.Gson;
import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.StatusDto;
import com.mutant.dna.mapper.DnaReadMapper;
import com.mutant.dna.model.DnaRead;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author npavila
 */
@Component
public class DnaReadMapperImpl implements DnaReadMapper {

    @Override
    public DnaRead DnaReadDtoToDnaRead(DnaReadDto dto) {
        DnaRead obj = new DnaRead();
        obj.setDnaRead(new Gson().toJson(dto));
        obj.setDateRead(new Date());
        obj.setMutant(false);
        return obj;
    }
}

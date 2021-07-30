/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.service.impl;

import com.mutant.dna.constants.Constants;
import com.mutant.dna.dto.DnaReadDto;
import com.mutant.dna.dto.StatusDto;
import com.mutant.dna.mapper.DnaReadMapper;
import com.mutant.dna.model.DnaRead;
import com.mutant.dna.repository.DnaReadRepo;
import com.mutant.dna.service.MutantDnaInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author npavila
 */
@Service
public class MutantDnaService implements MutantDnaInterface {

    @Autowired
    DnaReadRepo repo;

    @Autowired
    DnaReadMapper mapper;

    @Override
    public boolean isMutant(DnaReadDto dto, StringBuilder warn) {

        //Validando datos de entrada
        if (!validateArray(dto.getDna(), warn)) {
            return false;
        }

        String[][] matrixDna = listToMatrix(dto.getDna());
        List<String> lstChains = new ArrayList<>();

        readVertical(matrixDna, lstChains);
        readHorizontal(matrixDna, lstChains);
        readDiagonal(matrixDna, lstChains);
        readDiagonalInverse(matrixDna, lstChains);

        adjustValues(dto.getDna());

        //Registrando lectura de ADN
        DnaRead dnaRead = mapper.DnaReadDtoToDnaRead(dto);
        boolean isMutant = evalChains(lstChains);
        dnaRead.setMutant(isMutant);
        repo.save(dnaRead);

        return isMutant;
    }
    
    @Override
    public StatusDto getStats() {
        StatusDto dto = new StatusDto();
        List<DnaRead> allDnaRead = repo.findAll();

        long totalMutant = 0;
        long totalHuman = 0;
        for (DnaRead dnaRead : allDnaRead) {
            if(dnaRead.isMutant()){
                totalMutant ++;
            }else{
                totalHuman ++;
            }
        }
        
        dto.setCount_mutant_dna(totalMutant);
        dto.setCount_human_dna(totalHuman);
        
        if(totalHuman <= 0 && totalMutant > 0){
            dto.setRatio(1.0);
        }else if(totalHuman <= 0 && totalMutant <= 0){
            dto.setRatio(0.0);
        }else{
            double r = (double) totalMutant / (double) totalHuman;
            dto.setRatio(Precision.round(r,2));
        }
        
        return dto;
    }

    private boolean validateArray(List<String> lstADN, StringBuilder warn) {
        //Validando null o vacío
        if (lstADN == null || lstADN.isEmpty()) {
            warn.append("Valor de dna vacío o nulo");
            return false;
        }
        //Validando contenido
        if (!validateContain(lstADN)) {
            warn.append("Los valores leidos de dna contienen valores no permitidos");
            return false;
        }
        //Validando simetría de matriz
        if (!lstADN.stream().noneMatch((chain) -> (chain.length() != lstADN.size()))) {
            warn.append("Las cadenas de dna no cumplen la relación de simetría para matriz (NXN)");
            return false;
        }
        return true;
    }

    private static boolean validateContain(List<String> lstDna) {
        for (String val : lstDna) {
            //Removiendo espacios y validando vacios
            String nVal = val.replaceAll(" ", "");
            if (StringUtils.isBlank(nVal)) {
                return false;
            }
            //Verificando letras permitidas en la cadena
            Pattern pat = Pattern.compile(Constants.PATTERN_CHARACTERS);
            Matcher mat = pat.matcher(nVal);
            if (!mat.matches()) {
                return false;
            }
        }
        return true;
    }

    private void adjustValues(List<String> lstADN) {
        for (int row = 0; row < lstADN.size(); row++) {
            String nVal = lstADN.get(row).toUpperCase();
            lstADN.set(row, nVal);
        }
    }

    private String[][] listToMatrix(List<String> lstADN) {
        String[][] matrix = new String[lstADN.size()][lstADN.size()];
        for (int row = 0; row < lstADN.size(); row++) {
            String cols = lstADN.get(row);
            for (int col = 0; col < cols.length(); col++) {
                String val = "" + cols.charAt(col);
                matrix[row][col] = val;
            }
        }
        return matrix;
    }

    private void readVertical(String[][] matrix,
            List<String> lstChains) {
        int WIDTH = matrix.length;
        int HEIGHT = matrix[0].length;
        for (int i = 0; i < HEIGHT; i++) {
            String dnaRead = "";
            for (int j = 0; j < WIDTH; j++) {
                dnaRead += matrix[j][i] + ",";
            }
            dnaRead = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaRead);
        }
    }

    private void readHorizontal(String[][] matrix,
            List<String> lstChains) {
        int WIDTH = matrix.length;
        int HEIGHT = matrix[0].length;
        for (int i = 0; i < HEIGHT; i++) {
            String dnaRead = "";
            for (int j = 0; j < WIDTH; j++) {
                dnaRead += matrix[i][j] + ",";
            }
            dnaRead = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaRead);
        }
    }

    private void readDiagonal(String[][] matrix,
            List<String> lstChains) {
        int WIDTH = matrix.length;
        int HEIGHT = matrix[0].length;
        for (int k = 0; k <= WIDTH + HEIGHT - 2; k++) {
            String dnaRead = "";
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < HEIGHT && j < WIDTH) {
                    dnaRead += matrix[i][j] + ",";
                }
            }
            dnaRead = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaRead);
        }
    }

    private void readDiagonalInverse(String[][] matrix,
            List<String> lstChains) {
        int WIDTH = matrix.length;
        int HEIGHT = matrix[0].length;
        for (int k = 1 - WIDTH; k <= WIDTH - 1; k++) {
            String dnaRead = "";
            int ve = Math.max(0, k);
            int ho = -Math.min(0, k);
            for (int i = ve, j = ho; i < HEIGHT && j < WIDTH; i++, j++) {
                dnaRead += matrix[i][j] + ",";
            }
            dnaRead = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaRead);
        }
    }

    private boolean evalChains(List<String> lstDna) {
        for (String dna : lstDna) {
            String[] dnaArr = dna.split(",");
            if (dnaArr.length >= 4) {
                for (int i = 0; i < dnaArr.length - 1; i++) {
                    int frequency = Collections.frequency(Arrays.asList(dnaArr),
                            dnaArr[i]);
                    if (frequency >= Constants.MATCH_NUMBER_DNA) {
                        if (getTotalTimesIfExist(dnaArr, dnaArr[i]) >= Constants.MATCH_NUMBER_DNA) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getTotalTimesIfExist(String[] dnaChain, String dnaValue) {
        boolean match = false;
        int totalTimes = 0;
        int totalMax = 0;
        if (dnaChain == null || dnaChain.length <= 0) {
            return -1;
        }
        for (String dna : dnaChain) {
            if (!match) {
                totalMax = Math.max(totalTimes, totalMax);
                totalTimes = 0;
            }
            match = dna.equals(dnaValue);
            if (match) {
                totalTimes += 1;
            }
        }
        return totalMax;
    }

}

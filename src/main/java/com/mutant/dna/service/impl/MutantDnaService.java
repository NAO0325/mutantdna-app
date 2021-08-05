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

    private final DnaReadRepo repo;
    private final DnaReadMapper mapper;
    private final Constants constants;
    private int width = 0;
    private int height = 0;

    @Autowired
    public MutantDnaService(DnaReadRepo repo, DnaReadMapper mapper, Constants constants) {
        this.repo = repo;
        this.mapper = mapper;
        this.constants = constants;
    }

    @Override
    public boolean isMutant(DnaReadDto dto, StringBuilder warn) {

        //Validando datos de entrada
        if (!validateArray(dto, warn)) {
            return false;
        }

        String[][] matrixDna = listToMatrix(dto.getDna());
        List<String> lstChains = new ArrayList<>();

        readVerHor(matrixDna, lstChains, false);
        readVerHor(matrixDna, lstChains, true);
        readDiagonal(matrixDna, lstChains);
        readDiagonalInverse(matrixDna, lstChains);

        adjustValues(dto.getDna());

        //Registrando lectura de ADN
        DnaRead dnaRead = mapper.dnaReadDtoToDnaRead(dto);
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
            if (dnaRead.isMutant()) {
                totalMutant++;
            } else {
                totalHuman++;
            }
        }

        if (totalMutant > 0 && totalHuman == 0) {
            dto.setRatio(1.0);
        } else if (totalMutant == 0) {
            dto.setRatio(0.0);
        } else {
            double r = 0;
            if (totalHuman != 0) {
                r = (double) totalMutant / (double) totalHuman;
            }
            dto.setRatio(Precision.round(r, 2));
        }

        dto.setCountMutantDna(totalMutant);
        dto.setCountHumanDna(totalHuman);

        return dto;
    }

    private boolean validateArray(DnaReadDto dto, StringBuilder warn) {
        //Validando warn
        if(warn == null){
            warn = new StringBuilder();
            warn.append("Log de respuesta es nulo");
            return false;
        }
        //Validando null o vacío
        if (dto == null) {
            warn.append("Valor de request vacío o nulo");
            return false;
        }
        if (dto.getDna() == null ||  dto.getDna().isEmpty()) {
            warn.append("Valor de dna vacío o nulo");
            return false;
        }
        //Validando contenido
        if (!validateContain(dto.getDna())) {
            warn.append("Los valores leidos de dna contienen valores no permitidos");
            return false;
        }
        //Validando simetría de matriz
        if (dto.getDna().stream().anyMatch(chain -> (chain.length() != dto.getDna().size()))) {
            warn.append("Las cadenas de dna no cumplen la relación de simetría para matriz (NXN)");
            return false;
        }
        return true;
    }

    private boolean validateContain(List<String> lstDna) {
        for (String val : lstDna) {
            //Removiendo espacios y validando vacios
            String nVal = val.replace(" ", "");
            if (StringUtils.isBlank(nVal)) {
                return false;
            }
            //Verificando letras permitidas en la cadena
            Pattern pat = Pattern.compile(constants.getPatternCharacters());
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

    private void readVerHor(String[][] matrix, List<String> lstChains, boolean isHor) {
        width = matrix.length;
        height = matrix[0].length;
        StringBuilder dnaRead = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isHor) {
                    dnaRead.append(matrix[i][j]).append(",");
                } else {
                    dnaRead.append(matrix[j][i]).append(",");
                }
            }
            String dnaChain = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaChain);
            dnaRead.setLength(0);
        }
    }

    private void readDiagonal(String[][] matrix,
            List<String> lstChains) {
        width = matrix.length;
        height = matrix[0].length;
        StringBuilder dnaRead = new StringBuilder();
        for (int k = 0; k <= width + height - 2; k++) {
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < height && j < width) {
                    dnaRead.append(matrix[i][j]).append(",");
                }
            }
            String dnaChain = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaChain);
            dnaRead.setLength(0);
        }
    }

    private void readDiagonalInverse(String[][] matrix,
            List<String> lstChains) {
        width = matrix.length;
        height = matrix[0].length;
        StringBuilder dnaRead = new StringBuilder();
        for (int k = 1 - width; k <= width - 1; k++) {
            int ve = Math.max(0, k);
            int ho = -Math.min(0, k);
            for (int i = ve, j = ho; i < height && j < width; i++, j++) {
                dnaRead.append(matrix[i][j]).append(",");
            }
            String dnaChain = dnaRead.substring(0, dnaRead.length() - 1);
            lstChains.add(dnaChain);
            dnaRead.setLength(0);
        }
    }

    private boolean evalChains(List<String> lstDna) {
        for (String dna : lstDna) {
            String[] dnaArr = dna.split(",");
            if (dnaArr.length >= 4) {
                for (int i = 0; i < dnaArr.length - 1; i++) {
                    int frequency = Collections.frequency(Arrays.asList(dnaArr),
                            dnaArr[i]);
                    if (frequency >= constants.getMatchNumberDna() && isMatchOk(dnaArr, dnaArr[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isMatchOk(String[] dnaArr, String dnaValue) {
        return getTotalTimesIfExist(dnaArr, dnaValue) >= constants.getMatchNumberDna();
    }

    private int getTotalTimesIfExist(String[] dnaChain, String dnaValue) {
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

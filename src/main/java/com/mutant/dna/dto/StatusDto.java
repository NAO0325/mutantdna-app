/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mutant.dna.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author npavila
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusDto")
public class StatusDto {
    private Long count_mutant_dna;
    private Long count_human_dna;
    private Double ratio;
}

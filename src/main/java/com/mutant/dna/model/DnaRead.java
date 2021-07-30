package com.mutant.dna.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dna_read")
public class DnaRead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idread")
    private Long idread;
    @Basic(optional = false)
    @Column(name = "dna_read")
    private String dnaRead;
    @Basic(optional = false)
    @Column(name = "date_read")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRead;
    @Basic(optional = false)
    @Column(name = "is_mutant")
    private boolean isMutant;

}

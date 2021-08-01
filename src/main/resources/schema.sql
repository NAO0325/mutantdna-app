/************ Add: Sequences ***************/
CREATE SEQUENCE Dna_Read_idread_seq INCREMENT BY 1;
COMMENT ON SEQUENCE Dna_Read_idread_seq IS 'Secuencia para llave primaria autoincremental.';

/******************** Add Table: Dna_Read ************************/

/* Build Table Structure */
CREATE TABLE Dna_Read
(
	idread BIGINT DEFAULT nextval('public.Dna_Read_idread_seq'::regclass) NOT NULL,
	dna_read TEXT NOT NULL,
	date_read TIMESTAMP DEFAULT now() NOT NULL,
	is_mutant BOOL DEFAULT false NOT NULL
);

/* Add Comments */
COMMENT ON COLUMN Dna_Read.idread IS 'Llave primaria';

COMMENT ON COLUMN Dna_Read.dna_read IS 'Cadena en formato json ingresada';

COMMENT ON COLUMN Dna_Read.date_read IS 'Fecha de lectura de dna';

COMMENT ON COLUMN Dna_Read.is_mutant IS 'Indica si el dna leido es mutante o no';
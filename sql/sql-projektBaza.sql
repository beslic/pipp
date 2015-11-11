-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-11-11 19:41:40.084




-- tables
-- Table: anketa
CREATE TABLE anketa (
    idAnketa int  NOT NULL,
    idkorisnik int  NOT NULL,
    ime varchar(20)  NOT NULL,
    privatna boolean  NOT NULL,
    aktivna boolean  NOT NULL,
    datumIzrade date  NOT NULL,
    datumZavrsetka date  NOT NULL,
    CONSTRAINT anketa_pk PRIMARY KEY (idAnketa)
);



-- Table: anketar
CREATE TABLE anketar (
    idAnketar int  NOT NULL,
    idKorisnik int  NOT NULL,
    ime char(20)  NOT NULL,
    proveoAnketa int  NOT NULL,
    username char(20)  NOT NULL,
    password char(20)  NOT NULL,
    CONSTRAINT anketar_pk PRIMARY KEY (idAnketar)
);



-- Table: ispunjavanjeAnkete
CREATE TABLE ispunjavanjeAnkete (
    idIspunjavanjeAnkete int  NOT NULL,
    idAnketa int  NOT NULL,
    Geolokacija char(20)  NULL,
    Vrijeme date  NOT NULL,
    CONSTRAINT ispunjavanjeAnkete_pk PRIMARY KEY (idIspunjavanjeAnkete)
);



-- Table: korisnik
CREATE TABLE korisnik (
    idKorisnik int  NOT NULL,
    Ime char(20)  NOT NULL,
    Prezime char(20)  NOT NULL,
    kompanija char(30)  NULL,
    email char(30)  NOT NULL,
    adresa char(100)  NOT NULL,
    username char(20)  NOT NULL,
    password char(20)  NOT NULL,
    CONSTRAINT korisnik_pk PRIMARY KEY (idKorisnik)
);



-- Table: odabraniOdgovori
CREATE TABLE odabraniOdgovori (
    idOdabraniOdgovor int  NOT NULL,
    idOdgovor int  NOT NULL,
    idIspunjavanjeAnkete int  NOT NULL,
    CONSTRAINT odabraniOdgovori_pk PRIMARY KEY (idOdabraniOdgovor)
);



-- Table: odgovori
CREATE TABLE odgovori (
    idOdgovor int  NOT NULL,
    idPitanja int  NOT NULL,
    odgovor char(100)  NOT NULL,
    brojOdabiranja int  NOT NULL,
    CONSTRAINT odgovori_pk PRIMARY KEY (idOdgovor)
);



-- Table: pitanja
CREATE TABLE pitanja (
    idPitanja int  NOT NULL,
    idAnketa int  NOT NULL,
    pitanje char(100)  NOT NULL,
    brojPostavljanja int  NOT NULL,
    CONSTRAINT pitanja_pk PRIMARY KEY (idPitanja)
);



-- Table: pristupAnketama
CREATE TABLE pristupAnketama (
    idPristupAnketama int  NOT NULL,
    idAnketar int  NOT NULL,
    idAanketa int  NOT NULL,
    CONSTRAINT pristupAnketama_pk PRIMARY KEY (idPristupAnketama)
);







-- foreign keys
-- Reference:  Anketa_Narucitelj (table: anketa)


ALTER TABLE anketa ADD CONSTRAINT Anketa_Narucitelj 
    FOREIGN KEY (idkorisnik)
    REFERENCES korisnik (idKorisnik)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Odgovori_Pitanja (table: odgovori)


ALTER TABLE odgovori ADD CONSTRAINT Odgovori_Pitanja 
    FOREIGN KEY (idPitanja)
    REFERENCES pitanja (idPitanja)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Pitanja_Anketa (table: pitanja)


ALTER TABLE pitanja ADD CONSTRAINT Pitanja_Anketa 
    FOREIGN KEY (idAnketa)
    REFERENCES anketa (idAnketa)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  anketar_korisnik (table: anketar)


ALTER TABLE anketar ADD CONSTRAINT anketar_korisnik 
    FOREIGN KEY (idKorisnik)
    REFERENCES korisnik (idKorisnik)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  ispunjavanjeAnkete_anketa (table: ispunjavanjeAnkete)


ALTER TABLE ispunjavanjeAnkete ADD CONSTRAINT ispunjavanjeAnkete_anketa 
    FOREIGN KEY (idAnketa)
    REFERENCES anketa (idAnketa)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  ispunjavanjeAnkete_anketar (table: pristupAnketama)


ALTER TABLE pristupAnketama ADD CONSTRAINT ispunjavanjeAnkete_anketar 
    FOREIGN KEY (idAnketar)
    REFERENCES anketar (idAnketar)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  odabraniOdgovori_ispunjavanjeAnkete (table: odabraniOdgovori)


ALTER TABLE odabraniOdgovori ADD CONSTRAINT odabraniOdgovori_ispunjavanjeAnkete 
    FOREIGN KEY (idIspunjavanjeAnkete)
    REFERENCES ispunjavanjeAnkete (idIspunjavanjeAnkete)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  odgovori_Odgovori (table: odabraniOdgovori)


ALTER TABLE odabraniOdgovori ADD CONSTRAINT odgovori_Odgovori 
    FOREIGN KEY (idOdgovor)
    REFERENCES odgovori (idOdgovor)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  pristupAnketama_anketa (table: pristupAnketama)


ALTER TABLE pristupAnketama ADD CONSTRAINT pristupAnketama_anketa 
    FOREIGN KEY (idAanketa)
    REFERENCES anketa (idAnketa)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;






-- End of file.


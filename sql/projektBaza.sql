-- projektBaza v0.2
-- Vedran Biđin, 15.12.2015

/*
Promjene zbog Hibernate-a:
- svaka relacija ima novi primarni kljuc: idImeRelacije
- u skladu sa novim primarnim promjenjeni su strani kljucevi
- rbrOdgovor i rbrPitanje preimenovani u idOdgovor i idPitanje (zbog jednostavnosti)
- uklonjeni su svi suvisni atributi
*/

CREATE TABLE korisnik 
    (
      idKorisnik    SERIAL
    , korisnickoIme CHAR(40)
    , ime           CHAR(20)
    , prezime       CHAR(20)
    , email         CHAR(40)
    , lozinka       CHAR(40)
    , razinaPrava   SMALLINT
    , aktivan       BOOLEAN
    , CONSTRAINT pk_korisnik PRIMARY KEY (idKorisnik)
    , CONSTRAINT ck_korisnik_razinaPrava CHECK (razinaPrava BETWEEN 0 and 2)
	, CONSTRAINT uq_korisnik_korisnickoIme UNIQUE (korisnickoIme)
	, CONSTRAINT uq_korisnik_email UNIQUE (email)
    );
    
CREATE TABLE anketa 
    (
      idAnketa      SERIAL
    , idKorisnik    SERIAL
    , vrijemeIzrada TIMESTAMP
    , nazivAnketa   CHAR(20) 
    , opisAnketa    CHAR(200)
    , jePrivatna    BOOLEAN
    , aktivnaOd     TIME
    , aktivnaDo     TIME
    , CONSTRAINT pk_anketa PRIMARY KEY (idAnketa)
    , CONSTRAINT fk_anketa_korisnik FOREIGN KEY (idKorisnik) REFERENCES korisnik (idKorisnik)
    );
    
CREATE TABLE pitanje 
    (
      idPitanje    SERIAL
    , idAnketa     SERIAL
    , tekstPitanje CHAR(200)
    , CONSTRAINT pk_pitanje PRIMARY KEY (idPitanje)
    , CONSTRAINT fk_pitanje_anketa FOREIGN KEY (idAnketa) REFERENCES anketa (idAnketa)
    );
    
CREATE TABLE odgovor 
    (
      idOdgovor    SERIAL
    , idPitanje    SERIAL
    , tekstOdgovor CHAR(200)
    , CONSTRAINT pk_odgovor PRIMARY KEY (idOdgovor)
    , CONSTRAINT fk_odgovor_pitanje FOREIGN KEY (idPitanje) REFERENCES pitanje (idPitanje)
    );
      
CREATE TABLE ispunjavanje 
    (
      idIspunjavanje      SERIAL
    , idKorisnik          SERIAL
    , idAnketa            SERIAL
    , vrijemeIspunjavanja TIME
    , longitude           FLOAT
    , latitude            FLOAT
    , CONSTRAINT pk_ispunjavanje PRIMARY KEY (idIspunjavanje)
    , CONSTRAINT fk_ispunjavanje_korisnik FOREIGN KEY (idKorisnik) REFERENCES korisnik (idKorisnik)
    , CONSTRAINT fk_ispunjavanje_anketa FOREIGN KEY (idAnketa) REFERENCES anketa (idAnketa)
    );
    
CREATE TABLE jeAnketar 
    (
      idJeAnketar SERIAL
    , idAnketa    SERIAL
    , idKorisnik  SERIAL
    , CONSTRAINT pk_jeAnketar PRIMARY KEY (idJeAnketar)
    , CONSTRAINT fk_jeAnketar_anketa FOREIGN KEY (idAnketa) REFERENCES anketa (idAnketa)
    , CONSTRAINT fk_jeAnketar_korisnik FOREIGN KEY (idKorisnik) REFERENCES korisnik (idKorisnik)
    );
      
CREATE TABLE odabraniOdgovor 
    (
      idOdabraniOdgovor SERIAL
    , idIspunjavanje    SERIAL
    , idOdgovor         SERIAL
    , CONSTRAINT pk_odabraniOdgovor PRIMARY KEY (idOdabraniOdgovor)
    , CONSTRAINT fk_odabraniOdgovor_ispunjavanje FOREIGN KEY (idIspunjavanje) REFERENCES ispunjavanje (idIspunjavanje)
    , CONSTRAINT fk_odabraniOdgovor_odgovor FOREIGN KEY (idOdgovor) REFERENCES odgovor (idOdgovor)
    );
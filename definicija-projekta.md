# Projekt iz programske potpore - Sustav za anketiranje

## Sustav za provođenje anketiranja putem web aplikacije i mobilne aplikacije

#### Arhitektura sustava:
  * web aplikacija
  * mobilna aplikacija

#### Korisnici sustava:  

  * Adminitrator - jedan ili više korisnika koji održavaju sustav. Postoji samo jedan administratorski račun i samo jedan može biti prijavljen na sustav, ali njime se može koristiti više osoba (unutar firme, udruge i sličnih korisnika ovog sustava). Administrator može praviti anketu, učinit određenu anketu privatnom ili javnom, pregledati rezultate svih napravljenih anketa i slično.  

  * Naručitelj ankete (registrirani korisnik) - svi posjetitelji sustava se mogu registrirati na sustav za anketiranje. Nakon registracije bit će im omogućeno pravljenje ankete. Korisnik može odabrati hoće li njegova anketa biti javno dostupna (putem web aplikacije) ili samo privatna. U slučaju privatne ankete, jedini mogući način ispunjavanja ankete je koristeći našu mobilnu aplikaciju.  

  * Anketar - osoba čija je zdaća provoditi anketu. Anketa se provodi putem mobilne aplikacije. Nakon instalacije mobilne aplikacije na Android uređaj, anketar unosi _ID_ ankete čime će se (ukoliko je anketa pronađena u bazi, odnosno _ID_ je valjan) anketa će se skinuti na mobilni uređaj i moći će se započeti s ispunjavanjem ankete.  

  * Anonimni posjetitelji - mogu pristupiti javnom dijelu web aplikacije. Ukoliko postoje javno dostupne ankete, anonimni posjetitelji mogu ispunjavati ankete, vidjeti rezultate ankete i slično.

#### Sustav anketiranja omogućava obavljanje sljedećih procesa:
  1. Upravljaje podacima o registriranim korisnicima  

    Podaci koji se traže od korisnika su pri registraciji su:
    * ime (nužan podatak)
    * prezime (nije nužan podatak)
    * e-mail adresa (nužan podatak, ne smije već postojati u bazi)
    * adresa (nije nužan podatak, predstavlja adresu stanovanja, firme, ...)
    * korisničko ime (nužan podatak, ne smije već postojati u bazi)
    * šifra (nužan podatak)  

  Registracija korinika se vrši putem web apikacije (sam položaj _tipke_ odnosno _poveznice_ za registraciju, kao i sama registracija, mora biti jasno intuitivna). Na stranici za registraciju korisnik upisuje svoje podatke. Nakon klika na _tipku_ za registraciju korisnika se obavještava da je njegova registracija u tijeku. Nakon što su podaci poslani, provjereni (ukoliko neki od podataka nije u redu, korisnika se o tome obavještava), i ako su svi u redu, podaci će biti poslani

## ER model

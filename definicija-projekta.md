# Projekt iz programske potpore - Sustav za anketiranje

## Sustav za provođenje anketiranja putem web aplikacije i mobilne aplikacije

#### Arhitektura sustava:
  * web aplikacija
  * mobilna aplikacija

#### Korisnici sustava:  

  * Administrator - jedan ili više korisnika koji održavaju sustav. Administratorski računi izrađuju se ručno u bazi podataka. Svaki administrator ima vlastiti username i password.  Administrator može praviti anketu, učiniti određenu anketu privatnom ili javnom, pregledati rezultate svih napravljenih anketa i uređivati račune korisnika i anketara.  

  * Korisnik (registrirani korisnik) - svi posjetitelji sustava se mogu registrirati na sustav za anketiranje. Nakon registracije bit će im omogućeno pravljenje ankete. Korisnik može odabrati hoće li njegova anketa biti javno dostupna (putem web aplikacije) ili samo privatna. U slučaju privatne ankete, jedini mogući način ispunjavanja ankete je koristeći našu mobilnu aplikaciju. Korisnik može izraditi anketarske račune i pridružiti im mogućnost ispunjavanja anketa. Korisnik ne može izbrisati anketu, anketa može biti samo deaktivirana.

  * Anketar - osoba čija je zdaća provoditi anketu. Anketa se provodi putem mobilne aplikacije. Nakon instalacije mobilne aplikacije na Android uređaj, anketar unos podatke za prijavu. Podake za prijavu dobiva od korisnika za kojeg porovodi ankete. Ankete, za čije provođenje anketar ima odobrenje od korisnika, će se skinuti na mobilni uređaj i moći će se započeti s ispunjavanjem ankete.  

  * Anonimni posjetitelji - mogu pristupiti javnom dijelu web aplikacije. Ukoliko postoje javno dostupne aktivne ankete, anonimni posjetitelji mogu ispunjavati ankete, vidjeti rezultate ankete i slično.

#### Sustav anketiranja omogućava obavljanje sljedećih procesa:

  **1. Upravljaje podacima o registriranim korisnicima** 

  Registracija korinika se vrši putem web apikacije (sam položaj _tipke_ odnosno _poveznice_ za registraciju, kao i sama registracija, mora biti jasno intuitivna). Na stranici za registraciju korisnik upisuje svoje podatke. Nakon klika na _tipku_ za registraciju korisnika se obavještava da je njegova registracija u tijeku. Nakon što su podaci poslani, provjereni (ukoliko neki od podataka nije u redu, korisnika se o tome obavještava), i ako su svi u redu, podaci će biti poslani. Nakon primanja, zahtjev za registracijom treba biti odobren od administratora.
  
  U bilo kojem trenutku korisnik može izmijeniti vlastite podatke na svojoj profilnoj straici. Nakon izmjene podataka vrši se provjera. Ako su novi podatci ispravni korisnik ima opciju spremanja izmjena. Administrator ima mogućnost upravljanja podatcima o korisniku i mogućnost isključenja korisničkog računa.

 Podaci koji se traže od korisnika su pri registraciji su:
    * ime (nužan podatak)
    * prezime (nije nužan podatak)
    * e-mail adresa (nužan podatak, ne smije već postojati u bazi)
    * adresa (nije nužan podatak, predstavlja adresu stanovanja, firme, ...)
    * korisničko ime (nužan podatak, ne smije već postojati u bazi)
    * šifra (nužan podatak)  
    
**2. Definiranje ankete**
    
Registrirani korisnik ima opciju izrade novih anketa. Anketa može biti privatna ili javna i može sadržati proizvoljni broj pitanja (najmanje 1). Pitanja nude predodređene odgovore od kojih mora biti odabran točno jedan da pitanje bude odgovoreno. Svako pitanje može sadržavati proizvoljan broj odgovora (najmanje 1). Za popunjavanje ankete nije nužno da sva pitanja budu odgovorena. Za neodgovorena pitanja predviđen je default odgovor neodgovoren koji se automatski izrađuje.

**3. Upravljaje podacima o registriranim anketarima** 

Registraciju anketara vrši korisnik putem web aplikacije (sam položaj _tipke_ odnosno _poveznice_ za registraciju, kao i sama registracija, mora biti jasno intuitivna). Na stranici za izradu anketarskog računa korisnik upisuje svoje podatke. Nakon klika na _tipku_ za izradu/spremanje podataka korisnika se obavještava da je izrada računa u tijeku. Nakon što su podaci poslani, provjereni (ukoliko neki od podataka nije u redu, korisnika se o tome obavještava), i ako su svi u redu, podaci će biti poslani.Svaki korisnik ima određen broj anketarskih računa koje može kreirati. 


U bilo kojem trenutku korisnik može izmijeniti podatke o postojećim anketarima ili uključiti/isključiti račune anketara na stranici s popisom anketara. Nakon izmjene podataka vrši se provjera. Ako su novi podatci ispravni korisnik ima opciju spremanja izmjena. Administrator ima mogućnost upravljanja podatcima o anketarima, mogućnost izrade novih i isključenja postojećih anketara.  

Podaci koji se traže od korisnika su pri registraciji anketara su:

 * ime (nužan podatak)
 * korisničko ime (nužan podatak, ne smije već postojati u bazi)
 * šifra (nužan podatak)  
 
 

**4. Preuzimanje nove ankete na mobilni uređaj / slanje rezultata**

Nakon prve prijave na uređaj automatski se preuzimaju sve ankete koje su dostupne anketaru. Budući da je mobilna aplikacija predviđena i za korištenje bez pristupa internetu, anketar mora ručno pokrenuti sinkronizaciju podataka s bazom. Sinkronizacija uključuje slanje svih prikupljenih ispunjavanja, provjera dostupnosi anketa te na osnovi toga preuzimanje odnosno brisanje postojećih.

**5. Provođenje ankete**

Kada anketar žapočima provođenje ankete, automatski se bilježi vrijeme i (ako postoji pristup internetu) geolokacija. Anketar daje pitanja i bilježi odgovore u mobilnu aplikaciju. Sva pitanja imaju ponuđene odgovore od kojih samo jedan mora biti odabran da bi pitanje bilo odgovoreno. Sva pitanja moraju biti odgovorena da bi se anketa smatrala ispunjenom.

**6. Analiza i prikaz rezultata ankete**

Rezultati ankete biti će dostupni na web aplikaciji. Ako je anketa označena kao javna biti će dostupna svima, ako je označena kao privatna dostupna je samo korisniku koji je vlasnik ankete. Za pregled ankete biti će dostupni jednostavni i složeni upiti.

Jednostavni upit prikazuje broj i postotak odabira svih odgovora za određeno pitanje.
Složeni upit daje mogučnost udruživanja 2 ili više pitanja te promatranja udruženih rezultata.
Oba upita nude grafičko prikazivanje rezultata.

 

# Bankomat Aplikacija

Ova aplikacija simulira rad bankomata i omogućava korisnicima da podižu i uplaćuju novac sa svojih računa.

## Funkcionalnosti

Aplikacija nudi sledeće funkcionalnosti:

- Unos PIN-a za pristup računu.
- Podizanje novca sa računa.
- Uplata novca na račun.
- Pregled stanja na računu.

## Pokretanje Aplikacije

Da biste pokrenuli aplikaciju, pratite sledeće korake:

1. Klonirajte ovaj GitHub repozitorijum na svoj računar:

   ```shell
   git clone https://github.com/VašKorisničkiNalog/Bankomat-Aplikacija.git

Otvorite projekt u vašem razvojnom okruženju (npr. Eclipse, IntelliJ IDEA).

Pokrenite Main.java kako biste startovali aplikaciju.

Pratite upute na ekranu za unos PIN-a i izbor transakcija.


## Struktura Projekta

Projekat je organizovan u sledećim klasama:

Racun: Klasa koja predstavlja osnovni bankovni račun i sadrži metode za upravljanje stanjem i PIN-om.
- Napomena: PIN se generiše i dostupan je korisniku u glavnom ekranu iz razloga testiranja. 
PIN NE treba biti dostupan korisniku na glavnom ekranu, već PIN dobija u realnom svetu prilikom otvaranja računa. 

Racun.Korisnik: Podklasa koja nasleđuje Racun i sadrži metode specifične za korisničke transakcije.

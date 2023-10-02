# Aplikacija Bankomat

Ova aplikacija je simulacija bankomata i omogućava korisnicima da podižu i uplaćuju novac sa svojih računa.

## Funkcionalnosti

Aplikacija nudi sledeće funkcionalnosti:

- Unos PIN-a za pristup računu.
- Podizanje novca sa računa.
- Uplata novca na račun.
- Pregled stanja na računu.


## Fotografije

### Unos PINa
![pin](https://github.com/rile037/Banka/assets/60843900/102eadde-adeb-464e-a889-4b07911ecd19)

-----

### Glavni meni
![glavniMeni](https://github.com/rile037/Banka/assets/60843900/b962169a-cbb1-4729-8566-6fa3a612d5ab)

-----

### Podizanje novca i izvrsavanje transakcije
![podizanje](https://github.com/rile037/Banka/assets/60843900/36ba3994-d73e-4f6a-9728-ae587c3ff586)

-----

## Struktura Projekta

Projekat je organizovan po sledećim klasama:

**Racun**: Klasa koja predstavlja osnovni bankovni račun i sadrži metode za upravljanje stanjem i PIN-om.
- Napomena: PIN se generiše i dostupan je korisniku u glavnom ekranu iz razloga testiranja. 
PIN NE treba biti dostupan korisniku na glavnom ekranu, već PIN dobija u realnom svetu prilikom otvaranja računa. 

**Racun.Korisnik**: Podklasa koja nasleđuje Racun i sadrži metode specifične za korisničke transakcije.


## Pokretanje Aplikacije

Da biste pokrenuli aplikaciju, pratite sledeće korake:

1. Klonirajte ovaj GitHub repozitorijum na svoj računar:

   ```shell
   git clone https://github.com/VašKorisničkiNalog/Bankomat-Aplikacija.git

Otvorite projekt u vašem razvojnom okruženju (npr. Eclipse, IntelliJ IDEA).

Pokrenite Main.java kako biste startovali aplikaciju.

Pratite upute na ekranu za unos PIN-a i izbor transakcija.


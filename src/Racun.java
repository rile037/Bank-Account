import java.util.Random; // Importovanje klase za generisanje slučajnih brojeva

// Apstraktna klasa 'Transakcija' koja služi kao interfejs za ispis informacija o transakcijama
abstract class Transakcija {
    // Metoda za ispis informacija o transakciji
    public void izvodTransakcije(String vrstaTransakcije, double iznos, int brojRacuna) {
        // Ispisuje vlasnika računa
        System.out.println("\nVlasnik racuna: " + getVlasnikRacuna());
        // Ispisuje broj računa, maskiran
        System.out.println("Broj racuna: " + maskirajRacun(brojRacuna));
        System.out.println("--------------------------");
        // Ispisuje vrstu transakcije i iznos
        System.out.println("Vrsta transakcije: " + vrstaTransakcije);
        System.out.println("Iznos transakcije: " + iznos);
        System.out.println("Datum i vreme: \n");
    }

    // Apstraktna metoda za dobijanje vlasnika računa
    public abstract String getVlasnikRacuna();

    // Metoda za maskiranje broja računa
    public String maskirajRacun(int brojRacuna) {
        String racun = Integer.toString(brojRacuna);
        StringBuilder rezultat = new StringBuilder();

        for (int i = 0; i < racun.length(); i++) {
            char cifra = racun.charAt(i);
            // Maskira broj računa od 5. do 9. cifre
            if (i > 4 && i < 9) {
                rezultat.append("x");
            } else {
                rezultat.append(cifra);
            }
        }
        return rezultat.toString();
    }
}

// Klasa 'Racun' koja predstavlja osnovni račun
public class Racun {
    // Promenljive za vlasnika računa, broj računa, status, stanje i PIN
    private String vlasnikRacuna;
    private final int brojRacuna;
    private String statusRacuna;
    private double stanje;
    private int pin;

    // Konstruktor za inicijalizaciju računa
    public Racun(String vlasnikRacuna, int brojRacuna, double stanje) {
        this.vlasnikRacuna = vlasnikRacuna;
        this.brojRacuna = brojRacuna;
        this.stanje = stanje;
        this.statusRacuna = "Aktivan";
        this.pin = generisiPin();
    }

    // Metoda za zamrzavanje računa nakon 3 neuspela unosa PIN-a
    protected void zamrzniRacun() {
        System.out.println("Uneli ste pogresan PIN 3 puta.");
        System.out.println("Vas racun je BLOKIRAN.");
        System.out.println("- Kontaktirajte podrsku ili posetite najblizu filijalu.");
        this.statusRacuna = "Zamrznut";
    }

    // Metoda za generisanje PIN-a
    public int generisiPin() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    // Getteri i setteri za atribute
    public String getVlasnikRacuna() {
        return vlasnikRacuna;
    }

    public void setVlasnikRacuna(String vlasnikRacuna) {
        this.vlasnikRacuna = vlasnikRacuna;
    }

    protected int getBrojRacuna() {
        return this.brojRacuna;
    }

    public String proveriStatusRacuna() {
        return this.statusRacuna;
    }

    public int vratiPin() {
        return this.pin;
    }

    public boolean proveriPin(int unetiPin) {
        return unetiPin == this.pin;
    }

    public void promeniPin(int noviPin) {
        this.pin = noviPin;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public double getStanje() {
        return this.stanje;
    }

    // Klasa 'Korisnik' koja nasleđuje klasu 'Racun'
    public class Korisnik extends Racun {
        // Dodatni atributi za datum rođenja i transakciju
        private String datumRodjenja;
        public static final int minimum = 500;
        private Transakcija transakcija;

        // Konstruktor za korisnika
        public Korisnik(String datumRodjenja) {
            super(vlasnikRacuna, brojRacuna, stanje);
            this.datumRodjenja = datumRodjenja;
            this.transakcija = new Transakcija() {
                @Override
                public String getVlasnikRacuna() {
                    return vlasnikRacuna;
                }
            };
        }

        // Metoda za podizanje novca
        public void podigni(double iznos) {
            if (iznos <= 0) {
                System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            } else if (iznos > getStanje()) {
                System.out.println("\n- Nemate dovoljno sredstava na racunu.\n");
            } else if (((int) iznos % minimum != 0)) {
                System.out.println("\n- Minimum podizanja po transakciji je: " + minimum + "\n");
            } else {
                setStanje(getStanje() - iznos);
                System.out.println("\n- Uspesno ste podigli " + iznos + " rsd sa racuna.");
            }
        }

        // Metoda za uplatu novca
        public void uplati(double iznos) {
            if (((int) iznos % 10 != 0) || (((int) iznos < 10))) {
                System.out.println("Bankomat ne prima kovanice.\n");
            } else {
                System.out.println("\n- Staro stanje: " + getStanje());
                setStanje(getStanje() + iznos);
                System.out.println("- Novo stanje: " + getStanje() + "\n");
            }
        }
    }

}

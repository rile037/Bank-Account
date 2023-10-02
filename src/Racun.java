import java.util.Random;

interface Transakcija{
    void izvodTransakcije(String vrstaTransakcije, double iznos);
}

public class Racun {

    protected int brojRacuna;
    private String statusRacuna;
    protected double stanje;
    private int pin;

    public Racun(int brojRacuna, double stanje){
        this.brojRacuna = brojRacuna;
        this.stanje = stanje;
        this.statusRacuna = "Aktivan";
        this.pin = generisiPin();
    }

    protected int getBrojRacuna(){
        return this.brojRacuna;
    }
    protected void zamrzniRacun(){
        System.out.println("Uneli ste pogresan PIN 3 puta.");
        System.out.println("Vas racun je BLOKIRAN.");
        System.out.println("- Kontaktirajte podrsku ili posetite najblizu filijalu.");
        this.statusRacuna = "Zamrznut";
    }
    public String proveriStatusRacuna(){
        return this.statusRacuna;
    }
    public int vratiPin(){
        return this.pin;
    }
    public boolean proveriPin(int unetiPin){
        return unetiPin == this.pin;
    }
    public int generisiPin(){
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    public void promeniPin(int noviPin){
        this.pin = noviPin;
    }

    public void setStanje(double stanje){
        this.stanje = stanje;
    }

    public double getStanje(){
        return this.stanje;
    }

    public class Korisnik extends Racun implements Transakcija {
        String vlasnikRacuna, datumRodjenja;
        public static int minimum = 500;
        @Override
        public void izvodTransakcije(String vrstaTransakcije, double iznos){
            System.out.println("\nVlasnik racuna: " + getVlasnikRacuna());
            System.out.println("Broj racuna: " + maskirajRacun());
            System.out.println("--------------------------");
            System.out.println("Vrsta transakcije: " + vrstaTransakcije);
            System.out.println("Iznos transakcije: " + iznos);
            System.out.println("Datum i vreme: \n" );
        }
        public Korisnik(String vlasnikRacuna, String datumRodjenja) {
            super(Racun.this.brojRacuna, Racun.this.stanje);
            this.vlasnikRacuna = vlasnikRacuna;
            this.datumRodjenja = datumRodjenja;
        }



        public String maskirajRacun(){
            String racun = Integer.toString(getBrojRacuna());
            StringBuilder rezultat = new StringBuilder();

            for(int i = 0; i < racun.length(); i++){
                  char cifra = racun.charAt(i);
                  if(i > 4 && i < 9){
                      rezultat.append("*");
                  }
                  else{
                      rezultat.append(cifra);
                  }
              }
            return rezultat.toString();
        }

        public String getVlasnikRacuna(){
            return vlasnikRacuna;
        }

        public void setVlasnikRacuna(String vlasnikRacuna){
            this.vlasnikRacuna = vlasnikRacuna;
        }

        public String getDatumRodjenja(){
            return datumRodjenja;
        }

        public void setDatumRodjenja(String datumRodjenja){
            this.datumRodjenja = datumRodjenja;
        }

        public void podigni(double iznos){
            if (iznos <= 0) {
                System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            } else if (iznos > getStanje()) {
                System.out.println("\n- Nemate dovoljno sredstava na racunu.\n");
            } else if(((int) iznos % minimum != 0)){
                System.out.println("\n- Minimum podizanja po transakciji je: " + minimum + "\n");
            }
            else {
                setStanje(getStanje() - iznos);
                System.out.println("\n- Uspesno ste podigli " + iznos + " rsd sa racuna.");
            }
        }
        public void uplati(double iznos) {
            if (((int) iznos % 10 != 0) || (((int) iznos < 10))){
                System.out.println("Bankomat ne prima kovanice.\n");
            } else {
                System.out.println("\n- Staro stanje: " + getStanje());
                setStanje(getStanje() + iznos);
                System.out.println("- Novo stanje: " + getStanje() + "\n");
            }
        }
    }
}

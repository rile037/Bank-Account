import java.awt.font.GraphicAttribute;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Racun racun = new Racun(123456789, 25000);
        Racun.Korisnik korisnik = racun.new Korisnik("Nikola Rilak", "14.08.1999");
        boolean ispravanPin = false;
        int brojacPogresnogPina = 1;

        System.out.println("* [TEST] Vas PIN: " + racun.vratiPin());

        while (!ispravanPin) {
            System.out.println("Unesite pin: ");
            if (scanner.hasNextInt()) {
                int unetiPin = scanner.nextInt();
                if (racun.proveriPin(unetiPin)) {
                    System.out.println("Dobrodosli, " + korisnik.vlasnikRacuna);
                    prikaziStanje(korisnik);
                    ispravanPin = true;
                } else {
                    if (brojacPogresnogPina < 3) {
                        System.out.println("- Pogresan PIN, pokusajte ponovo\n");
                        brojacPogresnogPina++;
                    } else {
                        racun.zamrzniRacun();
                        scanner.close();
                        System.exit(0);
                    }
                }
            } else if (brojacPogresnogPina == 3) {
                racun.zamrzniRacun();
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("- Pogresan PIN, pokusajte ponovo\n");
                brojacPogresnogPina++;
                scanner.next();
            }
        }
        while (true) {
            prikaziMeni();
            if (scanner.hasNextInt()) {
                int izbor = scanner.nextInt();
                if(izbor >= 0 && izbor <= 3){
                    switch (izbor) {
                        case 1 -> podigniNovac(scanner, korisnik, racun);
                        case 2 -> uplatiNovac(scanner, korisnik);
                        case 3 -> prikaziStanje(korisnik);
                        case 0 -> {
                            zavrsiProgram(scanner);
                            return;
                        }
                        default -> System.out.println("\nNepoznat izbor, pokusajte ponovo.\n");
                    }
                }
                else{
                    System.out.println("\nNepoznat izbor, pokusajte ponovo\n");
                }
            }else{
                System.out.println("\nUnesite validan izbor.\n");
                scanner.next();
            }
        }
    }

    private static void prikaziMeni() {
        System.out.println("1. Podignite");
        System.out.println("2. Uplatite");
        System.out.println("3. Stanje racuna\n");
        System.out.println("0. Izlaz");
    }

    private static void prikaziStanje(Racun.Korisnik korisnik) {
        System.out.println("- Stanje vaseg racuna:" + korisnik.getStanje() + "\n");
    }

    private static void podigniNovac(Scanner scanner, Racun.Korisnik korisnik, Racun racun) {
        System.out.println("Unesite iznos koji zelite da podignete: ");
        if (scanner.hasNextInt()) {
            double iznos = scanner.nextDouble();
            if (iznos <= 0 || iznos > racun.getStanje()) {
                System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            } else {
                System.out.println("Da li zelite da izvod transakcije?:\n[1 == Da]\n[0 == Ne]");
                int izbor = scanner.nextInt();
                korisnik.podigni(iznos);
                prikaziStanje(korisnik);
                if(izbor == 1){
                    korisnik.izvodTransakcije("Isplata",iznos);
                }
            }
        } else {
            System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            scanner.next();
        }
    }

    private static void uplatiNovac(Scanner scanner, Racun.Korisnik korisnik) {
        System.out.println("\n-Bankomat prima novcanice od: \n- 10, 20, 50, 100, 200, 500, 1000, 2000, 5000\n");
        System.out.println("Unesite kolicinu novca: ");
        if (scanner.hasNextInt()) {
            double iznos = scanner.nextDouble();
            if (iznos <= 0) {
                System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            } else {
                System.out.println("Da li zelite da izvod transakcije?:\n[1 == Da]\n[0 == Ne]");
                int izbor = scanner.nextInt();
                korisnik.uplati(iznos);
                prikaziStanje(korisnik);
                if(izbor == 1){
                    korisnik.izvodTransakcije("Uplata",iznos);
                }
            }
        } else {
            System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            scanner.next();
        }
    }

    private static void zavrsiProgram(Scanner scanner) {
        System.out.println("\nUzmite Vasu karticu...");
        scanner.close();
        System.exit(0);
    }
}

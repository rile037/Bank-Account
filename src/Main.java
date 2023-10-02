import com.sun.jdi.connect.Connector; // Importovanje potrebnih paketa
import java.awt.font.GraphicAttribute;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Kreiranje objekta za unos
        Racun racun = new Racun("Nikola Rilak", 123456789, 250000); // Kreiranje računa
        Racun.Korisnik korisnik = racun.new Korisnik("14.08.1999"); // Kreiranje korisnika
        boolean ispravanPin = false;
        int brojacPogresnogPina = 1;

        // Kreiranje anonimne klase za transakciju
        Transakcija transakcija = new Transakcija() {
            @Override
            public String getVlasnikRacuna() {
                return korisnik.getVlasnikRacuna();
            }
        };

        System.out.println("* [TEST] Vas PIN: " + racun.vratiPin());

        while (!ispravanPin) {
            System.out.println("Unesite pin: ");
            try {
                int unetiPin = scanner.nextInt();
                if (racun.proveriPin(unetiPin)) {
                    System.out.println("Dobrodosli, " + korisnik.getVlasnikRacuna());
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
            } catch (InputMismatchException e) {
                System.out.println("- Pogresan PIN, pokusajte ponovo\n");
                brojacPogresnogPina++;
                scanner.next();
            }
        }
        while (true) {
            prikaziMeni();
            if (scanner.hasNextInt()) {
                int izbor = scanner.nextInt();
                if (izbor >= 0 && izbor <= 3) {
                    switch (izbor) {
                        case 1 -> podigniNovac(scanner, korisnik, racun, transakcija);
                        case 2 -> uplatiNovac(scanner, korisnik, transakcija);
                        case 3 -> prikaziStanje(korisnik);
                        case 0 -> {
                            zavrsiProgram(scanner);
                            return;
                        }
                        default -> System.out.println("\nNepoznat izbor, pokusajte ponovo.\n");
                    }
                } else {
                    System.out.println("\nNepoznat izbor, pokusajte ponovo\n");
                }
            } else {
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

    private static void podigniNovac(Scanner scanner, Racun.Korisnik korisnik, Racun racun, Transakcija transakcija) {
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
                if (izbor == 1) {
                    transakcija.izvodTransakcije("Isplata", iznos, korisnik.getBrojRacuna());
                }
            }
        } else {
            System.out.println("\nNevalidan iznos, pokusajte ponovo.\n");
            scanner.next();
        }
    }

    private static void uplatiNovac(Scanner scanner, Racun.Korisnik korisnik, Transakcija transakcija) {
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
                if (izbor == 1) {
                    transakcija.izvodTransakcije("Uplata", iznos, korisnik.getBrojRacuna());
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

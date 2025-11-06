
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<EProizvod> lista = new ArrayList<>();

    public static void main(String[] args) {

        lista.add(new Racunari("Laptop Lenovo", "RA001", 800, "i5", 8));
        lista.add(new Racunari("Desktop HP", "RA002", 1200, "i7", 16));
        lista.add(new Telefon("Samsung Galaxy", "TE001", 600, "Android", 6.7));
        lista.add(new Telefon("iPhone 13", "TE002", 900, "iOS", 6.1));
        lista.add(new TV("LG Smart TV", "TV001", 1500, 75));

        int izbor;
        do {
            System.out.println("\n===== MENI =====");
            System.out.println("1. Unos uredjaja");
            System.out.println("2. Pregled svih uredjaja sa maloprodajnom cijenom");
            System.out.println("3. Pregled uredjaja po tipu (RA/TE/TV)");
            System.out.println("0. Izlaz");
            System.out.print("Izbor: ");
            izbor = input.nextInt();
            input.nextLine(); 

            switch (izbor) {
                case 1: unos(); break;
                case 2: prikaziSve(); break;
                case 3: prikaziPoTipu(); break;
                case 0: System.out.println("Izlaz iz programa."); break;
                default: System.out.println("Nepoznata opcija!");
            }
        } while (izbor != 0);
    }

    static void unos() {
        System.out.print("Unesite opis: ");
        String opis = input.nextLine();
        System.out.print("Unesite sifru (RA/TE/TV...): ");
        String sifra = input.nextLine().toUpperCase();
        System.out.print("Unesite uvoznu cijenu: ");
        double cijena = input.nextDouble();
        input.nextLine();

        String tip = sifra.substring(0, 2);

        switch (tip) {
            case "RA":
                System.out.print("Procesor: ");
                String cpu = input.nextLine();
                System.out.print("Memorija (GB): ");
                int ram = input.nextInt();
                input.nextLine();
                lista.add(new Racunari(opis, sifra, cijena, cpu, ram));
                break;
            case "TE":
                System.out.print("Operativni sistem: ");
                String os = input.nextLine();
                System.out.print("Velicina ekrana (inch): ");
                double ekran = input.nextDouble();
                input.nextLine();
                lista.add(new Telefon(opis, sifra, cijena, os, ekran));
                break;
            case "TV":
                System.out.print("Velicina ekrana (inch): ");
                int tvekran = input.nextInt();
                input.nextLine();
                lista.add(new TV(opis, sifra, cijena, tvekran));
                break;
            default:
                System.out.println("Nepoznat tip sifre!");
        }
    }

    static void prikaziSve() {
        System.out.println("\n=== SVI UREDJAJI ===");
        for (EProizvod p : lista) {
            System.out.println(p + " | MP cijena: " + String.format("%.2f", p.izracunajMaloprodajnuCijenu()));
        }
    }

    static void prikaziPoTipu() {
        System.out.print("Unesite tip (RA/TE/TV): ");
        String t = input.nextLine().toUpperCase();
        System.out.println("\n=== UREDJAJI TIPA " + t + " ===");
        for (EProizvod p : lista) {
            if (p.getTip().equals(t)) {
                System.out.println(p + " | MP cijena: " + String.format("%.2f", p.izracunajMaloprodajnuCijenu()));
            }
        }
    }
}

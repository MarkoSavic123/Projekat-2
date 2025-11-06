
public class Telefon extends EProizvod {
    private String operativniSistem;
    private double velicinaEkrana;

    public Telefon(String opis, String sifra, double uvoznaCijena, String operativniSistem, double velicinaEkrana) {
        super(opis, sifra, uvoznaCijena);
        this.operativniSistem = operativniSistem;
        this.velicinaEkrana = velicinaEkrana;
    }

    public double izracunajMaloprodajnuCijenu() {
        double cijena = super.izracunajMaloprodajnuCijenu();
        if (velicinaEkrana > 6) {
            cijena *= 1.03;
        }
        return cijena;
    }

    public String toString() {
        return super.toString() + " | OS: " + operativniSistem + " | Ekran: " + velicinaEkrana + "\"";
    }
}

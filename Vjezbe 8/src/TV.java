
public class TV extends EProizvod {
    private int velicinaEkrana;

    public TV(String opis, String sifra, double uvoznaCijena, int velicinaEkrana) {
        super(opis, sifra, uvoznaCijena);
        this.velicinaEkrana = velicinaEkrana;
    }

    public double izracunajMaloprodajnuCijenu() {
        double cijena = super.izracunajMaloprodajnuCijenu();
        if (velicinaEkrana > 65) {
            cijena *= 1.10;
        }
        return cijena;
    }

    public String toString() {
        return super.toString() + " | Ekran: " + velicinaEkrana + "\"";
    }
}

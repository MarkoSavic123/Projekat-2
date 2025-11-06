
public class Racunari extends EProizvod {
    private String procesor;
    private int memorija;

    public Racunari(String opis, String sifra, double uvoznaCijena, String procesor, int memorija) {
        super(opis, sifra, uvoznaCijena);
        this.procesor = procesor;
        this.memorija = memorija;
    }

    public double izracunajMaloprodajnuCijenu() {
        double cijena = super.izracunajMaloprodajnuCijenu();
        return cijena * 1.05;
    }

    public String toString() {
        return super.toString() + " | Procesor: " + procesor + " | RAM: " + memorija + "GB";
    }
}

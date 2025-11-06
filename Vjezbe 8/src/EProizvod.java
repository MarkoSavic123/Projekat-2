
public class EProizvod {
	protected String opis;
    protected String sifra;
    protected double uvoznaCijena;

    public EProizvod(String opis, String sifra, double uvoznaCijena) {
        this.opis = opis;
        this.sifra = sifra;
        this.uvoznaCijena = uvoznaCijena;
    }

    public double izracunajMaloprodajnuCijenu() {
        return uvoznaCijena * 1.05; // osnovno povećanje 5%
    }

    public String getTip() {
        return sifra.substring(0, 2).toUpperCase();
    }

    @Override
    public String toString() {
        return "Opis: " + opis + " | Šifra: " + sifra + " | Uvozna cijena: " + uvoznaCijena;
    }
}

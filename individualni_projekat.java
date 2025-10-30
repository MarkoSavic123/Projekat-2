import java.util.ArrayList;

public class individualni_projekat {
	class Vozilo {
	    private String proizvodjac;
	    private int godinaProizvodnje;
	    private int kubikaza;
	    private String boja;
	    protected double osnovnaCijena = 100.0;
	 
	    public Vozilo(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja) {
	        this.proizvodjac = proizvodjac;
	        this.godinaProizvodnje = godinaProizvodnje;
	        this.kubikaza = kubikaza;
	        this.boja = boja;
	    }
	    public String getProizvodjac() { return proizvodjac; }
	    public int getGodinaProizvodnje() { return godinaProizvodnje; }
	    public int getKubikaza() { return kubikaza; }
	    public String getBoja() { return boja; }
	    
	    public double izracunajCijenuRegistracije() {
	        double cijena = osnovnaCijena;
	        if (godinaProizvodnje < 2010)
	            cijena += 30;
	        if (kubikaza > 2000)
	            cijena += 50;

	        return cijena;
	    }
	    public String osnovneInfo() {
	        return "Proizvođač: " + proizvodjac +
	               " | Godina: " + godinaProizvodnje +
	               " | Kubikaža: " + kubikaza +
	               " | Boja: " + boja;
	    }
	    public String toString() {
	        return osnovneInfo();
	    }
	}
	    class Automobil extends Vozilo {
	        private int brojVrata;
	        private String tipMotora;
	        public Automobil(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
                    int brojVrata, String tipMotora) {
	        	super(proizvodjac, godinaProizvodnje, kubikaza, boja);
	        	this.brojVrata = brojVrata;
	        	this.tipMotora = tipMotora;
	        }
	        public double izracunajCijenuRegistracije() {
	            double cijena = super.izracunajCijenuRegistracije();

	            if (tipMotora.equalsIgnoreCase("dizel"))
	                cijena += 20;

	            return cijena;
	        
	    	}
	        public String toString() {
	            return "[Automobil] " + osnovneInfo() +
	                   " | Broj vrata: " + brojVrata +
	                   " | Tip motora: " + tipMotora +
	                   " | Cijena registracije: " + izracunajCijenuRegistracije() + " EUR";
	        }
	    }
	    class Kamion extends Vozilo {
	        private int kapacitetTereta;
	        private boolean imaPrikolicu;

	        public Kamion(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
	                      int kapacitetTereta, boolean imaPrikolicu) {
	            super(proizvodjac, godinaProizvodnje, kubikaza, boja);
	            this.kapacitetTereta = kapacitetTereta;
	            this.imaPrikolicu = imaPrikolicu;
	        }
	        public double izracunajCijenuRegistracije() {
	            double cijena = super.izracunajCijenuRegistracije();

	            if (imaPrikolicu)
	                cijena += 50;

	            return cijena;
	        }
	        public String toString() {
	            return "[Kamion] " + osnovneInfo() +
	                   " | Kapacitet tereta: " + kapacitetTereta +
	                   " | Prikolica: " + (imaPrikolicu ? "da" : "ne") +
	                   " | Cijena registracije: " + izracunajCijenuRegistracije() + " EUR";
	        }
	    }
	    class Kombi extends Vozilo{
	    	private int brojPutnika;

	        public Kombi(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
	                     int brojPutnika) {
	            super(proizvodjac, godinaProizvodnje, kubikaza, boja);
	            this.brojPutnika = brojPutnika;
	        }
	        public double izracunajCijenuRegistracije() {
	            double cijena = super.izracunajCijenuRegistracije();

	            if (brojPutnika > 8)
	                cijena += 30;

	            return cijena;
	        }
	        public String toString() {
	            return "[Kombi] " + osnovneInfo() +
	                   " | Broj putnika: " + brojPutnika +
	                   " | Cijena registracije: " + izracunajCijenuRegistracije() + " EUR";
	        }
	    }

	    public class Main {
	        public static void main(String[] args) {
	            ArrayList<Vozilo> vozila = new ArrayList<>();

	            vozila.add(new Automobil("Audi", 2015, 1998, "Crna", 4, "dizel"));
	            vozila.add(new Kamion("MAN", 2008, 5000, "Siva", 10000, true));
	            vozila.add(new Kombi("Volkswagen", 2012, 2200, "Bijela", 9));

	            for (Vozilo v : vozila) {
	                System.out.println(v.toString());
	            }
	        }
	    }
}
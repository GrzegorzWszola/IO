package Model;

import java.util.List;

/**
 * klasa odpowiedzialna za obsluge miedzy pamiecia a kontrolerem
 */
public class Inwentarz {
	private IDAO dao;
	private List<Linia> linie = null;
	private List<Przystanek> przystanki = null;
	private List<Pojazd> pojazdy = null;
	private List<Kierowca> kierowcy = null;
	private List<IRozklad> rozklady = null;

	public Inwentarz(IDAO dao) {
		this.dao = dao;
		this.linie = this.dao.dajWszytskieLinie();
		this.rozklady = this.dao.dajWszystkieRozklady();
		// Pomijam reszte list, ktore nie sa potrzebne dla tego przypadku uzycia
	}

	/**
	 * funkcja odpowiedzialna za zwrocenie wszytskich linii z pamieci
	 * @return lista linii z pamieci
	 */
	public List<Linia> dajWszystkieLinie() {
		System.out.println("\nModel.Inwentarz()"); 
        System.out.println("✓ dajWszystkieLinie():");
		System.out.println("--- Znalezione linie");
		for (Linia linia : this.linie) {
			String[] parametry = linia.opisz().split(";");
			System.out.println("--- id: " + parametry[0] + " nazwa: " + parametry[1] + " idTras: " + parametry[2].replace("-", ", "));
		}
		return this.linie;
	}

	public List<Przystanek> getPrzystanki() {
		throw new UnsupportedOperationException();
	}
	public List<Pojazd> getPojazdy() {
		throw new UnsupportedOperationException();
	}
	public List<Kierowca> getKierowcy() {
		throw new UnsupportedOperationException();
	}
	public List<IRozklad> getRozklady() {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja odpowiedzialna za dodawanie rozkladow do pamieci
	 * @param rozklad dane do rozkladu w podstaci stringa
	 */
	public void dodajRozklad(String rozklad) {
		System.out.println("\nModel.Inwentarz()"); 
        System.out.println("✓ dodajRozklad():");
		String[] parametry = rozklad.split(";");
		System.out.print("--- przeslane parametry rozkladu: Id: " + parametry[0] + ", Typ: " + parametry[1] + ", dataOd: " + parametry[2] + ", czestotliwosc: " + parametry[3] + ", linie: " + parametry[4].replace("-", ", "));
		if (parametry[1].equals("2")) {
			System.out.println(" czasKoncaIStartu: " + parametry[5]);
		}
		if (parametry[1].equals("3")) {
			System.out.println(" typSwieta: " + parametry[5]);
		}
		IFabrykaRozkladu fabrykaRozkladu = new FabrykaRozkladu();
		IRozklad stworzonyRozklad = fabrykaRozkladu.utworzRozklad(rozklad);
		this.rozklady.add(stworzonyRozklad);
		this.dao.dodajRozklad(stworzonyRozklad);
	}
}
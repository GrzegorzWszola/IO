package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja fasady modelu
 */
public class Model implements IModel {
	private Inwentarz inwentarz;
	private IDAO dao;
	public IDAO unnamed_IDAO_;
	public IDAO _dao;

	public Model(Inwentarz inwentarz, IDAO dao) {
		this.inwentarz = inwentarz;
		this.dao = dao;
	}
	public String znajdzKierowce(int nrKierowcy) {
		throw new UnsupportedOperationException();
	}
	public String[] znajdzTrasyWLinii(String linia, String kierunekTrasy) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Funkcja sluzaca do zapisania rozkladu w pamieci
	 */
	public void zapiszRozklad(String rozklad) {
		System.out.println("\nModel.Model()"); 
        System.out.println("✓ zapiszRozklad():");
		String[] parametry = rozklad.split(";");
		System.out.print("--- przeslane parametry rozkladu: Id: " + parametry[0] + ", Typ: " + parametry[1] + ", dataOd: " + parametry[2] + ", czestotliwosc: " + parametry[3] + ", linie: " + parametry[4].replace("-", ", "));
		try {
			if (parametry[1].equals("2")) {
				System.out.println(" czasKoncaIStartu: " + parametry[5]);
			}
			if (parametry[1].equals("3")) {
				System.out.println(" typSwieta: " + parametry[5]);
			}
		} catch (Exception e) {
			System.out.println("Brak parametru dla danego typu");
			return;
		}

		this.inwentarz.dodajRozklad(rozklad);
	}

	public void aktualizujStatusPrzystanku(int idPrzystanku, int status) {
		throw new UnsupportedOperationException();
	}
	public String znajdzPojazd(int nrPojazdu) {
		throw new UnsupportedOperationException();
	}
	public void zapiszPrzyjazd(Przystanek danePrzystanku) {
		throw new UnsupportedOperationException();
	}
	public String znajdzLinie(int nrLinii) {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja odpowiedzialna za zwrocenie wszytskich linii w pamieci
	 * @param opisyWLinii zwrocona lista opisow linii
	 */
	public List<String> znajdzWszystkieLinie() {
		List<String> opisyLinii = new ArrayList<>();
		List<Linia> linie = this.inwentarz.dajWszystkieLinie();
		for(Linia linia : linie){
			opisyLinii.add(linia.opisz());
		}

		return opisyLinii;
	}

	public String[] sprawdzenieStanuPojazdow(String[] listaPojazdow) {
		throw new UnsupportedOperationException();
	}
}
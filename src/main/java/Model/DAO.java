package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * klasa odpowiedzialna za polaczenie miedzy aplikacja a pamiecia zewnetrzna w tym przypadku plikach csv
 */
public class DAO implements IDAO {

	private String PLIK_LINIE = "data/linie.csv";
    private String PLIK_PRZYSTANKI = "data/przystanki.csv";
    private String PLIK_TRASY = "data/trasy.csv";
    private String PLIK_ROZKLADY = "data/rozklady.csv";

	public DAO(){}

	public DAO(String sciezka_linie, String sciezka_przystanki, String sciezka_trasy, String sciezka_rozklady){
		this.PLIK_LINIE = sciezka_linie;
		this.PLIK_PRZYSTANKI = sciezka_przystanki;
		this.PLIK_TRASY = sciezka_trasy;
		this.PLIK_ROZKLADY = sciezka_rozklady;
	}

	public Linia znajdzLinie(int nrLinii) {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja zwracajaca wszystkie linie zapisane 
	 */
	public List<Linia> dajWszystkieLinie() {
		List<String> rezultat = odczytajZPliku(PLIK_LINIE);
		List<Linia> wynik = new ArrayList<>();
		for (String linia : rezultat){
			String[] parametry = linia.split(";");
			String[] trasyString = parametry[2].trim().split("-");
			List<Integer> idTras = new ArrayList<>();
			for (String id : trasyString){
				idTras.add(Integer.parseInt(id));
			}

			wynik.add(new Linia(Integer.parseInt(parametry[0]), parametry[1], idTras));
		}
		
		return wynik;
	}

	/**
	 * funkcja zwracajaca wszystkie rozklady
	 */
	public List<IRozklad> dajWszystkieRozklady() {
		List<String> rezultat = odczytajZPliku(PLIK_ROZKLADY);
		List<IRozklad> wynik = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		for (String rozklad : rezultat) {
			String[] parametry = rozklad.split(";");
			Integer id = Integer.parseInt(parametry[0]);
			Integer typ = Integer.parseInt(parametry[1]);
			Integer czestotliwosc = Integer.parseInt(parametry[2]);
			LocalDate dataOd = LocalDate.parse(parametry[3].trim(), formatter);
			String[] idLinii = parametry[4].split("-");
			List<Integer> linie = new ArrayList<>();
			for (String linia : idLinii){
				linie.add(Integer.parseInt(linia));
			}

			IRozklad rozkladPodstawowy = new RozkladPodstawowy(id, typ, dataOd, czestotliwosc, linie);
			switch (typ) {
				case 1:
					wynik.add(rozkladPodstawowy);
					break;
				case 2:
					String[] podzialCzasow = parametry[5].split("-");
					Float czasStartu = Float.parseFloat(podzialCzasow[0]);
					Float czasKonca = Float.parseFloat(podzialCzasow[1]);
					wynik.add(new RozkladNocny(rozkladPodstawowy, czasStartu, czasKonca));
					break;
				case 3:
					wynik.add(new RozkladSwiateczny(rozkladPodstawowy, parametry[5]));
					break;
			}
		}
		
		return wynik;
	}

	public Pojazd znajdzPojazd(int nrPojazdu) {
		throw new UnsupportedOperationException();
	}
	public Kierowca znajdzKierowce(int nrKierowcy) {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja odpowiadajaca za dodawanie rozkladow do pliku csv
	 */
	public void dodajRozklad(IRozklad rozklad) {
		String csvLine = rozklad.opisz();
		zapiszDoPliku(PLIK_ROZKLADY, csvLine);
	}

	public void edytujPrzystanek(Przystanek przystanek) {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja odpowiadajaca za dodawanie linii do pliku csv
	 */
	public void dodajLinie(Linia linia){
		String csvLine = linia.opisz();
        zapiszDoPliku(PLIK_LINIE, csvLine);
	}

	/**
	 * funkcja odpowiadajaca za dodawanie przystankow do pliku csv
	 */
	public void dodajPrzystanek(Przystanek przystanek){
		String csvLine = przystanek.opisz();
        zapiszDoPliku(PLIK_PRZYSTANKI, csvLine);
	}

	/**
	 * funkcja odpowiadajaca za dodawanie tras do pliku csv
	 */
	public void dodajTrase(Trasa trasa){
		String csvLine = trasa.opisz();
        zapiszDoPliku(PLIK_TRASY, csvLine);
	}

	/**
	 * funkcja odpowiadajaca za pokazanie przystankow w bazie
	 */
	public void pokazBazePrzystankow() {
		System.out.println("\nModel.Dao()"); 
        System.out.println("✓ pokażBazęPrzystankow():");
		List<String> rezultat = odczytajZPliku(PLIK_PRZYSTANKI);
		for (String przystanek : rezultat){
			System.out.println(przystanek);
		}
	}
	
	/**
	 * funkcja odpowiadajaca za pokazanie tras w bazie
	 */
	public void pokazBazeTras() {
		System.out.println("\nModel.Dao()"); 
        System.out.println("✓ pokażBazęTras():");
		List<String> rezultat = odczytajZPliku(PLIK_TRASY);
		for (String Trasa : rezultat){
			String[] TrasaOpis = Trasa.split(";");
			String nrTrasy = TrasaOpis[0];
			String kierunek = TrasaOpis[1];
			String[] przystanki = TrasaOpis[2].split("-");
			String[] czasNaPrzystanek = TrasaOpis[3].split("-");
			String calkowityCzas = TrasaOpis[4];

			String wynik = "NrTrasy: " + nrTrasy + " Kierunek trasy: " + kierunek + "\nPrzystanki:\n";
			wynik += "nrPrzystanku: ";
			for(String przyst : przystanki){
				wynik += przyst + ", ";
			}
			wynik = wynik.substring(0, wynik.length() - 2);
			wynik += "\n";
			for(String czasNaPrzyst : czasNaPrzystanek){
				String[] podzial = czasNaPrzyst.split(":");
				wynik += "nrPrzystanku: " + podzial[0] + " czas na segment pomiedzy: " + podzial[1] + "\n";
			}
			wynik += "Calkowity czas przejazdu trasy: " + calkowityCzas;
			System.out.println(wynik);
		}
	}

	/**
	 * funkcja odpowiadajaca za linii przystankow w bazie
	 */
	public void pokazBazeLinii() {
		System.out.println("\nModel.Dao()"); 
        System.out.println("✓ pokażBazęLinii():");
		List<String> rezultat = odczytajZPliku(PLIK_LINIE);
		for (String linia : rezultat){
			String[] opisLinii = linia.split(";");

			String wynik = "nrLinii: " + opisLinii[0] + " nazwa: " + opisLinii[1] + "\n"; 
			wynik += "nrTras: ";
			String[] trasyWLinii = opisLinii[2].split("-");
			for(String trasa : trasyWLinii) {
				wynik += trasa + ", ";
			}
			wynik = wynik.substring(0, wynik.length() - 2);

			System.out.println(wynik);
		}
	}

	/**
	 * funkcja odpowiadajaca za pokazanie rozkladow w bazie
	 */
	public void pokazBazeRozkladow() {
		System.out.println("\nModel.Dao()"); 
        System.out.println("✓ pokazBazeRozkladow():");
		List<String> rezultat = odczytajZPliku(PLIK_ROZKLADY);
		for (String rozklad : rezultat){
			String[] opisRozkladow = rozklad.split(";");

			String wynik = "id: " + opisRozkladow[0] + " typ: " + opisRozkladow[1] + " czestotliwosc przejazdow: " + opisRozkladow[2] + " dataOd: " + opisRozkladow[3]; 
			
			if (opisRozkladow[1].equals("2")){
				wynik += " czasStartuIKonca: " + opisRozkladow[5];
			}

			if (opisRozkladow[1].equals("3")){
				wynik += " typSwieta: " + opisRozkladow[5];
			}

			wynik += "\nNumery linii: ";
			
			String[] linieWRozkladzie = opisRozkladow[4].split("-");
			for(String linia : linieWRozkladzie) {
				wynik += linia + ", ";
			}
			wynik = wynik.substring(0, wynik.length() - 2);

			System.out.println(wynik);
		}
	}

	/**
	 * funkcja odpowiadajaca za zapisywanie danych do pliku
	 */
	private void zapiszDoPliku(String sciezka, String dane) {
        try (
            FileWriter fw = new FileWriter(sciezka, true);
            PrintWriter pw = new PrintWriter(fw)
        ) {
            pw.println(dane);
        } catch (IOException e) {
            System.err.println("DAO BŁĄD ZAPISU DO PLIKU " + sciezka + ": " + e.getMessage());
        }
    }

	/**
	 * funkcja odpowiadajaca za odczytywanie danych z pliku
	 */
	private List<String> odczytajZPliku(String sciezka) {
        List<String> wiersze = new ArrayList<>();
        try (
            // FileReader + BufferedReader do efektywnego czytania linii
            FileReader fr = new FileReader(sciezka);
            BufferedReader br = new BufferedReader(fr)
        ) {
            String wiersz;
            // Pętla czytająca, dopóki jest co czytać
            while ((wiersz = br.readLine()) != null) {
                if (!wiersz.trim().isEmpty()) { // Ignoruj puste linie
                    wiersze.add(wiersz);
                }
            }
        } catch (IOException e) {
            System.err.println("DAO BŁĄD ODCZYTU Z PLIKU " + sciezka + ": " + e.getMessage());
        }
        return wiersze;
    }
}
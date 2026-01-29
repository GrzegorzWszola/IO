package Kontroler;

import java.util.ArrayList;
import java.util.List;

import Model.IModel;

/**
 * Klasa odpowiedzialna za dodawanie nowych rozkladow
 */
public class DodawanieNowychRozkladowJazdy {
	private IModel model;
	private List<String> aktualneLinieKomunikacyjne = null;
	private List<String> listaLiniiDlaRozkladu = null;
	private String rozklad;

	public DodawanieNowychRozkladowJazdy(IModel model) {
		System.out.println("--- Rozpoczęto realizację PU dodawanie nowych rozkładów.");
		this.model = model;
		this.aktualneLinieKomunikacyjne = model.znajdzWszystkieLinie(); 
	}

	public void dodajRozklad() {
		this.rozklad = wprowadzParametryRozkladu();
		this.listaLiniiDlaRozkladu = PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(aktualneLinieKomunikacyjne);
		if (this.listaLiniiDlaRozkladu.size() > 0){
			rozklad += ";";
			for(String linia : listaLiniiDlaRozkladu){
				rozklad += linia + "-";
			}
			rozklad = rozklad.substring(0, rozklad.length() - 1);
			if (rozklad.split(";")[1].equals("3") && !rozklad.split(";")[1].isEmpty()){
				rozklad += ";" + wprowadzTypSwieta();
			} else if (rozklad.split(";")[1].equals("2") && !rozklad.split(";")[1].isEmpty()){
				rozklad += ";" + wprowadzCzasStartuIKonca();
			}
		} else {
			// Przekaz error do uzytkownika
			PrzekazanieInformacjiUzytkownikowi.przekazanieInformacjiUzytkownikowi("Error: Lista linii jest pusta");
			return;
		}

		if (rozklad != null){
			this.model.zapiszRozklad(rozklad);
		}
	}

	/**
	 * Funkcja odpowiedzialna za wprowadzenie przez uzytkownika danych rozkladu
	 * @return dane do rozkladu
	 */
	public String wprowadzParametryRozkladu() {
		System.out.println("\nKontroler.DodawanieNowychRozkladowJazdy()"); 
        System.out.println("✓ wprowadzParametryRozkladu():");
		System.out.println("--- wprowadzone parametry:Id: 1, Typ: 3 (swiateczny), dataOd: 01.01.2026, czestotliwoscPrzejazdów: 5 (5 razy dziennie)");
		// Hardcodowane wprowadzenie danych dla testu

		return "1;3;01.01.2026;5";
	}

	/**
	 * Funkcja odpowiedzialna za wprowadzenie przez uzytkownika typu swieta dla rozkladu swiatecznego
	 * @return dane do rozkladu
	 */
	public String wprowadzTypSwieta() {
		System.out.println("\nKontroler.DodawanieNowychRozkladowJazdy()"); 
        System.out.println("✓ wprowadzTypSwieta():");
		String typSwieta = "Wigilia";
		System.out.println("--- wprowadzonyTypSwieta: " + typSwieta);
		return typSwieta;
	}

	/**
	 * Funkcja odpowiedzialna za wprowadzenie przez uzytkownika czasu startu i konca dla rozkladu nocnego
	 * @return dane do rozkladu
	 */
	public String wprowadzCzasStartuIKonca() {
		System.out.println("\nKontroler.DodawanieNowychRozkladowJazdy()"); 
        System.out.println("✓ wprowadzCzasStartuIKonca():");
		String czasStartuIKonca = "12.00-06.00";
		System.out.println("--- wprowadzonyTypSwieta: " + czasStartuIKonca);
		return czasStartuIKonca;
	}
}
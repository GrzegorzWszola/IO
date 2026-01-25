package Kontroler;

import Model.IModel;

/**
 * Fasada kontrolera dla Kierownika ruchu
 */
public class KontrolerKierownikaRuchu implements IKontrolerKierownikaRuchu{
	private IModel model;

	/**
	 * @param model fasada modelu
	 */
	public KontrolerKierownikaRuchu(IModel model){
		this.model = model;
	}

	public void planowanieObjazdów() {
		throw new UnsupportedOperationException();
	}

	public void wprowadzenieEwntyalnychZmianAwaryjnych() {
		throw new UnsupportedOperationException();
	}

	public void generowanieRaportówPunktualnościKursów() {
		throw new UnsupportedOperationException();
	}

	public void edycjaAktualnychPrzystanków() {
		throw new UnsupportedOperationException();
	}

	public void dodanieNowychKierowcówDoSystemu() {
		throw new UnsupportedOperationException();
	}

	/**
	 * funkcja do dodawania nowych rozkladow
	 */
	public void dodawanieNowychRozkladowJazdy() {
		System.out.println("\nKontroler.KontrolerKierownikaRuchu()"); 
        System.out.println("✓ dodawanieNowychRozkladowJazdy():");
		DodawanieNowychRozkladowJazdy pu08 = new DodawanieNowychRozkladowJazdy(this.model);
		pu08.dodajRozklad();
	}
}
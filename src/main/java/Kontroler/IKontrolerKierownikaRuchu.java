package Kontroler;

/**
 * Interfejs fasady Kontrolera dla Kierownika Ruchu.
 */
public interface IKontrolerKierownikaRuchu {

	public void planowanieObjazdów();

	public void wprowadzenieEwntyalnychZmianAwaryjnych();

	public void generowanieRaportówPunktualnościKursów();

	public void edycjaAktualnychPrzystanków();

	public void dodanieNowychKierowcówDoSystemu();
    /**
     * Realizacja przypadku uzycia PU08 dodawanie nowych rozkladow
     */
	public void dodawanieNowychRozkladowJazdy();
}
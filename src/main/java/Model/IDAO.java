package Model;

import java.util.List;

public interface IDAO {

	public Linia znajdzLinie(int nrLinii);

	public Pojazd znajdzPojazd(int nrPojazdu);

	public Kierowca znajdzKierowce(int nrKierowcy);

	public void dodajRozklad(IRozklad rozklad);

	public void edytujPrzystanek(Przystanek przystanek);

	public void dodajLinie(Linia linia);

	public List<Linia> dajWszystkieLinie();

	public List<IRozklad> dajWszystkieRozklady();

	public List<Pojazd> dajWszystkiePojazdy();

	public void dodajPrzystanek(Przystanek przystanek);

	public void dodajTrase(Trasa trasa);

	public void pokazBazePrzystankow();
	
	public void pokazBazeTras();
	
	public void pokazBazeLinii();

	public void pokazBazeRozkladow();

}
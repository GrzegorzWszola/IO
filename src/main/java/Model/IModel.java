package Model;

import java.util.List;

public interface IModel {

	public String znajdzKierowce(int nrKierowcy);

	public String[] znajdzTrasyWLinii(String linia, String kierunekTrasy);

	public void zapiszRozklad(String rozklad);

	public void aktualizujStatusPrzystanku(int idPrzystanku, int status);

	public String znajdzPojazd(int nrPojazdu);

	public void zapiszPrzyjazd(Przystanek danePrzystanku);

	public String znajdzLinie(int nrLinii);

	public List<String> znajdzWszystkieLinie();

	public String[] sprawdzenieStanuPojazdow(String[] listaPojazdow);
}
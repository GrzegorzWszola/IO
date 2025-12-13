package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RozkladPodstawowy implements IRozklad {
	private List<Integer> linie;
	private Integer id;
	private Integer typ;
	private LocalDate dataOd;
	private Integer czestotliwoscPrzejazdow;

	public RozkladPodstawowy(Integer id, Integer typ, LocalDate dataOd, Integer czestotliwoscPrzejazdu, List<Integer> linie) {
		this.id = id;
		this.typ = typ;
		this.dataOd = dataOd;
		this.czestotliwoscPrzejazdow = czestotliwoscPrzejazdu;
		this.linie = linie;
	}

	public int getTyp() {
		return this.typ;
	}

	public LocalDate getDataOd() {
		return this.dataOd;
	}

	public int getCzestotliwoscPrzejazdow() {
		return this.czestotliwoscPrzejazdow;
	}

	public String opisz() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		StringBuilder wynik = new StringBuilder();
		wynik.append(this.id).append(";");
		wynik.append(this.typ).append(";");
		wynik.append(this.czestotliwoscPrzejazdow).append(";");
		wynik.append(this.dataOd.format(formatter)).append(";");

		for (Integer liniaId : this.linie) {
			wynik.append(liniaId).append("-");
		}
		wynik.setLength(wynik.length() - 1); 

		return wynik.toString();
	}
}
package Model;

import java.util.List;

public class Linia {
	private Integer nrLinii;
	private String nazwa;
	private List<Integer> trasy;

	public Linia(Integer nrLinii, String nazwa, List<Integer> trasy) {
		this.nrLinii = nrLinii;
		this.nazwa = nazwa;
		this.trasy = trasy;
	}

	public Integer getNrLinii() {
			return nrLinii;
		}

    public String getNazwa() {
        return nazwa;
    }

    public List<Integer> getTrasy() {
        return trasy;
    }

	public String opisz() {
		String wynik = nrLinii + ";" + nazwa + ";";
		for (Integer trasa : trasy){
			wynik += trasa.toString() + "-";
		}
		wynik = wynik.substring(0, wynik.length() - 1);

		return wynik;
	}
}
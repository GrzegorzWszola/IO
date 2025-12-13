package Model;

public class Przystanek {
	private Integer nrPrzystanku;
	private String nazwa;
	private int typ;

	public Przystanek(Integer nrPrzystanku, String nazwa, int typ) {
		this.nrPrzystanku = nrPrzystanku;
		this.nazwa = nazwa;
		this.typ = typ;
	}

	public Integer getNrPrzystanku() { return this.nrPrzystanku; }

	public String opisz() {
		return nrPrzystanku + ";" + nazwa + ";" + typ;
	}
}
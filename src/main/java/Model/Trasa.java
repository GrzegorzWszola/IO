package Model;

import java.util.List;

public class Trasa {
	private Integer nrTrasy;
	private String kierunek;
	private List<Przystanek> przystanki;
	private List<SegmentTrasy> czasNaPrzystanek;
	private Float calkowityCzasPrzejazdu;

	public Trasa(Integer nrTrasy, String kierunek, List<Przystanek> przystanki, List<SegmentTrasy> czasNaPrzystanek, Float calkowityCzasPrzejazdu) {
		this.nrTrasy = nrTrasy;
		this.kierunek = kierunek;
		this.przystanki = przystanki;
		this.czasNaPrzystanek = czasNaPrzystanek;
		this.calkowityCzasPrzejazdu = calkowityCzasPrzejazdu;
	}

	public String opisz() {
		String rezultat = this.nrTrasy + ";" + this.kierunek + ";";
		for (Przystanek przystanek : przystanki){
			rezultat += przystanek.getNrPrzystanku().toString() + "-";
		}
		rezultat = rezultat.substring(0, rezultat.length() - 1);
		rezultat += ";";
		for(SegmentTrasy segmTrasy : czasNaPrzystanek){
			rezultat += segmTrasy.getNumerPrzystanku().toString() + ":" + segmTrasy.getCzasDlaPrzystanku().toString() + "-";
			this.calkowityCzasPrzejazdu += segmTrasy.getCzasDlaPrzystanku();
		}
		rezultat = rezultat.substring(0, rezultat.length() - 1);
		rezultat += ";";
		rezultat += calkowityCzasPrzejazdu.toString();

		return rezultat;
	}

	public Integer getNrTrasy() {
		return nrTrasy;
	}
}

class SegmentTrasy {
	private Integer numerPrzystanku;
	private Float czasDlaPrzystanku;

	public SegmentTrasy(Integer numerPrzystanku, Float czasDlaPrzystanku){
		this.numerPrzystanku = numerPrzystanku;
		this.czasDlaPrzystanku = czasDlaPrzystanku;
	}

	public Integer getNumerPrzystanku() {
		return numerPrzystanku;
	}

	public Float getCzasDlaPrzystanku() {
		return czasDlaPrzystanku;
	}
}
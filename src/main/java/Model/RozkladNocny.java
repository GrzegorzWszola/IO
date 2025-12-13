package Model;

import java.time.LocalDate;

public class RozkladNocny extends DekoratorRozkladu {
	private float czasStartu;
	private float czasKonca;

	public RozkladNocny(IRozklad rozklad, float czasStartu, float czasKonca) {
		super(rozklad);
	}

	public String opisz() {
		throw new UnsupportedOperationException();
	}

	public Linia[] getLinie() {
		throw new UnsupportedOperationException();
	}

	public int getTyp() {
		throw new UnsupportedOperationException();
	}

	public LocalDate getDataOd() {
		throw new UnsupportedOperationException();
	}

	public int getCzestotliwoscPrzejazdow() {
		throw new UnsupportedOperationException();
	}
}
package Model;

import java.time.LocalDate;

public abstract class DekoratorRozkladu implements IRozklad {
	protected IRozklad rozklad;

	public DekoratorRozkladu(IRozklad rozklad) {
		this.rozklad = rozklad;
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

	public abstract String opisz();
}
package Model;

import java.time.LocalDate;
import java.util.List;

public abstract class DekoratorRozkladu implements IRozklad {
	protected IRozklad rozklad;

	public DekoratorRozkladu(IRozklad rozklad) {
		this.rozklad = rozklad;
	}
	public int getId() {
		return this.rozklad.getId();
	}
	public List<Integer> getLinie() {
		return this.rozklad.getLinie();
	}
	public int getTyp() {
		return this.rozklad.getTyp();
	}
	public LocalDate getDataOd() {
		return this.rozklad.getDataOd();
	}
	public int getCzestotliwoscPrzejazdow() {
		return this.rozklad.getCzestotliwoscPrzejazdow();
	}

	public abstract String opisz();
}
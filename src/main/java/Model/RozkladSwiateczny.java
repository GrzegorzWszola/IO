package Model;

import java.time.LocalDate;

public class RozkladSwiateczny extends DekoratorRozkladu {
	private String typSwieta;

	public RozkladSwiateczny(IRozklad rozklad, String typSwieta) {
		super(rozklad);
		this.typSwieta = typSwieta;
	}

	@Override
    public String opisz() {
        return rozklad.opisz() + ";" + typSwieta;
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
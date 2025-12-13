package Model;

import java.time.LocalDate;

public interface IRozklad {

	public int getTyp();

	public LocalDate getDataOd();

	public int getCzestotliwoscPrzejazdow();

	public String opisz();
}
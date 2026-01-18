package Model;

import java.time.LocalDate;
import java.util.List;

public interface IRozklad {

	public int getId();

	public List<Integer> getLinie();

	public int getTyp();

	public LocalDate getDataOd();

	public int getCzestotliwoscPrzejazdow();

	public String opisz();

	default String getTypSwieta(){
		return "";
	};

	default public float getCzasStartu() {
		return 0;
	}

	default public float getCzasKonca() {
		return 0;
	}
}
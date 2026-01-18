package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiadajaca za dodawanie odpowiedniego rozkladu
 */
public class FabrykaRozkladu implements IFabrykaRozkladu {
	public IRozklad utworzRozklad(String dane) {
		System.out.println("\nModel.FabrykaRozkladu()"); 
        System.out.println("✓ dodajRozklad():");
		List<Integer> nrLinii = new ArrayList<>();
		IRozklad rozkladPodstawowy = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		String[] parametry = dane.split(";");
		Integer id = Integer.parseInt(parametry[0]);
		Integer typ = Integer.parseInt(parametry[1]);
		Integer czestotliwoscPrzejazdow = Integer.parseInt(parametry[3]);
		LocalDate dataOd = LocalDate.parse(parametry[2].trim(), formatter);
		String[] nrLiniiString = parametry[4].split("-");
		for (String linia : nrLiniiString){
			nrLinii.add(Integer.parseInt(linia));
		}

		rozkladPodstawowy = new RozkladPodstawowy(id, typ, dataOd, czestotliwoscPrzejazdow, nrLinii);
		switch (typ) {
			case 1: // ROZKŁAD PODSTAWOWY		
				System.out.println("\nModel.FabrykaRozkladu()"); 
				System.out.println("✓ dodajRozklad():");
				System.out.println("--- utworzono rozkład podstawowy:");
				return rozkladPodstawowy;
				
			case 2: // ROZKŁAD NOCNY
				if (parametry.length < 6) throw new IllegalArgumentException("Brak czasu startu/końca dla Rozkładu Nocnego.");
				String czasStartuIKonca = parametry[5].trim();
				
				IFabrykaRozkladu fabrykaRozkladuNocenego = new FabrykaRozkladuNocnego(rozkladPodstawowy);
				return fabrykaRozkladuNocenego.utworzRozklad(czasStartuIKonca);
				
			case 3: // ROZKŁAD ŚWIĄTECZNY
				if (parametry.length < 6) throw new IllegalArgumentException("Brak typu święta dla Rozkładu Świątecznego.");
				String typSwieta = parametry[5].trim();
				
				IFabrykaRozkladu fabrykaRozkladuSwiatecznego = new FabrykaRozkladuSwiatecznego(rozkladPodstawowy);
				return fabrykaRozkladuSwiatecznego.utworzRozklad(typSwieta);
				
			default:
				System.err.println("Nieznany typ rozkładu: " + typ);
				return null;
		}
	}
}

package Kontroler;

import java.util.List;

public class PrzekazanieInformacjiUzytkownikowi {

	public static void przekazanieInformacjiUzytkownikowi(String informacja) {
		System.out.println(informacja);
	}

	public static void wyswietlAktualneLinie(List<String> aktualneLinieKomunikacyjne) {
		System.out.println("\nKontroler.PrzekazanieInformacjiUzytkownikowi()"); 
        System.out.println("✓ wyswietlAktualneLinie():");
		System.out.println("\n=============================================");
		System.out.println(" AKTUALNE LINIE KOMUNIKACYJNE");
		System.out.println("=============================================");
		
		if (aktualneLinieKomunikacyjne == null || aktualneLinieKomunikacyjne.isEmpty()) {
			System.out.println("Brak dostępnych linii do wyświetlenia.");
			return;
		}

		// Nagłówek dla czytelności
		System.out.printf("%-8s | %-10s | %s\n", "NR LINII", "NAZWA", "ID TRAS");
		System.out.println("--------------------------------------------");

		for (String linia : aktualneLinieKomunikacyjne) {
			String[] parametry = linia.split(";");
					
			// Używamy printf do formatowania kolumn i wyrównywania tekstu
			System.out.printf("%-8s | %-10s | %s\n", 
				parametry[0], 
				parametry[1], 
				parametry[2].replace("-", ", "));
		}
		System.out.println("=============================================");
	}
}
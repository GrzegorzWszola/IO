package Model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("PU08")
public class FabrykaRozkladuTest {
    private IFabrykaRozkladu fabrykaRozkladu;

    @BeforeEach
    void setUp() {
        fabrykaRozkladu = new FabrykaRozkladu();
    }

    @Test
    @DisplayName("Sukces dodawanie rozkładu podstawowego")
    void dodajRozkladPodstawowy() {
        // Jeśli
        String dane = "1;1;01.01.2026;5;1-2";
        List<Integer> przewidywaneLinie = List.of(1, 2);
        LocalDate data = LocalDate.of(2026, 1, 1);
        // Gdy
        IRozklad stworzonyRozklad = fabrykaRozkladu.utworzRozklad(dane);
        // Wtedy
        assertEquals(1, stworzonyRozklad.getId());
        assertEquals(1, stworzonyRozklad.getTyp());
        assertEquals(data, stworzonyRozklad.getDataOd());
        assertEquals(5, stworzonyRozklad.getCzestotliwoscPrzejazdow());
        assertEquals(przewidywaneLinie, stworzonyRozklad.getLinie());
    }

    @Test
    @Tag("RozkladSwiateczny")
    @DisplayName("Sukces dodawanie rozkładu świątecznego")
    void dodajRozkladSwiateczny() {
        // Jeśli
        String dane = "1;3;01.01.2026;5;1-2;Wigilia";
        List<Integer> przewidywaneLinie = List.of(1, 2);
        LocalDate data = LocalDate.of(2026, 1, 1);
        // Gdy
        IRozklad stworzonyRozklad = fabrykaRozkladu.utworzRozklad(dane);
        // Wtedy
        assertEquals(1, stworzonyRozklad.getId());
        assertEquals(3, stworzonyRozklad.getTyp());
        assertEquals(data, stworzonyRozklad.getDataOd());
        assertEquals(5, stworzonyRozklad.getCzestotliwoscPrzejazdow());
        assertEquals(przewidywaneLinie, stworzonyRozklad.getLinie());
        assertEquals("Wigilia", stworzonyRozklad.getTypSwieta());
    }

    @Test
    @DisplayName("Sukces dodawanie rozkładu nocnego")
    void dodajRozkladNocny() {
        // Jeśli
        String dane = "1;2;01.01.2026;5;1-2;1.0-2.0";
        List<Integer> przewidywaneLinie = List.of(1, 2);
        LocalDate data = LocalDate.of(2026, 1, 1);
        // Gdy
        IRozklad stworzonyRozklad = fabrykaRozkladu.utworzRozklad(dane);
        // Wtedy
        assertEquals(1, stworzonyRozklad.getId());
        assertEquals(2, stworzonyRozklad.getTyp());
        assertEquals(data, stworzonyRozklad.getDataOd());
        assertEquals(5, stworzonyRozklad.getCzestotliwoscPrzejazdow());
        assertEquals(przewidywaneLinie, stworzonyRozklad.getLinie());
        assertEquals(1.0, stworzonyRozklad.getCzasStartu());
        assertEquals(2.0, stworzonyRozklad.getCzasKonca());
    }

    @Test
    @DisplayName("Nieznany typ rozkładu zwraca null")
    void nieznanyTypRozkladu() {
        // Jeśli
        String dane = "1;4;01.01.2026;5;1-2;1.0-2.0";
        // Gdy
        IRozklad stworzonyRozklad = fabrykaRozkladu.utworzRozklad(dane);
        // Wtedy
        assertEquals(null, stworzonyRozklad);
    }

    @Test
    @DisplayName("Rozkład nocny bez potrzebnych informacji")
    void brakParametrówDoTypuNocnego() {
        // Jeśli
        String dane = "1;2;01.01.2026;5;1-2";
        // Gdy
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fabrykaRozkladu.utworzRozklad(dane);
        });
        // Wtedy
        assertEquals("Brak czasu startu/końca dla Rozkładu Nocnego.", exception.getMessage());
    }

    @Test
    @Tag("RozkladSwiateczny")
    @DisplayName("Ddodawanie rozkładu światęcznego, gdzie w danych nie świeto jest niepoprawne")
    void tworzenieRozkladuSwiatecznegoBezSwieta() {
        // Jeśli
        String dane = "1;3;01.01.2026;5;1-2;1Listopada";
        // Gdy
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fabrykaRozkladu.utworzRozklad(dane);
        });
        // Wtedy
        assertEquals("Nazwa święta nie mogą zawierać cyfr", exception.getMessage());
    }

    @Test
    @DisplayName("Tworzenie rozkładu, gdzie dane zostały \"zgubione\" po drodze")
    void tworzenieRozkladuBezDanych() {
        // Jeśli
        String dane = "";
        // Gdy i Wtedy
        Assertions.assertThrows(NumberFormatException.class, () -> {
            fabrykaRozkladu.utworzRozklad(dane);
        });
    }

    @Test
    @DisplayName("Niepoprawne dane")
    void tworzenieRozkladuZNiepoprawnymiDanymi() {
        // Jeśli
        String dane = "abc;2;01.01.2026;5;1-2";
        // Gdy i Wtedy
        Assertions.assertThrows(Exception.class, () -> {
            fabrykaRozkladu.utworzRozklad(dane);
        });
    }

    @Test
    @DisplayName("Niepoprawna data")
    void tworzenieRozkladuZNiepoprawnaData() {
        // Jeśli
        String dane = "1;2;32.12.2026;5;1-2";
        // Gdy i Wtedy
        Assertions.assertThrows(Exception.class, () -> {
            fabrykaRozkladu.utworzRozklad(dane);
        });
    }
}

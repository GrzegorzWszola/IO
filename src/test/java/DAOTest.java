import Model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("PU08")
class DAOTest {
    // Tymczasowy folder
    @TempDir
    Path tempDir; 

    private DAO dao;
    private File fileLinie;
    private File fileRozklady;

    @BeforeEach
    void setUp() throws IOException {
        // Tworzenie ścieżek do plików tymczasowych
        fileLinie = tempDir.resolve("linie.csv").toFile();
        fileRozklady = tempDir.resolve("rozklady.csv").toFile();
        
        // Inicjalizacja DAO z tymczasowymi ścieżkami
        dao = new DAO(
            fileLinie.getAbsolutePath(), 
            "temp_przystanki.csv", 
            "temp_trasy.csv", 
            fileRozklady.getAbsolutePath()
        );
    }

    @Test
    @DisplayName("Zwrócenie wszystkich linii z poprawnymi danymi")
    void testDajWszystkieLinie_PoprawneDane() throws IOException {
        // Jeśli
        String content = "1;100;10-20-30\n2;200;40-50";
        Files.writeString(fileLinie.toPath(), content);
        // Gdy
        List<Linia> wynik = dao.dajWszystkieLinie();
        // Wtedy
        assertEquals(2, wynik.size());
        assertEquals("100", wynik.get(0).getNazwa());
        assertEquals(3, wynik.get(0).getTrasy().size());
        assertTrue(wynik.get(0).getTrasy().contains(20));
    }

    @Test
    @DisplayName("Dodanie wszystkich typów rozkładów")
    void testDajWszystkieRozklady_RozneTypy() throws IOException {
        // Jeśli
        String content = 
            "1;1;15;01.01.2024;1-2\n" + 
            "2;2;30;01.01.2024;3-4;23.0-4.5\n" +
            "3;3;60;01.01.2024;5;Wielkanoc";
        Files.writeString(fileRozklady.toPath(), content);
        // Gdy
        List<IRozklad> rozklady = dao.dajWszystkieRozklady();
        // Wtedy
        assertEquals(3, rozklady.size());
        assertTrue(rozklady.get(0) instanceof RozkladPodstawowy);
        assertTrue(rozklady.get(1) instanceof RozkladNocny);
        assertTrue(rozklady.get(2) instanceof RozkladSwiateczny);
    }

    @Test
    @DisplayName("Dodanie linii z zapisem do pliku")
    void testDodajLinie_ZapisDoPliku() throws IOException {
        // Jeśli
        Linia nowaLinia = new Linia(5, "Testowa", List.of(1, 2));
        // Gdy
        dao.dodajLinie(nowaLinia);
        // Wtedu
        List<String> wiersze = Files.readAllLines(fileLinie.toPath());
        assertEquals(1, wiersze.size());
        assertTrue(wiersze.get(0).contains("Testowa"));
    }

    @Test
    @DisplayName("Odczyt z nieistniejącego pliku")
    void testOdczytZNieistniejacegoPliku() {
        // Jeśli
        DAO daoBad = new DAO("nie_ma_mnie.csv", "", "", "");
        // Gdy
        List<Linia> wynik = daoBad.dajWszystkieLinie();
        // Wtedy
        assertNotNull(wynik);
        assertTrue(wynik.isEmpty());
    }
}
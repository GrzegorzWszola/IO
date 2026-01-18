import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Model.IDAO;
import Model.IRozklad;
import Model.Inwentarz;
import Model.RozkladNocny;
import Model.RozkladSwiateczny;

@Tag("PU08")
public class InwentarzTest {
    private IDAO daoMock;
    private Inwentarz inwentarz;

    @BeforeEach
    void setUp() {
        // Tworzymy mock(atrape) DAO
        daoMock = mock(IDAO.class);
        // Ustawienie co maja robic metody atrapy
        when(daoMock.dajWszystkieRozklady()).thenReturn(new ArrayList<>());
        when(daoMock.dajWszystkieLinie()).thenReturn(new ArrayList<>());
        // Tworzymy inwentarz z atrapą dao
        inwentarz = new Inwentarz(daoMock);
    }

    @Test
    @DisplayName("Załadowanie danych z dao przy starcie")
    void załadujDaneZDao() {
        // Sprawdza czy mock DAO został wywołany chociaż raz
        verify(daoMock, atLeastOnce()).dajWszystkieRozklady();
        verify(daoMock, atLeastOnce()).dajWszystkieLinie();
    }

    @Test
    @DisplayName("Dodanie nowego rozkładu podstawowego")
    void tworzenieRozkladuPodstawowego() {
        // Jeśli
        String dane = "1;1;01.01.2026;5;1-2";
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy 
        // Sprawdzamy czy metoda dodajRozklad() została wywołana i stworzyła 
        // obiekt klasy IRozklad
        verify(daoMock, atLeastOnce()).dodajRozklad(any(IRozklad.class));
    }

    @Test
    @DisplayName("Dodanie nowego rozkładu świątecznego")
    void tworzenieRozkladuSwiateczne() {
        // Jeśli
        String dane = "1;3;01.01.2026;5;1-2;Wigilia";
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy
        // Sprawdzamy czy metoda została wywołana i tworzy obiekt klasy
        // RozkladSwiateczny
        verify(daoMock, atLeastOnce()).dodajRozklad(any(RozkladSwiateczny.class));
    }

    @Test
    @DisplayName("Dodanie nowego rozkładu nocnego")
    void tworzenieNowegoRozkladuNocnego() {
        // Jeśli
        String dane = "1;2;01.01.2026;5;1-2;1.0-2.0";
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy
        // Sprawdzamy czy metoda została wywołana i tworzy obiekt klasy
        // RozkladNocny
        verify(daoMock, atLeastOnce()).dodajRozklad(any(RozkladNocny.class));
    }

    @Test
    @DisplayName("Niedodanie rozkladu przez niepoprawny typ")
    void tworzenieNowegoRozkladuNiepoprawnyTyp() {
        // Jeśli
        String dane = "1;4;01.01.2026;5;1-2;1.0-2.0";
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy
        // Sprawdzamy czy metoda nie została wykonana
        verify(daoMock, never()).dodajRozklad(any());
    }

    @Test
    @DisplayName("Niedodanie rozkładu bo zła data")
    void tworzenieNowegoRozkladuNiepoprawnaData() {
        // Jeśli
        String dane = "1;2;01.13.2026;5;1-2;1.0-2.0";
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy
        // Sprawdzamy czy metoda nie została wykonana
        verify(daoMock, never()).dodajRozklad(any());
    }

    @Test
    @DisplayName("Podanie null jako parametr rozkład")
    void podanieNullNaParametr() {
        // Jeśli
        String dane = null;
        // Gdy
        inwentarz.dodajRozklad(dane);
        // Wtedy
        // Sprawdzamy czy metoda nie została wykonana
        verify(daoMock, never()).dodajRozklad(any());   
    }
}

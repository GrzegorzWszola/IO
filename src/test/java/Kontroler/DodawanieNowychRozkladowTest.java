package Kontroler;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import Model.IModel;

@Tag("PU08")
public class DodawanieNowychRozkladowTest {
    private IModel modelMock;
    private DodawanieNowychRozkladowJazdy dodawanieRozkladow;

    @BeforeEach
    void setUp() {
        // Tworzymy mock modelu
        modelMock = mock(IModel.class);
        // Ustawienie metod modelu
        when(modelMock.znajdzWszystkieLinie()).thenReturn(new ArrayList<>());
        // Stworz klase do testowania
        dodawanieRozkladow = new DodawanieNowychRozkladowJazdy(modelMock);
    }

    @Test
    @DisplayName("Dodanie poprawnego rozkladu podstawowego")
    void dodaniePoprawnegoRozkladu() {
        // Jeśli
        // Tworzymy szpiega na naszej klasie
        DodawanieNowychRozkladowJazdy dodawanieRozkladowSpy = spy(dodawanieRozkladow);
        // Robimy atrapy funkcji aby zwracały to co chcemy
        doReturn("1;1;01.01.2026;5").when(dodawanieRozkladowSpy).wprowadzParametryRozkladu();   
        // Gdy
        // Narzut konkretnego zwrotu z klasy statycznej
        try (MockedStatic<PrzekazanieInformacjiUzytkownikowi> mocked = 
            mockStatic(PrzekazanieInformacjiUzytkownikowi.class)) {
            mocked.when(() -> PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(any()))
              .thenReturn(List.of("1", "2"));

            dodawanieRozkladowSpy.dodajRozklad();
        }
        // Wtedy
        verify(modelMock).zapiszRozklad(contains("1;1;01.01.2026;5;1-2"));
    }

    @Test
    @Tag("RozkladSwiateczny")
    @DisplayName("Dodanie poprawnego rozkladu świątecznego")
    void dodaniePoprawnegoRozkladuSwiatecznego() {
        // Jeśli
        // Tworzymy szpiega na naszej klasie
        DodawanieNowychRozkladowJazdy dodawanieRozkladowSpy = spy(dodawanieRozkladow);
        // Robimy atrapy funkcji aby zwracały to co chcemy
        doReturn("1;3;01.01.2026;5").when(dodawanieRozkladowSpy).wprowadzParametryRozkladu();   
        doReturn("Wigilia").when(dodawanieRozkladowSpy).wprowadzTypSwieta();
        // Gdy
        // Narzut konkretnego zwrotu z klasy statycznej
        try (MockedStatic<PrzekazanieInformacjiUzytkownikowi> mocked = 
            mockStatic(PrzekazanieInformacjiUzytkownikowi.class)) {
            mocked.when(() -> PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(any()))
              .thenReturn(List.of("1", "2"));

            dodawanieRozkladowSpy.dodajRozklad();
        }
        // Wtedy
        verify(modelMock).zapiszRozklad(contains("1;3;01.01.2026;5;1-2;Wigilia"));
    }

    @Test
    @DisplayName("Dodanie poprawnego rozkladu nocnego")
    void dodaniePoprawnegoRozkladuNocnego() {
        // Jeśli
        // Tworzymy szpiega na naszej klasie
        DodawanieNowychRozkladowJazdy dodawanieRozkladowSpy = spy(dodawanieRozkladow);
        // Robimy atrapy funkcji aby zwracały to co chcemy
        doReturn("1;2;01.01.2026;5").when(dodawanieRozkladowSpy).wprowadzParametryRozkladu();   
        doReturn("12.00-6.00").when(dodawanieRozkladowSpy).wprowadzCzasStartuIKonca();
        // Gdy
        // Narzut konkretnego zwrotu z klasy statycznej
        try (MockedStatic<PrzekazanieInformacjiUzytkownikowi> mocked = 
            mockStatic(PrzekazanieInformacjiUzytkownikowi.class)) {
            mocked.when(() -> PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(any()))
              .thenReturn(List.of("1", "2"));

            dodawanieRozkladowSpy.dodajRozklad();
        }
        // Wtedy
        verify(modelMock).zapiszRozklad(contains("1;2;01.01.2026;5;1-2;12.00-6.00"));
    }

    @Test
    @DisplayName("Dodanie rozkładu z pustą listą linii - wyświetlenie błędu")
    void dodanieRozkladuZPustaListaLinii() {
        // Jeśli
        DodawanieNowychRozkladowJazdy dodawanieRozkladowSpy = spy(dodawanieRozkladow);
        doReturn("1;1;01.01.2026;5").when(dodawanieRozkladowSpy).wprowadzParametryRozkladu();
        
        // Gdy
        try (MockedStatic<PrzekazanieInformacjiUzytkownikowi> mocked = 
            mockStatic(PrzekazanieInformacjiUzytkownikowi.class)) {
            mocked.when(() -> PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(any()))
                .thenReturn(new ArrayList<>()); // Pusta lista

            dodawanieRozkladowSpy.dodajRozklad();
            
            // Wtedy - sprawdzamy czy wyświetlono błąd
            mocked.verify(() -> 
                PrzekazanieInformacjiUzytkownikowi.przekazanieInformacjiUzytkownikowi(
                    "Error: Lista linii jest pusta"));
        }
        
        // Sprawdzamy że rozkład NIE został zapisany
        verify(modelMock, never()).zapiszRozklad(anyString());
    }

    @Test
    @DisplayName("Sprawdzenie że wprowadzTypSwieta nie jest wywoływane dla rozkładu podstawowego")
    void sprawdzenieZeWprowadzTypSwietaNieJestWywolywaneDlaRozkladuPodstawowego() {
        // Jeśli
        DodawanieNowychRozkladowJazdy dodawanieRozkladowSpy = spy(dodawanieRozkladow);
        doReturn("1;1;01.01.2026;5").when(dodawanieRozkladowSpy).wprowadzParametryRozkladu();
        
        // Gdy
        try (MockedStatic<PrzekazanieInformacjiUzytkownikowi> mocked = 
            mockStatic(PrzekazanieInformacjiUzytkownikowi.class)) {
            mocked.when(() -> PrzekazanieInformacjiUzytkownikowi.wyswietlAktualneLinie(any()))
                .thenReturn(List.of("1", "2"));

            dodawanieRozkladowSpy.dodajRozklad();
        }
        
        // Wtedy
        verify(dodawanieRozkladowSpy, never()).wprowadzTypSwieta();
        verify(dodawanieRozkladowSpy, never()).wprowadzCzasStartuIKonca();
    }
}
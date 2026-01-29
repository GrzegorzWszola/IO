package testyFitnesse;

import Kontroler.DodawanieNowychRozkladowJazdy;
import fit.ColumnFixture;

public class testDodanieNowegoRozkladu extends ColumnFixture {
    public String id;
    public String typ;
    public String dataOd;
    public String czestotliwosc;
    public String typSwieta;
    public String czasNocny;

    private String oczekiwany;
    private int czyDodano;

    public boolean dodajNowyRozklad() {
        // poczatkowa ilość rozkładów zapisanych
        int stanPrzed = dajLiczbeRozkladow();
        // Wykonanie operacji
        DodawanieNowychRozkladowJazdy pu08 = new DodawanieNowychRozkladowJazdy(SetUp.model) {
            @Override
            public String wprowadzParametryRozkladu() {
                return id + ';' + typ + ';' + dataOd + ';' + czestotliwosc;
            }
            @Override
            public String wprowadzTypSwieta() {
                return typSwieta;
            }
            @Override
            public String wprowadzCzasStartuIKonca() {
                return czasNocny;
            }
        };

        pu08.dodajRozklad();

        oczekiwany = "";

        oczekiwany = id + ';' + typ + ';' + czestotliwosc + ';' + dataOd;
        oczekiwany += ";" + "1-2";
        if (typSwieta != null && !typSwieta.isEmpty() && typ != null && typ.equals("3")) {
            oczekiwany += ";" + typSwieta;
        }
        if (czasNocny != null && !czasNocny.isEmpty() && typ != null && typ.equals("2")) {
            oczekiwany += ";" + czasNocny;
        } 
    
        czyDodano = dajLiczbeRozkladow() - stanPrzed;
        return (czyDodano == 1);
    }

    public int dajLiczbeRozkladow() {
        return SetUp.dao.dajWszystkieRozklady().size();
    }

    public boolean zawieraPrawidlowyRozklad() {
        return SetUp.dao.dajWszystkieRozklady().getLast().opisz().equals(oczekiwany);
    }

    public String pokazRozklad() {
        if (czyDodano == 0)
        {
            return null;
        }
        return SetUp.dao.dajWszystkieRozklady().getLast().opisz();
    }
}

package Kontroler;

import Model.DAO;
import Model.IDAO;
import Model.IModel;
import Model.Inwentarz;
import Model.Model;

public class SystemZarzadzaniaSmarTransit {
	public static void main(String[] args) {
        System.out.println(SystemZarzadzaniaSmarTransit.class.getName()); 
        System.out.println("✓ main():");
        System.out.println("--- System Zarządzania Smart Transit rozpoczął działanie.");

		IDAO dao = new DAO();

		dao.pokazBazePrzystankow();
		dao.pokazBazeTras();
		dao.pokazBazeLinii();

		Inwentarz inwentarz = new Inwentarz(dao);
		IModel model = new Model(inwentarz, dao);

		IKontrolerKierownikaRuchu kierownik = new KontrolerKierownikaRuchu(model);

		kierownik.dodawanieNowychRozkladowJazdy();

		dao.pokazBazeRozkladow();
	}
}
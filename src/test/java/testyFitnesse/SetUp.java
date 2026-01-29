package testyFitnesse;

import Model.DAO;
import Model.Inwentarz;
import Model.Model;
import fit.Fixture;

public class SetUp extends Fixture {
    static DAO dao;
    static Inwentarz inwentarz;
    static Model model;

    public SetUp() {
        dao = new DAO();
        inwentarz = new Inwentarz(dao);
        model = new Model(inwentarz, dao);
    }
}

package Model;

import java.time.LocalDate;
import java.util.List;

public class RozkladSwiateczny extends DekoratorRozkladu {
	private String typSwieta;

	public RozkladSwiateczny(IRozklad rozklad, String typSwieta) {
		super(rozklad);
		this.typSwieta = typSwieta;
	}

	@Override
    public String opisz() {
        return rozklad.opisz() + ";" + typSwieta;
	}
	
	@Override
	public String getTypSwieta() {
		return this.typSwieta;
	}
}
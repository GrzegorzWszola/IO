package Model;

public class RozkladNocny extends DekoratorRozkladu {
	private float czasStartu;
	private float czasKonca;

	public RozkladNocny(IRozklad rozklad, float czasStartu, float czasKonca) {
		super(rozklad);
		this.czasStartu = czasStartu;
		this.czasKonca = czasKonca;
	}

	@Override
	public String opisz() {
		return this.rozklad.opisz() + ";" + czasStartu + "-" + czasKonca;
	}

	@Override
	public float getCzasStartu() {
		return this.czasStartu;
	}

	@Override
	public float getCzasKonca() {
		return this.czasKonca;
	}
}
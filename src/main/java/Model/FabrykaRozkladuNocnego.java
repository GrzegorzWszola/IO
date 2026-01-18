package Model;

public class FabrykaRozkladuNocnego implements IFabrykaRozkladu {
	private IRozklad rozklad;

	public FabrykaRozkladuNocnego(IRozklad rozklad) {
		this.rozklad = rozklad;
	}

	public IRozklad utworzRozklad(String dane) {
		String[] czasy = dane.split("-");
		String czasStartu = czasy[0];
		String czasKonca = czasy[1];
		float czasStartuFloat = 0;
		float czasKoncaFloat = 0;
		try{
			czasStartuFloat = Float.parseFloat(czasStartu);
			czasKoncaFloat = Float.parseFloat(czasKonca);
		} catch (NumberFormatException e){
			System.err.println("Error: " + e.getMessage());
		}
		return new RozkladNocny(rozklad, czasStartuFloat, czasKoncaFloat);
	}
}
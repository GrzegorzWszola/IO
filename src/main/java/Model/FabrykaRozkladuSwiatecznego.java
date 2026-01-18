package Model;

/**
 * Klasa tworzaca fabryke rozkladu swiatecznego
 */
public class FabrykaRozkladuSwiatecznego implements IFabrykaRozkladu {
	private IRozklad rozklad;

	public FabrykaRozkladuSwiatecznego(IRozklad rozklad) {
		this.rozklad = rozklad;
	}

	public IRozklad utworzRozklad(String dane) {
		System.out.println("\nModel.FabrykaRozkladuSwiatecznego()"); 
        System.out.println("✓ utworzRozklad():");
		System.out.println("--- utworzony rozklad ze swietem: " + dane);
		if (!dane.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Nazwa święta nie mogą zawierać cyfr"); 
		return new RozkladSwiateczny(this.rozklad, dane);
	}
}
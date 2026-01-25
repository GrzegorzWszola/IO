import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Zestaw testów dla tagu PU08")
@SelectPackages({"Model", "Kontroler"})
@IncludeTags("PU08")
public class SuitePU08 {}

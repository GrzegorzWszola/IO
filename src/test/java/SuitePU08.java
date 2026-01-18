import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SuiteDisplayName("Zestaw testów dla tagu PU08")
@SelectClasses({DAOTest.class, InwentarzTest.class, FabrykaRozkladuTest.class})
@IncludeTags("PU08")
public class SuitePU08 {}

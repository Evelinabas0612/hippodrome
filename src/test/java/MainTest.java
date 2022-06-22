import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;
import org.testng.annotations.Test;

public class MainTest {

    @Disabled("Отключен")
    @Test
    @Timeout(value = 21)
    public void mainTest() throws Exception{
    Main.main(null);
    }
}

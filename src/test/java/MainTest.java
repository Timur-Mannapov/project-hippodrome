import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Disabled
    @Timeout(22)
    void mainTest() {
        Main.main(null);
    }
}
/**
 * Unit Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void fromName() {
        // Check all Units.
        assertEquals(Unit.DEGC, Unit.fromName("degrees-celsius"));
        assertEquals(Unit.DEGF, Unit.fromName("degrees-fahrenheit"));
        assertEquals(Unit.MS, Unit.fromName("milliseconds"));
        assertEquals(Unit.NM, Unit.fromName("nanometers"));
        assertEquals(Unit.SEC, Unit.fromName("seconds"));

        // Check bad name & null parameters.
        assertEquals(null, Unit.fromName("not-unit"));
        assertEquals(null, Unit.fromName(null));
    }

    @Test
    void fromLabel() {
        // Check all Units.
        assertEquals(Unit.DEGC, Unit.fromLabel("degc"));
        assertEquals(Unit.DEGF, Unit.fromLabel("degf"));
        assertEquals(Unit.MS, Unit.fromLabel("ms"));
        assertEquals(Unit.NM, Unit.fromLabel("nm"));
        assertEquals(Unit.SEC, Unit.fromLabel("sec"));

        // Check bad name & null parameters.
        assertEquals(null, Unit.fromLabel("nu"));
        assertEquals(null, Unit.fromLabel(null));
    }
}
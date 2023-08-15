/**
 * Value Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import arterburn.aaron.model.Value.DecimalValue;
import arterburn.aaron.model.Value.StringValue;

class ValueTest {

    @Test
    void value() {
        // Setup
        String expectedString = "abc";
        double expectedDecimal = 1.5d;
        Value<Double> decimalValue = new DecimalValue(expectedDecimal);
        Value<String> stringValue = new StringValue(expectedString);

        // Assert getter provides correct value
        assertEquals(expectedDecimal, decimalValue.value());
        assertEquals(expectedString, stringValue.value());
    }

    @Test
    void units() {
        // Setup
        Unit expectedUnit = Unit.DEGC;
        Value<Double> value = new DecimalValue(1.5d, expectedUnit);

        // Assert getter provides correct value
        assertEquals(expectedUnit, value.units());
    }

    @Test
    void parse() {
        // Setup
        String decimalData = "1.2";
        String stringData = "\"abc\"";

        // Act
        Value<Double> actualDecimal = Value.parse(decimalData, Unit.DEGC);
        Value<String> actualString = Value.parse(stringData);

        // Assert Decimal data parsed correctly.
        double expectedDecimal = 1.2d;

        assertEquals(Unit.DEGC, actualDecimal.units());
        assertEquals(expectedDecimal, actualDecimal.value());

        // Assert String data parsed correctly.
        String expectedString = "abc";
        assertEquals(expectedString, actualString.value());
    }

    @Test
    void parseMap() {
        // Setup
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append("KEY_1 = \"abc\"\n");
        dataBuilder.append("KEY_2 = 1.2\n");

        // Act
        Map<String, Value> actual = Value.parseMap(dataBuilder.toString());

        // Assert data parsed correctly.
        String expected1 = "abc";
        double expected2 = 1.2d;
        assertEquals(expected1, actual.get("KEY_1").value());
        assertEquals(expected2, actual.get("KEY_2").value());
    }
}
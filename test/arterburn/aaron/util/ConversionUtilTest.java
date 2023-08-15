/**
 * Conversion Util Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import arterburn.aaron.model.Unit;
import arterburn.aaron.model.Value;
import arterburn.aaron.model.Value.DecimalValue;

class ConversionUtilTest {

    @Test
    void convertTo() {
        // Setup
        Value<Double> degfValue = new DecimalValue(50d, Unit.DEGF);
        Value<Double> degcValue = new DecimalValue(10d, Unit.DEGC);
        Value<Double> msValue = new DecimalValue(1920d, Unit.MS);
        Value<Double> nmValue = new DecimalValue(100d, Unit.NM);
        Value<Double> secValue = new DecimalValue(1.92d, Unit.SEC);

        // Act
        Value<Double> degcConverted = ConversionUtil.convertToCelsius(degfValue);
        Value<Double> degfConverted = ConversionUtil.convertToFahrenheit(degcValue);
        Value<Double> msConverted = ConversionUtil.convertToMilliseconds(secValue);
        Value<Double> nmConverted = ConversionUtil.convertToNanometers(nmValue);
        Value<Double> secConverted = ConversionUtil.convertToSeconds(msValue);

        // Assert conversions correct
        assertEquals(degcValue.value(), degcConverted.value());
        assertEquals(degfValue.value(), degfConverted.value());
        assertEquals(msValue.value(), msConverted.value());
        assertEquals(nmValue.value(), nmConverted.value());
        assertEquals(secValue.value(), secConverted.value());
    }

    @Test
    void convertToCelsius() {
        // Setup
        Value<Double> origValue = new DecimalValue(50d, Unit.DEGF);

        // Act
        Value<Double> actual = ConversionUtil.convertToCelsius(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(10d, Unit.DEGC);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(10d, Unit.MS);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.convertToCelsius(wrongUnit));
    }

    @Test
    void convertToFahrenheit() {
        // Setup
        Value<Double> origValue = new DecimalValue(10d, Unit.DEGC);

        // Act
        Value<Double> actual = ConversionUtil.convertToFahrenheit(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(50d, Unit.DEGF);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(50d, Unit.MS);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.convertToFahrenheit(wrongUnit));
    }

    @Test
    void convertToMilliseconds() {
        // Setup
        Value<Double> origValue = new DecimalValue(1.92d, Unit.SEC);

        // Act
        Value<Double> actual = ConversionUtil.convertToMilliseconds(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(1920d, Unit.MS);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(1.92d, Unit.DEGC);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.convertToMilliseconds(wrongUnit));
    }

    @Test
    void convertToNanometers() {
        // Check
        Value<Double> wrongUnit = new DecimalValue(100d, Unit.DEGC);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.convertToSeconds(wrongUnit));
    }

    @Test
    void convertToSeconds() {
        // Setup
        Value<Double> origValue = new DecimalValue(1920d, Unit.MS);

        // Act
        Value<Double> actual = ConversionUtil.convertToSeconds(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(1.92d, Unit.SEC);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(1920d, Unit.DEGC);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.convertToSeconds(wrongUnit));
    }

    @Test
    void celsiusToFahrenheit() {
        // Setup
        Value<Double> origValue = new DecimalValue(10d, Unit.DEGC);

        // Act
        Value<Double> actual = ConversionUtil.celsiusToFahrenheit(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(50d, Unit.DEGF);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(1d, Unit.DEGF);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.celsiusToFahrenheit(wrongUnit));
    }

    @Test
    void fahrenheitToCelsius() {
        // Setup
        Value<Double> origValue = new DecimalValue(50d, Unit.DEGF);

        // Act
        Value<Double> actual = ConversionUtil.fahrenheitToCelsius(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(10d, Unit.DEGC);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(10d, Unit.DEGC);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.fahrenheitToCelsius(wrongUnit));
    }

    @Test
    void millisecondsToSeconds() {
        // Setup
        Value<Double> origValue = new DecimalValue(1920d, Unit.MS);

        // Act
        Value<Double> actual = ConversionUtil.millisecondsToSeconds(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(1.92d, Unit.SEC);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(1920d, Unit.SEC);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.millisecondsToSeconds(wrongUnit));
    }

    @Test
    void secondsToMilliseconds() {
        // Setup
        Value<Double> origValue = new DecimalValue(1.92d, Unit.SEC);

        // Act
        Value<Double> actual = ConversionUtil.secondsToMilliseconds(origValue);

        // Assert value converted correctly
        Value<Double> expected = new DecimalValue(1920d, Unit.MS);
        assertEquals(expected.value(), actual.value());
        assertEquals(expected.units(), actual.units());

        // Check
        Value<Double> wrongUnit = new DecimalValue(1920d, Unit.MS);
        assertThrows(IllegalArgumentException.class ,() -> ConversionUtil.secondsToMilliseconds(wrongUnit));
    }
}
/**
 * Conversion Util Class
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.util;

import arterburn.aaron.model.Unit;
import arterburn.aaron.model.Value;
import arterburn.aaron.model.Value.*;

/**
 * Utility class containing methods to convert values.
 */
public class ConversionUtil {

    /**
     * Converts Value to the given Unit.
     * @param value Value to convert
     * @param newUnits Unit of measurement to convert Value to
     * @return Converted Value
     */
    public static Value<Double> convertTo(Value<Double> value, Unit newUnits) {
        return switch (newUnits) {
            case DEGC -> convertToCelsius(value);
            case DEGF -> convertToFahrenheit(value);
            case NM -> convertToNanometers(value);
            case MS -> convertToMilliseconds(value);
            case SEC -> convertToSeconds(value);
        };
    }

    /**
     * Converts Value to degrees celsius
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> convertToCelsius(Value<Double> value) {
        return switch (value.units()) {
            case DEGC -> value;
            case DEGF -> fahrenheitToCelsius(value);
            default -> {
                throw new IllegalArgumentException(String.format("%s not convertable to %s", value.units(), Unit.DEGC));
            }
        };
    }

    /**
     * Converts Value to degrees fahrenheit
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> convertToFahrenheit(Value<Double> value) {
        return switch (value.units()) {
            case DEGF -> value;
            case DEGC -> celsiusToFahrenheit(value);
            default -> {
                throw new IllegalArgumentException(String.format("%s not convertable to %s", value.units(), Unit.DEGF));
            }
        };
    }

    /**
     * Converts Value to milliseconds
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> convertToMilliseconds(Value<Double> value) {
        return switch (value.units()) {
            case MS -> value;
            case SEC -> secondsToMilliseconds(value);
            default -> {
                throw new IllegalArgumentException(String.format("%s not convertable to %s", value.units(), Unit.MS));
            }
        };
    }

    /**
     * Converts Value to nanometers
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> convertToNanometers(Value<Double> value) {
        return switch (value.units()) {
            case NM -> value;
            default -> {
                throw new IllegalArgumentException(String.format("%s not convertable to %s", value.units(), Unit.NM));
            }
        };
    }

    /**
     * Converts Value to seconds
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> convertToSeconds(Value<Double> value) {
        return switch (value.units()) {
            case SEC -> value;
            case MS -> millisecondsToSeconds(value);
            default -> {
                throw new IllegalArgumentException(String.format("%s not convertable to %s", value.units(), Unit.SEC));
            }
        };
    }

    /**
     * Converts degrees celsius to degrees fahrenheit
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> celsiusToFahrenheit(Value<Double> value) {
        if (value.units() != Unit.DEGC) {
            throw new IllegalArgumentException(String.format("Value must be in %s", Unit.DEGC));
        }

        Double converted = ((value.value() * 9) / 5) + 32;
        return new DecimalValue(converted, Unit.DEGF);
    }

    /**
     * Converts degrees fahrenheit to degrees celsius
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> fahrenheitToCelsius(Value<Double> value) {
        if (value.units() != Unit.DEGF) {
            throw new IllegalArgumentException(String.format("Value must be in %s", Unit.DEGF));
        }

        double converted = ((value.value() - 32) * 5) / 9;
        return new DecimalValue(converted, Unit.DEGC);
    }

    /**
     * Converts milliseconds to seconds
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> millisecondsToSeconds(Value<Double> value) {
        if (value.units() != Unit.MS) {
            throw new IllegalArgumentException(String.format("Value must be in %s", Unit.MS));
        }

        double converted = value.value() / 1000d;
        return new DecimalValue(converted, Unit.SEC);
    }

    /**
     * Converts milliseconds to milliseconds
     * @param value Value to convert
     * @return Converted Value
     */
    public static Value<Double> secondsToMilliseconds(Value<Double> value) {
        if (value.units() != Unit.SEC) {
            throw new IllegalArgumentException(String.format("Value must be in %s", Unit.SEC));
        }

        double converted = value.value() * 1000d;
        return new DecimalValue(converted, Unit.MS);
    }
}

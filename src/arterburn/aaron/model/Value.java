/**
 * Value Class
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores a value including optional unit of measurement.
 * @param <T> Data type of value
 */
public abstract class Value<T> implements Comparable<Value<T>> {
    private static final Pattern dataRowPattern = Pattern.compile("([^=|\\s]+) = ([^\\<|\\s]+)(?: \\<(.*)\\>)?");

    private final T val;
    private final Unit units;

    /**
     * Constructor
     * @param value Value
     */
    public Value(T value) {
        this(value, null);
    }

    /**
     * Constructor
     * @param value Value
     * @param units Unit of measurement
     */
    public Value(T value, Unit units) {
        this.val = value;
        this.units = units;
    }

    /**
     * Gets value
     * @return Value
     */
    public T value() {
        return this.val;
    }

    /**
     * Gets unit of measurement
     * @return Unit of measurement
     */
    public Unit units() {
        return this.units;
    }

    /**
     * Parses data into a Value of correct type.
     * @param data Raw data
     * @return Value of correct type
     */
    public static Value parse(String data) {
        return parse(data, null);
    }

    /**
     * Parses data into a Value of correct type.
     * @param data Raw data
     * @param units Unit of measurement
     * @return Value of correct type
     */
    public static Value parse(String data, Unit units) {
        Value result = null;

        result = parseDecimalValue(data, units);

        if (result == null) {
            result = parseStringValue(data);
        }

        return result;
    }

    /**
     * Parses list of data into Map of Values
     * @param data Raw data containing keys and values
     * @return Map of values
     */
    public static Map<String, Value> parseMap(String data) {
        final HashMap<String, Value> map = new HashMap<String, Value>();

        if (data != null) {
            String[] dataRows = data.split(System.lineSeparator());

            Arrays.stream(dataRows).forEach(dataRow -> {
                Matcher matcher = dataRowPattern.matcher(dataRow);

                if (matcher.find()) {
                    String key = matcher.group(1);
                    Unit units = Unit.fromLabel(matcher.group(3));
                    Value result = Value.parse(matcher.group(2), units);
                    map.put(key, result);
                }
            });
        }

        return map;
    }

    /**
     * Parses data into a decimal Value.
     * @param data Raw data
     * @param units Unit of measurement
     * @return Value set to decimal
     */
    private static Value<Double> parseDecimalValue(String data, Unit units) {
        try {
            double val = Double.parseDouble(data);
            return new DecimalValue(val, units);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Parses data into a string Value.
     * @param data Raw data
     * @return Value set to string
     */
    private static Value<String> parseStringValue(String data) {
        try {
            String val =  data.replaceAll("\"", "");
            return new StringValue(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Value Class to hold string values.
     */
    public static class StringValue extends Value<String> {

        /**
         * Constructor
         * @param value Value
         */
        public StringValue(String value) {
            super(value);
        }

        /**
         * Compares value to another value.
         * @param otherValue Value to compare
         * @return 0 if equal, -1 if less, 1 if more
         */
        public int compareTo(Value<String> otherValue) {
            return this.value().compareTo(otherValue.value());
        }
    }

    /**
     * Value Class to hold decimal values.
     */
    public static class DecimalValue extends Value<Double> {

        /**
         * Constructor
         * @param value Value
         */
        public DecimalValue(Double value) {
            super(value);
        }

        /**
         * Constructor
         * @param value Value
         * @param units Unit of measurement
         */
        public DecimalValue(Double value, Unit units) {
            super(value, units);
        }

        /**
         * Compares value to another value.
         * @param otherValue Value to compare
         * @return 0 if equal, -1 if less, 1 if more
         */
        public int compareTo(Value<Double> otherValue) {
            return this.value().compareTo(otherValue.value());
        }
    }
}

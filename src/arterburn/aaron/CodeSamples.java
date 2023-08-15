/**
 * USGS - Code Samples
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arterburn.aaron.model.Criteria;
import arterburn.aaron.model.Filter;
import arterburn.aaron.model.Unit;
import arterburn.aaron.model.Value;
import arterburn.aaron.util.ConversionUtil;
import arterburn.aaron.util.FilterUtil;

/**
 * Coding samples requested. Each question (1-5) has a method implementing it. Some have overridden methods to
 * provide optional parameters or alternative parameter types.
 */
public class CodeSamples {

    /* START Question 1 */

    /**
     * Given a string formatted as keyword = value where each keyword = value pair is on a separate line,
     * write a reusable function that accepts string and a keyword and returns the value of a given keyword.
     *
     * @param keyword Keyword for value
     * @param input Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionOne(String keyword, String input) {
        Value result = null;

        // Pattern to match a key value pair "K = V".
        final String pattern = "(?:%s) = (.+)";
        Matcher matcher = Pattern.compile(String.format(pattern, keyword)).matcher(input);

        // If result matched parse value.
        if (matcher.find()) {
            result = Value.parse(matcher.group(1));
        }

        // Return result if found, NULL if not.
        return result;
    }

    /* END Question 1 */
    /* START Question 2 */

    /**
     * Create a new function to support strings where there are units in <> after some of the values?
     *
     * @param keyword Keyword for value
     * @param input Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionTwo(String keyword, String input) {
        Value result = null;

        // Pattern to match a key value pair with optional unit of measurement "K = V <U>".
        final String pattern = "(?:%s) = ([^\\<|\\s]+)(?: \\<(.*)\\>)?";
        Matcher matcher = Pattern.compile(String.format(pattern, keyword)).matcher(input);

        // If result matched parse value & unit or measurement.
        if (matcher.find()) {
            Unit valueUnits = Unit.fromLabel(matcher.group(2));
            result = Value.parse(matcher.group(1), valueUnits);
        }

        // Return result if found, NULL if not.
        return result;
    }

    /* END Question 2 */
    /* START Question 3 */

    /**
     * Create a new function, class, or script that accepts the same string and keyword input, but now supports
     * unit conversions for those values that have units encoded (e.g., <MS> (milliseconds) to <S> (seconds)
     * or <NM> (nanometers) to <MM> (millimeters)).
     *
     * @param keyword Keyword for value
     * @param units Unit of measurement value should be returned as.
     * @param input Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionThree(String keyword, String units, String input) {
        return questionThree(keyword, Unit.fromName(units), input);
    }


    /**
     * Create a new function, class, or script that accepts the same string and keyword input, but now supports
     * unit conversions for those values that have units encoded (e.g., <MS> (milliseconds) to <S> (seconds)
     * or <NM> (nanometers) to <MM> (millimeters)).
     *
     * @param keyword Keyword for value
     * @param units Unit of measurement value should be returned as.
     * @param input Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionThree(String keyword, Unit units, String input) {
        Value result = null;

        // Pattern to match a key value pair with optional unit of measurement "K = V <U>".
        final String pattern = "(?:%s) = ([^\\<|\\s]+)(?: \\<(.*)\\>)?";
        Matcher matcher = Pattern.compile(String.format(pattern, keyword)).matcher(input);

        // If result matched parse value & unit or measurement.
        if (matcher.find()) {
            Unit valueUnits = Unit.fromLabel(matcher.group(2));
            result = Value.parse(matcher.group(1), valueUnits);

            // If not requested units convert value.
            if (result.units() != units) {
                result = ConversionUtil.convertTo(result, units);
            }
        }

        // Return result if found, NULL if not.
        return result;
    }

    /* END Question 3 */
    /* START Question 4 */

    /**
     * Extend or rewrite the function to accept multiple strings, a keyword, and a filter (e.g., maximum)
     * that finds the value of the keyword in each string and then applies the filter. In the example below,
     * the pre-filter values are -35.94, -25.94, and -42.34.
     *
     * @param keyword Keyword for value
     * @param filter Filter to apply to values
     * @param inputs Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionFour(String keyword, String filter, String... inputs) {
        return questionFour(keyword, Filter.fromName(filter), inputs);
    }


    /**
     * Extend or rewrite the function to accept multiple strings, a keyword, and a filter (e.g., maximum)
     * that finds the value of the keyword in each string and then applies the filter. In the example below,
     * the pre-filter values are -35.94, -25.94, and -42.34.
     *
     * @param keyword Keyword for value
     * @param filter Filter to apply to values
     * @param inputs Data to be searched
     * @return Value for given keyword, NULL if not found
     */
    public static Value questionFour(String keyword, Filter filter, String... inputs) {
        // Parse inputs to key value maps.
        List<Map<String, Value>> inputData = Arrays.stream(inputs).map(Value::parseMap).toList();

        List<Value> values = new ArrayList<>();
        Value result = null;

        // Add value to results for each filtered data input that contains requested keyword.
        inputData.stream().filter(d -> d.containsKey(keyword)).forEach(d -> values.add(d.get(keyword)));

        if (!values.isEmpty()) {
            // If filter provided get value from filter else return first value.
            result = (filter != null) ? FilterUtil.filter(values, filter) : values.get(1);
        }

        // Return result if found, NULL if not.
        return result;
    }

    /* END Question 4 */
    /* START Question 5 */

    /**
     * Rewrite or create a function, class, or script that accepts multiple strings, a keyword, and a filter
     * or criteria where the filter is an equality using another keyword.
     *
     * @param keyword Keyword for value
     * @param criteria Criteria values must meet
     * @param inputs Data to be searched
     * @return List of Values for given keyword
     */
    public static List<Value> questionFive(String keyword, String criteria, String... inputs) {
        return questionFive(keyword, null, criteria, inputs);
    }


    /**
     * Rewrite or create a function, class, or script that accepts multiple strings, a keyword, and a filter
     * or criteria where the filter is an equality using another keyword.
     *
     * @param keyword Keyword for value
     * @param filter Filter to apply to values
     * @param criteria Criteria values must meet
     * @param inputs Data to be searched
     * @return List of Values for given keyword
     */
    public static List<Value> questionFive(String keyword, Filter filter, String criteria, String... inputs) {
        return questionFive(keyword, filter, Criteria.parse(criteria), inputs);
    }


    /**
     * Rewrite or create a function, class, or script that accepts multiple strings, a keyword, and a filter
     * or criteria where the filter is an equality using another keyword.
     *
     * @param keyword Keyword for value
     * @param filter Filter to apply to values
     * @param criteria Criteria values must meet
     * @param inputs Data to be searched
     * @return List of Values for given keyword
     */
    public static List<Value> questionFive(String keyword, Filter filter, Criteria criteria, String... inputs) {
        // Parse inputs to key value maps.
        List<Map<String, Value>> inputData = Arrays.stream(inputs).map(Value::parseMap).toList();

        // Set initial filtered data to input data.
        List<Map<String, Value>> filteredData = inputData;
        List<Value> results = new ArrayList<>();

        if (criteria != null) {
            // Define predicate to check if each data input contains criteria field & meets criteria condition.
            Predicate<Map<String, Value>> p = data ->
                    (data.containsKey(criteria.field())) ? criteria.meetsCondition(data.get(criteria.field())) : false;

            // Filter input data to only include those that meet criteria.
            filteredData = inputData.stream().filter(p).toList();
        }

        // Add value to results for each filtered data input that contains requested keyword.
        filteredData.stream().filter(d -> d.containsKey(keyword)).forEach(d -> results.add(d.get(keyword)));

        // Return list of results.
        return results;
    }

    /* END Question 5 */
}

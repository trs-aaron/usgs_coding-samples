/**
 * Criteria Record Class
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import arterburn.aaron.model.Value.StringValue;

/**
 * Criteria containing key of value and condition it must meet.
 * @param field Name of value to apply criteria
 * @param condition Condition that must be meet by value
 */
public record Criteria(String field, Value condition) {
    private static final Pattern dataPattern = Pattern.compile("([^is|\\s]+) is ([^\\<|\\s]+)");

    /**
     * Determines if given value meets condition of criteria.
     * @param value Value to check against condition
     * @return Whether condition is meet by value
     */
    public boolean meetsCondition(Value value) {
        // Check if value is equal to condition.
        return (value != null) ? this.condition().compareTo(value) == 0 : false;
    }

    /**
     * Parses criteria string to object.
     * @param data Raw criteria string
     * @return Created criteria object
     */
    public static Criteria parse(String data) {
        Criteria result = null;
        Matcher matcher = dataPattern.matcher(data);

        // If data is in correct format convert to Criteria object.
        if (matcher.find()) {
            String key = matcher.group(1);
            Value condition = new StringValue(matcher.group(2));
            result = new Criteria(key, condition);
        }

        return result;
    }
}

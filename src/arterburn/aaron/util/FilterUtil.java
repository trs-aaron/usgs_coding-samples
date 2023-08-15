/**
 * Filter Util Class
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.util;

import java.util.List;

import arterburn.aaron.model.Filter;
import arterburn.aaron.model.Value;

/**
 * i
 */
public class FilterUtil {

    /**
     * Apply correct filter on values.
     * @param values Values to filter
     * @param filter Filter to apply to values
     * @return Filtered Value
     */
    public static Value filter(List<Value> values, Filter filter) {
        return switch (filter) {
            case MAXIMUM ->  values.stream().max(Value::compareTo).get();
            case MINIMUM -> values.stream().min(Value::compareTo).get();
        };
    }
}

/**
 * Filter Util Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import arterburn.aaron.model.Filter;
import arterburn.aaron.model.Unit;
import arterburn.aaron.model.Value;
import arterburn.aaron.model.Value.DecimalValue;

class FilterUtilTest {

    @Test
    void filter() {
        // Setup
        Value<Double> minValue = new DecimalValue(1.1d, Unit.SEC);
        Value<Double> midValue = new DecimalValue(1.5d, Unit.SEC);
        Value<Double> maxValue = new DecimalValue(1.9d, Unit.SEC);
        List<Value> values = Arrays.asList(minValue, midValue, maxValue);

        // Act
        Value<Double> actualMax = FilterUtil.filter(values, Filter.MAXIMUM);
        Value<Double> actualMin = FilterUtil.filter(values, Filter.MINIMUM);

        // Assert filtered correctly
        assertEquals(maxValue.value(), actualMax.value());
        assertEquals(minValue.value(), actualMin.value());
    }
}
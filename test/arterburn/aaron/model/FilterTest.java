/**
 * Filter Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    @Test
    void fromName() {
        // Check all Filters.
        assertEquals(Filter.MAXIMUM, Filter.fromName("maximum"));
        assertEquals(Filter.MINIMUM, Filter.fromName("minimum"));

        // Check bad name & null parameters.
        assertEquals(null, Filter.fromName("not-filter"));
        assertEquals(null, Filter.fromName(null));
    }
}
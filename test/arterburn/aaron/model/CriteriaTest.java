/**
 * Criteria Class Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import arterburn.aaron.model.Value.StringValue;

class CriteriaTest {

    @Test
    void meetsCondition() {
        // Setup
        Criteria criteria = new Criteria("key", new StringValue("good-test"));
        Value<String> goodValue = new StringValue("good-test");
        Value<String> badValue = new StringValue("bad-test");

        // Act and assert goodValue meets condition & badValue does not.
        assertTrue(criteria.meetsCondition(goodValue));
        assertFalse(criteria.meetsCondition(badValue));
    }

    @Test
    void parse() {
        // Setup
        String data = "KEY is 123";
        Criteria expected = new Criteria("KEY", new StringValue("123"));

        // Act
        Criteria actual = Criteria.parse(data);

        // Assert parsed Criteria is same as expected.
        assertEquals(expected.field(), actual.field());
        assertEquals(expected.condition().value(), actual.condition().value());
    }

}
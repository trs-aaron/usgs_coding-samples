/**
 * USGS - Code Samples Tests
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import arterburn.aaron.model.Value;

/**
 * Uses data examples from request to validate requested functionality.
 */
class CodeSamplesTest {

    @Test
    void questionOne() {
        // Create input string
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder.append("BANDWIDTH = 52.6\n");
        inputBuilder.append("EXPOSURE_DURATION = 192\n");
        inputBuilder.append("EXPOSURE_TYPE = AUTO\n");
        inputBuilder.append("DETECTOR_TEMPERATURE = -42.55\n");
        inputBuilder.append("FOCAL_PLANE_TEMPERATURE = -35.94\n");
        inputBuilder.append("FILTER_TEMPERATURE = \"N/A\"\n");
        inputBuilder.append("OPTICS_TEMPERATURE = -37.36");

        // Initialize Parameters
        String input = inputBuilder.toString();
        String keyword = "EXPOSURE_DURATION";

        // Act
        Value<Double> result = CodeSamples.questionOne(keyword, input);
        double actual = result.value();

        // Assert actual result value is same as expected value
        double expected = 192d;
        assertEquals(expected, actual);
    }

    @Test
    void questionTwo() {
        // Create input string
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder.append("CENTER_FILTER_WAVELENGTH = 747.7 <NM>\n");
        inputBuilder.append("BANDWIDTH = 52.6 <NM>\n");
        inputBuilder.append("EXPOSURE_DURATION = 192 <MS>\n");
        inputBuilder.append("EXPOSURE_TYPE = AUTO\n");
        inputBuilder.append("DETECTOR_TEMPERATURE = -42.55 <DEGC>\n");
        inputBuilder.append("FOCAL_PLANE_TEMPERATURE = -35.94 <DEGC>\n");
        inputBuilder.append("FILTER_TEMPERATURE = \"N/A\"\n");
        inputBuilder.append("OPTICS_TEMPERATURE = -37.36 <DEGC>\n");

        // Initialize Parameters
        String input = inputBuilder.toString();
        String keyword = "EXPOSURE_DURATION";

        // Act
        Value<Double> result = CodeSamples.questionTwo(keyword, input);
        double actual = result.value();

        // Assert actual result value is same as expected value
        double expected = 192d;
        assertEquals(expected, actual);
    }

    @Test
    void questionThree() {
        // Create input string
        StringBuilder inputBuilder = new StringBuilder();
        inputBuilder.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder.append("CENTER_FILTER_WAVELENGTH = 747.7 <NM>\n");
        inputBuilder.append("BANDWIDTH = 52.6 <NM>\n");
        inputBuilder.append("EXPOSURE_DURATION = 192 <MS>\n");
        inputBuilder.append("EXPOSURE_TYPE = AUTO\n");
        inputBuilder.append("DETECTOR_TEMPERATURE = -42.55 <DEGC>\n");
        inputBuilder.append("FOCAL_PLANE_TEMPERATURE = -35.94 <DEGC>\n");
        inputBuilder.append("FILTER_TEMPERATURE = \"N/A\"\n");
        inputBuilder.append("OPTICS_TEMPERATURE = -37.36 <DEGC>");

        // Initialize Parameters
        String input = inputBuilder.toString();
        String keyword = "EXPOSURE_DURATION";
        String units = "Seconds";

        // Act
        Value<Double> result = CodeSamples.questionThree(keyword, units, input);
        double actual = result.value();

        // Assert actual result value is same as expected value
        double expected = 0.192d;
        assertEquals(expected, actual);
    }

    @Test
    void questionFour() {
        // Create input string 1
        StringBuilder inputBuilder1 = new StringBuilder();
        inputBuilder1.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder1.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder1.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder1.append("BANDWIDTH = 52.6\n");
        inputBuilder1.append("EXPOSURE_DURATION = 192\n");
        inputBuilder1.append("DETECTOR_TEMPERATURE = -42.55\n");
        inputBuilder1.append("FOCAL_PLANE_TEMPERATURE = -35.94\n");
        inputBuilder1.append("FILTER_TEMPERATURE = \"N/A\"");

        // Create input string 2
        StringBuilder inputBuilder2 = new StringBuilder();
        inputBuilder2.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder2.append("FILTER_NUMBER = \"5\"\n");
        inputBuilder2.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder2.append("BANDWIDTH = 52.6\n");
        inputBuilder2.append("EXPOSURE_DURATION = 150\n");
        inputBuilder2.append("EXPOSURE_TYPE = AUTO\n");
        inputBuilder2.append("DETECTOR_TEMPERATURE = -32.20\n");
        inputBuilder2.append("FOCAL_PLANE_TEMPERATURE = -25.94\n");
        inputBuilder2.append("FILTER_TEMPERATURE = \"N/A\"");

        // Create input string 3
        StringBuilder inputBuilder3 = new StringBuilder();
        inputBuilder3.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder3.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder3.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder3.append("BANDWIDTH = 52.6\n");
        inputBuilder3.append("EXPOSURE_DURATION = 209\n");
        inputBuilder3.append("DETECTOR_TEMPERATURE = -47.85\n");
        inputBuilder3.append("FOCAL_PLANE_TEMPERATURE = -42.34\n");
        inputBuilder3.append("FILTER_TEMPERATURE = \"N/A\"");

        // Initialize Parameters
        String keyword = "FOCAL_PLANE_TEMPERATURE";
        String filter = "maximum";
        String input1 = inputBuilder1.toString();
        String input2 = inputBuilder2.toString();
        String input3 = inputBuilder3.toString();

        // Act
        Value<Double> result = CodeSamples.questionFour(keyword, filter, input1, input2, input3);
        Double actual = result.value();

        // Assert actual result value is same as expected value.
        Double expected = -25.94d;
        assertEquals(expected, actual);
    }

    @Test
    void questionFive() {
        // Create input string 1
        StringBuilder inputBuilder1 = new StringBuilder();
        inputBuilder1.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder1.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder1.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder1.append("BANDWIDTH = 52.6\n");
        inputBuilder1.append("EXPOSURE_DURATION = 192\n");
        inputBuilder1.append("DETECTOR_TEMPERATURE = -42.55\n");
        inputBuilder1.append("FOCAL_PLANE_TEMPERATURE = -35.94\n");
        inputBuilder1.append("FILTER_TEMPERATURE = \"N/A\"");

        // Create input string 2
        StringBuilder inputBuilder2 = new StringBuilder();
        inputBuilder2.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder2.append("FILTER_NUMBER = \"5\"\n");
        inputBuilder2.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder2.append("BANDWIDTH = 52.6\n");
        inputBuilder2.append("EXPOSURE_DURATION = 150\n");
        inputBuilder2.append("EXPOSURE_TYPE = AUTO\n");
        inputBuilder2.append("DETECTOR_TEMPERATURE = -32.20\n");
        inputBuilder2.append("FOCAL_PLANE_TEMPERATURE = -25.94\n");
        inputBuilder2.append("FILTER_TEMPERATURE = \"N/A\"");

        // Create input string 3
        StringBuilder inputBuilder3 = new StringBuilder();
        inputBuilder3.append("INSTRUMENT_ID = \"MDIS-NAC\"\n");
        inputBuilder3.append("FILTER_NUMBER = \"2\"\n");
        inputBuilder3.append("CENTER_FILTER_WAVELENGTH = 747.7\n");
        inputBuilder3.append("BANDWIDTH = 52.6\n");
        inputBuilder3.append("EXPOSURE_DURATION = 209\n");
        inputBuilder3.append("DETECTOR_TEMPERATURE = -47.85\n");
        inputBuilder3.append("FOCAL_PLANE_TEMPERATURE = -42.34\n");
        inputBuilder3.append("FILTER_TEMPERATURE = \"N/A\"");

        // Initialize Parameters
        String keyword = "EXPOSURE_DURATION";
        String criteria = "FILTER_NUMBER is 2";
        String input1 = inputBuilder1.toString();
        String input2 = inputBuilder2.toString();
        String input3 = inputBuilder3.toString();

        // Act
        List<Value> results = CodeSamples.questionFive(keyword,criteria, input1, input2, input3);
        Double[] actual = results.stream().map(Value::value).toArray(Double[]::new);

        // Assert actual result values are same as expected values.
        Double[] expected = new Double[] { 192d, 209d };
        assertArrayEquals(expected, actual);
    }
}
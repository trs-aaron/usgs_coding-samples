/**
 * Unit Enum
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

/**
 * Value units of measurement.
 */
public enum Unit {
    DEGC("degrees-celsius","degc"),
    DEGF("degrees-fahrenheit", "degf"),
    MS("milliseconds", "ms"),
    NM("nanometers", "nm"),
    SEC("seconds", "sec");

    private final String name;
    private final String label;

    /**
     * Constructor
     * @param name Name of unit
     * @param label Label for unit
     */
    Unit(String name, String label) {
        this.name = name;
        this.label = label;
    }

    /**
     * Returns Unit enum value for name
     * @param name Name of unit
     * @return Unit enum
     */
    public static Unit fromName(String name) {

        if (name !=null) {

            String n = name.toLowerCase();

            if (DEGC.name.equals(n)) {
                return DEGC;
            }

            if (DEGF.name.equals(n)) {
                return DEGF;
            }

            if (NM.name.equals(n)) {
                return NM;
            }

            if (MS.name.equals(n)) {
                return MS;
            }

            if (SEC.name.equals(n)) {
                return SEC;
            }
        }

        return null;
    }

    /**
     * Returns Unit enum value for label
     * @param label Label of unit
     * @return Unit enum
     */
    public static Unit fromLabel(String label) {

        if (label != null) {

            String l = label.toLowerCase();

            if (DEGC.label.equals(l)) {
                return DEGC;
            }

            if (DEGF.label.equals(l)) {
                return DEGF;
            }

            if (NM.label.equals(l)) {
                return NM;
            }

            if (MS.label.equals(l)) {
                return MS;
            }

            if (SEC.label.equals(l)) {
                return SEC;
            }
        }

        return null;
    }
}

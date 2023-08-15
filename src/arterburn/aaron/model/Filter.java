/**
 * Filter Enum
 * @author Aaron James Arterburn
 * @Date 8/14/2023
 */
package arterburn.aaron.model;

/**
 * Value filter types.
 */
public enum Filter {
    MAXIMUM("maximum"),
    MINIMUM("minimum");

    private final String name;

    /**
     * Constructor
     * @param name Name of filter
     */
    Filter(String name) {
        this.name = name;
    }

    /**
     * Returns Filter enum value for name
     * @param name Name of filter
     * @return Filter enum
     */
    public static Filter fromName(String name) {
        if (name != null) {
            String n = name.toLowerCase();

            if (MAXIMUM.name.equals(n)) {
                return MAXIMUM;
            }

            if (MINIMUM.name.equals(n)) {
                return MINIMUM;
            }
        }

        return null;
    }
}
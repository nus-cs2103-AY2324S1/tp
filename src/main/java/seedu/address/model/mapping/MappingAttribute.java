package seedu.address.model.mapping;

import java.util.Set;

/**
 * Represents the attribute related to mapping.
 */
public enum MappingAttribute {
    LOCALCODE("localcode"),
    LOCALNAME("localname"),
    PARTNERCODE("partnercode"),
    PARTNERNAME("partnername"),
    UNIVERSITY("university"),
    INFORMATION("information");

    private final String value;

    /**
     * Constructor for MappingAttribute.
     *
     * @param value The string representation of the attribute.
     */
    MappingAttribute(String value) {
        this.value = value;
    }

    /**
     * Gets the string representation of the attribute.
     *
     * @return The string representation of the attribute.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Converts a string to the corresponding MappingAttribute.
     *
     * @param text The string to be converted.
     * @return The MappingAttribute corresponding to the input string, or null if not found.
     */
    public static MappingAttribute fromString(String text) {
        if (text == null) {
            return null;
        }
        for (MappingAttribute enumValue : MappingAttribute.values()) {
            if (enumValue.value.equals(text)) {
                return enumValue;
            }
        }
        return null;
    }

    /**
     * Checks if the given attribute is valid for sorting.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for sorting, false otherwise.
     */
    public static boolean isValidAttributeForSort(String test) {
        MappingAttribute attribute = MappingAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Set.of(LOCALCODE, LOCALNAME, PARTNERCODE, PARTNERNAME, UNIVERSITY, INFORMATION).contains(attribute);
    }

    /**
     * Checks if the given attribute is valid for searching.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for searching, false otherwise.
     */
    public static boolean isValidAttributeForSearch(String test) {
        MappingAttribute attribute = MappingAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Set.of(LOCALCODE, LOCALNAME, PARTNERCODE, PARTNERNAME, UNIVERSITY, INFORMATION).contains(attribute);
    }
}

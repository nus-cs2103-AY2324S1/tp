package seedu.address.model.university;

import java.util.Objects;
/**
 * Represents the attribute related to university.
 */
public enum UniversityAttribute {
    UNIVERSITY("university");

    private final String value;

    /**
     * Constructor for UniversityAttribute.
     *
     * @param value The string representation of the attribute.
     */
    UniversityAttribute(String value) {
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
     * Converts a string to the corresponding UniversityAttribute.
     *
     * @param text The string to be converted.
     * @return The UniversityAttribute corresponding to the input string, or null if not found.
     */
    public static UniversityAttribute fromString(String text) {
        if (text == null) {
            return null;
        }
        for (UniversityAttribute enumValue : UniversityAttribute.values()) {
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
        UniversityAttribute attribute = UniversityAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Objects.equals(UNIVERSITY, attribute);
    }

    /**
     * Checks if the given attribute is valid for searching.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for searching, false otherwise.
     */
    public static boolean isValidAttributeForSearch(String test) {
        UniversityAttribute attribute = UniversityAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Objects.equals(UNIVERSITY, attribute);
    }
}

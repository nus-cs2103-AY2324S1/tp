package seedu.address.model.localcourse;

import java.util.Set;

/**
 * Represents the attribute related to a local course.
 */
public enum LocalCourseAttribute {
    LOCALCODE("localcode"),
    LOCALNAME("localname"),
    LOCALUNIT("unit"),
    LOCALDESCRIPTION("localdescription");

    private final String value;

    /**
     * Constructor for LocalCourseAttribute.
     *
     * @param value The string representation of the attribute.
     */
    LocalCourseAttribute(String value) {
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
     * Converts a string to the corresponding LocalCourseAttribute.
     *
     * @param text The string to be converted.
     * @return The LocalCourseAttribute corresponding to the input string, or null if not found.
     */
    public static LocalCourseAttribute fromString(String text) {
        if (text == null) {
            return null;
        }
        for (LocalCourseAttribute enumValue : LocalCourseAttribute.values()) {
            if (enumValue.value.equals(text)) {
                return enumValue;
            }
        }
        return null;
    }

    /**
     * Checks if the given attribute is valid for search.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for search, false otherwise.
     */
    public static boolean isValidAttributeForSearch(String test) {
        LocalCourseAttribute attribute = LocalCourseAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Set.of(LOCALCODE, LOCALNAME, LOCALDESCRIPTION).contains(attribute);
    }

    /**
     * Checks if the given attribute is valid for sorting.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for sorting, false otherwise.
     */
    public static boolean isValidAttributeForSort(String test) {
        LocalCourseAttribute attribute = LocalCourseAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Set.of(LOCALCODE, LOCALNAME).contains(attribute);
    }

    /**
     * Checks if the given attribute is valid for update.
     *
     * @param test The string to be tested.
     * @return True if the attribute is valid for update, false otherwise.
     */
    public static boolean isValidAttributeForUpdate(String test) {
        LocalCourseAttribute attribute = LocalCourseAttribute.fromString(test);
        if (attribute == null) {
            return false;
        }
        return Set.of(LOCALCODE, LOCALNAME, LOCALUNIT, LOCALDESCRIPTION).contains(attribute);
    }
}

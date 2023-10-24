package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a tag for a course in the address book.
 */
public class CourseTag extends Label {
    public static final String MESSAGE_CONSTRAINTS = "Course codes should start with 2-3 alphabets, "
            + "followed by 4 numbers, and optionally end with an alphabet.";

    // 2-3 alphabets, followed by 4 digits, and optionally ending with an alphabet
    public static final String VALIDATION_REGEX = "^[A-Za-z]{2,3}\\d{4}[A-Za-z]?$";

    /**
     * Constructs a {@code CourseTag}.
     *
     * @param name A course code.
     */
    public CourseTag(String name) {
        super(name);
    }
    /**
     * Factory method to construct a {@code CourseTag}.
     *
     * @param name A valid course code.
     */
    public static CourseTag of(String name) {
        requireNonNull(name);
        checkArgument(isValidCourseCode(name), MESSAGE_CONSTRAINTS);
        return new CourseTag(name.toUpperCase()); // Course codes can only have uppercase letters
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidCourseCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseTag)) {
            return false;
        }

        CourseTag otherCourseTag = (CourseTag) other;
        return name.equals(otherCourseTag.name);
    }
}

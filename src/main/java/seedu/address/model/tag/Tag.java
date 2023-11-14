package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPatientTagName(String)}
 */
public class Tag {

    public static final String DUPLICATE_TAG = "Doctors should not have duplicate tags!";
    public static final String EXTRA_PATIENT_TAG = "Each Patient should only have one priority tag!";
    public static final String INVALID_PATIENT_TAG = "Patient tag should be a valid priority level: Low, Medium or"
            + " High.";
    public static final String INVALID_DOCTOR_TAG = "Doctor tag should be a valid specialisation.";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid patient tag name.
     */
    public static boolean isValidPatientTagName(String test) {
        for (ValidPatientTags tag : ValidPatientTags.values()) {
            if (tag.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given string is a valid patient tag name.
     */
    public static boolean isValidFullPatientTagName(String test) {
        for (ValidPatientTags tag : ValidPatientTags.values()) {
            String fullTagName = "priority: " + tag;
            if (fullTagName.equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given string is a valid doctor tag name.
     */
    public static boolean isValidDoctorTagName(String test) {
        for (ValidDoctorTags tag : ValidDoctorTags.values()) {
            if (tag.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPatientTag() {
        return isValidFullPatientTagName(tagName);
    }

    public boolean isValidDoctorTag() {
        return isValidDoctorTagName(tagName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagName.equals(otherTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        return '[' + tagName + ']';
    }

}

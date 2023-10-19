package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String tagName;
    @JsonIgnore
    public final String courseCode;
    @JsonIgnore
    public final String tutorialGroup;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tagName.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        String[] str = tagName.split(" ", 2);
        String courseCode = str[0];
        String tutorialGroup = str.length == 2 ? str[1] : null;
        this.tagName = tagName;
        this.courseCode = courseCode;
        this.tutorialGroup = tutorialGroup;
    }

    @JsonCreator
    public static Tag create(@JsonProperty("tagName") String tag) {
        return new Tag(tag);
    }

    public String getTagName() {
        return this.tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
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

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

}

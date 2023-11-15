package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Only three kinds of tag names are allowed: Friend, Close Friend (or cf), and Emergency";

    private final TagType tagType;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagType = TagType.fromString(tagName);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        try {
            TagType.fromString(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagType == otherTag.tagType;
    }

    @Override
    public int hashCode() {
        return tagType.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagType.getDisplayName() + ']';
    }

    /**
     * Check if the tag is Emergency tag
     *
     * @return boolean, true if it is emergency tag, else will return false.
     */
    public boolean isEmergencyTag() {
        return tagType == TagType.EMERGENCY;
    }

    public String getTagName() {
        return tagType.getDisplayName();
    }
}

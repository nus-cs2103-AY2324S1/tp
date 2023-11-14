package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Note's content in SEPlendid.
 */
public class Content {
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*[^\\s].*";

    public final String content;

    /**
     * Constructs a {@code Content}.
     *
     * @param content A valid content.
     */
    public Content(String content) {
        content = content.trim();
        requireNonNull(content);
        checkArgument(isValidContent(content), ConstraintMessage.NOTE_CONTENT.toString());
        this.content = content;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Content)) {
            return false;
        }

        Content otherContent = (Content) other;
        return content.equals(otherContent.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}

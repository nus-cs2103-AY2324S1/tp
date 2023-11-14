package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment for an applicant in the applicant list.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidComment(String)}
 */
public class Comment {
    public static final String MESSAGE_CONSTRAINTS = "Comment takes in any string. Comment can be blank";

    /*
     * Takes in any string
     */
    public static final String VALIDATION_REGEX = "^.*$";

    public final String comment;

    /**
     * Constructs an {@code Comment}.
     *
     * @param comment A possible comment for a person.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        this.comment = comment;
    }

    /**
     * Returns true if a given string is a valid comment.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return comment;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return comment.equals(otherComment.comment);
    }

    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}

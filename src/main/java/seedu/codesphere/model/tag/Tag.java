package seedu.codesphere.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the student list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_CONSTRAINTS_ENUMS = "Tags names should be good/average/poor";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    //@@author tiongjjyi
    public final StudentRank ranking;

    /**
     * Constructs a {@code Tag}.
     *
     * @param ranking A valid tag name.
     */
    public Tag(StudentRank ranking) {
        requireNonNull(ranking);
        checkArgument(isValidTagName(ranking.toString()), MESSAGE_CONSTRAINTS);
        this.ranking = ranking;
    }
    //@@author tiongjjyi
    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns string value of the enumerations.
     */
    public String getStringRanking() {
        return this.ranking.toString();
    }
    //@@author tiongjjyi
    /**
     * Returns value of the enumerations.
     */
    public StudentRank getRanking() {
        return this.ranking;
    }
    //@@author tiongjjyi
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
        return ranking.toString().equals(otherTag.ranking.toString());
    }

    @Override
    public int hashCode() {
        return ranking.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + ranking.toString() + ']';
    }

}

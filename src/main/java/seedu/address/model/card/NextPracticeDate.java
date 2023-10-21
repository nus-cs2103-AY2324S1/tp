package seedu.address.model.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Card's answer in lesSON.
 */
public class NextPracticeDate implements Comparable<NextPracticeDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Next Practice Date should be a valid date, such as in the form YYYY-MM-DDThh-mm-ss";

    public final LocalDateTime nextPracticeDate;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public NextPracticeDate(LocalDateTime nextPracticeDate) {
        requireNonNull(nextPracticeDate);
        checkArgument(isValidNextPracticeDate(nextPracticeDate.toString()), MESSAGE_CONSTRAINTS);
        this.nextPracticeDate = nextPracticeDate;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidNextPracticeDate(String test) {
        try {
            LocalDateTime.parse(test);
        } catch (DateTimeParseException d) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(NextPracticeDate o) {
        return this.nextPracticeDate.compareTo(o.nextPracticeDate);
    }

    @Override
    public String toString() {
        return this.nextPracticeDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NextPracticeDate)) {
            return false;
        }

        NextPracticeDate otherDate = (NextPracticeDate) other;
        return nextPracticeDate.equals(otherDate.nextPracticeDate);
    }

    @Override
    public int hashCode() {
        return nextPracticeDate.hashCode();
    }

}

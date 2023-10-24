package seedu.address.model.week;

import seedu.address.model.tag.Tag;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Week in TAvigator.
 * Guarantees: immutable; week is valid as declared in {@link #isValidWeek(int)}
 */
public class Week {
    public static final String MESSAGE_CONSTRAINTS = "Week should be an integer from 0 to 13";
    private final int weekNumber;

    /**
     * Constructs a {@code Week}.
     *
     * @param weekNumber A valid week number.
     */
    public Week(int weekNumber) {
        checkArgument(isValidWeek(weekNumber), MESSAGE_CONSTRAINTS);
        this.weekNumber = weekNumber;
    }

    /**
     * Returns true if a given week is a valid week in the semester.
     */
    public static boolean isValidWeek(int weekNumber) {
        return weekNumber <= 13 && weekNumber >= 0;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "" + this.weekNumber;
    }

    public int getWeekNumber() {
        return this.weekNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Week)) {
            return false;
        }

        Week otherWeek = (Week) other;
        return this.weekNumber == otherWeek.weekNumber;
    }
}


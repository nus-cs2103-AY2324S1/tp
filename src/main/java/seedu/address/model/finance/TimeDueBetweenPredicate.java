package seedu.address.model.finance;

import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Duration;

/**
 * Tests that a {@code Finance}'s {@code TimeDue} is between the given start and end times.
 */
public class TimeDueBetweenPredicate implements Predicate<Finance> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final Duration duration;

    /**
     * Creates a TimeDueBeforePredicate with the given time range.
     * @param duration time period of interests.
     */
    public TimeDueBetweenPredicate(Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean test(Finance finance) {
        return finance.getTimeDue().isWithin(duration);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeDueBetweenPredicate)) {
            return false;
        }

        TimeDueBetweenPredicate otherTimeDueBeforePredicate = (TimeDueBetweenPredicate) other;
        return duration.equals(otherTimeDueBeforePredicate.duration);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("timeStart", duration.getTimeStartValue())
                .add("timeEnd", duration.getTimeEndValue())
                .toString();
    }
}

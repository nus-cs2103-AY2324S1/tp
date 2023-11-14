package seedu.address.model.schedule;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Schedule}'s start time or end time lies on a given date.
 */
public class ScheduleIsOnDatePredicate implements Predicate<Schedule> {
    private final Date date;

    public ScheduleIsOnDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Schedule schedule) {
        return schedule.isOnDate(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleIsOnDatePredicate)) {
            return false;
        }

        ScheduleIsOnDatePredicate otherScheduleIsOnDatePredicate = (ScheduleIsOnDatePredicate) other;
        return date.equals(otherScheduleIsOnDatePredicate.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("date", date).toString();
    }
}

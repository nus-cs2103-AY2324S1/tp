package seedu.address.model.schedule;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Schedule in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Schedule {
    private static final String MESSAGE_CONSTRAINTS = "Schedules start time should be before its end time.";
    private final TutorIndex tutorIndex;
    private final StartTime startTime;
    private final EndTime endTime;

    /**
     * Every field must be present and not null.
     */
    public Schedule(TutorIndex tutorIndex, StartTime startTime, EndTime endTime) {
        requireAllNonNull(tutorIndex, startTime, endTime);
        checkArgument(isValidSchedule(startTime, endTime), MESSAGE_CONSTRAINTS);

        this.tutorIndex = tutorIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean isValidSchedule(StartTime startTime, EndTime endTime) {
        return startTime.getTime().isBefore(endTime.getTime());
    }

    public TutorIndex getTutorIndex() {
        return tutorIndex;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if both schedules have the same tutor index and time fields.
     * This defines a stronger notion of equality between two schedules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return tutorIndex.equals(otherSchedule.tutorIndex)
                && startTime.equals(otherSchedule.startTime)
                && endTime.equals(otherSchedule.endTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tutorIndex, startTime, endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tutorIndex", tutorIndex)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}

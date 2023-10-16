package seedu.address.model.person;


import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's free time of the day in the TAM.
 * Guarantees: immutable; is valid as declared in {@link #isValidFreeTime(LocalTime, LocalTime)}
 */
public class FreeTime {
    public static final FreeTime EMPTY_FREE_TIME = new FreeTime();
    public static final String MESSAGE_CONSTRAINTS =
            "TA's free time should have a start and end time in HH:mm format";
    public final LocalTime from;
    public final LocalTime to;

    /**
     * Constructs a {@code FreeTime}.
     *
     * @param from Start time.
     * @param to   End time.
     */
    public FreeTime(LocalTime from, LocalTime to) {
        checkArgument(isValidFreeTime(from, to), MESSAGE_CONSTRAINTS);
        this.from = from;
        this.to = to;
    }

    /**
     * Empty FreeTime
     */
    private FreeTime() {
        this.from = null;
        this.to = null;
    }

    /**
     * Returns true if given start and end time is valid.
     */
    public static boolean isValidFreeTime(LocalTime from, LocalTime to) {
        return to.isAfter(from);
    }

    /**
     * Returns string representation of from time.
     *
     * @return From time in HH:mm format.
     */
    public String getFrom() {
        if (from == null) {
            return null;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return from.format(dtf);
    }

    /**
     * Returns string representation of to time.
     *
     * @return To time in HH:mm format.
     */
    public String getTo() {
        if (to == null) {
            return null;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return to.format(dtf);
    }

    @Override
    public String toString() {
        if (this.equals(FreeTime.EMPTY_FREE_TIME)) {
            return " ";
        }
        return String.format("%s-%s", from, to);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (this == EMPTY_FREE_TIME) {
            return false;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.person.FreeTime)) {
            return false;
        }

        seedu.address.model.person.FreeTime otherFreeTime = (seedu.address.model.person.FreeTime) other;

        return from.equals(otherFreeTime.from) && to.equals(otherFreeTime.to);
    }

}

package seedu.address.model.person;

import java.time.DayOfWeek;

import static java.util.Objects.requireNonNull;

/**
 * Represents the day where the Tutee has tuition every week.
 * Guarantees: immutable; is valid as declared in
 */
public class Day {
    public final DayOfWeek value;

    public static final String MESSAGE_CONSTRAINTS =
            "Days should be written using their full names or their first three letters, and it should not be blank";

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day) {
        requireNonNull(day);
        value = parse(day);
    }

    public DayOfWeek parse(String test) {
        String lowerCaseTest = test.toLowerCase();
        switch (lowerCaseTest) {
        case "mon":
        case "monday":
            return DayOfWeek.MONDAY;
        case "tue":
        case "tuesday":
            return DayOfWeek.TUESDAY;
        case "wed":
        case "wednesday":
            return DayOfWeek.WEDNESDAY;
        case "thu":
        case "thursday":
            return DayOfWeek.THURSDAY;
        case "fri":
        case "friday":
            return DayOfWeek.FRIDAY;
        case "sat":
        case "saturday":
            return DayOfWeek.SATURDAY;
        case "sun":
        case "sunday":
            return DayOfWeek.SUNDAY;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

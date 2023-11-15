package seedu.address.model.person.timetable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

/**
 * Represents a block of free time in a day, with a day of the week and start and end times.
 * Guarantees: immutable; is valid as declared in {@link #isValidFreeTime(String)}
 */
public class FreeTime implements Comparable<FreeTime> {

    public static final String MESSAGE_CONSTRAINTS = "Input should be in the format 'DAY HHMM HHMM', \n"
            + "where 'DAY' is a valid day of the week (e.g., Monday, tuesday, WEDNESDAY), \n"
            + "and 'HHMM' represents a valid 24-hour time format in half-hour blocks (e.g., 0000, 1230, 2300). \n"
            + "Day is case-insensitive.";

    public static final String VALIDATION_REGEX = "^(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday) "
            + "([01]?\\d|2[0-3])(00|30) (([01]?\\d|2[0-3])(00|30)|(2400))$";

    public final String freeTimeString;
    private final DayOfWeek day;
    private final HalfHourBlocks timeBlocks;

    /**
     * Constructs a {@code FreeTime}.
     *
     * @param freeTime A valid time.
     */
    public FreeTime(String freeTime) {
        requireNonNull(freeTime);
        checkArgument(isValidFreeTime(freeTime), MESSAGE_CONSTRAINTS);

        String[] parts = freeTime.split(" ");
        this.day = DayOfWeek.valueOf(parts[0].toUpperCase());
        int startBlock = Integer.parseInt(parts[1]) / 100 * 2 + (Integer.parseInt(parts[1]) % 100) / 30;
        int endBlock = Integer.parseInt(parts[2]) / 100 * 2 + (Integer.parseInt(parts[2]) % 100) / 30;
        this.timeBlocks = new HalfHourBlocks(startBlock, endBlock);

        //Capitalize the first letter of the day
        String formattedDay = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        this.freeTimeString = formattedDay + " " + parts[1] + " " + parts[2];
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidFreeTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares this FreeTime instance with another instance.
     * The comparison is primarily based on the day of the week, followed by the start time, and then the end time.
     *
     * @param other The other FreeTime instance to compare against.
     * @return A negative integer, zero, or a positive integer as this FreeTime is less than, equal to, or greater
     *      than the specified FreeTime.
     */
    @Override
    public int compareTo(FreeTime other) {
        if (this.day.compareTo(other.day) != 0) {
            return this.day.compareTo(other.day);
        }
        // You can further refine this if needed
        return this.timeBlocks.toString().compareTo(other.timeBlocks.toString());
    }

    /**
     * Checks if the current FreeTime overlaps with another FreeTime.
     * If there's an overlap, returns a new FreeTime representing the overlapping period.
     *
     * @param other The other FreeTime to check against.
     * @return An Optional containing the overlapping FreeTime if it exists, or an empty Optional otherwise.
     */
    public FreeTime overlap(FreeTime other) {
        if (this.day != other.day) {
            return null;
        }

        if (this.timeBlocks.overlaps(other.timeBlocks)) {
            HalfHourBlocks overlapBlocks = this.timeBlocks.getOverlap(other.timeBlocks);
            // You can convert overlapBlocks back to a string representation if needed
            return new FreeTime(day + " " + overlapBlocks.toString());
        }

        return null;
    }

    /**
     * Checks if the current {@code FreeTime} overlaps with the specified {@code FreeTime}.
     *
     * @param other The other {@code FreeTime} instance to check for overlap.
     * @return {@code true} if the current {@code FreeTime} overlaps with the specified {@code FreeTime},
     *         {@code false} otherwise.
     */
    public boolean isOverlap(FreeTime other) {
        if (this.day != other.day) {
            return false;
        }

        return this.timeBlocks.overlaps(other.timeBlocks);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FreeTime)) {
            return false;
        }

        FreeTime otherFreeTime = (FreeTime) other;
        return freeTimeString.equals(otherFreeTime.freeTimeString);
    }

    @Override
    public int hashCode() {
        return freeTimeString.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + freeTimeString + ']';
    }
}

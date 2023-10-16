package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lessons.Week;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

/**
 * Encaspulates a student's tutoring schedule.
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS = "Schedule should either be empty or follow the following format:\n"
            + "[Day, e.g. Mon] [startTime in HHmm, e.g. 1000] [endTime in HHmm] [Recurrence type, e.g. Weekly]";
    enum RecurringType {
        NONE, WEEKLY, BIWEEKLY, MONTHLY;
        /**
         * Checks if the provided string is one of the Days enums.
         * @param test
         * @return
         */
        public static boolean containsType(String test) {

            for (Schedule.RecurringType c : Schedule.RecurringType.values()) {
                if (c.name().equals(test)) {
                    return true;
                }
            }

            return false;
        }

        /**
         * Finds the correct day by the string given
         * @param name
         * @return
         */
        public static Schedule.RecurringType findByName(String name) {
            Schedule.RecurringType t = null;
            for (Schedule.RecurringType d : values()) {
                if (d.name().equalsIgnoreCase(name)) {
                    t = d;
                }
            }
            return t;
        }
    }

    private RecurringType recurringType = RecurringType.NONE;
    private LocalDate startDate;
    private LocalDate endDate;

    private Week week;

    /**
     * Constructor for a schedule
     * @param schedule
     */
    public Schedule(String schedule) {
        requireNonNull(schedule);
        checkArgument(Schedule.isValidSchedule(schedule));
        // TODO
        // for now, return the default: Mon 1200 - 1400 Weekly
        this.recurringType = RecurringType.WEEKLY;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(12); // change this

        // check the arguments

        this.week = new Week(schedule);
    }

    /**
     * Constructor for an empty schedule
     */
    public Schedule() {

    }

    /**
     * Checks if the provided schedule string is valid
     * Mon 1200 1400 Weekly
     */
    public static boolean isValidSchedule(String schedule) {
        if (schedule.isEmpty()) {
            return true;
        }
        String formatted = schedule.toUpperCase();
        String[] arr = formatted.split(" ");
        // must have 4 length
        if (arr.length != 4) {
            return false;
        }

        // first must be one of enum days
        if (!Week.Days.containsDays(arr[0])) {
            return false;
        }

        // last must be one of enum recurringtype
        if (!Schedule.containsRecurring(arr[3])) {
            return false;
        }

        // TODO: format checking for start time and end time

        return true;

    }



    /**
     * Checks if the provided string is one of the Recurring enums.
     * @param test
     * @return
     */
    private static boolean containsRecurring(String test) {

        for (RecurringType c : RecurringType.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Formats the student's schedule
     * @return
     */
    @Override
    public String toString() {
        return this.recurringType.toString() + " classes on: \n" + week;

    }

    /**
     * Serializes the student's schedule
     * @see #deserialize(String)
     * TODO: make this override something
     */
    public String serialize() {
        if (this.week == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.recurringType.toString()).append(",");
        stringBuilder.append(this.week.serialize());

        return stringBuilder.toString();

    }

    /**
     * Serializes the student's schedule after it's stored.
     * @see #serialize()
     *
     * String format:
     * [Recurring Type],[Comma-separated list of times]
     *     e.g. MON: 1200 - 1400, WED: 1630 - 1700
     * Overall example:
     *     WEEKLY,MON 1200 - 1400,WED: 1630 - 1700
     */
    public static Schedule deserialize(String string) throws IllegalValueException {
        String[] data = string.split(",");
        if (data.length == 0 || data.length == 1) {
            // no schedule yet
            return new Schedule();
        }

        RecurringType recurringType = RecurringType.findByName(data[0]);
        if (recurringType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Schedule.class.getSimpleName()));
        }

        for (int i = 1; i < data.length; i++) {
            // each item is an entry
        }

        return new Schedule(); // TODO
    }
}

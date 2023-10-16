package seedu.address.model.person;

import seedu.address.model.lessons.Week;

import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Encaspulates a student's tutoring schedule.
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS = "Schedule should either be empty or follow the following format:\n"
            + "[Day, e.g. Mon] [startTime in HHmm, e.g. 1000] [endTime in HHmm] [Recurrence type, e.g. Weekly]";
    enum RecurringType {
        NONE, WEEKLY, BIWEEKLY, MONTHLY
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
     * TODO: make this override something
     */
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.recurringType.toString()).append(",");
        stringBuilder.append(this.week.serialize());

        return stringBuilder.toString();

    }
}

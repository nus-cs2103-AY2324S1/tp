package seedu.address.model.person;

import seedu.address.model.lessons.Week;

import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * Encaspulates a student's tutoring schedule.
 */
public class Schedule {
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

        // TODO
        // for now, return the default: Mon 1200 - 1400 Weekly
        this.recurringType = RecurringType.WEEKLY;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(12); // change this
        this.week = new Week();
    }


    /**
     * Converts a String-based 24 hour time format into the number of minutes since 00:00
     * @param time The input string
     * @return the number of minutes
     */
    public int getMinutesFromTime(String time) {
        String [] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Formats the student's schedule
     * @return
     */
    @Override
    public String toString() {
        return "Weekly classes on: \n" + week;

    }
}

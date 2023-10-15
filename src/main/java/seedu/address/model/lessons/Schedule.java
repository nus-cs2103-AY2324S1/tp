package seedu.address.model.lessons;

import java.time.LocalDate;

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
}

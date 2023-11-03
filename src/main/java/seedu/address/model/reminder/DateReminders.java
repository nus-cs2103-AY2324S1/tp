package seedu.address.model.reminder;

import java.time.LocalDate;

import javafx.collections.ObservableList;

/**
 * Contains a date and its respective reminderList
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public final class DateReminders {
    private LocalDate date;
    private ObservableList<Reminder> reminderList;

    /**
     * Contains a date and its respective reminderList
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public DateReminders(LocalDate date, ObservableList<Reminder> reminderList) {
        this.date = date;
        this.reminderList = reminderList;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public ObservableList<Reminder> getReminderList() {
        return this.reminderList;
    }
}

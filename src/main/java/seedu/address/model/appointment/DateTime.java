package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the date and time of an appointment.
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Constructs a DateTime object with the specified date and time.
     *
     * @param dateTime The date and time of the appointment.
     */
    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retrieves the date and time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

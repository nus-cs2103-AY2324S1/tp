package seedu.address.model.appointment;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;

public class  DateTime {
    LocalDateTime dateTime;

    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
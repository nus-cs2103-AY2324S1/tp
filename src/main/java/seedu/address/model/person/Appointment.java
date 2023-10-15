package seedu.address.model.person;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {
    public final LocalDateTime date;
    public final String value;

    public Appointment(LocalDateTime date, String value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + date.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return value.equals(otherAppointment.value)
                && date.equals(otherAppointment.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value, date);
    }
}

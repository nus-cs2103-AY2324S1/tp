package seedu.address.model.appointment;

import java.time.LocalDate;

/**
 * An empty Appointment Object to represent Appointments that have not been made.
 */
public class NullAppointment extends ScheduleItem implements Comparable<ScheduleItem> {
    public static final String MESSAGE_NULL_APT = "No Appointment made!";
    private static final NullAppointment nullappointment = new NullAppointment();
    private final String value;
    private NullAppointment() {
        this.value = MESSAGE_NULL_APT;
    }
    public static NullAppointment getNullAppointment() {
        return nullappointment;
    }
    @Override
    public String toString() {
        return MESSAGE_NULL_APT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // Check if other is the same NullAppointment
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public int compareTo(ScheduleItem appointment) {
        if (appointment == this) {
            return 0;
        } else {
            return 1; //null appointment returns >0 so it will be sorted further down the list
        }
    }

    @Override
    public boolean isSameDate(LocalDate date) {
        return false;
    }
}

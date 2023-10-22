package seedu.address.model.appointment;

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
    public static NullAppointment getNullappointment() {
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
        return 1;
//        if (appointment instanceof Appointment) {
//            return 1;
//        }
//
//        return 0;
    }
}

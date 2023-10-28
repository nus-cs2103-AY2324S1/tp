package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WellNus;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALEX_APPOINTMENT = new AppointmentBuilder().withName("Alex Yeoh")
            .withDate("2023-10-31").withStartTime("12:00").withEndTime("13:00")
            .withDescription("First Session").build();
    public static final Appointment ALEX_SECOND_APPOINTMENT = new AppointmentBuilder().withName("Alex Yeoh")
            .withDate("2023-11-16").withStartTime("14:00").withEndTime("15:00")
            .withDescription("Second Session").build();
    public static final Appointment BERNICE_APPOINTMENT = new AppointmentBuilder().withName("Bernice Yu")
            .withDate("2023-11-16").withStartTime("16:00").withEndTime("17:00")
            .withDescription("First Session").build();

    /**
     * Returns an {@code AppointmentBook} with all the typical appointments.
     */
    public static WellNus getTypicalAppointmentBook() {
        WellNus ab = new WellNus();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALEX_APPOINTMENT, ALEX_SECOND_APPOINTMENT, BERNICE_APPOINTMENT));
    }
}

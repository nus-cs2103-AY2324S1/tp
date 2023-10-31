package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALICE_APPOINTMENT = new AppointmentBuilder().withName("Alice Pauline")
            .withDate("2023-10-31").withStartTime("12:00").withEndTime("13:00")
            .withDescription("First Session").build();
    public static final Appointment ALICE_SECOND_APPOINTMENT = new AppointmentBuilder().withName("Alice Pauline")
            .withDate("2023-11-16").withStartTime("14:00").withEndTime("15:00")
            .withDescription("Second Session").build();
    public static final Appointment BERNICE_APPOINTMENT = new AppointmentBuilder().withName("Benson Yu")
            .withDate("2023-11-16").withStartTime("16:00").withEndTime("17:00")
            .withDescription("First Session").build();


    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APPOINTMENT, ALICE_SECOND_APPOINTMENT, BERNICE_APPOINTMENT));
    }
}

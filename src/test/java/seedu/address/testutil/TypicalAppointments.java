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
    public static final Appointment BENNY_APPOINTMENT = new AppointmentBuilder().withName("Benny Dover")
            .withDate("2023-11-16").withStartTime("16:00").withEndTime("17:00")
            .withDescription("First Session").build();
    public static final Appointment JOHN_APPOINTMENT = new AppointmentBuilder().withName("John Tan")
            .withDate("2023-10-31").withStartTime("13:00").withEndTime("14:00")
            .withDescription("Second Session").build();
    public static final Appointment DAVID_APPOINTMENT = new AppointmentBuilder().withName("David Kim")
            .withDate("2023-10-31").withStartTime("11:00").withEndTime("12:00")
            .withDescription("Second Session").build();

    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().withName("Alice")
            .withDate("2023-11-01").withStartTime("09:00").withEndTime("10:00").build();
    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withName("Bob")
            .withDate("2023-11-02").withStartTime("10:00").withEndTime("11:00").build();
    public static final Appointment APPOINTMENT_3 = new AppointmentBuilder().withName("Charlie")
            .withDate("2023-11-03").withStartTime("09:00").withEndTime("10:00").build();
    public static final Appointment APPOINTMENT_4 = new AppointmentBuilder()
            .withName("David").withDate("2023-11-03").withStartTime("10:00").withEndTime("11:00").build();
    public static final Appointment APPOINTMENT_5 = new AppointmentBuilder().withName("Eve")
            .withDate("2023-11-04").withStartTime("09:00").withEndTime("10:00").build();


    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APPOINTMENT, ALICE_SECOND_APPOINTMENT, BENNY_APPOINTMENT));
    }
}

package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALEX = new AppointmentBuilder().withName("Alex Yeoh")
            .withDate("2023-10-31").withStartTime("12:00").withEndTime("13:00")
            .withDescription("First Session").build();
    public static final Appointment ALEX_SECOND = new AppointmentBuilder().withName("Alex Yeoh")
            .withDate("2023-11-16").withStartTime("14:00").withEndTime("15:00")
            .withDescription("Second Session").build();
    public static final Appointment BERNICE = new AppointmentBuilder().withName("Bernice Yu")
            .withDate("2023-12-10").withStartTime("16:00").withEndTime("17:00")
            .withDescription("First Session").build();

    private TypicalAppointments() {} // prevents instantiation
}

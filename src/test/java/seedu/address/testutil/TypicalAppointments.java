package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALEX = new AppointmentBuilder().withName("Alex Yeoh")
            .withDateTime("2023-10-31 16:00:00").withDescription("First Session").build();
    public static final Appointment ALEX_SECOND = new AppointmentBuilder().withName("Alex Yeoh")
            .withDateTime("2023-11-16 12:00:00").withDescription("Second Session").build();
    public static final Appointment BERNICE = new AppointmentBuilder().withName("Bernice Yu")
            .withDateTime("2023-12-10 13:00:00").withDescription("First Session").build();

    private TypicalAppointments() {} // prevents instantiation
}

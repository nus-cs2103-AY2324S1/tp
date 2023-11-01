package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHERYL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Ic;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalAppointment {

    public static final Appointment APPOINTMENT_1 = new AppointmentBuilder().build();

    public static final Appointment APPOINTMENT_2 = new AppointmentBuilder().withDoctorIc(new Ic(VALID_NRIC_CHERYL))
            .withAppointmentTime(VALID_DATE_2).build();

    private TypicalAppointment() {
    } // prevents instantiation

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2));
    }
}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;

/**
 * A utility class containing a list of {@code Appointment} objects to be used
 * in tests.
 */
public class TypicalAppointments {

    public static final Appointment EYE_EXAM = new AppointmentBuilder().withName("Eye Exam")
            .withDateTime("01-01-2001 1200").withNric("S1234567B").build();
    public static final Appointment VACCINATION = new AppointmentBuilder().withName("Vaccination")
            .withDateTime("11-09-2001 1200").withNric("S1234567A").build();
    public static final Appointment COLONOSCOPY = new AppointmentBuilder().withName("Colonoscopy")
            .withDateTime("21-12-2001 1200").withNric("T1234567A").build();

    // Manually added
    public static final Appointment BIOPSY = new AppointmentBuilder().withName("Biopsy")
            .withDateTime("01-01-2001 1200").withNric("S0000002A").build();
    public static final Appointment STD_TEST = new AppointmentBuilder().withName("STD Test")
            .withDateTime("17-08-1964 1900").withNric("S0000003A").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment SLEEP_STUDY = new AppointmentBuilder().withName(VALID_NAME_SLEEP_STUDY)
            .withDateTime(VALID_DATETIME_SLEEP_STUDY).withNric(VALID_NRIC_AMY).build();
    public static final Appointment THYROID_CHECK = new AppointmentBuilder().withName(VALID_NAME_THYROID_CHECK)
            .withDateTime(VALID_DATETIME_THYROID_CHECK).withNric(VALID_NRIC_BOB).build();

    private TypicalAppointments() {
    } // prevents instantiation

    public static UniqueAppointmentList getTypicalAppointments() {
        UniqueAppointmentList ab = new UniqueAppointmentList();
        ab.add(EYE_EXAM);
        ab.add(VACCINATION);
        ab.add(COLONOSCOPY);
        return ab;
    }
}

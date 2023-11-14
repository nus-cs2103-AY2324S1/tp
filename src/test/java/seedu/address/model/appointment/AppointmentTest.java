package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SLEEP_STUDY;
import static seedu.address.testutil.TypicalAppointments.EYE_EXAM;
import static seedu.address.testutil.TypicalAppointments.SLEEP_STUDY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(EYE_EXAM.equals(EYE_EXAM));

        // null -> returns false
        assertFalse(EYE_EXAM.equals(null));

        // different name, same date -> returns false
        Appointment editedEyeExam = new AppointmentBuilder(EYE_EXAM).withName(VALID_NAME_SLEEP_STUDY).build();
        assertFalse(EYE_EXAM.equals(editedEyeExam));

        // name differs in case, same date -> returns false
        Appointment editedSleepStudy = new AppointmentBuilder(SLEEP_STUDY).withName(VALID_NAME_SLEEP_STUDY
                .toLowerCase()).build();
        assertFalse(SLEEP_STUDY.equals(editedSleepStudy));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_SLEEP_STUDY + " ";
        editedSleepStudy = new AppointmentBuilder(SLEEP_STUDY).withName(nameWithTrailingSpaces).build();
        assertFalse(SLEEP_STUDY.equals(editedSleepStudy));

        // different date, same name -> returns false
        editedSleepStudy = new AppointmentBuilder(SLEEP_STUDY).withDateTime("01-01-2001 1200").build();
        assertFalse(SLEEP_STUDY.equals(editedSleepStudy));

        // same values -> returns true
        Appointment eyeExamCopy = new AppointmentBuilder(EYE_EXAM).build();
        assertTrue(EYE_EXAM.equals(eyeExamCopy));

        // different type -> returns false
        assertFalse(EYE_EXAM.equals(5));

        // different Appointment -> returns false
        assertFalse(EYE_EXAM.equals(SLEEP_STUDY));
    }

    @Test
    public void toStringMethod() {
        String expected = Appointment.class.getCanonicalName() + "{name=" + EYE_EXAM.getName()
                + ", dateTime=" + EYE_EXAM.getDateTime() + ", nric=" + EYE_EXAM.getNric()
                + "}";
        assertEquals(expected, EYE_EXAM.toString());
    }
}

package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.CHERYL;
import static seedu.address.testutil.TypicalDoctor.DEREK;
import static seedu.address.testutil.TypicalPatient.AMY;
import static seedu.address.testutil.TypicalPatient.BOB;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
    }

    @Test
    public void constructor_invalidDoctor_throwsIllegalArgumentException() {
        Doctor invalidDoctor = null;
        Patient validPatient = AMY;
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00");
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidDoctor, validPatient,
                validAppointmentTime));
    }
    @Test
    public void constructor_invalidPatient_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient invalidPatient = null;
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00");
        assertThrows(IllegalArgumentException.class, () -> new Appointment(validDoctor, invalidPatient,
                validAppointmentTime));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient validPatient = AMY;
        LocalDateTime invalidAppointmentTime = null;
        assertThrows(IllegalArgumentException.class, () -> new Appointment(validDoctor, validPatient,
                invalidAppointmentTime));
    }

    @Test
    public void testGetDoctor() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getDoctor(), DEREK);
    }

    @Test
    public void testGetPatient() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getPatient(), AMY);
    }

    @Test
    public void testGetAppointmentTime() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getAppointmentTime(), appointmentTime);
    }
    @Test
    public void testChangeDoctor() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changeDoctor(CHERYL);
        assertEquals(newAppointment.getDoctor(), CHERYL);
    }

    @Test
    public void testChangePatient() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changePatient(BOB);
        assertEquals(newAppointment.getPatient(), BOB);
    }
    @Test
    public void testSetAppointmentTime() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        LocalDateTime newAppointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.setAppointmentTime(newAppointmentTime);
        assertEquals(newAppointment.getAppointmentTime(), newAppointmentTime);
    }

    @Test
    public void testChangeStatus() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        String newStatus = "Completed";
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changeStatus(newStatus);
        assertEquals(newAppointment.getStatus(), newStatus);
    }

    @Test
    public void equals() {
        LocalDateTime appointmentTime = LocalDateTime.parse("2022/02/14 13:30:00");
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);

        // same values -> returns true
        assertTrue(newAppointment.equals(new Appointment(DEREK, AMY, appointmentTime)));

        // same object -> returns true
        assertTrue(newAppointment.equals(newAppointment));

        // null -> returns false
        assertFalse(newAppointment.equals(null));

        // different types -> returns false
        assertFalse(newAppointment.equals(5.0f));

        // different values -> returns false
        assertFalse(newAppointment.equals(new Appointment(CHERYL, AMY, appointmentTime)));
    }
}

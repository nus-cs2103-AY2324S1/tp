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
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        assertThrows(NullPointerException.class, () -> new Appointment(invalidDoctor, validPatient,
                validAppointmentTime));
    }
    @Test
    public void constructor_invalidPatient_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient invalidPatient = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        assertThrows(NullPointerException.class, () -> new Appointment(validDoctor, invalidPatient,
                validAppointmentTime));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient validPatient = AMY;
        LocalDateTime invalidAppointmentTime = null;
        assertThrows(NullPointerException.class, () -> new Appointment(validDoctor, validPatient,
                invalidAppointmentTime));
    }

    @Test
    public void secondConstructor_invalidDoctor_throwsIllegalArgumentException() {
        Doctor invalidDoctor = null;
        Patient validPatient = AMY;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        assertThrows(NullPointerException.class, () -> new Appointment(invalidDoctor, validPatient,
                validAppointmentTime, "Follow-Up"));
    }
    @Test
    public void secondConstructorr_invalidPatient_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient invalidPatient = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime validAppointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        assertThrows(NullPointerException.class, () -> new Appointment(validDoctor, invalidPatient,
                validAppointmentTime, "Follow-Up"));
    }

    @Test
    public void secondConstructor_invalidAppointmentTime_throwsIllegalArgumentException() {
        Doctor validDoctor = DEREK;
        Patient validPatient = AMY;
        LocalDateTime invalidAppointmentTime = null;
        assertThrows(NullPointerException.class, () -> new Appointment(validDoctor, validPatient,
                invalidAppointmentTime, "Follow-Up"));
    }
    @Test
    public void constructor_nullStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        assertThrows(NullPointerException.class, () -> new Appointment(DEREK, AMY, appointmentTime, null));
    }

    @Test
    public void testGetDoctor() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getDoctor(), DEREK);
    }

    @Test
    public void testGetPatient() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getPatient(), AMY);
    }

    @Test
    public void testGetAppointmentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        assertEquals(newAppointment.getAppointmentTime(), appointmentTime);
    }
    @Test
    public void testChangeDoctor() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changeDoctor(CHERYL);
        assertEquals(newAppointment.getDoctor(), CHERYL);
    }

    @Test
    public void testChangePatient() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changePatient(BOB);
        assertEquals(newAppointment.getPatient(), BOB);
    }
    @Test
    public void testSetAppointmentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        LocalDateTime newAppointmentTime = LocalDateTime.parse("2022-02-14 14:30:00", formatter);
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.setAppointmentTime(newAppointmentTime);
        assertEquals(newAppointment.getAppointmentTime(), newAppointmentTime);
    }

    @Test
    public void testChangeStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
        String newStatus = "Completed";
        Appointment newAppointment = new Appointment(DEREK, AMY, appointmentTime);
        newAppointment.changeStatus(newStatus);
        assertEquals(newAppointment.getStatus(), newStatus);
    }

    @Test
    public void equals() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime appointmentTime = LocalDateTime.parse("2022-02-14 13:30:00", formatter);
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

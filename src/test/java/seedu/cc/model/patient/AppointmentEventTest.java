package seedu.cc.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.cc.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

class AppointmentEventTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentEvent(null, new Time("12:00")));
        assertThrows(NullPointerException.class, () -> new AppointmentEvent(new Date("2023-12-01"), null));

    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        String invalidTime = "25:00";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEvent(new Date("2023-12-01"),
                new Time(invalidTime)));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentEvent(new Date(invalidDate),
                new Time("12:00")));

    }

    @Test
    void testEquals() {
        AppointmentEvent appointmentEvent = new AppointmentEvent(new Date("2023-10-01"), new Time("12:00"));

        // same values -> returns true
        assertEquals(appointmentEvent, new AppointmentEvent(new Date("2023-10-01"), new Time("12:00")));

        // same object -> returns true
        assertEquals(appointmentEvent, appointmentEvent);

        // null -> returns false
        assertNotEquals(null, appointmentEvent);

        // different values -> returns false
        assertNotEquals(appointmentEvent, new AppointmentEvent(new Date("2023-10-02"), new Time("12:00")));
        assertNotEquals(appointmentEvent, new AppointmentEvent(new Date("2023-10-01"), new Time("13:00")));
    }
}

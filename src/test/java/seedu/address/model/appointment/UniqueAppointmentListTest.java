package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALEX;
import static seedu.address.testutil.TypicalAppointments.ALEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(ALEX));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(ALEX);
        assertTrue(uniqueAppointmentList.contains(ALEX));
    }

    @Test
    public void contains_appointmentWithSameFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(ALEX);
        Appointment editedAlex = new AppointmentBuilder().withName("Alex Yeoh")
                .withDate("2023-10-31").withStartTime("12:00").withEndTime("13:00")
                .withDescription("First Session").build();
        assertTrue(uniqueAppointmentList.contains(editedAlex));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(ALEX);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(ALEX));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(ALEX));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(ALEX);
        uniqueAppointmentList.remove(ALEX);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void removeRelatedAppointments_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(ALEX);
        uniqueAppointmentList.removeRelatedAppointments(ALEX.getName());
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void removeRelatedAppointments_existingAppointment_removesAllAppointmentsWithSameName() {
        uniqueAppointmentList.add(ALEX);
        uniqueAppointmentList.add(ALEX_SECOND);
        uniqueAppointmentList.removeRelatedAppointments(ALEX.getName());
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList().toString(),
                uniqueAppointmentList.toString());
    }
}

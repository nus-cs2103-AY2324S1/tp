package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALICE_SECOND_APPOINTMENT;

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
        assertFalse(uniqueAppointmentList.contains(ALICE_APPOINTMENT));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        assertTrue(uniqueAppointmentList.contains(ALICE_APPOINTMENT));
    }

    @Test
    public void contains_appointmentWithSameFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        Appointment editedAlex = new AppointmentBuilder().withName("Alice Pauline")
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
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(ALICE_APPOINTMENT));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(ALICE_APPOINTMENT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        uniqueAppointmentList.remove(ALICE_APPOINTMENT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void removeRelatedAppointments_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        uniqueAppointmentList.removeRelatedAppointments(ALICE_APPOINTMENT.getName());
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void removeRelatedAppointments_existingAppointment_removesAllAppointmentsWithSameName() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        uniqueAppointmentList.add(ALICE_SECOND_APPOINTMENT);
        uniqueAppointmentList.removeRelatedAppointments(ALICE_APPOINTMENT.getName());
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

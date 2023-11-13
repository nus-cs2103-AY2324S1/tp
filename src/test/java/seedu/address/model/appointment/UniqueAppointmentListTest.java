package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALICE_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_5;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    // Test for contains method

    // null object
    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.hasAppointment(null));
    }

    // appointment not in list
    @Test
    public void hasAppointment_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.hasAppointment(ALICE_APPOINTMENT));
    }

    @Test
    public void hasAppointment_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        assertTrue(uniqueAppointmentList.hasAppointment(ALICE_APPOINTMENT));
    }

    @Test
    public void hasAppointment_appointmentWithSameFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        Appointment editedAlex = new AppointmentBuilder().withName("Alice Pauline")
                .withDate("2023-10-31").withStartTime("12:00").withEndTime("13:00")
                .withDescription("First Session").build();
        assertTrue(uniqueAppointmentList.hasAppointment(editedAlex));
    }

    // Tests for add method

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(ALICE_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(ALICE_APPOINTMENT));
    }

    // Tests for remove method

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

    // Tests for removeRelatedAppointments method
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

    // UnmodifiableObservableList cannot be modified
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

    @Test
    public void sortAppointmentsAfterAddition() {
        // APPOINTMENT_1 on "2023-11-01" from "09:00" to "10:00"
        // APPOINTMENT_2 on "2023-11-02" from "10:00" to "11:00"
        // APPOINTMENT_3 on "2023-11-03" from "09:00" to "10:00"
        // APPOINTMENT_4 on "2023-11-03" from "10:00" to "11:00"
        // APPOINTMENT_5 on "2023-11-04" from "09:00" to "10:00"

        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

        uniqueAppointmentList.add(APPOINTMENT_5);
        uniqueAppointmentList.add(APPOINTMENT_4);
        uniqueAppointmentList.add(APPOINTMENT_3);
        uniqueAppointmentList.add(APPOINTMENT_2);
        uniqueAppointmentList.add(APPOINTMENT_1);

        Iterator<Appointment> iterator = uniqueAppointmentList.iterator();

        // Check if appointments are sorted correctly after addition
        assertEquals(APPOINTMENT_1, iterator.next());
        assertEquals(APPOINTMENT_2, iterator.next());
        assertEquals(APPOINTMENT_3, iterator.next());
        assertEquals(APPOINTMENT_4, iterator.next());
        assertEquals(APPOINTMENT_5, iterator.next());
    }

    @Test
    public void sortAppointmentsAfterDeletion() {
        UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

        uniqueAppointmentList.add(APPOINTMENT_5);
        uniqueAppointmentList.add(APPOINTMENT_4);
        uniqueAppointmentList.add(APPOINTMENT_3);
        uniqueAppointmentList.add(APPOINTMENT_2);
        uniqueAppointmentList.add(APPOINTMENT_1);

        uniqueAppointmentList.remove(APPOINTMENT_3);

        Iterator<Appointment> iterator = uniqueAppointmentList.iterator();

        // Check if appointments are sorted correctly after deletion
        assertEquals(APPOINTMENT_1, iterator.next());
        assertEquals(APPOINTMENT_2, iterator.next());
        assertEquals(APPOINTMENT_4, iterator.next());
        assertEquals(APPOINTMENT_5, iterator.next());

        // Ensure that the removed appointment is no longer in the list
        assertFalse(uniqueAppointmentList.hasAppointment(APPOINTMENT_3));
    }
}

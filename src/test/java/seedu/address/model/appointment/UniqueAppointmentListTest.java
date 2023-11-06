package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(APPOINTMENT1));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(APPOINTMENT1);
        assertTrue(uniqueAppointmentList.contains(APPOINTMENT1));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT1);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(APPOINTMENT1));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, APPOINTMENT1));
    }

    @Test
    public void setAppointment_nullAppointmentPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(APPOINTMENT1, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () ->
                uniqueAppointmentList.setAppointment(APPOINTMENT1, APPOINTMENT1));
    }

    @Test
    public void setAppointment_editedAppointmentIsSamePerson_success() {
        uniqueAppointmentList.add(APPOINTMENT1);
        uniqueAppointmentList.setAppointment(APPOINTMENT1, APPOINTMENT1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(APPOINTMENT1);
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT1)
                .withDescription("Another appointment")
                .build();
        uniqueAppointmentList.setAppointment(APPOINTMENT1, editedAppointment);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedAppointment);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(APPOINTMENT1);
        uniqueAppointmentList.setAppointment(APPOINTMENT1, APPOINTMENT2);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT2);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT1);
        uniqueAppointmentList.add(APPOINTMENT2);
        assertThrows(DuplicateAppointmentException.class, ()
                -> uniqueAppointmentList.setAppointment(APPOINTMENT1, APPOINTMENT2));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove((Appointment) null));
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove((Person) null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(APPOINTMENT1));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(APPOINTMENT1);
        uniqueAppointmentList.remove(APPOINTMENT1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(APPOINTMENT1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT2);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(APPOINTMENT1);
        List<Appointment> appointmentList = Collections.singletonList(APPOINTMENT2);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT2);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPOINTMENT1, APPOINTMENT1);
        assertThrows(DuplicateAppointmentException.class, ()
                -> uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList().toString(), uniqueAppointmentList.toString());
    }
}

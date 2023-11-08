package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointment.APPOINTMENT_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.exceptions.DuplicateObjectException;
import seedu.address.model.person.exceptions.ObjectNotFoundException;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        assertTrue(uniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        assertThrows(DuplicateObjectException.class, () -> uniqueAppointmentList.add(APPOINTMENT_1));
    }

    @Test
    public void setObject_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setObject(null, APPOINTMENT_2));
    }

    @Test
    public void setObject_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setObject(APPOINTMENT_1,
                null));
    }

    @Test
    public void setObject_targetAppointmentNotInList_throwsObjectNotFoundException() {
        assertThrows(ObjectNotFoundException.class, () -> uniqueAppointmentList.setObject(APPOINTMENT_1,
                APPOINTMENT_1));
    }

    @Test
    public void setObject_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.setObject(APPOINTMENT_1, APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsObjectNotFoundException() {
        assertThrows(ObjectNotFoundException.class, () -> uniqueAppointmentList.remove(APPOINTMENT_1));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        uniqueAppointmentList.remove(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_2);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setObjects_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setObjects((List<Appointment>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(APPOINTMENT_1);
        List<Appointment> appointmentList = Collections.singletonList(APPOINTMENT_1);
        uniqueAppointmentList.setObjects(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicateObjectException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPOINTMENT_1, APPOINTMENT_1);
        assertThrows(DuplicateObjectException.class, () ->
                uniqueAppointmentList.setObjects(listWithDuplicateAppointments));
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


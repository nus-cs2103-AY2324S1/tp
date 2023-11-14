package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.EYE_EXAM;
import static seedu.address.testutil.TypicalAppointments.SLEEP_STUDY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(EYE_EXAM));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(EYE_EXAM);
        assertTrue(uniqueAppointmentList.contains(EYE_EXAM));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateappointmentException() {
        uniqueAppointmentList.add(EYE_EXAM);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(EYE_EXAM));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, EYE_EXAM));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(EYE_EXAM, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsappointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList
                .setAppointment(EYE_EXAM, EYE_EXAM));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameappointment_success() {
        uniqueAppointmentList.add(EYE_EXAM);
        uniqueAppointmentList.setAppointment(EYE_EXAM, EYE_EXAM);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(EYE_EXAM);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }


    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateappointmentException() {
        uniqueAppointmentList.add(EYE_EXAM);
        uniqueAppointmentList.add(SLEEP_STUDY);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointment(EYE_EXAM, SLEEP_STUDY));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsappointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(EYE_EXAM));
    }

    @Test
    public void remove_existingappointment_removesappointment() {
        uniqueAppointmentList.add(EYE_EXAM);
        uniqueAppointmentList.remove(EYE_EXAM);
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
        uniqueAppointmentList.add(EYE_EXAM);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(SLEEP_STUDY);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setappointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(EYE_EXAM);
        List<Appointment> appointmentList = Collections.singletonList(SLEEP_STUDY);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(SLEEP_STUDY);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setappointments_listWithDuplicateappointments_throwsDuplicateappointmentException() {
        List<Appointment> listWithDuplicateappointments = Arrays.asList(EYE_EXAM, EYE_EXAM);
        assertThrows(DuplicateAppointmentException.class, () ->
                uniqueAppointmentList.setAppointments(listWithDuplicateappointments));
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

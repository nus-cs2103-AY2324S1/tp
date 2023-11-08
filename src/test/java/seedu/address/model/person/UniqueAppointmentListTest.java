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

    private final UniqueAppointmentList UniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(UniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        assertTrue(UniqueAppointmentList.contains(APPOINTMENT_1));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicateAppointmentException() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        assertThrows(DuplicateObjectException.class, () -> UniqueAppointmentList.add(APPOINTMENT_1));
    }

    @Test
    public void setObject_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.setObject(null, APPOINTMENT_2));
    }

    @Test
    public void setObject_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.setObject(APPOINTMENT_1,
                null));
    }

    @Test
    public void setObject_targetPersonNotInList_throwsObjectNotFoundException() {
        assertThrows(ObjectNotFoundException.class, () -> UniqueAppointmentList.setObject(APPOINTMENT_1,
                APPOINTMENT_1));
    }

    @Test
    public void setObject_editedPersonIsSamePerson_success() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        UniqueAppointmentList.setObject(APPOINTMENT_1, APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_1);
        assertEquals(expectedUniqueAppointmentList, UniqueAppointmentList);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsObjectNotFoundException() {
        assertThrows(ObjectNotFoundException.class, () -> UniqueAppointmentList.remove(APPOINTMENT_1));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        UniqueAppointmentList.remove(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, UniqueAppointmentList);
    }

    @Test
    public void setPersons_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setObjects_UniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_2);
        UniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, UniqueAppointmentList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueAppointmentList.setObjects((List<Appointment>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        UniqueAppointmentList.add(APPOINTMENT_1);
        List<Appointment> AppointmentList = Collections.singletonList(APPOINTMENT_1);
        UniqueAppointmentList.setObjects(AppointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(APPOINTMENT_1);
        assertEquals(expectedUniqueAppointmentList, UniqueAppointmentList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicateObjectException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(APPOINTMENT_1, APPOINTMENT_1);
        assertThrows(DuplicateObjectException.class, () -> UniqueAppointmentList.setObjects(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> UniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(UniqueAppointmentList.asUnmodifiableObservableList().toString(), UniqueAppointmentList.toString());
    }
}


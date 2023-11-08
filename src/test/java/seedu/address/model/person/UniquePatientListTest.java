package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatient.AMY;
import static seedu.address.testutil.TypicalPatient.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePatientList UniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(UniquePatientList.contains(AMY));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        UniquePatientList.add(AMY);
        assertTrue(UniquePatientList.contains(AMY));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        UniquePatientList.add(AMY);
        Patient editedAMY = new PatientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_MEDIUM).build();
        assertTrue(UniquePatientList.contains(editedAMY));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        UniquePatientList.add(AMY);
        assertThrows(DuplicatePersonException.class, () -> UniquePatientList.add(AMY));
    }

    @Test
    public void setObject_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.setObject(null, AMY));
    }

    @Test
    public void setObject_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.setObject(AMY, null));
    }

    @Test
    public void setObject_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> UniquePatientList.setObject(AMY, AMY));
    }

    @Test
    public void setObject_editedPersonIsSamePerson_success() {
        UniquePatientList.add(AMY);
        UniquePatientList.setObject(AMY, AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(AMY);
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasSameIdentity_success() {
        UniquePatientList.add(AMY);
        Patient editedAMY = new PatientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_MEDIUM)
                .build();
        UniquePatientList.setObject(AMY, editedAMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAMY);
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasDifferentIdentity_success() {
        UniquePatientList.add(AMY);
        UniquePatientList.setObject(AMY, BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        UniquePatientList.add(AMY);
        UniquePatientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> UniquePatientList.setObject(AMY, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> UniquePatientList.remove(AMY));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        UniquePatientList.add(AMY);
        UniquePatientList.remove(AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.setPersons((UniquePatientList) null));
    }

    @Test
    public void setPersons_UniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        UniquePatientList.add(AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        UniquePatientList.setPersons(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniquePatientList.setObjects((List<Patient>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        UniquePatientList.add(AMY);
        List<Patient> patientList = Collections.singletonList(BOB);
        UniquePatientList.setObjects(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, UniquePatientList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(AMY, AMY);
        assertThrows(DuplicatePersonException.class, () -> UniquePatientList.setObjects(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> UniquePatientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(UniquePatientList.asUnmodifiableObservableList().toString(), UniquePatientList.toString());
    }
}

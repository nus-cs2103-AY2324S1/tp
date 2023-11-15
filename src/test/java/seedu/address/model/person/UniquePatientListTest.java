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

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(AMY));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePatientList.add(AMY);
        assertTrue(uniquePatientList.contains(AMY));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(AMY);
        Patient editedAmy = new PatientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_MEDIUM).build();
        assertTrue(uniquePatientList.contains(editedAmy));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePatientList.add(AMY);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.add(AMY));
    }

    @Test
    public void setObject_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setObject(null, AMY));
    }

    @Test
    public void setObject_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setObject(AMY, null));
    }

    @Test
    public void setObject_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.setObject(AMY, AMY));
    }

    @Test
    public void setObject_editedPersonIsSamePerson_success() {
        uniquePatientList.add(AMY);
        uniquePatientList.setObject(AMY, AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(AMY);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasSameIdentity_success() {
        uniquePatientList.add(AMY);
        Patient editedAmy = new PatientBuilder(AMY).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_MEDIUM)
                .build();
        uniquePatientList.setObject(AMY, editedAmy);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAmy);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasDifferentIdentity_success() {
        uniquePatientList.add(AMY);
        uniquePatientList.setObject(AMY, BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setObject_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(AMY);
        uniquePatientList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setObject(AMY, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePatientList.remove(AMY));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePatientList.add(AMY);
        uniquePatientList.remove(AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullUniquePatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPersons((UniquePatientList) null));
    }

    @Test
    public void setPersons_uniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        uniquePatientList.add(AMY);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        uniquePatientList.setPersons(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setObjects((List<Patient>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(AMY);
        List<Patient> patientList = Collections.singletonList(BOB);
        uniquePatientList.setObjects(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(AMY, AMY);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setObjects(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePatientList.asUnmodifiableObservableList().toString(), uniquePatientList.toString());
    }
}

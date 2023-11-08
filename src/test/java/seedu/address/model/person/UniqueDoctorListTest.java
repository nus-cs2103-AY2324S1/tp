package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DEREK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARDIOLOGIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalDoctor.BOYD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.DoctorBuilder;

public class UniqueDoctorListTest {

    private final UniqueDoctorList uniqueDoctorList = new UniqueDoctorList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueDoctorList.add(ALICE);
        assertTrue(uniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDoctorList.add(ALICE);
        Doctor editedAlice = new DoctorBuilder(ALICE).withAddress(VALID_ADDRESS_DEREK).withTags(VALID_TAG_CARDIOLOGIST)
                .build();
        assertTrue(uniqueDoctorList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueDoctorList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.add(ALICE));
    }

    @Test
    public void setObject_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setObject(null, ALICE));
    }

    @Test
    public void setObject_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setObject(ALICE, null));
    }

    @Test
    public void setObject_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.setObject(ALICE, ALICE));
    }

    @Test
    public void setObject_editedPersonIsSamePerson_success() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.setObject(ALICE, ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(ALICE);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasSameIdentity_success() {
        uniqueDoctorList.add(ALICE);
        Doctor editedAlice = new DoctorBuilder(ALICE).withAddress(VALID_ADDRESS_DEREK).withTags(VALID_TAG_MEDIUM)
                .build();
        uniqueDoctorList.setObject(ALICE, editedAlice);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(editedAlice);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasDifferentIdentity_success() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.setObject(ALICE, BOYD);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.add(BOYD);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setObject(ALICE, BOYD));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDoctorList.add(ALICE);
        uniqueDoctorList.remove(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPersons_nullUniqueDoctorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctors((UniqueDoctorList) null));
    }

    @Test
    public void setPersons_uniqueDoctorList_replacesOwnListWithProvidedUniqueDoctorList() {
        uniqueDoctorList.add(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        uniqueDoctorList.setDoctors(expectedUniqueDoctorList);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setObjects((List<Doctor>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        uniqueDoctorList.add(ALICE);
        List<Doctor> doctorList = Collections.singletonList(BOYD);
        uniqueDoctorList.setObjects(doctorList);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Doctor> listWithDuplicateDoctors = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setObjects(listWithDuplicateDoctors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDoctorList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueDoctorList.asUnmodifiableObservableList().toString(), uniqueDoctorList.toString());
    }
}

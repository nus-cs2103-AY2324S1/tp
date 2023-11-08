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

    private final UniqueDoctorList UniqueDoctorList = new UniqueDoctorList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(UniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        UniqueDoctorList.add(ALICE);
        assertTrue(UniqueDoctorList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        UniqueDoctorList.add(ALICE);
        Doctor editedALICE = new DoctorBuilder(ALICE).withAddress(VALID_ADDRESS_DEREK).withTags(VALID_TAG_CARDIOLOGIST)
                .build();
        assertTrue(UniqueDoctorList.contains(editedALICE));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        UniqueDoctorList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> UniqueDoctorList.add(ALICE));
    }

    @Test
    public void setObject_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.setObject(null, ALICE));
    }

    @Test
    public void setObject_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.setObject(ALICE, null));
    }

    @Test
    public void setObject_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> UniqueDoctorList.setObject(ALICE, ALICE));
    }

    @Test
    public void setObject_editedPersonIsSamePerson_success() {
        UniqueDoctorList.add(ALICE);
        UniqueDoctorList.setObject(ALICE, ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(ALICE);
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasSameIdentity_success() {
        UniqueDoctorList.add(ALICE);
        Doctor editedALICE = new DoctorBuilder(ALICE).withAddress(VALID_ADDRESS_DEREK).withTags(VALID_TAG_MEDIUM)
                .build();
        UniqueDoctorList.setObject(ALICE, editedALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(editedALICE);
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasDifferentIdentity_success() {
        UniqueDoctorList.add(ALICE);
        UniqueDoctorList.setObject(ALICE, BOYD);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setObject_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        UniqueDoctorList.add(ALICE);
        UniqueDoctorList.add(BOYD);
        assertThrows(DuplicatePersonException.class, () -> UniqueDoctorList.setObject(ALICE, BOYD));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> UniqueDoctorList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        UniqueDoctorList.add(ALICE);
        UniqueDoctorList.remove(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setPersons_nullUniqueDoctorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.setDoctors((UniqueDoctorList) null));
    }

    @Test
    public void setPersons_UniqueDoctorList_replacesOwnListWithProvidedUniqueDoctorList() {
        UniqueDoctorList.add(ALICE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        UniqueDoctorList.setDoctors(expectedUniqueDoctorList);
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueDoctorList.setObjects((List<Doctor>) null));
    }

    @Test
    public void setObjects_list_replacesOwnListWithProvidedList() {
        UniqueDoctorList.add(ALICE);
        List<Doctor> DoctorList = Collections.singletonList(BOYD);
        UniqueDoctorList.setObjects(DoctorList);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(BOYD);
        assertEquals(expectedUniqueDoctorList, UniqueDoctorList);
    }

    @Test
    public void setObjects_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Doctor> listWithDuplicateDoctors = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> UniqueDoctorList.setObjects(listWithDuplicateDoctors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> UniqueDoctorList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(UniqueDoctorList.asUnmodifiableObservableList().toString(), UniqueDoctorList.toString());
    }
}

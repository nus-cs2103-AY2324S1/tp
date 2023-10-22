package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALLEN;
import static seedu.address.testutil.TypicalDoctor.WAYNE;

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
    public void contains_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueDoctorList.contains(WAYNE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueDoctorList.add(WAYNE);
        assertTrue(uniqueDoctorList.contains(WAYNE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDoctorList.add(WAYNE);
        Doctor editedWayne = new DoctorBuilder(WAYNE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueDoctorList.contains(editedWayne));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueDoctorList.add(WAYNE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.add(WAYNE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctor(null, WAYNE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctor(WAYNE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.setDoctor(WAYNE, WAYNE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueDoctorList.add(WAYNE);
        uniqueDoctorList.setDoctor(WAYNE, WAYNE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(WAYNE);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueDoctorList.add(WAYNE);
        Doctor editedWayne = new DoctorBuilder(WAYNE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueDoctorList.setDoctor(WAYNE, editedWayne);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(editedWayne);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueDoctorList.add(WAYNE);
        uniqueDoctorList.setDoctor(WAYNE, ALLEN);
        UniqueDoctorList expectedUniqueDpctorList = new UniqueDoctorList();
        expectedUniqueDpctorList.add(ALLEN);
        assertEquals(expectedUniqueDpctorList, uniqueDoctorList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDoctorList.add(WAYNE);
        uniqueDoctorList.add(ALLEN);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setDoctor(WAYNE, ALLEN));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDoctorList.remove(WAYNE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDoctorList.add(WAYNE);
        uniqueDoctorList.remove(WAYNE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctors((UniqueDoctorList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueDoctorList.add(WAYNE);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(ALLEN);
        uniqueDoctorList.setDoctors(expectedUniqueDoctorList);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDoctorList.setDoctors((List<Doctor>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueDoctorList.add(WAYNE);
        List<Doctor> doctorList = Collections.singletonList(ALLEN);
        uniqueDoctorList.setDoctors(doctorList);
        UniqueDoctorList expectedUniqueDoctorList = new UniqueDoctorList();
        expectedUniqueDoctorList.add(ALLEN);
        assertEquals(expectedUniqueDoctorList, uniqueDoctorList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Doctor> listWithDuplicateDoctors = Arrays.asList(WAYNE, WAYNE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDoctorList.setDoctors(listWithDuplicateDoctors));
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

package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalDates.ALICE;
import static seedu.lovebook.testutil.TypicalDates.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.date.exceptions.DuplicatePersonException;
import seedu.lovebook.model.date.exceptions.PersonNotFoundException;
import seedu.lovebook.testutil.PersonBuilder;

public class UniqueDateListTest {

    private final UniqueDateList uniqueDateList = new UniqueDateList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueDateList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueDateList.add(ALICE);
        assertTrue(uniqueDateList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDateList.add(ALICE);
        Date editedAlice = new PersonBuilder(ALICE).withHeight(VALID_HEIGHT_BOB)
                .build();
        assertTrue(uniqueDateList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueDateList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDateList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDate(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setDate(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDateList.setDate(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueDateList.add(ALICE);
        uniqueDateList.setDate(ALICE, ALICE);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(ALICE);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueDateList.add(ALICE);
        Date editedAlice = new PersonBuilder(ALICE).withHeight(VALID_HEIGHT_BOB).build();
        uniqueDateList.setDate(ALICE, editedAlice);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(editedAlice);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueDateList.add(ALICE);
        uniqueDateList.setDate(ALICE, BOB);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(BOB);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDateList.add(ALICE);
        uniqueDateList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueDateList.setDate(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDateList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDateList.add(ALICE);
        uniqueDateList.remove(ALICE);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setPersons((UniqueDateList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueDateList.add(ALICE);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(BOB);
        uniqueDateList.setPersons(expectedUniqueDateList);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDateList.setPersons((List<Date>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueDateList.add(ALICE);
        List<Date> dateList = Collections.singletonList(BOB);
        uniqueDateList.setPersons(dateList);
        UniqueDateList expectedUniqueDateList = new UniqueDateList();
        expectedUniqueDateList.add(BOB);
        assertEquals(expectedUniqueDateList, uniqueDateList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Date> listWithDuplicateDates = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDateList.setPersons(listWithDuplicateDates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDateList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueDateList.asUnmodifiableObservableList().toString(), uniqueDateList.toString());
    }
}

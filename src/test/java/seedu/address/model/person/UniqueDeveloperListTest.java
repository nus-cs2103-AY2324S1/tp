package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_1_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDevelopers.ALICE;
import static seedu.address.testutil.TypicalDevelopers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.developer.Developer;
import seedu.address.model.developer.UniqueDeveloperList;
import seedu.address.model.person.exceptions.DeveloperNotFoundException;
import seedu.address.model.person.exceptions.DuplicateDeveloperException;
import seedu.address.testutil.DeveloperBuilder;

public class UniqueDeveloperListTest {

    private final UniqueDeveloperList uniqueDeveloperList = new UniqueDeveloperList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.contains(null));
    }

    @Test
    public void contains_developerNotInList_returnsFalse() {
        assertFalse(uniqueDeveloperList.contains(ALICE));
    }

    @Test
    public void contains_developerInList_returnsTrue() {
        uniqueDeveloperList.add(ALICE);
        assertTrue(uniqueDeveloperList.contains(ALICE));
    }

    @Test
    public void contains_developerWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDeveloperList.add(ALICE);
        Developer editedAlice = new DeveloperBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withProjects(VALID_PROJECT_2_AMY).build();
        assertTrue(uniqueDeveloperList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueDeveloperList.add(ALICE);
        assertThrows(DuplicateDeveloperException.class, () -> uniqueDeveloperList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.setDeveloper(null, ALICE));
    }

    @Test
    public void setDeveloper_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.setDeveloper(ALICE, null));
    }


    @Test
    public void setDeveloper_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(DeveloperNotFoundException.class, () -> uniqueDeveloperList.setDeveloper(ALICE, ALICE));
    }

    @Test
    public void setDeveloper_editedPersonIsSamePerson_success() {
        uniqueDeveloperList.add(ALICE);
        uniqueDeveloperList.setDeveloper(ALICE, ALICE);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDeveloper_editedDeveloperHasSameIdentity_success() {
        uniqueDeveloperList.add(ALICE);
        Developer editedAlice = new DeveloperBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withProjects(VALID_PROJECT_1_BOB).build();
        uniqueDeveloperList.setDeveloper(ALICE, editedAlice);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDeveloper_editedDeveloperHasDifferentIdentity_success() {
        uniqueDeveloperList.add(ALICE);
        uniqueDeveloperList.setDeveloper(ALICE, BOB);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDeveloper_editedDeveloperHasNonUniqueIdentity_throwsDuplicateDeveloperException() {
        uniqueDeveloperList.add(ALICE);
        uniqueDeveloperList.add(BOB);
        assertThrows(DuplicateDeveloperException.class, () -> uniqueDeveloperList.setDeveloper(ALICE, BOB));
    }

    @Test
    public void remove_nullDeveloper_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.remove(null));
    }

    @Test
    public void remove_developerDoesNotExist_throwsDeveloperNotFoundException() {
        assertThrows(DeveloperNotFoundException.class, () -> uniqueDeveloperList.remove(ALICE));
    }

    @Test
    public void remove_existingDeveloper_removesDeveloper() {
        uniqueDeveloperList.add(ALICE);
        uniqueDeveloperList.remove(ALICE);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDevelopers_nullUniqueDeveloperList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.setDevelopers((UniqueDeveloperList) null));
    }

    @Test
    public void setDevelopers_uniqueDeveloperList_replacesOwnListWithProvidedUniqueDeveloperList() {
        uniqueDeveloperList.add(ALICE);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        expectedUniquePersonList.add(BOB);
        uniqueDeveloperList.setDevelopers(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDevelopers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeveloperList.setDevelopers((List<Developer>) null));
    }

    @Test
    public void setDevelopers_list_replacesOwnListWithProvidedList() {
        uniqueDeveloperList.add(ALICE);
        List<Developer> developerList = Collections.singletonList(BOB);
        uniqueDeveloperList.setDevelopers(developerList);
        UniqueDeveloperList expectedUniquePersonList = new UniqueDeveloperList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniqueDeveloperList);
    }

    @Test
    public void setDevelopers_listWithDuplicateDevelopers_throwsDuplicateDeveloperException() {
        List<Developer> listWithDuplicateDevelopers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateDeveloperException.class, ()
                -> uniqueDeveloperList.setDevelopers(listWithDuplicateDevelopers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDeveloperList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueDeveloperList.asUnmodifiableObservableList().toString(), uniqueDeveloperList.toString());
    }
}

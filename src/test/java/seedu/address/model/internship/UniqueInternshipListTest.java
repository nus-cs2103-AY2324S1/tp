package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.CITADEL;
import static seedu.address.testutil.TypicalInternships.JANESTREET;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.exceptions.DuplicateInternshipException;
import seedu.address.model.internship.exceptions.InternshipNotFoundException;
import seedu.address.testutil.InternshipBuilder;

public class UniqueInternshipListTest {

    private final UniqueInternshipList uniqueInternshipList = new UniqueInternshipList();

    @Test
    public void equals() {
        // self -> returns true
        assertTrue(uniqueInternshipList.equals(uniqueInternshipList));

        // null -> returns false
        assertFalse(uniqueInternshipList.equals(null));
    }

    @Test
    public void contains_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.contains(null));
    }

    @Test
    public void contains_internshipNotInList_returnsFalse() {
        assertFalse(uniqueInternshipList.contains(JANESTREET));
    }

    @Test
    public void contains_internshipInList_returnsTrue() {
        uniqueInternshipList.create(JANESTREET);
        assertTrue(uniqueInternshipList.contains(JANESTREET));
    }

    @Test
    public void contains_internshipWithSameIdentityFieldsInList_returnsTrue() {
        uniqueInternshipList.create(JANESTREET);
        Internship editedJane = new InternshipBuilder(JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET)
                .build();
        assertTrue(uniqueInternshipList.contains(editedJane));
    }

    @Test
    public void add_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.create(null));
    }

    @Test
    public void add_duplicateInternship_throwsDuplicateInternshipException() {
        uniqueInternshipList.create(JANESTREET);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.create(JANESTREET));
    }

    @Test
    public void setInternship_nullTargetInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(null, JANESTREET));
    }

    @Test
    public void setInternship_nullEditedInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternship(JANESTREET, null));
    }

    @Test
    public void setInternship_targetInternshipNotInList_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () ->
                uniqueInternshipList.setInternship(JANESTREET, JANESTREET));
    }

    @Test
    public void setInternship_editedInternshipIsSameInternship_success() {
        uniqueInternshipList.create(JANESTREET);
        uniqueInternshipList.setInternship(JANESTREET, JANESTREET);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(JANESTREET);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasSameIdentity_success() {
        uniqueInternshipList.create(JANESTREET);
        Internship editedJane = new InternshipBuilder(JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET)
                .build();
        uniqueInternshipList.setInternship(JANESTREET, editedJane);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(editedJane);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasDifferentIdentity_success() {
        uniqueInternshipList.create(JANESTREET);
        uniqueInternshipList.setInternship(JANESTREET, CITADEL);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(CITADEL);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternship_editedInternshipHasNonUniqueIdentity_throwsDuplicateInternshipException() {
        uniqueInternshipList.create(JANESTREET);
        uniqueInternshipList.create(CITADEL);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList.setInternship(JANESTREET, CITADEL));
    }

    @Test
    public void remove_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.remove(null));
    }

    @Test
    public void remove_internshipDoesNotExist_throwsInternshipNotFoundException() {
        assertThrows(InternshipNotFoundException.class, () -> uniqueInternshipList.remove(JANESTREET));
    }

    @Test
    public void remove_existingInternship_removesInternship() {
        uniqueInternshipList.create(JANESTREET);
        uniqueInternshipList.remove(JANESTREET);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullUniqueInternshipList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueInternshipList.setInternships((UniqueInternshipList) null));
    }

    @Test
    public void setInternships_uniqueInternshipList_replacesOwnListWithProvidedUniqueInternshipList() {
        uniqueInternshipList.create(JANESTREET);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(CITADEL);
        uniqueInternshipList.setInternships(expectedUniqueInternshipList);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueInternshipList.setInternships((List<Internship>) null));
    }

    @Test
    public void setInternships_list_replacesOwnListWithProvidedList() {
        uniqueInternshipList.create(JANESTREET);
        List<Internship> internshipList = Collections.singletonList(CITADEL);
        uniqueInternshipList.setInternships(internshipList);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(CITADEL);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void setInternships_listWithDuplicateInternships_throwsDuplicateInternshipException() {
        List<Internship> listWithDuplicateInternships = Arrays.asList(JANESTREET, JANESTREET);
        assertThrows(DuplicateInternshipException.class, () -> uniqueInternshipList
                .setInternships(listWithDuplicateInternships));
    }
    @Test
    public void sortInternships_byCompanyName_sortsCorrectly() {
        uniqueInternshipList.create(JANESTREET);
        uniqueInternshipList.create(CITADEL);
        uniqueInternshipList.sortInternships(InternshipComparators.BY_COMPANY_NAME);
        UniqueInternshipList expectedUniqueInternshipList = new UniqueInternshipList();
        expectedUniqueInternshipList.create(CITADEL);
        expectedUniqueInternshipList.create(JANESTREET);
        assertEquals(expectedUniqueInternshipList, uniqueInternshipList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueInternshipList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueInternshipList.asUnmodifiableObservableList().toString(), uniqueInternshipList.toString());
    }
}

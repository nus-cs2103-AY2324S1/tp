package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_REJECTED;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.B;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternApplications;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.application.exceptions.DuplicateInternApplicationException;
import seedu.letsgethired.model.application.exceptions.InternApplicationNotFoundException;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class UniqueInternApplicationListTest {

    private final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

    @Test
    public void contains_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.contains(null));
    }

    @Test
    public void contains_internApplicationNotInList_returnsFalse() {
        assertFalse(uniqueApplicationList.contains(JANE_STREET));
    }

    @Test
    public void contains_internApplicationInList_returnsTrue() {
        uniqueApplicationList.add(JANE_STREET);
        assertTrue(uniqueApplicationList.contains(JANE_STREET));
    }

    @Test
    public void contains_internApplicationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicationList.add(JANE_STREET);
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET)
                .withStatus(VALID_STATUS_REJECTED)
                .build();
        assertTrue(uniqueApplicationList.contains(editedAlice));
    }

    @Test
    public void add_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.add(null));
    }

    @Test
    public void add_duplicateInternApplication_throwsDuplicateInternApplicationException() {
        uniqueApplicationList.add(JANE_STREET);
        assertThrows(DuplicateInternApplicationException.class, () -> uniqueApplicationList.add(JANE_STREET));
    }

    @Test
    public void setInternApplication_nullTargetInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(null, JANE_STREET));
    }

    @Test
    public void setInternApplication_nullEditedInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(JANE_STREET,
                null));
    }

    @Test
    public void setInternApplication_targetInternApplicationNotInList_throwsInternApplicationNotFoundException() {
        assertThrows(InternApplicationNotFoundException.class, () -> uniqueApplicationList
                .setApplication(JANE_STREET, JANE_STREET));
    }

    @Test
    public void setInternApplication_editedInternApplicationIsSameInternApplication_success() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.setApplication(JANE_STREET, JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(JANE_STREET);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplication_editedInternApplicationHasSameIdentity_success() {
        uniqueApplicationList.add(JANE_STREET);
        InternApplication editedAlice = new InternApplicationBuilder(JANE_STREET)
                .withStatus(VALID_STATUS_REJECTED)
                .build();
        uniqueApplicationList.setApplication(JANE_STREET, editedAlice);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(editedAlice);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplication_editedInternApplicationHasDifferentIdentity_success() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.setApplication(JANE_STREET, B);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(B);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplication_editedApplicationHasNonUniqueIdentity_throwsDuplicateInternApplicationException() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.add(B);
        assertThrows(
                DuplicateInternApplicationException.class, () -> uniqueApplicationList.setApplication(JANE_STREET, B));
    }

    @Test
    public void remove_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.remove(null));
    }

    @Test
    public void remove_internApplicationDoesNotExist_throwsInternApplicationNotFoundException() {
        assertThrows(InternApplicationNotFoundException.class, () -> uniqueApplicationList.remove(JANE_STREET));
    }

    @Test
    public void remove_existingInternApplication_removesInternApplication() {
        uniqueApplicationList.add(JANE_STREET);
        uniqueApplicationList.remove(JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplications_nullUniqueInternApplicationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((UniqueApplicationList) null));
    }

    @Test
    public void setInternApplications_uniqueApplicationList_replacesOwnListWithProvidedUniqueInternApplicationList() {
        uniqueApplicationList.add(JANE_STREET);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(B);
        uniqueApplicationList.setApplications(expectedUniqueApplicationList);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplications_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((List<InternApplication>) null));
    }

    @Test
    public void setInternApplications_list_replacesOwnListWithProvidedList() {
        uniqueApplicationList.add(JANE_STREET);
        List<InternApplication> internApplicationList = Collections.singletonList(B);
        uniqueApplicationList.setApplications(internApplicationList);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(B);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setInternApplications_listWithDuplicateInternApplications_throwsDuplicateInternApplicationException() {
        List<InternApplication> listWithDuplicateInternApplications = Arrays.asList(JANE_STREET, JANE_STREET);
        assertThrows(DuplicateInternApplicationException.class, () -> uniqueApplicationList
                .setApplications(listWithDuplicateInternApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueApplicationList.asUnmodifiableObservableList().toString(),
                uniqueApplicationList.toString());
    }
    @Test
    public void clone_emptyList_success() {
        UniqueApplicationList originalList = new UniqueApplicationList();
        UniqueApplicationList clonedList = originalList.clone();

        // Check that the cloned list is not the same instance as the original
        assertNotSame(originalList, clonedList);
    }

    @Test
    public void clone_nonEmptyList_success() {
        UniqueApplicationList originalList = new UniqueApplicationList();
        originalList.add(JANE_STREET);
        originalList.add(OPTIVER);

        // Clone the list
        UniqueApplicationList clonedList = originalList.clone();

        // Check that the cloned list is not the same instance as the original
        assertNotSame(originalList, clonedList);

        // Use iterators to go through each list
        Iterator<InternApplication> originalIterator = originalList.iterator();
        Iterator<InternApplication> clonedIterator = clonedList.iterator();

        // Check that the applications in the cloned list have the same attributes as the original
        while (originalIterator.hasNext() && clonedIterator.hasNext()) {
            InternApplication originalApp = originalIterator.next();
            InternApplication clonedApp = clonedIterator.next();

            // Check each attribute of the application
            assertEquals(originalApp.getCompany(), clonedApp.getCompany());
            assertEquals(originalApp.getRole(), clonedApp.getRole());
            assertEquals(originalApp.getCycle(), clonedApp.getCycle());
            assertEquals(originalApp.getNotes(), clonedApp.getNotes());
            assertEquals(originalApp.getStatus(), clonedApp.getStatus());
        }

        // Ensure that both iterators have reached the end of the lists
        assertFalse(originalIterator.hasNext());
        assertFalse(clonedIterator.hasNext());
    }

    @Test
    public void equals() {
        UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

        uniqueApplicationList.setApplications(getTypicalInternApplications());

        // same object
        assertEquals(uniqueApplicationList, uniqueApplicationList);

        // not an application list
        assertNotEquals(uniqueApplicationList, new Object());

        UniqueApplicationList otherUniqueApplicationList = new UniqueApplicationList();

        otherUniqueApplicationList.setApplications(getTypicalInternApplications());

        // other object with same internship applications
        assertEquals(uniqueApplicationList, otherUniqueApplicationList);
    }
}

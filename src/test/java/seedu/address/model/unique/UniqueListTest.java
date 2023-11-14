package seedu.address.model.unique;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.unique.exceptions.DuplicateElementException;
import seedu.address.model.unique.exceptions.ElementNotFoundException;

public class UniqueListTest {

    private static class UniqueMock implements Unique<UniqueMock> {
        private final String identityField;
        private final String nonIdentityField;

        public UniqueMock(String identityField, String nonIdentityField) {
            this.identityField = identityField;
            this.nonIdentityField = nonIdentityField;
        }

        @Override
        public boolean isDuplicate(UniqueMock u) {
            return identityField.equals(u.identityField);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UniqueMock)) {
                return false;
            }

            UniqueMock otherMock = (UniqueMock) other;
            return identityField.equals(otherMock.identityField)
                    && nonIdentityField.equals(otherMock.identityField);
        }
    }

    private final UniqueList<UniqueMock> uniqueUniqueMockList = new UniqueList<>();

    private final UniqueMock u1 = new UniqueMock("MIH1101 OP", "123");

    private final UniqueMock u2 = new UniqueMock("MIH1101 OP", "321");

    private final UniqueMock u3 = new UniqueMock("CS2103T PE", "123");

    @Test
    public void contains_nullUniqueMock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(uniqueUniqueMockList.contains(u1));
    }

    @Test
    public void contains_assignmentInList_returnsTrue() {
        uniqueUniqueMockList.add(u1);
        assertTrue(uniqueUniqueMockList.contains(u1));
    }

    @Test
    public void contains_assignmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueUniqueMockList.add(u1);
        assertTrue(uniqueUniqueMockList.contains(u2));
    }

    @Test
    public void add_nullUniqueMock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.add(null));
    }

    @Test
    public void add_duplicateUniqueMock_throwsDuplicateUniqueMockException() {
        uniqueUniqueMockList.add(u1);
        assertThrows(DuplicateElementException.class, () -> uniqueUniqueMockList.add(u1));
    }

    @Test
    public void set_nullTargetUniqueMock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.set(null, u1));
    }

    @Test
    public void set_nullEditedUniqueMock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.set(u1, null));
    }

    @Test
    public void set_targetUniqueMockNotInList_throwsUniqueMockNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueUniqueMockList.set(u1, u1));
    }

    @Test
    public void set_editedUniqueMockIsSameUniqueMock_success() {
        uniqueUniqueMockList.add(u1);
        uniqueUniqueMockList.set(u1, u1);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<>();
        expectedUniqueUniqueMockList.add(u1);
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void set_editedUniqueMockHasSameIdentity_success() {
        uniqueUniqueMockList.add(u1);

        uniqueUniqueMockList.set(u1, u2);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<>();
        expectedUniqueUniqueMockList.add(u2);
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void set_editedUniqueMockHasDifferentIdentity_success() {
        uniqueUniqueMockList.add(u1);
        uniqueUniqueMockList.set(u1, u3);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<>();
        expectedUniqueUniqueMockList.add(u3);
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void set_editedUniqueMockHasNonUniqueIdentity_throwsDuplicateUniqueMockException() {
        uniqueUniqueMockList.add(u1);
        uniqueUniqueMockList.add(u3);
        assertThrows(DuplicateElementException.class, () -> uniqueUniqueMockList.set(u1, u3));
    }

    @Test
    public void remove_nullUniqueMock_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.remove(null));
    }

    @Test
    public void remove_assignmentDoesNotExist_throwsUniqueMockNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniqueUniqueMockList.remove(u1));
    }

    @Test
    public void remove_existingUniqueMock_removesUniqueMock() {
        uniqueUniqueMockList.add(u1);
        uniqueUniqueMockList.remove(u1);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<>();
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void sets_nullUniqueUniqueMockList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.setList((UniqueList<UniqueMock>) null));
    }

    @Test
    public void sets_uniqueUniqueMockList_replacesOwnListWithProvidedUniqueUniqueMockList() {
        uniqueUniqueMockList.add(u1);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<>();
        expectedUniqueUniqueMockList.add(u3);
        uniqueUniqueMockList.setList(expectedUniqueUniqueMockList);
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void sets_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniqueMockList.setList((List<UniqueMock>) null));
    }

    @Test
    public void sets_list_replacesOwnListWithProvidedList() {
        uniqueUniqueMockList.add(u1);
        List<UniqueMock> assignmentList = Collections.singletonList(u3);
        uniqueUniqueMockList.setList(assignmentList);
        UniqueList<UniqueMock> expectedUniqueUniqueMockList = new UniqueList<UniqueMock>();
        expectedUniqueUniqueMockList.add(u3);
        assertEquals(expectedUniqueUniqueMockList, uniqueUniqueMockList);
    }

    @Test
    public void sets_listWithDuplicateUniqueMocks_throwsDuplicateUniqueMockException() {
        List<UniqueMock> listWithDuplicateUniqueMocks = Arrays.asList(u1, u1);
        assertThrows(DuplicateElementException.class, () -> uniqueUniqueMockList.setList(listWithDuplicateUniqueMocks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueUniqueMockList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueUniqueMockList.asUnmodifiableObservableList().toString(), uniqueUniqueMockList.toString());
    }
}

package seedu.address.model.university;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.NTU;
import static seedu.address.testutil.TypicalObjects.STANFORD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.university.exceptions.DuplicateUniversityException;
import seedu.address.model.university.exceptions.UniversityNotFoundException;
import seedu.address.testutil.UniversityBuilder;

public class UniqueUniversityListTest {

    private final UniqueUniversityList uniqueUniversityList = new UniqueUniversityList();

    @Test
    public void contains_nullUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniversityList.contains(null));
    }

    @Test
    public void contains_universityNotInList_returnsFalse() {
        assertFalse(uniqueUniversityList.contains(NTU));
    }

    @Test
    public void contains_universityInList_returnTrue() {
        uniqueUniversityList.add(NTU);
        assertTrue(uniqueUniversityList.contains(NTU));
    }

    @Test
    public void contains_universityWithSameIdentityFieldsInList_returnsTrue() {
        uniqueUniversityList.add(STANFORD);
        University universityWithSameIdentityFields =
                new UniversityBuilder(NTU).withUniversityName(STANFORD.getUniversityName().getValue()).build();
        assertTrue(uniqueUniversityList.contains(universityWithSameIdentityFields));
    }

    @Test
    public void add_nullUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniversityList.add(null));
    }

    @Test
    public void add_duplicateUniversity_throwsDuplicateUniversityException() {
        uniqueUniversityList.add(NTU);
        assertThrows(DuplicateUniversityException.class, () -> uniqueUniversityList.add(NTU));
    }

    @Test
    public void setUniversity_nullTargetUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniversityList.setUniversity(null, NTU));
    }

    @Test
    public void setUniversity_nullEditedUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueUniversityList.setUniversity(NTU, null));
    }

    @Test
    public void setUniversity_targetUniversityNotInList_throwsUniversityNotFoundException() {
        assertThrows(UniversityNotFoundException.class, () ->
                uniqueUniversityList.setUniversity(NTU, NTU));
    }

    @Test
    public void setUniversity_editedUniversityIsSameUniversity_success() {
        uniqueUniversityList.add(NTU);
        uniqueUniversityList.setUniversity(NTU, NTU);
        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        expectedUniqueUniversityList.add(NTU);
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    @Test
    public void setUniversity_editedUniversityHasSameIdentity_success() {
        uniqueUniversityList.add(NTU);
        University editedNtu =
                new UniversityBuilder(NTU).withUniversityName("National Tech Uni").build();

        uniqueUniversityList.setUniversity(NTU, editedNtu);

        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        expectedUniqueUniversityList.add(editedNtu);
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    @Test
    public void setUniversity_editedUniversityHasDifferentIdentity_success() {
        uniqueUniversityList.add(NTU);
        uniqueUniversityList.setUniversity(NTU, STANFORD);
        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        expectedUniqueUniversityList.add(STANFORD);
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    @Test
    public void setUniversity_editedUniversityHasNonUniqueIdentity_throwsDuplicateUniversityException() {
        uniqueUniversityList.add(NTU);
        uniqueUniversityList.add(STANFORD);
        assertThrows(DuplicateUniversityException.class, () ->
                uniqueUniversityList.setUniversity(NTU, STANFORD));
    }

    // Tests remove

    @Test
    public void remove_nullUniversity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueUniversityList.remove(null));
    }

    @Test
    public void remove_universityDoesNotExist_throwsUniversityNotFoundException() {
        assertThrows(UniversityNotFoundException.class, () -> uniqueUniversityList.remove(NTU));
    }

    @Test
    public void remove_existingUniversity_removesUniversity() {
        uniqueUniversityList.add(NTU);
        uniqueUniversityList.remove(NTU);
        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    // Tests setLocalCourses

    @Test
    public void setUniversities_nullUniqueUniversityList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueUniversityList.setUniversities((UniqueUniversityList) null));
    }

    @Test
    public void setUniversities_uniqueUniversityList_replacesOwnListWithProvidedUniqueUniversityList() {
        uniqueUniversityList.add(NTU);
        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        expectedUniqueUniversityList.add(NTU);
        uniqueUniversityList.setUniversities(expectedUniqueUniversityList);
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    @Test
    public void setUniversities_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueUniversityList.setUniversities((List<University>) null));
    }

    @Test
    public void setUniversity_list_replacesOwnListWithProvidedList() {
        uniqueUniversityList.add(NTU);
        List<University> universityList = Collections.singletonList(NTU);
        uniqueUniversityList.setUniversities(universityList);
        UniqueUniversityList expectedUniqueUniversityList = new UniqueUniversityList();
        expectedUniqueUniversityList.add(NTU);
        assertEquals(expectedUniqueUniversityList, uniqueUniversityList);
    }

    @Test
    public void setUniversities_listWithDuplicateUniversity_throwsDuplicateUniversityException() {
        List<University> listWithDuplicateUniversities = Arrays.asList(NTU, NTU);
        assertThrows(DuplicateUniversityException.class, () -> uniqueUniversityList.setUniversities(
                listWithDuplicateUniversities));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueUniversityList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueUniversityList.asUnmodifiableObservableList().toString(),
                uniqueUniversityList.toString());
    }
}

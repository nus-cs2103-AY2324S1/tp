package seedu.address.model.localcourse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS1231S;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS3230;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.localcourse.exceptions.DuplicateLocalCourseException;
import seedu.address.model.localcourse.exceptions.LocalCourseNotFoundException;
import seedu.address.testutil.LocalCourseBuilder;

public class UniqueLocalCourseListTest {

    private final UniqueLocalCourseList uniqueLocalCourseList = new UniqueLocalCourseList();

    // Tests contains, add

    @Test
    public void contains_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLocalCourseList.contains(null));
    }

    @Test
    public void contains_localCourseNotInList_returnsFalse() {
        assertFalse(uniqueLocalCourseList.contains(CS2040S));
    }

    @Test
    public void contains_localCourseInList_returnsTrue() {
        uniqueLocalCourseList.add(CS2040S);
        assertTrue(uniqueLocalCourseList.contains(CS2040S));
    }

    @Test
    public void contains_localCourseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLocalCourseList.add(CS3230);
        LocalCourse localCourseWithSameIdentityFields =
                new LocalCourseBuilder(CS2040S).withLocalCode(CS3230.getLocalCode().getValue()).build();
        assertTrue(uniqueLocalCourseList.contains(localCourseWithSameIdentityFields));
    }

    @Test
    public void add_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLocalCourseList.add(null));
    }

    @Test
    public void add_duplicateLocalCourse_throwsDuplicateLocalCourseException() {
        uniqueLocalCourseList.add(CS3230);
        assertThrows(DuplicateLocalCourseException.class, () -> uniqueLocalCourseList.add(CS3230));
    }

    @Test
    public void setLocalCourse_nullTargetLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLocalCourseList.setLocalCourse(null, CS2030S));
    }

    // Tests setLocalCourse, setLocalCourses

    @Test
    public void setLocalCourse_nullEditedLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueLocalCourseList.setLocalCourse(CS2030S, null));
    }

    @Test
    public void setLocalCourse_targetLocalCourseNotInList_throwsLocalCourseNotFoundException() {
        assertThrows(LocalCourseNotFoundException.class, () ->
                uniqueLocalCourseList.setLocalCourse(CS1231S, CS1231S));
    }

    @Test
    public void setLocalCourse_editedLocalCourseIsSameLocalCourse_success() {
        uniqueLocalCourseList.add(CS1231S);
        uniqueLocalCourseList.setLocalCourse(CS1231S, CS1231S);
        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        expectedUniqueLocalCourseList.add(CS1231S);
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    @Test
    public void setLocalCourse_editedLocalCourseHasSameIdentity_success() {
        uniqueLocalCourseList.add(CS1231S);
        LocalCourse editedCS1231S =
                new LocalCourseBuilder(CS1231S).withLocalName("Introductory Mathematics").build();

        uniqueLocalCourseList.setLocalCourse(CS1231S, editedCS1231S);

        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        expectedUniqueLocalCourseList.add(editedCS1231S);
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    @Test
    public void setLocalCourse_editedLocalCourseHasDifferentIdentity_success() {
        uniqueLocalCourseList.add(CS1231S);
        uniqueLocalCourseList.setLocalCourse(CS1231S, CS2030S);
        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        expectedUniqueLocalCourseList.add(CS2030S);
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    @Test
    public void setLocalCourse_editedLocalCourseHasNonUniqueIdentity_throwsDuplicateLocalCourseException() {
        uniqueLocalCourseList.add(CS2030S);
        uniqueLocalCourseList.add(CS2040S);
        assertThrows(DuplicateLocalCourseException.class, () ->
                uniqueLocalCourseList.setLocalCourse(CS2030S, CS2040S));
    }

    // Tests remove

    @Test
    public void remove_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLocalCourseList.remove(null));
    }

    @Test
    public void remove_localCourseDoesNotExist_throwsLocalCourseNotFoundException() {
        assertThrows(LocalCourseNotFoundException.class, () -> uniqueLocalCourseList.remove(CS1231S));
    }

    @Test
    public void remove_existingLocalCourse_removesLocalCourse() {
        uniqueLocalCourseList.add(CS3230);
        uniqueLocalCourseList.remove(CS3230);
        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    // Tests setLocalCourses

    @Test
    public void setLocalCourses_nullUniqueLocalCourseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueLocalCourseList.setLocalCourses((UniqueLocalCourseList) null));
    }

    @Test
    public void setLocalCourses_uniqueLocalCourseList_replacesOwnListWithProvidedUniqueLocalCourseList() {
        uniqueLocalCourseList.add(CS1231S);
        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        expectedUniqueLocalCourseList.add(CS2040S);
        uniqueLocalCourseList.setLocalCourses(expectedUniqueLocalCourseList);
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    @Test
    public void setLocalCourses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueLocalCourseList.setLocalCourses((List<LocalCourse>) null));
    }

    @Test
    public void setLocalCourses_list_replacesOwnListWithProvidedList() {
        uniqueLocalCourseList.add(CS2030S);
        List<LocalCourse> localCourseList = Collections.singletonList(CS2040S);
        uniqueLocalCourseList.setLocalCourses(localCourseList);
        UniqueLocalCourseList expectedUniqueLocalCourseList = new UniqueLocalCourseList();
        expectedUniqueLocalCourseList.add(CS2040S);
        assertEquals(expectedUniqueLocalCourseList, uniqueLocalCourseList);
    }

    @Test
    public void setLocalCourses_listWithDuplicateLocalCourses_throwsDuplicateLocalCourseException() {
        List<LocalCourse> listWithDuplicateLocalCourses = Arrays.asList(CS2030S, CS2030S);
        assertThrows(DuplicateLocalCourseException.class, () -> uniqueLocalCourseList.setLocalCourses(
                listWithDuplicateLocalCourses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueLocalCourseList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueLocalCourseList.asUnmodifiableObservableList().toString(), uniqueLocalCourseList.toString());
    }
}

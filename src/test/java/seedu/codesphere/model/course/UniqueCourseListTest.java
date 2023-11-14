package seedu.codesphere.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.CS1101S;
import static seedu.codesphere.testutil.TypicalCourses.CS2100;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.codesphere.model.course.exceptions.CourseNotFoundException;
import seedu.codesphere.model.course.exceptions.DuplicateCourseException;
import seedu.codesphere.testutil.CourseBuilder;

public class UniqueCourseListTest {

    private final UniqueCourseList uniqueCourseList = new UniqueCourseList();

    @Test
    public void contains_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.contains(null));
    }

    @Test
    public void contains_courseNotInList_returnsFalse() {
        assertFalse(uniqueCourseList.contains(CS1101S));
    }

    @Test
    public void contains_courseInList_returnsTrue() {
        uniqueCourseList.add(CS1101S);
        assertTrue(uniqueCourseList.contains(CS1101S));
    }

    @Test
    public void contains_courseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCourseList.add(CS1101S);
        Course editedCourse = new CourseBuilder(CS1101S).build();
        assertTrue(uniqueCourseList.contains(CS1101S));
    }

    @Test
    public void add_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.add(null));
    }

    @Test
    public void add_duplicateCourse_throwsDuplicateStudentException() {
        uniqueCourseList.add(CS1101S);
        assertThrows(DuplicateCourseException.class, () -> uniqueCourseList.add(CS1101S));
    }

    @Test
    public void setCourse_nullTargetCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.setCourse(null, CS1101S));
    }

    @Test
    public void setCourse_nullEditedCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.setCourse(CS1101S, null));
    }

    @Test
    public void setCourse_targetCourseNotInList_throwsStudentNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> uniqueCourseList.setCourse(CS1101S, CS1101S));
    }

    @Test
    public void setCourse_editedCourseIsSameCourse_success() {
        uniqueCourseList.add(CS1101S);
        uniqueCourseList.setCourse(CS1101S, CS1101S);
        UniqueCourseList expectedUniqueCourseList = new UniqueCourseList();
        expectedUniqueCourseList.add(CS1101S);
        assertEquals(expectedUniqueCourseList, uniqueCourseList);
    }

    @Test
    public void setCourse_editedCourseHasSameIdentity_success() {
        uniqueCourseList.add(CS1101S);
        Course editedCourse = new CourseBuilder(CS1101S).build();
        uniqueCourseList.setCourse(CS1101S, editedCourse);
        UniqueCourseList expectedUniqueStudentList = new UniqueCourseList();
        expectedUniqueStudentList.add(editedCourse);
        assertEquals(expectedUniqueStudentList, uniqueCourseList);
    }

    @Test
    public void setCourse_editedCourseHasDifferentIdentity_success() {
        uniqueCourseList.add(CS1101S);
        uniqueCourseList.setCourse(CS1101S, CS2100);
        UniqueCourseList expectedUniqueCourseList = new UniqueCourseList();
        expectedUniqueCourseList.add(CS2100);
        assertEquals(expectedUniqueCourseList, uniqueCourseList);
    }

    @Test
    public void setCourse_editedCourseHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueCourseList.add(CS1101S);
        uniqueCourseList.add(CS2100);
        assertThrows(DuplicateCourseException.class, () -> uniqueCourseList.setCourse(CS1101S, CS2100));
    }

    @Test
    public void remove_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.remove(null));
    }

    @Test
    public void remove_courseDoesNotExist_throwsCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> uniqueCourseList.remove(CS1101S));
    }

    @Test
    public void remove_existingCourse_removesCourse() {
        uniqueCourseList.add(CS1101S);
        uniqueCourseList.remove(CS1101S);
        UniqueCourseList expectedUniqueStudentList = new UniqueCourseList();
        assertEquals(expectedUniqueStudentList, uniqueCourseList);
    }

    @Test
    public void setCourses_nullUniqueCourseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.setCourses((UniqueCourseList) null));
    }

    @Test
    public void setCourses_uniqueCourseList_replacesOwnListWithProvidedUniqueCourseList() {
        uniqueCourseList.add(CS1101S);
        UniqueCourseList expectedUniqueCourseList = new UniqueCourseList();
        expectedUniqueCourseList.add(CS2100);
        uniqueCourseList.setCourses(expectedUniqueCourseList);
        assertEquals(expectedUniqueCourseList, uniqueCourseList);
    }

    @Test
    public void setCourses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseList.setCourses((List<Course>) null));
    }

    @Test
    public void setCourses_list_replacesOwnListWithProvidedList() {
        uniqueCourseList.add(CS1101S);
        List<Course> studentList = Collections.singletonList(CS2100);
        uniqueCourseList.setCourses(studentList);
        UniqueCourseList expectedUniqueStudentList = new UniqueCourseList();
        expectedUniqueStudentList.add(CS2100);
        assertEquals(expectedUniqueStudentList, uniqueCourseList);
    }

    @Test
    public void setCourses_listWithDuplicateCourses_throwsDuplicateCourseException() {
        List<Course> listWithDuplicateCourses = Arrays.asList(CS1101S, CS1101S);
        assertThrows(DuplicateCourseException.class, () -> uniqueCourseList.setCourses(listWithDuplicateCourses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueCourseList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCourseList.asUnmodifiableObservableList().toString(), uniqueCourseList.toString());
    }
}

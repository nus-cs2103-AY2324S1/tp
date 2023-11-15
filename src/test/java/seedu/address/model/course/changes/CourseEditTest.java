package seedu.address.model.course.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.course.Course;

public class CourseEditTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseEdit(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseEdit(invalidDescription));
    }

    @Test
    public void constructor_invalidCourses_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CourseEdit("CS210333-MA2001"));
    }

    @Test
    public void isValidCourseEdit() {
        //valid
        assertTrue(CourseEdit.isValidCourseEdit("MA2001-CS2103T"));

        // missing original course
        assertFalse(CourseEdit.isValidCourseEdit("-CS2103T"));

        // missing new course
        assertFalse(CourseEdit.isValidCourseEdit("CS2103T-"));
    }

    @Test
    public void checkIfValidOriginalCourse() {
        // invalid description
        assertFalse(CourseEdit.checkIfValidOriginalCourse("-CS2103T"));

        // invalid original course
        assertFalse(CourseEdit.checkIfValidOriginalCourse("CS210333T-MA2001"));

        // valid original course
        assertTrue(CourseEdit.checkIfValidOriginalCourse("CS2103T-MA2001"));
    }

    @Test
    public void checkIfValidNewCourse() {
        // invalid description
        assertFalse(CourseEdit.checkIfValidNewCourse("-CS2103T"));

        // invalid new course
        assertFalse(CourseEdit.checkIfValidNewCourse("CS2103T-MA200111"));

        // valid original course
        assertTrue(CourseEdit.checkIfValidNewCourse("CS2103T-MA2001"));
    }

    @Test
    public void getParsedOriginalCourseName() {
        // invalid description
        assertNull(CourseEdit.getParsedOriginalCourseName("-CS2103T"));

        // valid description
        assertEquals("CS2103T", CourseEdit.getParsedOriginalCourseName("CS2103T-MA2001"));
    }

    @Test
    public void getParsedNewCourseName() {
        // invalid description
        assertNull(CourseEdit.getParsedNewCourseName("-CS2103T"));

        // valid description
        assertEquals("MA2001", CourseEdit.getParsedNewCourseName("CS2103T-MA2001"));
    }

    @Test
    public void test_getOriginalAndNewCourses() {
        CourseEdit courseEdit = new CourseEdit("MA2001-CS2103T");
        assertEquals(new Course("MA2001"), courseEdit.getOriginalCourse());
        assertEquals(new Course("CS2103T"), courseEdit.getNewCourse());
    }

    @Test
    public void equals() {
        CourseEdit courseEdit = new CourseEdit("MA2001-CS2103T");

        // same values -> returns true
        assertTrue(courseEdit.equals(new CourseEdit("MA2001-CS2103T")));

        // same object -> returns true
        assertTrue(courseEdit.equals(courseEdit));

        // null -> returns false
        assertFalse(courseEdit.equals(null));

        // different types -> returns false
        assertFalse(courseEdit.equals(5.0f));

        // different values -> returns false
        assertFalse(courseEdit.equals(new CourseEdit("CS2103T-MA2001")));
    }
}

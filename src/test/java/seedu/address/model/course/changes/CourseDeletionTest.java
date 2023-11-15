package seedu.address.model.course.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.course.Course;

public class CourseDeletionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseDeletion(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseDeletion(invalidDescription));
    }

    @Test
    public void constructor_invalidCourse_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CourseDeletion("del-CS210333"));
    }

    @Test
    public void isValidCourseDeletion() {
        //valid
        assertTrue(CourseDeletion.isValidCourseDeletion("del-CS2103T"));

        // del spelt incorrectly
        assertFalse(CourseDeletion.isValidCourseDeletion("der-CS2103T"));

        // missing course
        assertFalse(CourseDeletion.isValidCourseDeletion("del-"));
    }

    @Test
    public void checkIfValidCourse() {
        // invalid description
        assertFalse(CourseDeletion.checkIfValidCourse("-CS2103T"));

        // invalid course
        assertFalse(CourseDeletion.checkIfValidCourse("del-CS210333"));

        // valid course
        assertTrue(CourseDeletion.checkIfValidCourse("del-CS2103T"));
    }

    @Test
    public void getParsedCourseName() {
        // invalid description
        assertNull(CourseDeletion.getParsedCourseName("-CS2103T"));

        // valid description
        assertEquals("CS2103T", CourseDeletion.getParsedCourseName("del-CS2103T"));
    }

    @Test
    public void test_getCourseToDelete() {
        CourseDeletion courseDeletion = new CourseDeletion("del-CS2103T");
        assertEquals(new Course("CS2103T"), courseDeletion.getCourseToDelete());
    }

    @Test
    public void equals() {
        CourseDeletion courseDeletion = new CourseDeletion("del-CS2103T");

        // same values -> returns true
        assertTrue(courseDeletion.equals(new CourseDeletion("del-CS2103T")));

        // same object -> returns true
        assertTrue(courseDeletion.equals(courseDeletion));

        // null -> returns false
        assertFalse(courseDeletion.equals(null));

        // different types -> returns false
        assertFalse(courseDeletion.equals(5.0f));

        // different values -> returns false
        assertFalse(courseDeletion.equals(new CourseDeletion("del-MA2001")));
    }
}

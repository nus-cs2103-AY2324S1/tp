package seedu.address.model.course;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class CourseTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null, null, null));
    }

    @Test
    public void constructor_invalidCourseCode_throwsIllegalArgumentException() {
        String invalidCourseCode = "CS1231SS";
        assertThrows(IllegalArgumentException.class, (
        ) -> new Course("Discrete Structures", invalidCourseCode, new HashSet<>()));
    }

    @Test
    public void constructor_validCourseCode_success() {
        String validCourseCode = "CS1231S";
        new Course("Discrete Structures", validCourseCode, new HashSet<>());
        assert true; // no exception thrown
    }

    @Test
    public void equalsToItself() {
        Course course = new Course("Discrete Structures", "CS1231S", new HashSet<>());
        assert course.equals(course);
    }

    @Test
    public void equalsToOtherCourseWithSameNameAndCourseCode() {
        Course course1 = new Course("Discrete Structures", "CS1231S", new HashSet<>());
        Course course2 = new Course("Discrete Structures", "CS1231S", new HashSet<>());
        assert course1.equals(course2);
    }

    @Test
    public void notEqualsToOtherCourseWithDifferentName() {
        Course course1 = new Course("Discrete Structures", "CS1231S", new HashSet<>());
        Course course2 = new Course("Software Engineering", "CS1231S", new HashSet<>());
        assert !course1.equals(course2);
    }

    @Test
    public void notEqualsToOtherCourseWithDifferentCourseCode() {
        Course course1 = new Course("Discrete Structures", "CS1231S", new HashSet<>());
        Course course2 = new Course("Discrete Structures", "CS2100", new HashSet<>());
        assert !course1.equals(course2);
    }
}
